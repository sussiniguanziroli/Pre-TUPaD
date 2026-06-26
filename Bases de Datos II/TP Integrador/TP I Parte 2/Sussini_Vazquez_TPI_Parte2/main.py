from pymongo import MongoClient
from bson import ObjectId
from datetime import datetime, timezone

# ── Conexión ──────────────────────────────────────────────────────────────────
# String de conexión
MONGO_URI = "mongodb+srv://db_admin:admin1234@cluster-techstore.bvsefu1.mongodb.net/"
DB_NAME   = "techstore_db"

client = MongoClient(MONGO_URI)
db = client[DB_NAME]
productos = db["productos"]

print("Conexión exitosa a MongoDB Atlas - TechStore")


# ── CREATE ────────────────────────────────────────────────────────────────────
def crear_producto(nombre, descripcion, categoria_id_str, precio, stock, especificaciones=None):
    """Inserta un nuevo producto en la colección. Nace con activo: True."""
    nuevo = {
        "nombre": nombre,
        "descripcion": descripcion,
        "categoria_id": ObjectId(categoria_id_str),
        "precio": precio,
        "stock": stock,
        "especificaciones": especificaciones or {},
        "imagenes": [],
        "fecha_alta": datetime.now(timezone.utc),
        "activo": True,
    }
    resultado = productos.insert_one(nuevo)
    print(f"[CREATE] Producto insertado con _id: {resultado.inserted_id}")
    return resultado.inserted_id


# ── READ ──────────────────────────────────────────────────────────────────────
def listar_productos():
    """
    Devuelve solo los productos con activo: True.
    """
    cursor = productos.find({"activo": True})
    lista  = list(cursor)
    print(f"[READ] {len(lista)} productos activos encontrados:")
    for p in lista:
        print(f" - {p['_id']} | {p['nombre']} | ${p['precio']} | stock: {p['stock']}")
    return lista


# ── UPDATE ────────────────────────────────────────────────────────────────────
def actualizar_producto(producto_id, cambios: dict):
    """Actualiza campos específicos de un producto con $set (no reemplaza el documento)."""
    resultado = productos.update_one(
        {"_id": ObjectId(producto_id), "activo": True},
        {"$set": cambios}
    )
    if resultado.matched_count:
        print(f"[UPDATE] Producto {producto_id} actualizado. Campos: {list(cambios.keys())}")
    else:
        print(f"[UPDATE] No se encontró el producto {producto_id}")
    return resultado.matched_count


# ── DELETE (baja lógica) ──────────────────────────────────────────────────────
def dar_de_baja(producto_id):
    """
    Baja lógica: setea activo: False sin borrar el documento físicamente.
    """
    resultado = productos.update_one(
        {"_id": ObjectId(producto_id), "activo": True},
        {"$set": {"activo": False}}
    )
    if resultado.matched_count:
        print(f"[DELETE] Producto {producto_id} dado de baja lógica (activo: False)")
    else:
        print(f"[DELETE] No se encontró el producto {producto_id} (o ya estaba dado de baja)")
    return resultado.matched_count


# ── Ejecución de prueba ───────────────────────────────────────────────────────
if __name__ == "__main__":
    # ID de la categoría (Celulares y Smartphones)
    CATEGORIA_ID = "6a1c8ce166179daefb1f9a07"

    print("\n--- CREATE ---")
    nuevo_id = crear_producto(
        nombre = "Auriculares Bluetooth Sony WH-CH520",
        descripcion = "Auriculares inalámbricos con cancelación de ruido pasiva",
        categoria_id_str = CATEGORIA_ID,
        precio = 65000,
        stock = 30,
        especificaciones = {"conectividad": "Bluetooth 5.2", "bateria": "50hs"}
    )

    print("\n--- READ ---")
    listar_productos()

    print("\n--- UPDATE ---")
    actualizar_producto(nuevo_id, {"precio": 59990, "stock": 25})

    print("\n--- READ (post-update) ---")
    listar_productos()

    print("\n--- DELETE (baja lógica) ---")
    dar_de_baja(nuevo_id)

    print("\n--- READ (post-delete: el producto dado de baja NO debe aparecer) ---")
    listar_productos()

    client.close()
    print("\nConexión cerrada.")

const productos = [
    {
        id: 1,
        nombre: "Hamburguesa Triple",
        descripcion: "Hamburguesa triple smash con mucho cheddar.",
        precio: 25000,
        imagen: "https://placehold.co/600x400",
        categoria: "Hamburguesas"
    },
    {
        id: 2,
        nombre: "Pizza Muzzarella",
        descripcion: "Salsa de tomate casera y muzzarella abundante.",
        precio: 18000,
        imagen: "https://placehold.co/600x400",
        categoria: "Pizzas"
    },
    {
        id: 3,
        nombre: "Papas con Cheddar",
        descripcion: "Papas fritas crocantes con salsa cheddar y bacon.",
        precio: 8000,
        imagen: "https://placehold.co/600x400",
        categoria: "Papas Fritas"
    }
];

const categorias = ["Todas", "Hamburguesas", "Pizzas", "Papas Fritas", "Bebidas"];

const contenedorProductos = document.getElementById("contenedor-productos");
const contenedorCategorias = document.getElementById("contenedor-categorias");

function renderizarProductos(lista) {
    contenedorProductos.innerHTML = "";
    lista.forEach(prod => {
        const article = document.createElement("article");
        article.innerHTML = `
            <img src="${prod.imagen}" alt="${prod.nombre}">
            <h3>${prod.nombre}</h3>
            <p>${prod.descripcion}</p>
            <p>Precio: <strong>$${prod.precio}</strong></p>
            <button>Ver Detalles</button>
            <button>Agregar al Carrito</button>
        `;
        contenedorProductos.appendChild(article);
    });
}

function renderizarCategorias() {
    categorias.forEach(cat => {
        const li = document.createElement("li");
        li.innerHTML = `<a href="#">${cat}</a>`;
        contenedorCategorias.appendChild(li);
    });
}

renderizarProductos(productos);
renderizarCategorias();
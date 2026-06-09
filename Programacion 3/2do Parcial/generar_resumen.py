from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import cm
from reportlab.lib.colors import HexColor, white, black
from reportlab.platypus import (
    SimpleDocTemplate, Paragraph, Spacer, PageBreak,
    Table, TableStyle, HRFlowable, KeepTogether
)
from reportlab.lib.enums import TA_LEFT, TA_CENTER, TA_JUSTIFY
from reportlab.platypus import Flowable

OUTPUT = r"C:\Users\paddy\Desktop\TUPaD UTN\Programacion 3\2do Parcial\Resumen_TP5_al_TP8_Parcial2.pdf"

# ── Colores ──────────────────────────────────────────────────────────────────
AZUL       = HexColor("#1A3C6E")
AZUL_MED   = HexColor("#2563A8")
AZUL_CLR   = HexColor("#DBEAFE")
VERDE      = HexColor("#166534")
VERDE_CLR  = HexColor("#DCFCE7")
GRIS_OSC   = HexColor("#1F2937")
GRIS_MED   = HexColor("#4B5563")
GRIS_CLR   = HexColor("#F3F4F6")
NARANJA    = HexColor("#92400E")
NARANJA_CLR= HexColor("#FEF3C7")
ROJO       = HexColor("#991B1B")
ROJO_CLR   = HexColor("#FEE2E2")
BLANCO     = white

W, H = A4

# ── Estilos ──────────────────────────────────────────────────────────────────
styles = getSampleStyleSheet()

def style(name, **kw):
    return ParagraphStyle(name, **kw)

S_PORTADA_TITULO = style("portada_titulo",
    fontName="Helvetica-Bold", fontSize=26, leading=34,
    textColor=BLANCO, alignment=TA_CENTER, spaceAfter=8)
S_PORTADA_SUB = style("portada_sub",
    fontName="Helvetica", fontSize=14, leading=20,
    textColor=HexColor("#CBD5E1"), alignment=TA_CENTER, spaceAfter=6)
S_PORTADA_NOMBRE = style("portada_nombre",
    fontName="Helvetica-Bold", fontSize=13, leading=18,
    textColor=HexColor("#93C5FD"), alignment=TA_CENTER)

S_CAP_TITULO = style("cap_titulo",
    fontName="Helvetica-Bold", fontSize=18, leading=24,
    textColor=BLANCO, alignment=TA_LEFT, spaceBefore=0, spaceAfter=0)
S_CAP_NRO = style("cap_nro",
    fontName="Helvetica-Bold", fontSize=11,
    textColor=HexColor("#93C5FD"), alignment=TA_LEFT, spaceAfter=2)

S_H2 = style("h2",
    fontName="Helvetica-Bold", fontSize=13, leading=18,
    textColor=AZUL, spaceBefore=14, spaceAfter=4)
S_H3 = style("h3",
    fontName="Helvetica-Bold", fontSize=11, leading=15,
    textColor=AZUL_MED, spaceBefore=10, spaceAfter=3)
S_H4 = style("h4",
    fontName="Helvetica-Bold", fontSize=10, leading=14,
    textColor=GRIS_OSC, spaceBefore=8, spaceAfter=2)

S_BODY = style("body",
    fontName="Helvetica", fontSize=9.5, leading=14,
    textColor=GRIS_OSC, alignment=TA_JUSTIFY, spaceBefore=2, spaceAfter=4)
S_BODY_B = style("body_bold",
    fontName="Helvetica-Bold", fontSize=9.5, leading=14,
    textColor=GRIS_OSC, spaceBefore=2, spaceAfter=2)
S_BULLET = style("bullet",
    fontName="Helvetica", fontSize=9.5, leading=14,
    textColor=GRIS_OSC, leftIndent=14, firstLineIndent=-8,
    spaceBefore=1, spaceAfter=1)
S_BULLET2 = style("bullet2",
    fontName="Helvetica", fontSize=9, leading=13,
    textColor=GRIS_MED, leftIndent=26, firstLineIndent=-8,
    spaceBefore=0, spaceAfter=0)

S_CODE = style("code",
    fontName="Courier", fontSize=8, leading=12,
    textColor=HexColor("#1E293B"), backColor=HexColor("#F8FAFC"),
    borderPadding=(4,6,4,6), spaceBefore=4, spaceAfter=4,
    leftIndent=8, rightIndent=8)
S_CODE_COMMENT = style("code_comment",
    fontName="Courier-Oblique", fontSize=8, leading=12,
    textColor=HexColor("#64748B"),
    leftIndent=8)

S_NOTA = style("nota",
    fontName="Helvetica-Oblique", fontSize=9, leading=13,
    textColor=NARANJA, spaceBefore=4, spaceAfter=4)
S_ALERTA = style("alerta",
    fontName="Helvetica-Bold", fontSize=9, leading=13,
    textColor=ROJO, spaceBefore=4, spaceAfter=4)

S_GUION_TITULO = style("guion_titulo",
    fontName="Helvetica-Bold", fontSize=14, leading=19,
    textColor=VERDE, spaceBefore=10, spaceAfter=4)
S_GUION_PASO = style("guion_paso",
    fontName="Helvetica-Bold", fontSize=10, leading=14,
    textColor=VERDE, spaceBefore=6, spaceAfter=2)
S_GUION_BODY = style("guion_body",
    fontName="Helvetica", fontSize=9.5, leading=14,
    textColor=GRIS_OSC, alignment=TA_JUSTIFY, spaceBefore=2, spaceAfter=3)
S_GUION_VOZ = style("guion_voz",
    fontName="Helvetica-Oblique", fontSize=9.5, leading=14,
    textColor=HexColor("#374151"), leftIndent=10,
    spaceBefore=2, spaceAfter=4)

# ── Helpers ──────────────────────────────────────────────────────────────────

def hr(color=HexColor("#CBD5E1"), w=0.5):
    return HRFlowable(width="100%", thickness=w, color=color, spaceAfter=6, spaceBefore=2)

def sp(n=6):
    return Spacer(1, n)

def p(text, s=S_BODY):
    return Paragraph(text, s)

def h2(text): return p(text, S_H2)
def h3(text): return p(text, S_H3)
def h4(text): return p(text, S_H4)
def body(text): return p(text, S_BODY)
def nota(text): return p(f"&#9432; {text}", S_NOTA)
def alerta(text): return p(f"&#9888; {text}", S_ALERTA)

def bul(text, bold_part="", level=1):
    s = S_BULLET if level == 1 else S_BULLET2
    if bold_part:
        text = text.replace(bold_part, f"<b>{bold_part}</b>", 1)
    return Paragraph(f"• {text}", s)

def code_block(lines):
    items = []
    for ln in lines:
        stripped = ln.strip()
        if stripped.startswith("//") or stripped.startswith("#"):
            items.append(Paragraph(ln.replace(" ","&nbsp;"), S_CODE_COMMENT))
        else:
            items.append(Paragraph(ln.replace(" ","&nbsp;").replace("<","&lt;").replace(">","&gt;"), S_CODE))
    return items

def caja_info(titulo, contenido_items, color_fondo=AZUL_CLR, color_borde=AZUL_MED):
    rows = [[Paragraph(f"<b>{titulo}</b>", style("ci_t", fontName="Helvetica-Bold",
                fontSize=9, textColor=color_borde))]]
    for it in contenido_items:
        rows.append([it])
    t = Table(rows, colWidths=[15.5*cm])
    t.setStyle(TableStyle([
        ("BACKGROUND", (0,0),(0,0), color_fondo),
        ("BACKGROUND", (0,1),(-1,-1), HexColor("#FFFFFF")),
        ("BOX", (0,0),(-1,-1), 1, color_borde),
        ("LINEBELOW",(0,0),(0,0), 0.5, color_borde),
        ("LEFTPADDING",(0,0),(-1,-1), 8),
        ("RIGHTPADDING",(0,0),(-1,-1), 8),
        ("TOPPADDING",(0,0),(0,0), 5),
        ("BOTTOMPADDING",(0,0),(0,0), 5),
        ("TOPPADDING",(0,1),(-1,-1), 3),
        ("BOTTOMPADDING",(0,-1),(-1,-1), 6),
    ]))
    return t

def banner_capitulo(numero, titulo_tp, descripcion):
    inner = [
        [Paragraph(numero, S_CAP_NRO), Paragraph(titulo_tp, S_CAP_TITULO)],
        [Paragraph("", S_BODY), Paragraph(descripcion, style("cap_desc",
            fontName="Helvetica", fontSize=10, textColor=HexColor("#BFDBFE"),
            alignment=TA_LEFT))]
    ]
    t_inner = Table(inner, colWidths=[2*cm, 13.5*cm])
    t_inner.setStyle(TableStyle([
        ("VALIGN",(0,0),(-1,-1),"MIDDLE"),
        ("TOPPADDING",(0,0),(-1,-1),2),
        ("BOTTOMPADDING",(0,0),(-1,-1),2),
    ]))
    outer = Table([[t_inner]], colWidths=[15.5*cm])
    outer.setStyle(TableStyle([
        ("BACKGROUND",(0,0),(-1,-1), AZUL),
        ("LEFTPADDING",(0,0),(-1,-1),14),
        ("RIGHTPADDING",(0,0),(-1,-1),14),
        ("TOPPADDING",(0,0),(-1,-1),14),
        ("BOTTOMPADDING",(0,0),(-1,-1),14),
        ("ROUNDEDCORNERS",[4,4,4,4]),
    ]))
    return outer

def tabla_dos_col(filas, header=None, w1=5*cm, w2=10.5*cm):
    data = []
    if header:
        data.append([Paragraph(f"<b>{header[0]}</b>", S_BODY_B),
                     Paragraph(f"<b>{header[1]}</b>", S_BODY_B)])
    for a, b in filas:
        data.append([Paragraph(a, S_BODY), Paragraph(b, S_BODY)])
    t = Table(data, colWidths=[w1, w2])
    st = [
        ("GRID",(0,0),(-1,-1), 0.4, HexColor("#E5E7EB")),
        ("BACKGROUND",(0,0),(-1,0), GRIS_CLR),
        ("VALIGN",(0,0),(-1,-1),"TOP"),
        ("LEFTPADDING",(0,0),(-1,-1),6),
        ("RIGHTPADDING",(0,0),(-1,-1),6),
        ("TOPPADDING",(0,0),(-1,-1),4),
        ("BOTTOMPADDING",(0,0),(-1,-1),4),
    ]
    if not header:
        st.pop(1)
    t.setStyle(TableStyle(st))
    return t

# ── Portada ──────────────────────────────────────────────────────────────────

def build_portada():
    outer = Table([[""]], colWidths=[15.5*cm], rowHeights=[22*cm])
    outer.setStyle(TableStyle([
        ("BACKGROUND",(0,0),(-1,-1), AZUL),
    ]))

    items = []
    items.append(sp(40))
    items.append(p("PROGRAMACIÓN III", style("p3", fontName="Helvetica-Bold", fontSize=11,
        textColor=HexColor("#93C5FD"), alignment=TA_CENTER)))
    items.append(sp(8))
    items.append(p("Resumen de Estudio", style("rs", fontName="Helvetica", fontSize=12,
        textColor=HexColor("#CBD5E1"), alignment=TA_CENTER)))
    items.append(sp(16))
    items.append(p("TP5 al TP8\n+ 2do Parcial", S_PORTADA_TITULO))
    items.append(sp(20))
    items.append(HRFlowable(width="60%", thickness=1, color=HexColor("#3B82F6"),
        hAlign="CENTER", spaceBefore=0, spaceAfter=16))
    items.append(p("toString · equals · hashCode · Lombok · DTOs\nProgramación Funcional · Streams · JPA · Hibernate\nRepositorios Genéricos · JPQL · Menú ABM",
        style("temas", fontName="Helvetica", fontSize=10, textColor=HexColor("#93C5FD"),
              alignment=TA_CENTER, leading=17)))
    items.append(sp(30))
    items.append(p("Patricio María Sussini Guanziroli", S_PORTADA_NOMBRE))
    items.append(p("UTN · Tecnicatura Universitaria en Programación · 2026", S_PORTADA_SUB))

    cover = Table([[items]], colWidths=[15.5*cm])
    cover.setStyle(TableStyle([
        ("BACKGROUND",(0,0),(-1,-1), AZUL),
        ("TOPPADDING",(0,0),(-1,-1),0),
        ("BOTTOMPADDING",(0,0),(-1,-1),0),
        ("LEFTPADDING",(0,0),(-1,-1),0),
        ("RIGHTPADDING",(0,0),(-1,-1),0),
    ]))
    return [cover]

# ── Índice ────────────────────────────────────────────────────────────────────

def build_indice():
    story = []
    story.append(h2("Índice de contenidos"))
    story.append(hr())
    capitulos = [
        ("TP 5", "toString, equals y hashCode", "Implementación manual de los tres contratos fundamentales del objeto Java. Uso con HashSet y HashMap."),
        ("TP 6", "Lombok y DTOs", "Eliminación de boilerplate con anotaciones Lombok. Patrón SuperBuilder. Data Transfer Objects con Java Records."),
        ("TP 7", "Programación Funcional — Streams", "Lambdas, interfaces funcionales, operaciones intermedias y terminales, Collectors, Optional."),
        ("TP 8", "JPA con Hibernate", "Jakarta Persistence API, mapeo de entidades, relaciones ManyToOne/OneToMany, ciclo de vida, JPQL."),
        ("2do Parcial", "Repositorios Genéricos y Menú ABM", "Patrón Repository genérico, BaseRepository, CategoriaRepository, ProductoRepository, menú de consola."),
        ("Guión", "Presentación del Video", "Estructura sugerida para grabar el video de presentación del 2do parcial."),
    ]
    data = []
    for tp, titulo, desc in capitulos:
        data.append([
            Paragraph(f"<b>{tp}</b>", style("idx_tp", fontName="Helvetica-Bold",
                fontSize=9, textColor=AZUL_MED)),
            Paragraph(f"<b>{titulo}</b>", style("idx_t", fontName="Helvetica-Bold",
                fontSize=9, textColor=GRIS_OSC)),
            Paragraph(desc, style("idx_d", fontName="Helvetica", fontSize=8.5,
                textColor=GRIS_MED, leading=12)),
        ])
    t = Table(data, colWidths=[2.2*cm, 4.2*cm, 9.1*cm])
    t.setStyle(TableStyle([
        ("GRID",(0,0),(-1,-1), 0.3, HexColor("#E5E7EB")),
        ("ROWBACKGROUNDS",(0,0),(-1,-1),[BLANCO, GRIS_CLR]),
        ("VALIGN",(0,0),(-1,-1),"TOP"),
        ("LEFTPADDING",(0,0),(-1,-1),7),
        ("RIGHTPADDING",(0,0),(-1,-1),7),
        ("TOPPADDING",(0,0),(-1,-1),6),
        ("BOTTOMPADDING",(0,0),(-1,-1),6),
    ]))
    story.append(t)
    return story

# ── TP5 ───────────────────────────────────────────────────────────────────────

def build_tp5():
    story = []
    story.append(banner_capitulo("TP 5", "toString, equals y hashCode",
        "Contratos fundamentales de objetos Java · HashSet · HashMap"))
    story.append(sp(10))

    story.append(h2("¿Por qué existen estos tres métodos?"))
    story.append(body(
        "En Java, toda clase hereda de <b>Object</b>. Object define por defecto tres métodos "
        "críticos: <b>toString()</b>, <b>equals()</b> y <b>hashCode()</b>. "
        "El problema es que las implementaciones por defecto comparan referencias de memoria, "
        "no contenido. Para que dos objetos con los mismos datos sean considerados iguales "
        "(en colecciones, búsquedas, etc.) hay que sobrescribir estos métodos."))
    story.append(sp(4))

    story.append(h2("toString()"))
    story.append(body(
        "Convierte el objeto a una representación legible en texto. Java lo llama automáticamente "
        "al concatenar un objeto con un String o al pasarlo a println(). "
        "Sin sobrescribir, imprime algo como: <b>Producto@3e25a5</b> (clase + dirección de memoria)."))

    story.append(sp(4))
    story.append(caja_info("Ejemplo — toString() manual", [
        *code_block([
            "@Override",
            "public String toString() {",
            "    return \"Producto{nombre='\" + nombre + \"', precio=\" + precio + \"}\";",
            "}",
        ])
    ]))
    story.append(sp(4))

    story.append(h2("equals()"))
    story.append(body(
        "Define cuándo dos objetos son <i>equivalentes</i> según el negocio. "
        "Por defecto compara referencias (==). Al sobrescribir, comparamos campos significativos."))
    story.append(body("<b>Contrato de equals() — obligatorio cumplir:</b>"))
    story.append(bul("<b>Reflexivo:</b> x.equals(x) siempre true."))
    story.append(bul("<b>Simétrico:</b> x.equals(y) ↔ y.equals(x)."))
    story.append(bul("<b>Transitivo:</b> si x==y e y==z, entonces x==z."))
    story.append(bul("<b>Consistente:</b> mismo resultado si el objeto no cambia."))
    story.append(bul("<b>Null-safe:</b> x.equals(null) siempre false."))
    story.append(sp(4))
    story.append(caja_info("Ejemplo — equals() en Producto (por nombre)", [
        *code_block([
            "@Override",
            "public boolean equals(Object o) {",
            "    if (this == o) return true;",
            "    if (!(o instanceof Producto)) return false;",
            "    Producto p = (Producto) o;",
            "    return Objects.equals(nombre, p.nombre);",
            "}",
        ])
    ]))

    story.append(h2("hashCode()"))
    story.append(body(
        "Devuelve un número entero que identifica al objeto para estructuras basadas en hashing "
        "(<b>HashSet</b>, <b>HashMap</b>, <b>Hashtable</b>). "
        "El motor usa hashCode() para ubicar el bucket y luego equals() para confirmar."))
    story.append(alerta("Regla de oro: si dos objetos son iguales según equals(), DEBEN tener el mismo hashCode(). "
        "Si se rompe esta regla, HashSet y HashMap fallan silenciosamente."))
    story.append(caja_info("Ejemplo — hashCode() consistente con equals()", [
        *code_block([
            "@Override",
            "public int hashCode() {",
            "    return Objects.hash(nombre);  // mismos campos que equals()",
            "}",
        ])
    ]))
    story.append(sp(4))

    story.append(h2("Cómo funciona HashSet internamente"))
    story.append(body(
        "Cuando hacemos <b>set.add(objeto)</b> o <b>set.contains(objeto)</b>:"))
    pasos = [
        ("1", "Calcula hashCode() del objeto → determina el bucket (posición en el array interno)."),
        ("2", "Dentro del bucket, recorre los elementos y aplica equals() para evitar duplicados."),
        ("3", "Si equals() devuelve true → considera que ya existe → no inserta."),
    ]
    data = [[Paragraph(f"<b>Paso {a}</b>", S_BODY_B), Paragraph(b, S_BODY)] for a,b in pasos]
    t = Table(data, colWidths=[2.5*cm, 13*cm])
    t.setStyle(TableStyle([
        ("GRID",(0,0),(-1,-1),0.4,HexColor("#E5E7EB")),
        ("BACKGROUND",(0,0),(0,-1), AZUL_CLR),
        ("VALIGN",(0,0),(-1,-1),"MIDDLE"),
        ("LEFTPADDING",(0,0),(-1,-1),7),
        ("TOPPADDING",(0,0),(-1,-1),4),
        ("BOTTOMPADDING",(0,0),(-1,-1),4),
    ]))
    story.append(t)

    return story

# ── TP6 ───────────────────────────────────────────────────────────────────────

def build_tp6():
    story = []
    story.append(banner_capitulo("TP 6", "Lombok y DTOs",
        "Eliminación de boilerplate · SuperBuilder · Java Records"))
    story.append(sp(10))

    story.append(h2("¿Qué es Lombok?"))
    story.append(body(
        "Lombok es una librería que genera automáticamente en tiempo de compilación el código "
        "repetitivo (boilerplate): getters, setters, constructores, equals, hashCode, toString. "
        "El código generado no aparece en los archivos .java pero sí en los .class compilados."))
    story.append(sp(4))

    story.append(h2("Anotaciones principales"))
    anots = [
        ("@Getter / @Setter", "Genera get/set para todos los campos (o uno específico si se pone en el campo)."),
        ("@NoArgsConstructor", "Genera constructor sin parámetros. Requerido por JPA."),
        ("@AllArgsConstructor", "Genera constructor con todos los campos como parámetros."),
        ("@ToString", "Genera toString() con todos los campos. Con callSuper=true incluye los de la superclase."),
        ("@EqualsAndHashCode", "Genera equals() y hashCode(). Con callSuper=true incluye campos del padre. Con onlyExplicitlyIncluded=true solo usa los marcados con @Include."),
        ("@Builder", "Patrón Builder para la clase. No funciona bien con herencia."),
        ("@SuperBuilder", "Versión de Builder que soporta herencia. Requiere anotarlo en la clase padre e hijas."),
    ]
    story.append(tabla_dos_col(anots, header=("Anotación", "Descripción"), w1=4.5*cm, w2=11*cm))
    story.append(sp(6))

    story.append(h2("@SuperBuilder — Patrón Builder con herencia"))
    story.append(body(
        "Permite construir objetos con una cadena fluida de métodos en jerarquías de herencia. "
        "Se debe anotar TANTO la clase padre (Base) como cada clase hija."))
    story.append(caja_info("Ejemplo — SuperBuilder en acción", [
        *code_block([
            "// Construccion con builder",
            "Producto p = Producto.builder()",
            "    .nombre(\"Notebook\")       // campo de Producto",
            "    .precio(1500.0)",
            "    .eliminado(false)          // campo de Base (superclase)",
            "    .createdAt(LocalDateTime.now())",
            "    .build();",
        ])
    ]))

    story.append(h2("DTOs — Data Transfer Objects"))
    story.append(body(
        "Un DTO es un objeto que transporta solo los datos necesarios, "
        "ocultando información sensible (contraseña, rol) o simplificando la estructura. "
        "Se crea a partir de una entidad pero no es una entidad JPA."))

    story.append(h3("Java Record como DTO"))
    story.append(body(
        "Desde Java 16, los <b>Records</b> son clases inmutables con constructor, "
        "equals, hashCode y toString generados automáticamente. Son ideales para DTOs "
        "porque son concisos y no permiten modificar su estado tras la construcción."))
    story.append(caja_info("Ejemplo — UsuarioDTO como record", [
        *code_block([
            "public record UsuarioDTO(",
            "    Long id,",
            "    String nombre,",
            "    String apellido,",
            "    String mail",
            "    // SIN contrasena ni rol",
            ") {}",
            "",
            "// Uso:",
            "UsuarioDTO dto = new UsuarioDTO(usuario.getId(),",
            "    usuario.getNombre(), usuario.getApellido(), usuario.getMail());",
        ])
    ]))

    return story

# ── TP7 ───────────────────────────────────────────────────────────────────────

def build_tp7():
    story = []
    story.append(banner_capitulo("TP 7", "Programación Funcional — Streams",
        "Lambdas · Interfaces funcionales · Operaciones intermedias y terminales · Optional"))
    story.append(sp(10))

    story.append(h2("¿Qué es la Programación Funcional?"))
    story.append(body(
        "Es un paradigma que trata la computación como evaluación de funciones matemáticas. "
        "En Java (desde Java 8), se incorpora mediante <b>Lambdas</b>, <b>Streams</b> e "
        "<b>Interfaces Funcionales</b>. El objetivo es escribir código más declarativo "
        "(qué hacer) y menos imperativo (cómo hacerlo)."))

    story.append(h2("Lambdas"))
    story.append(body(
        "Una lambda es una función anónima que se puede pasar como argumento. "
        "Su sintaxis es: <b>(parámetros) -&gt; cuerpo</b>"))
    story.append(caja_info("Ejemplos de lambda", [
        *code_block([
            "// Lambda con un parametro",
            "productos.forEach(p -> System.out.println(p.getNombre()));",
            "",
            "// Lambda como predicado",
            "Predicate<Producto> disponible = p -> p.getDisponible();",
            "",
            "// Referencia a metodo (azucar sintactico de lambda)",
            "productos.forEach(System.out::println);",
        ])
    ]))

    story.append(h2("Interfaces Funcionales principales"))
    ifaces = [
        ("Predicate&lt;T&gt;", "T → boolean", "Filtrar: p -> p.getStock() > 0"),
        ("Function&lt;T,R&gt;", "T → R", "Transformar: p -> p.getNombre()"),
        ("Consumer&lt;T&gt;", "T → void", "Consumir: p -> System.out.println(p)"),
        ("Supplier&lt;T&gt;", "() → T", "Proveer: () -> new Producto()"),
        ("Comparator&lt;T&gt;", "(T,T) → int", "Ordenar: Comparator.comparing(Producto::getPrecio)"),
    ]
    story.append(tabla_dos_col(
        [(a, f"<i>{b}</i>   Ej: {c}") for a,b,c in ifaces],
        header=("Interfaz", "Firma / Ejemplo"),
        w1=4*cm, w2=11.5*cm
    ))
    story.append(sp(6))

    story.append(h2("Streams — flujo de datos"))
    story.append(body(
        "Un Stream es una secuencia de elementos que soporta operaciones encadenadas. "
        "<b>No modifica la colección original</b> y puede ser secuencial o paralelo."))

    story.append(h3("Operaciones Intermedias — devuelven otro Stream (lazy)"))
    ops_int = [
        ("filter(Predicate)", "Descarta elementos que no cumplen la condición."),
        ("map(Function)", "Transforma cada elemento en otro."),
        ("sorted(Comparator)", "Ordena el stream."),
        ("distinct()", "Elimina duplicados (usa equals())."),
        ("limit(n)", "Toma solo los primeros n elementos."),
        ("peek(Consumer)", "Inspecciona sin transformar (útil para debug)."),
    ]
    story.append(tabla_dos_col(ops_int, header=("Operación", "Qué hace"), w1=4.5*cm, w2=11*cm))
    story.append(sp(6))

    story.append(h3("Operaciones Terminales — consumen el Stream (eager)"))
    ops_ter = [
        ("forEach(Consumer)", "Ejecuta acción por cada elemento."),
        ("collect(Collector)", "Acumula en colección (List, Set, Map)."),
        ("count()", "Cuenta los elementos."),
        ("findFirst()", "Devuelve Optional con el primer elemento."),
        ("anyMatch(Predicate)", "True si alguno cumple la condición."),
        ("reduce(identity, BinaryOp)", "Reduce a un solo valor (suma, concatenar, etc.)."),
    ]
    story.append(tabla_dos_col(ops_ter, header=("Operación", "Qué hace"), w1=4.5*cm, w2=11*cm))
    story.append(sp(6))

    story.append(h2("Ejemplo completo con el dominio del TP"))
    story.append(caja_info("Streams sobre listas de Producto", [
        *code_block([
            "// Filtrar productos disponibles con stock bajo",
            "List<Producto> stockBajo = productos.stream()",
            "    .filter(Producto::getDisponible)",
            "    .filter(p -> p.getStock() < 5)",
            "    .collect(Collectors.toList());",
            "",
            "// Calcular total de un pedido",
            "double total = detalles.stream()",
            "    .mapToDouble(d -> d.getPrecio() * d.getCantidad())",
            "    .sum();",
            "",
            "// Agrupar productos por categoria",
            "Map<String, List<Producto>> porCat = productos.stream()",
            "    .collect(Collectors.groupingBy(p -> p.getCategoria().getNombre()));",
        ])
    ]))

    story.append(h2("Optional"))
    story.append(body(
        "Contenedor que puede o no tener un valor. Evita NullPointerException al forzar "
        "al desarrollador a manejar el caso de ausencia explícitamente."))
    story.append(caja_info("Uso de Optional", [
        *code_block([
            "Optional<Producto> opt = repo.buscarPorId(id);",
            "",
            "// Verificar presencia",
            "if (opt.isPresent()) { ... }",
            "if (opt.isEmpty()) { ... }",
            "",
            "// Obtener valor (lanza excepcion si vacio)",
            "Producto p = opt.get();",
            "",
            "// Obtener o default",
            "Producto p = opt.orElse(new Producto());",
            "",
            "// Obtener o lanzar excepcion personalizada",
            "Producto p = opt.orElseThrow(() -> new RuntimeException(\"No encontrado\"));",
        ])
    ]))

    return story

# ── TP8 ───────────────────────────────────────────────────────────────────────

def build_tp8():
    story = []
    story.append(banner_capitulo("TP 8", "JPA con Hibernate",
        "Jakarta Persistence API · Mapeo ORM · Relaciones · JPQL · Ciclo de vida"))
    story.append(sp(10))

    story.append(h2("¿Qué es JPA?"))
    story.append(body(
        "<b>JPA (Jakarta Persistence API)</b> es una especificación de Java para mapear objetos "
        "a tablas de bases de datos relacionales (<b>ORM — Object Relational Mapping</b>). "
        "<b>Hibernate</b> es la implementación más popular de JPA. "
        "Con JPA, trabajamos con objetos Java y Hibernate se encarga de generar y ejecutar el SQL."))

    story.append(h2("Anotaciones JPA esenciales"))
    anots = [
        ("@Entity", "Marca la clase como entidad persistente (tabla en la BD)."),
        ("@Table(name=...)", "Especifica el nombre de la tabla. Por defecto usa el nombre de la clase."),
        ("@MappedSuperclass", "La clase no tiene tabla propia, pero sus campos se heredan. Usada en Base."),
        ("@Id", "Marca el campo como clave primaria."),
        ("@GeneratedValue(IDENTITY)", "El ID lo genera la BD automáticamente (autoincrement)."),
        ("@Column(unique=true)", "Mapea un campo a una columna. unique=true agrega constraint."),
        ("@ManyToOne", "Relación muchos-a-uno. Ej: muchos Productos → una Categoria."),
        ("@OneToMany", "Relación uno-a-muchos. Ej: un Usuario → muchos Pedidos."),
        ("@JoinColumn(name=...)", "Define la columna de clave foránea en la tabla."),
        ("@Enumerated(STRING)", "Persiste un enum como texto en la BD (no como número)."),
    ]
    story.append(tabla_dos_col(anots, header=("Anotación", "Descripción"), w1=4.5*cm, w2=11*cm))
    story.append(sp(6))

    story.append(h2("Clase Base con @MappedSuperclass"))
    story.append(body(
        "Patrón donde una clase abstracta tiene los campos comunes (id, eliminado, createdAt) "
        "y todas las entidades la extienden. No genera tabla propia."))
    story.append(caja_info("Base.java", [
        *code_block([
            "@Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor",
            "@MappedSuperclass",
            "public abstract class Base {",
            "    @Id",
            "    @GeneratedValue(strategy = GenerationType.IDENTITY)",
            "    private Long id;",
            "    private boolean eliminado;",
            "    private LocalDateTime createdAt;",
            "}",
        ])
    ]))

    story.append(h2("FetchType — LAZY vs EAGER"))
    story.append(body(
        "Define <i>cuándo</i> se carga una relación desde la base de datos:"))
    story.append(bul("<b>EAGER</b>: se carga junto con la entidad principal en el mismo SELECT. "
        "Conveniente pero puede traer datos de más."))
    story.append(bul("<b>LAZY</b>: se carga solo cuando se accede al campo por primera vez (proxy). "
        "Más eficiente pero requiere que el EntityManager esté abierto."))
    story.append(nota("Por defecto: @ManyToOne es EAGER. @OneToMany es LAZY."))

    story.append(h2("CascadeType y orphanRemoval"))
    cascades = [
        ("PERSIST", "Al persistir la entidad padre, se persisten las hijas también."),
        ("MERGE", "Al hacer merge del padre, se mergean las hijas."),
        ("REMOVE", "Al eliminar el padre, se eliminan las hijas."),
        ("ALL", "Aplica todos los anteriores."),
    ]
    story.append(tabla_dos_col(cascades, header=("CascadeType", "Efecto"), w1=3.5*cm, w2=12*cm))
    story.append(sp(4))
    story.append(body(
        "<b>orphanRemoval=true</b>: si se saca un hijo de la colección del padre, "
        "se elimina automáticamente de la BD (queda huérfano)."))

    story.append(h2("EntityManager — operaciones principales"))
    ops = [
        ("persist(obj)", "Inserta un objeto nuevo (transient → managed)."),
        ("merge(obj)", "Actualiza un objeto o persiste uno nuevo si no tiene ID. Devuelve la instancia managed."),
        ("find(Clase, id)", "Busca por clave primaria. Devuelve null si no existe."),
        ("remove(obj)", "Elimina el objeto de la BD (debe estar managed)."),
        ("createQuery(jpql, Clase)", "Crea una TypedQuery para ejecutar JPQL."),
    ]
    story.append(tabla_dos_col(ops, header=("Método", "Descripción"), w1=4.5*cm, w2=11*cm))
    story.append(sp(6))

    story.append(h2("JPQL — Java Persistence Query Language"))
    story.append(body(
        "JPQL es como SQL pero trabaja sobre <b>entidades y campos Java</b>, no sobre tablas y columnas. "
        "Es portable entre distintos motores de base de datos."))
    story.append(caja_info("Ejemplos JPQL", [
        *code_block([
            "// Listar activos (usando nombre de la entidad Java, no la tabla)",
            "SELECT e FROM Categoria e WHERE e.eliminado = false",
            "",
            "// Buscar por campo de entidad relacionada (NO necesita JOIN explicito)",
            "SELECT p FROM Producto p WHERE p.categoria.id = :categoriaId",
            "    AND p.eliminado = false",
            "",
            "// Uso con TypedQuery (sin casteos, type-safe)",
            "TypedQuery<Producto> q = em.createQuery(jpql, Producto.class);",
            "q.setParameter(\"categoriaId\", id);",
            "List<Producto> resultado = q.getResultList();",
        ])
    ]))

    story.append(h2("persistence.xml — configuración central"))
    story.append(body(
        "Archivo en <b>src/main/resources/META-INF/persistence.xml</b>. "
        "Define la unidad de persistencia: driver, URL, usuario, proveedor JPA y propiedades de Hibernate."))
    story.append(caja_info("Propiedades clave de Hibernate", [
        *code_block([
            "hibernate.hbm2ddl.auto = create   // Recrea tablas al iniciar (borra datos)",
            "hibernate.hbm2ddl.auto = update   // Actualiza esquema, preserva datos",
            "hibernate.hbm2ddl.auto = validate // Solo valida, no modifica",
            "hibernate.show_sql     = false     // No imprime SQL en consola",
        ])
    ]))

    story.append(h2("Borrado Lógico vs Físico"))
    story.append(body(
        "En sistemas reales casi nunca se borra físicamente un registro (<b>DELETE</b>). "
        "En cambio, se marca como eliminado con un campo booleano:"))
    story.append(bul("<b>Borrado físico:</b> <font face='Courier'>em.remove(entity)</font> → "
        "borra el registro. Puede lanzar excepción si hay foreign keys que lo referencian."))
    story.append(bul("<b>Borrado lógico:</b> <font face='Courier'>entity.setEliminado(true); em.merge(entity)</font> "
        "→ el registro queda pero con <font face='Courier'>eliminado=true</font>. "
        "Las consultas filtran por <font face='Courier'>WHERE eliminado=false</font>."))
    story.append(alerta(
        "El TP8 tuvo -5 puntos por usar borrado físico (em.remove) en un producto "
        "que tenía referencias en DetallePedido → excepción de integridad referencial."))

    return story

# ── 2do Parcial ───────────────────────────────────────────────────────────────

def build_parcial():
    story = []
    story.append(banner_capitulo("2do Parcial", "Repositorios Genéricos y Menú ABM",
        "Patrón Repository · BaseRepository&lt;T&gt; · JPQL · Menú de Consola"))
    story.append(sp(10))

    story.append(h2("Patrón Repository"))
    story.append(body(
        "El patrón Repository abstrae el acceso a datos detrás de una interfaz. "
        "La lógica de negocio (Main) no sabe cómo se persisten los datos, solo llama métodos "
        "como <b>guardar()</b>, <b>buscarPorId()</b>, etc. "
        "Esto hace el código más testeable, mantenible y desacoplado de JPA."))

    story.append(h2("JPAUtil — Singleton del EntityManagerFactory"))
    story.append(body(
        "El <b>EntityManagerFactory</b> es costoso de crear (lee persistence.xml, "
        "abre la conexión a la BD, etc.). Por eso se crea una sola vez y se reutiliza. "
        "JPAUtil lo encapsula como singleton estático."))
    story.append(caja_info("JPAUtil.java", [
        *code_block([
            "public class JPAUtil {",
            "    private static final EntityManagerFactory emf =",
            "        Persistence.createEntityManagerFactory(\"miUnidad\");",
            "",
            "    public static EntityManagerFactory getEntityManagerFactory() {",
            "        return emf;",
            "    }",
            "    public static void close() {",
            "        if (emf != null && emf.isOpen()) emf.close();",
            "    }",
            "}",
        ])
    ]))

    story.append(h2("BaseRepository&lt;T extends Base&gt;"))
    story.append(body(
        "Clase abstracta genérica que implementa las 4 operaciones CRUD comunes a cualquier entidad. "
        "El bound <b>T extends Base</b> garantiza que T tiene getId() y setEliminado(), "
        "evitando cualquier casteo."))
    story.append(body("<b>Principio clave:</b> cada método crea y cierra su propio EntityManager "
        "en un bloque <b>finally</b>. Así se evitan EntityManagers \"abiertos\" o fugas de recursos."))

    story.append(sp(4))
    mets = [
        ("guardar(T entity)", "em.merge(entity). Sirve para INSERT y UPDATE. Devuelve la entidad con ID generado."),
        ("buscarPorId(Long id)", "em.find(clase, id). Devuelve Optional.ofNullable() para manejar el caso de no encontrar."),
        ("listarActivos()", "JPQL: SELECT e FROM <Entidad> e WHERE e.eliminado = false. Usa clase.getSimpleName() como nombre de entidad."),
        ("eliminarLogico(Long id)", "Busca la entidad, hace setEliminado(true), llama merge(). Devuelve boolean."),
    ]
    story.append(tabla_dos_col(mets, header=("Método", "Implementación"), w1=4.5*cm, w2=11*cm))
    story.append(sp(6))

    story.append(caja_info("BaseRepository.java — estructura completa", [
        *code_block([
            "public abstract class BaseRepository<T extends Base> {",
            "    private final Class<T> clazz;",
            "    private final EntityManagerFactory emf;",
            "",
            "    public BaseRepository(Class<T> clazz) {",
            "        this.clazz = clazz;",
            "        this.emf = JPAUtil.getEntityManagerFactory();",
            "    }",
            "",
            "    public T guardar(T entity) {",
            "        EntityManager em = emf.createEntityManager();",
            "        try {",
            "            em.getTransaction().begin();",
            "            T merged = em.merge(entity);",
            "            em.getTransaction().commit();",
            "            return merged;",
            "        } catch (Exception e) {",
            "            if (em.getTransaction().isActive())",
            "                em.getTransaction().rollback();",
            "            throw e;",
            "        } finally {",
            "            em.close();  // SIEMPRE se cierra",
            "        }",
            "    }",
            "",
            "    public Optional<T> buscarPorId(Long id) {",
            "        EntityManager em = emf.createEntityManager();",
            "        try {",
            "            return Optional.ofNullable(em.find(clazz, id));",
            "        } finally { em.close(); }",
            "    }",
            "",
            "    public List<T> listarActivos() {",
            "        EntityManager em = emf.createEntityManager();",
            "        try {",
            "            String jpql = \"SELECT e FROM \" + clazz.getSimpleName()",
            "                + \" e WHERE e.eliminado = false\";",
            "            return em.createQuery(jpql, clazz).getResultList();",
            "        } finally { em.close(); }",
            "    }",
            "",
            "    public boolean eliminarLogico(Long id) {",
            "        EntityManager em = emf.createEntityManager();",
            "        try {",
            "            T entity = em.find(clazz, id);",
            "            if (entity == null) return false;",
            "            em.getTransaction().begin();",
            "            entity.setEliminado(true);",
            "            em.merge(entity);",
            "            em.getTransaction().commit();",
            "            return true;",
            "        } catch (Exception e) {",
            "            if (em.getTransaction().isActive())",
            "                em.getTransaction().rollback();",
            "            throw e;",
            "        } finally { em.close(); }",
            "    }",
            "}",
        ])
    ]))

    story.append(h2("CategoriaRepository y ProductoRepository"))
    story.append(caja_info("Implementaciones concretas", [
        *code_block([
            "// Solo necesita el constructor con super()",
            "public class CategoriaRepository extends BaseRepository<Categoria> {",
            "    public CategoriaRepository() { super(Categoria.class); }",
            "}",
            "",
            "// Agrega buscarPorCategoria con JPQL tipada",
            "public class ProductoRepository extends BaseRepository<Producto> {",
            "    public ProductoRepository() { super(Producto.class); }",
            "",
            "    // JPQL: productos activos de una categoria dada",
            "    public List<Producto> buscarPorCategoria(Long categoriaId) {",
            "        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();",
            "        try {",
            "            TypedQuery<Producto> q = em.createQuery(",
            "                \"SELECT p FROM Producto p WHERE p.categoria.id = :categoriaId\"",
            "                + \" AND p.eliminado = false\", Producto.class);",
            "            q.setParameter(\"categoriaId\", categoriaId);",
            "            return q.getResultList();",
            "        } finally { em.close(); }",
            "    }",
            "}",
        ])
    ]))

    story.append(h2("Errores comunes a evitar — lección del TP8"))
    errores = [
        ("getSingleResult() sin try/catch",
         "Si la query no encuentra resultados lanza NoResultException y rompe el programa. Solución: usar find() con Optional, o envolver en try/catch."),
        ("Borrado físico (em.remove) con FKs",
         "Si un Producto tiene DetallePedidos, removiéndolo se viola la integridad referencial. Solución: borrado lógico siempre."),
        ("No cerrar EntityManager",
         "Si el EM no se cierra en finally, puede haber fugas de conexión. Solución: siempre em.close() en el bloque finally."),
        ("rollback sin verificar isActive()",
         "Si la transacción no comenzó (el error fue antes del begin()), llamar rollback() lanza otra excepción. Solución: if (em.getTransaction().isActive()) em.getTransaction().rollback()."),
    ]
    for titulo, desc in errores:
        story.append(caja_info(f"&#9888; Error: {titulo}", [body(desc)],
            color_fondo=ROJO_CLR, color_borde=ROJO))

    return story

# ── Guión de video ────────────────────────────────────────────────────────────

def build_guion():
    story = []
    story.append(banner_capitulo("Guión", "Presentación del Video",
        "Estructura sugerida para el video de demostración del 2do Parcial"))
    story.append(sp(10))

    story.append(p("GUIÓN DE PRESENTACIÓN — 2DO PARCIAL PROGRAMACIÓN III", S_GUION_TITULO))
    story.append(body(
        "El video debe mostrar el proyecto funcionando y explicar las decisiones técnicas. "
        "Duración sugerida: <b>10 a 15 minutos</b>. Tener el IDE y la terminal listos antes de grabar."))
    story.append(hr(VERDE))
    story.append(sp(4))

    secciones = [
        ("PARTE 1 — Introducción (1-2 min)",
         "Presentación personal y del trabajo",
         [
             ("Lo que decís:", "\"Hola, soy Patricio Sussini. Voy a presentar el 2do parcial de Programación III, "
              "que consiste en extender el proyecto JPA del TP8 agregando un patrón Repository genérico "
              "y un menú de consola para gestionar Categorías y Productos.\""),
             ("Mostrás:", "La carpeta del proyecto abierta en NetBeans / VS Code. Señalás la estructura de paquetes: "
              "entities, enums, util, repository, Main."),
         ]),

        ("PARTE 2 — Estructura del proyecto (2-3 min)",
         "Recorrida por los archivos nuevos",
         [
             ("Abrís JPAUtil.java y explicás:", "\"Este es el singleton del EntityManagerFactory. "
              "Se crea una sola vez al cargar la clase porque es costoso de instanciar. "
              "Tiene un método close() para liberar la conexión al salir.\""),
             ("Abrís BaseRepository.java y explicás:", "\"Esta es la clase abstracta genérica. "
              "El tipo T está acotado a Base para poder llamar setEliminado() sin castear. "
              "Cada método crea su propio EntityManager y lo cierra en el bloque finally, "
              "sin importar si hubo error o no.\""),
             ("Destacás guardar():", "\"Uso merge() y no persist() porque sirve para las dos cosas: "
              "insertar objetos nuevos (sin ID) y actualizar existentes (con ID). "
              "Si hay excepción, verifico que la transacción esté activa antes de hacer rollback, "
              "para no tirar una segunda excepción.\""),
             ("Abrís ProductoRepository.java y explicás buscarPorCategoria():",
              "\"Acá uso TypedQuery de Producto sin casteos. "
              "La JPQL navega la relación p.categoria.id directamente, sin necesitar un JOIN explícito. "
              "El parámetro es nombrado con :categoriaId para mayor claridad.\""),
         ]),

        ("PARTE 3 — Demo en vivo (5-7 min)",
         "Ejecutar la aplicación y recorrer el menú",
         [
             ("Ejecutás con mvn exec:java (o desde el IDE) y mostrás:", "El menú principal limpio, sin logs de Hibernate."),
             ("Demo Categorías — Alta:", "Creás una categoría, mostrás el mensaje con el ID generado automáticamente."),
             ("Demo Categorías — Listado:", "Mostrás la tabla formateada con ID, NOMBRE, DESCRIPCION."),
             ("Demo Categorías — Modificación:",
              "Seleccionás una categoría. Dejás el nombre vacío (Enter) → se mantiene. Cambiás solo la descripción."),
             ("Demo Categorías — Baja lógica:",
              "Das de baja una categoría. Verificás con Listado que ya no aparece. "
              "\"El registro no se borra de la BD, solo tiene eliminado=true.\""),
             ("Demo Productos — Alta:",
              "Mostrás que primero lista las categorías activas. Ingresás datos con validaciones: "
              "probás poner precio negativo → mensaje de error. Después ingresás datos válidos."),
             ("Demo Productos — Listado:", "Mostrás la tabla con ID, NOMBRE, CATEGORIA, STOCK, PRECIO."),
             ("Demo Reportes — Productos por Categoría:",
              "\"Acá se ejecuta la JPQL de ProductoRepository. Seleccionamos una categoría "
              "y se listan solo los productos activos de esa categoría.\""),
         ]),

        ("PARTE 4 — Explicación técnica final (1-2 min)",
         "Cierre con los conceptos clave",
         [
             ("Explicás:", "\"En este parcial apliqué tres conceptos centrales del curso:\""),
             ("1 — JPA y Hibernate:", "Las entidades ya existían del TP8. No las modifiqué. "
              "El persistence.xml tiene hbm2ddl.auto=update para que los datos persistan entre ejecuciones."),
             ("2 — Patrón Repository genérico:", "BaseRepository centraliza el CRUD evitando repetición de código. "
              "CategoriaRepository y ProductoRepository solo aportan lo específico de cada entidad."),
             ("3 — Borrado lógico:", "Ninguna entidad se borra físicamente. "
              "Esto evita excepciones de integridad referencial y permite recuperar datos si fuera necesario."),
             ("Cerrás con:", "\"Gracias. Patricio Sussini, Programación III, UTN 2026.\""),
         ]),
    ]

    for titulo_sec, subtitulo_sec, pasos in secciones:
        story.append(sp(6))
        bloque = [
            p(titulo_sec, S_GUION_TITULO),
            p(subtitulo_sec, style("gs_sub", fontName="Helvetica-Oblique", fontSize=9.5,
                textColor=GRIS_MED, spaceAfter=6)),
            hr(VERDE, 0.4),
        ]
        for etiqueta, texto in pasos:
            bloque.append(p(etiqueta, S_GUION_PASO))
            bloque.append(p(f"\"{texto}\"" if not texto.startswith("\"") else texto, S_GUION_VOZ))
        story.append(caja_info("", bloque, color_fondo=VERDE_CLR, color_borde=VERDE))

    story.append(sp(10))
    story.append(caja_info("Consejos antes de grabar", [
        body("• Corré el proyecto <b>una vez antes</b> de grabar para que la BD ya tenga el esquema creado."),
        body("• Preparate <b>datos de ejemplo</b> con nombres y precios reales (no 'aaa', '123')."),
        body("• Cerrá notificaciones y otras ventanas del sistema operativo."),
        body("• Tené los archivos clave abiertos en el IDE antes de empezar: "
             "BaseRepository.java, ProductoRepository.java, Main.java."),
        body("• No leas el guión literalmente — hablá como si lo explicaras a un compañero."),
    ], color_fondo=NARANJA_CLR, color_borde=NARANJA))

    return story

# ── Build ─────────────────────────────────────────────────────────────────────

def build():
    doc = SimpleDocTemplate(
        OUTPUT,
        pagesize=A4,
        leftMargin=2.5*cm, rightMargin=2.5*cm,
        topMargin=2*cm,    bottomMargin=2*cm,
        title="Resumen TP5-TP8 + 2do Parcial — Programación III",
        author="Patricio Sussini",
        subject="Programacion III UTN",
    )

    story = []
    story += build_portada()
    story.append(PageBreak())

    story += build_indice()
    story.append(PageBreak())

    story += build_tp5()
    story.append(PageBreak())

    story += build_tp6()
    story.append(PageBreak())

    story += build_tp7()
    story.append(PageBreak())

    story += build_tp8()
    story.append(PageBreak())

    story += build_parcial()
    story.append(PageBreak())

    story += build_guion()

    doc.build(story)
    print(f"PDF generado: {OUTPUT}")

if __name__ == "__main__":
    build()

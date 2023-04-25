window.addEventListener('load', () => {
    if (sessionStorage.getItem('nombreUsuario') !== null) {
        /*Nueva opción en el navegador*/
        document.getElementById("nodoPadreNavegadorElementos").removeChild(document.getElementById("nodoEliminarConectado"));
        const nuevaOpcion = document.createElement("li");
        nuevaOpcion.setAttribute("class", "nav-item dropdown");
        nuevaOpcion.setAttribute("id", "nodoEliminarConectado");
        const enlaceReferencia = document.createElement("a");
        enlaceReferencia.setAttribute("href", "#");
        enlaceReferencia.setAttribute("class", "nav-link dropdown-toggle");
        enlaceReferencia.setAttribute("data-bs-toggle", "dropdown");
        enlaceReferencia.setAttribute("data-toggle", "dropdown");
        enlaceReferencia.setAttribute("aria-haspopup", "true");
        enlaceReferencia.setAttribute("aria-expanded", "false");
        enlaceReferencia.setAttribute("id", "dropdownLog");
        enlaceReferencia.appendChild(document.createTextNode(sessionStorage.getItem("nombreUsuario")));
        const contenedorSubOpciones = document.createElement("div");
        contenedorSubOpciones.setAttribute("class", "dropdown-menu");
        contenedorSubOpciones.setAttribute("aria-labelledby", "dropdownLog");
        const opcionPerfil = document.createElement("a");
        opcionPerfil.setAttribute("class", "dropdown-item");
        opcionPerfil.setAttribute("href", "perfil.html");
        opcionPerfil.appendChild(document.createTextNode("Mi perfil"));
        const divisor = document.createElement("div");
        divisor.setAttribute("class", "dropdown-divider");
        const opcionCerrar = document.createElement("a");
        opcionCerrar.setAttribute("class", "dropdown-item");
        opcionCerrar.setAttribute("id", "cerrar");
        opcionCerrar.setAttribute("href", "#");
        opcionCerrar.appendChild(document.createTextNode("Cerrar sesión"));
        nuevaOpcion.appendChild(enlaceReferencia);
        contenedorSubOpciones.appendChild(opcionPerfil);
        contenedorSubOpciones.appendChild(divisor);
        contenedorSubOpciones.appendChild(opcionCerrar);
        nuevaOpcion.appendChild(contenedorSubOpciones);
        document.getElementById("nodoPadreNavegadorElementos").appendChild(nuevaOpcion);
    }
    if (document.getElementById("cerrar") !== null) {
        document.getElementById("cerrar").addEventListener("click", () => {
            document.getElementById("modal").style.display = "flex";
        });
    }
    document.getElementById("cerrarDeVerdad").addEventListener("click", () => {
        document.getElementById("modal").style.display = "none";
        sessionStorage.removeItem("nombreUsuario");
        devolverNavegador();
    });
    document.getElementById("cerrarAbortar").addEventListener("click", () => {
        document.getElementById("modal").style.display = "none";

    });
});

function devolverNavegador() {
    document.getElementById("nodoPadreNavegadorElementos").removeChild(document.getElementById("nodoEliminarConectado"));
    const opcionNavegador = document.createElement("li");
    opcionNavegador.setAttribute("class", "nav-item");
    opcionNavegador.setAttribute("id", "nodoEliminarConectado");
    const enlaceNavegador = document.createElement("a");
    enlaceNavegador.setAttribute("class", "nav-link");
    enlaceNavegador.setAttribute("href", "login.html");
    const icono = document.createElement("i");
    icono.setAttribute("class", "bi bi-person-circle");
    enlaceNavegador.appendChild(icono);
    opcionNavegador.appendChild(enlaceNavegador);
    document.getElementById("nodoPadreNavegadorElementos").appendChild(opcionNavegador);
}
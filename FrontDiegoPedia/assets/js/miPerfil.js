window.addEventListener('load', () => {
    document.getElementById("cambiarCredenciales").addEventListener("click", () => {
        document.getElementById("sectionCambiarCredenciales").style.display = "flex";
        document.getElementById("animationLogin").style.display = "none";
    });
    document.getElementById("cientificosFavoritos").addEventListener("click", () => {
        document.getElementById("sectionCientificosFavoritos").style.display = "block";
        document.getElementById("animationLogin").style.display = "none";

    });
    document.getElementById("contribucionesFavoritas").addEventListener("click", () => {
        document.getElementById("sectionContribucionesFavoritas").style.display = "block";
        document.getElementById("animationLogin").style.display = "none";

    });
    document.getElementById("masOpciones").addEventListener("click", () => {
        document.getElementById("sectionMasOpciones").style.display = "flex";
        document.getElementById("animationLogin").style.display = "none";
    });

    document.getElementById("eliminarCuenta").addEventListener("click", () => {
        document.getElementById("sectionEliminarCuenta").style.display = "flex";
        document.getElementById("sectionMasOpciones").style.display = "none";
    });

    document.getElementById("regresarDesdeCambiarCredenciales").addEventListener("click", () => {
        document.getElementById("sectionCambiarCredenciales").style.display = "none";
        document.getElementById("animationLogin").style.display = "flex";

    });
    document.getElementById("regresarDesdeCientificosFavoritos").addEventListener("click", () => {
        document.getElementById("sectionCientificosFavoritos").style.display = "none";
        document.getElementById("animationLogin").style.display = "flex";

    });
    document.getElementById("regresarDesdeContribucionesFavoritas").addEventListener("click", () => {
        document.getElementById("sectionContribucionesFavoritas").style.display = "none";
        document.getElementById("animationLogin").style.display = "flex";

    });
    document.getElementById("regresarDesdeMasOpciones").addEventListener("click", () => {
        document.getElementById("sectionMasOpciones").style.display = "none";
        document.getElementById("animationLogin").style.display = "flex";

    });
    document.getElementById("regresarDesdeEliminarCuenta").addEventListener("click", () => {
        document.getElementById("sectionEliminarCuenta").style.display = "none";
        document.getElementById("animationLogin").style.display = "flex";

    });
    prepararCientificosFavoritos();
    prepararContribucionesFavoritas();
});
function prepararCientificosFavoritos() {
    const nodoPadreCientificos = document.getElementById("referenciaCientificosBusqueda");
    service.getAllRelacionesUsuarioCientificoByNombreUsuario(sessionStorage.getItem("nombreUsuario")).then(data => { insertarEnBuscadorCientificos(data, nodoPadreCientificos); });
    if (document.getElementById("especificacionesBusquedaCientificos").hasChildNodes()) {
        document.getElementById("especificacionesBusquedaCientificos").removeChild(document.getElementById("especificacionesBusquedaCientificos").firstChild);
    }
    document.getElementById("especificacionesBusquedaCientificos").appendChild(document.createTextNode("Mostrando mis científicos favoritos."));
}
function prepararContribucionesFavoritas() {
    const nodoPadreContribuciones = document.getElementById("referenciaContribucionesBusqueda");
    service.getAllRelacionesUsuarioContribucionByNombreUsuario(sessionStorage.getItem("nombreUsuario")).then(data => { insertarEnBuscadorContribuciones(data, nodoPadreContribuciones); });
    if (document.getElementById("especificacionesBusquedaContribuciones").hasChildNodes()) {
        document.getElementById("especificacionesBusquedaContribuciones").removeChild(document.getElementById("especificacionesBusquedaContribuciones").firstChild);
    }
    document.getElementById("especificacionesBusquedaContribuciones").appendChild(document.createTextNode("Mostrando mis contribuciones favoritas."));
}



function insertarEnBuscadorCientificos(data, nodoPadre) {
    if (data.length !== 0) {
        prepararResultadosCientificos(data, nodoPadre);
        let contenedorCardInicial, contenedorCardPrimero, imagenCientifico, contenedorCardSegundo, contenedorTituloTercero, contenedorTituloCuarto, contenedorTituloQuinto, contenedorCardUltimo, contenedorCardUltimo11, contenedorCardUltimo12, contenedorCardUltimo13, contenedorCardUltimo14, contenedorCardUltimo15, contador = 0;
        for (objeto of data) {
            contador++;
            contenedorCardInicial = document.createElement("div");
            if (data.length !== 1) {
                contenedorCardInicial.setAttribute("class", "col-lg-4 mb-5");
            }
            contenedorCardPrimero = document.createElement("div");
            contenedorCardPrimero.setAttribute("class", "card h-100 shadow border-0");
            if (contador % 3 == 2) {
                contenedorCardPrimero.style.borderLeft = "4px solid #000";
                contenedorCardPrimero.style.borderRight = "4px solid #000";
            }
            imagenCientifico = document.createElement("img");
            imagenCientifico.setAttribute("src", objeto.cientifico.enlaceFoto);
            imagenCientifico.setAttribute("class", "card-img-top");
            imagenCientifico.setAttribute("alt", objeto.cientifico.apellidos);
            contenedorCardSegundo = document.createElement("div");
            contenedorCardSegundo.setAttribute("class", "card-body p-4");
            contenedorTituloTercero = document.createElement("div");
            contenedorTituloTercero.setAttribute("class", "blockquote-footer");
            contenedorTituloTercero.appendChild(document.createTextNode(objeto.cientifico.apellidos + ", " + objeto.cientifico.nombre));
            contenedorTituloCuarto = document.createElement("a");
            contenedorTituloCuarto.setAttribute("class", "link-dark");
            contenedorTituloCuarto.setAttribute("href", objeto.cientifico.enlaceMasInformacion);
            contenedorTituloQuinto = document.createElement("h5");
            contenedorTituloQuinto.setAttribute("class", "badge bg-dark bg-gradient rounded-pill mb-2");
            contenedorTituloQuinto.appendChild(document.createTextNode("Más información del ciéntifico..."))
            contenedorTituloCuarto.appendChild(contenedorTituloQuinto);
            contenedorCardSegundo.appendChild(contenedorTituloTercero);
            contenedorCardSegundo.appendChild(contenedorTituloCuarto);
            contenedorCardUltimo = document.createElement("div");
            contenedorCardUltimo.setAttribute("class", "card-footer p-1 pt-0 bg-transparent border-top-0");
            contenedorCardUltimo11 = document.createElement("div");
            contenedorCardUltimo11.setAttribute("class", "d-flex align-items-end justify-content-between");
            contenedorCardUltimo12 = document.createElement("div");
            contenedorCardUltimo12.setAttribute("class", "d-flex flex-column align-items-center");
            contenedorCardUltimo13 = document.createElement("p");
            contenedorCardUltimo13.setAttribute("class", "fw-bold");
            contenedorCardUltimo13.appendChild(document.createTextNode("Fecha nacimiento: " + objeto.cientifico.fechaNacimiento));
            contenedorCardUltimo14 = document.createElement("p");
            contenedorCardUltimo14.setAttribute("class", "fw-bold");
            if (objeto.cientifico.fechaDefuncion === null) {
                contenedorCardUltimo14.appendChild(document.createTextNode("Fecha defuncion: vivo."));

            } else {
                contenedorCardUltimo14.appendChild(document.createTextNode("Fecha defuncion: " + objeto.cientifico.fechaDefuncion + "."));
            }
            contenedorCardUltimo15 = document.createElement("p");
            contenedorCardUltimo15.setAttribute("class", "fw-bold");
            contenedorCardUltimo15.appendChild(document.createTextNode("Nacionalidad: " + objeto.cientifico.nacionalidad + "."));
            contenedorCardUltimo12.appendChild(contenedorCardUltimo13);
            contenedorCardUltimo12.appendChild(contenedorCardUltimo14);
            contenedorCardUltimo12.appendChild(contenedorCardUltimo15);
            contenedorCardUltimo11.appendChild(contenedorCardUltimo12);
            contenedorCardUltimo.appendChild(contenedorCardUltimo11);
            contenedorCardPrimero.appendChild(imagenCientifico);
            contenedorCardPrimero.appendChild(contenedorCardSegundo);
            contenedorCardPrimero.appendChild(contenedorCardUltimo);
            contenedorCardInicial.appendChild(contenedorCardPrimero)
            nodoPadre.appendChild(contenedorCardInicial);
        }
    } else if (data.length == 0) {
        document.getElementById("resultadoBusquedaCientificos").style.display = "none";
        document.getElementById("mensajePerfilCientificos").style.display = "flex";
        if (document.getElementById("nodoTextMensajeCientificos").hasChildNodes()) {
            document.getElementById("nodoTextMensajeCientificos").removeChild(document.getElementById("nodoTextMensajeCientificos").firstChild);
        }
        document.getElementById("nodoTextMensajeCientificos").appendChild(document.createTextNode("Todavía no tienes científicos favoritos :("));
    } else {
        document.getElementById("resultadoBusquedaCientificos").style.display = "none";
        document.getElementById("mensajePerfilCientificos").style.display = "flex";
        if (document.getElementById("nodoTextMensajeCientificos").hasChildNodes()) {
            document.getElementById("nodoTextMensajeCientificos").removeChild(document.getElementById("nodoTextMensajeCientificos").firstChild);
        }
        document.getElementById("nodoTextMensajeCientificos").appendChild(document.createTextNode("Algo inesperado ha ocurrido."));
    }
}


function insertarEnBuscadorContribuciones(data, nodoPadre) {
    if (data.length !== 0) {
        prepararResultadosContribuciones(data, nodoPadre);
        let contenedorCardInicial, contenedorCardPrimero, imagenCientifico, contenedorCardSegundo, contenedorTituloTercero, contenedorTituloCuarto, contenedorTituloQuinto, contenedorCardUltimo, contenedorCardUltimo11, contenedorCardUltimo12, contenedorCardUltimo13, contenedorCardUltimo14, contador = 0;
        for (objeto of data) {
            contador++;
            contenedorCardInicial = document.createElement("div");
            if (data.length !== 1) {
                contenedorCardInicial.setAttribute("class", "col-lg-4 mb-5");
            }
            contenedorCardPrimero = document.createElement("div");
            contenedorCardPrimero.setAttribute("class", "card h-100 shadow border-0");
            if (contador % 3 == 2) {
                contenedorCardPrimero.style.borderLeft = "4px solid #000";
                contenedorCardPrimero.style.borderRight = "4px solid #000";
            }
            imagenCientifico = document.createElement("img");
            if (objeto.contribucion.rama === "Álgebra") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesAlgebra.jpg");
            } else if (objeto.contribucion.rama === "Análisis") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesAnalisis.jpg");
            } else if (objeto.contribucion.rama === "Astrofísica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesAstrofisica.jpg");
            } else if (objeto.contribucion.rama === "Cálculo") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCalculo.jpg");
            } else if (objeto.contribucion.rama === "Cosmología") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCosmologia.jpg");
            } else if (objeto.contribucion.rama === "Dinámica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesDinamica.png");
            } else if (objeto.contribucion.rama === "Electromagnetismo") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesElectromagnetismo.jpg");
            } else if (objeto.contribucion.rama === "Estadística") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesEstadistica.jpg");
            } else if (objeto.contribucion.rama === "Física cuántica de campos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCampos.jpg");
            } else if (objeto.contribucion.rama === "Física de partículas") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesParticulas.jpg");
            } else if (objeto.contribucion.rama === "Física Estadística") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesEstadistica.png");
            } else if (objeto.contribucion.rama === "Genética") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesGenetica.jpg");
            } else if (objeto.contribucion.rama === "Geometría") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesGeometria.jpg");
            } else if (objeto.contribucion.rama === "Lógica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesLogica.jpg");
            } else if (objeto.contribucion.rama === "Mecánica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesMecanica.jpg");
            } else if (objeto.contribucion.rama === "Mecánica Clásica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesMecanicaClasica.jpg");
            } else if (objeto.contribucion.rama === "Mecánica Cuántica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCuantica.png");
            } else if (objeto.contribucion.rama === "Mecánica Cuántica Relativista") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCuanticaRelativista.jpg");
            } else if (objeto.contribucion.rama === "Mecánica Teórica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesMecanicaTeorica.jpg");
            } else if (objeto.contribucion.rama === "Óptica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesOptica.jpg");
            } else if (objeto.contribucion.rama === "Relatividad") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesRelatividad.jpg");
            } else if (objeto.contribucion.rama === "Sistemas dinámicos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesSistemas.jpg");
            } else if (objeto.contribucion.rama === "Teoría de conjuntos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesConjuntos.jpg");
            } else if (objeto.contribucion.rama === "Teoría de grupos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesGrupos.png");
            } else if (objeto.contribucion.rama === "Teoría de juegos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesJuegos.jpg");
            } else if (objeto.contribucion.rama === "Teoría de nudos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesNudos.jpg");
            } else if (objeto.contribucion.rama === "Teoría de Números") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesNumeros.jpg");
            } else if (objeto.contribucion.rama === "Teoría del caos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCaos.jpg");
            } else if (objeto.contribucion.rama === "Topología") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesTopologia.jpg");
            }
            imagenCientifico.setAttribute("class", "card-img-top");
            imagenCientifico.setAttribute("alt", "Imagen genérica de contribuciones");
            contenedorCardSegundo = document.createElement("div");
            contenedorCardSegundo.setAttribute("class", "card-body p-4");
            contenedorTituloTercero = document.createElement("div");
            contenedorTituloTercero.setAttribute("class", "blockquote-footer");
            contenedorTituloTercero.appendChild(document.createTextNode(objeto.contribucion.nombre));
            contenedorTituloCuarto = document.createElement("a");
            contenedorTituloCuarto.setAttribute("class", "link-dark");
            contenedorTituloCuarto.setAttribute("href", objeto.contribucion.enlaceMasInformacion);
            contenedorTituloQuinto = document.createElement("h5");
            contenedorTituloQuinto.setAttribute("class", "badge bg-dark bg-gradient rounded-pill mb-2");
            contenedorTituloQuinto.appendChild(document.createTextNode("Más información de la contribución..."))
            contenedorTituloCuarto.appendChild(contenedorTituloQuinto);
            contenedorCardSegundo.appendChild(contenedorTituloTercero);
            contenedorCardSegundo.appendChild(contenedorTituloCuarto);
            contenedorCardUltimo = document.createElement("div");
            contenedorCardUltimo.setAttribute("class", "card-footer p-1 pt-0 bg-transparent border-top-0");
            contenedorCardUltimo11 = document.createElement("div");
            contenedorCardUltimo11.setAttribute("class", "d-flex align-items-end justify-content-between");
            contenedorCardUltimo12 = document.createElement("div");
            contenedorCardUltimo12.setAttribute("class", "d-flex  flex-column align-items-center");
            contenedorCardUltimo13 = document.createElement("p");
            contenedorCardUltimo13.setAttribute("class", "fw-bold");
            contenedorCardUltimo13.appendChild(document.createTextNode("Campo: " + objeto.contribucion.campo + "."));
            contenedorCardUltimo14 = document.createElement("p");
            contenedorCardUltimo14.setAttribute("class", "fw-bold");
            contenedorCardUltimo14.appendChild(document.createTextNode("Rama: " + objeto.contribucion.rama + "."));
            contenedorCardUltimo12.appendChild(contenedorCardUltimo13);
            contenedorCardUltimo12.appendChild(contenedorCardUltimo14);
            contenedorCardUltimo11.appendChild(contenedorCardUltimo12);
            contenedorCardUltimo.appendChild(contenedorCardUltimo11);
            contenedorCardPrimero.appendChild(imagenCientifico);
            contenedorCardPrimero.appendChild(contenedorCardSegundo);
            contenedorCardPrimero.appendChild(contenedorCardUltimo);
            contenedorCardInicial.appendChild(contenedorCardPrimero)
            nodoPadre.appendChild(contenedorCardInicial);
        }
    } else if (data.length == 0) {
        document.getElementById("resultadoBusquedaContribuciones").style.display = "none";
        document.getElementById("mensajePerfilContribuciones").style.display = "flex";
        if (document.getElementById("nodoTextMensajeContribuciones").hasChildNodes()) {
            document.getElementById("nodoTextMensajeContribuciones").removeChild(document.getElementById("nodoTextMensajeContribuciones").firstChild);
        }
        document.getElementById("nodoTextMensajeContribuciones").appendChild(document.createTextNode("Todavía no tienes contribuciones favoritas :("));
    } else {
        document.getElementById("resultadoBusquedaContribuciones").style.display = "none";
        document.getElementById("mensajePerfilContribuciones").style.display = "flex";
        if (document.getElementById("nodoTextMensajeContribuciones").hasChildNodes()) {
            document.getElementById("nodoTextMensajeContribuciones").removeChild(document.getElementById("nodoTextMensajeContribuciones").firstChild);
        }
        document.getElementById("nodoTextMensajeContribuciones").appendChild(document.createTextNode("Algo inesperado ha ocurrido."));

    }
}
function prepararResultadosCientificos(data, nodoPadre) {
    document.getElementById("resultadoBusquedaCientificos").style.display = "block";
    document.getElementById("mensajePerfilCientificos").style.display = "none";
    if (document.getElementById("nodoTextMensajeCientificos").hasChildNodes()) {
        document.getElementById("nodoTextMensajeCientificos").removeChild(document.getElementById("nodoTextMensajeCientificos").firstChild);
    }
    document.getElementById("mensajePerfilCientificos").appendChild(document.createTextNode(""));
    if (document.getElementById("nodoTextMensajeCientificos").hasChildNodes()) {
        document.getElementById("nodoTextMensajeCientificos").removeChild(document.getElementById("resultadoBusquedaCientificos").firstChild);
    }
    if (data.length == 1) {
        document.getElementById("resultadosBusquedaCientificos").appendChild(document.createTextNode("Se ha obtenido: " + data.length + " resultado."));

    } else {
        document.getElementById("resultadosBusquedaCientificos").appendChild(document.createTextNode("Se han obtenido: " + data.length + " resultados."));
    }
    while (nodoPadre.hasChildNodes()) {
        nodoPadre.removeChild(nodoPadre.firstChild);
    }
}
function prepararResultadosContribuciones(data, nodoPadre) {
    document.getElementById("resultadoBusquedaContribuciones").style.display = "block";
    document.getElementById("mensajePerfilContribuciones").style.display = "none";
    if (document.getElementById("nodoTextMensajeContribuciones").hasChildNodes()) {
        document.getElementById("nodoTextMensajeContribuciones").removeChild(document.getElementById("nodoTextMensajeContribuciones").firstChild);
    }
    document.getElementById("mensajePerfilContribuciones").appendChild(document.createTextNode(""));
    if (document.getElementById("resultadoBusquedaContribuciones").hasChildNodes()) {
        document.getElementById("resultadoBusquedaContribuciones").removeChild(document.getElementById("resultadoBusquedaContribuciones").firstChild);
    }
    if (data.length == 1) {
        document.getElementById("resultadosBusquedaContribuciones").appendChild(document.createTextNode("Se ha obtenido: " + data.length + " resultado."));

    } else {
        document.getElementById("resultadosBusquedaContribuciones").appendChild(document.createTextNode("Se han obtenido: " + data.length + " resultados."));
    }
    while (nodoPadre.hasChildNodes()) {
        nodoPadre.removeChild(nodoPadre.firstChild);
    }
}
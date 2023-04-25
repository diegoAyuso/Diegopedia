window.addEventListener('load', () => {
    document.getElementById("btnBuscarRelacionesCientificoContribucion").addEventListener("click", () => { tratarDeBuscar(); });
    document.getElementById("btnClearRelacionesCientificoContribucion").addEventListener("click", () => {
        document.getElementById("campoBuscarRelacionesCientificoContribucion").value = "";
    });
    document.getElementById("btnBuscarRelacionesCientificoContribucion").click();
});


function tratarDeBuscar() {
    const nodoPadreRelacionesCientificoContribucion = document.getElementById("referenciaRelacionesCientificoContribucionBusqueda");
    generarEspera();
    if (document.getElementById("campoBuscarRelacionesCientificoContribucion").value === "") {
        service.getRelacionesCientificoContribucionAll().then(data => { insertarEnBuscador(data, nodoPadreRelacionesCientificoContribucion); });
        if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
            document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
        }
        document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando todas las relaciones entre científicos y contribuciones."));
    } else {
        if (tipoComparadorRelacionesCientificoContribucion.options[tipoComparadorRelacionesCientificoContribucion.selectedIndex].value === "nombreContribucion") {
            service.getAllRelacionesCientificoContribucionByNombreContribucion(document.getElementById("campoBuscarRelacionesCientificoContribucion").value).then(data => { insertarEnBuscador(data, nodoPadreRelacionesCientificoContribucion); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando relaciones entre científicos y contribuciones cuyo nombre de contribución contiene la cadena '" + document.getElementById("campoBuscarRelacionesCientificoContribucion").value + "'."));
        } else if (tipoComparadorRelacionesCientificoContribucion.options[tipoComparadorRelacionesCientificoContribucion.selectedIndex].value === "apellidosCientifico") {
            service.getAllRelacionesCientificoContribucionByApellidosCientifico(document.getElementById("campoBuscarRelacionesCientificoContribucion").value).then(data => { insertarEnBuscador(data, nodoPadreRelacionesCientificoContribucion); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando relaciones entre científicos y contribuciones cuyos apellidos de cíentifico contienen la cadena '" + document.getElementById("campoBuscarRelacionesCientificoContribucion").value + "'."));
        } else if (tipoComparadorRelacionesCientificoContribucion.options[tipoComparadorRelacionesCientificoContribucion.selectedIndex].value === "epoca") {
            service.getAllRelacionesCientificoContribucionByEpoca(document.getElementById("campoBuscarRelacionesCientificoContribucion").value).then(data => { insertarEnBuscador(data, nodoPadreRelacionesCientificoContribucion); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando relaciones entre científicos y contribuciones cuya época contiene la cadena '" + document.getElementById("campoBuscarRelacionesCientificoContribucion").value + "'."));
        } else if (tipoComparadorRelacionesCientificoContribucion.options[tipoComparadorRelacionesCientificoContribucion.selectedIndex].value === "aportacion") {
            service.getAllRelacionesCientificoContribucionByAportacion(document.getElementById("campoBuscarRelacionesCientificoContribucion").value).then(data => { insertarEnBuscador(data, nodoPadreRelacionesCientificoContribucion); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando relaciones entre científicos y contribuciones cuya aportación contiene la cadena '" + document.getElementById("campoBuscarRelacionesCientificoContribucion").value + "'."));
        }
    }
}
function insertarEnBuscador(data, nodoPadre) {
    if (data.length != 0) {
        prepararResultados(data, nodoPadre);
        let contenedorCardInicial, contenedorCardPrimero, imagenCientifico, contenedorCardSegundo, contenedorTituloTercero, contenedorTituloTercero2, contenedorTituloCuarto, contenedorTituloQuinto, contenedorTituloCuarto2, contenedorTituloQuinto2, contenedorCardUltimo, contenedorCardUltimo11, contenedorCardUltimo12, contenedorCardUltimo13, contenedorCardUltimo14, contador = 0;
        for (objeto of data) {
            contador++;
            contenedorCardInicial = document.createElement("div");
            contenedorCardInicial.setAttribute("class", "col-lg-4 mb-5");
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
            contenedorTituloTercero.appendChild(document.createTextNode("Para: " + objeto.contribucion.nombre));
            contenedorTituloTercero2 = document.createElement("div");
            contenedorTituloTercero2.setAttribute("class", "blockquote-footer");
            contenedorTituloTercero2.appendChild(document.createTextNode("De: " + objeto.cientifico.apellidos));
            contenedorTituloCuarto = document.createElement("a");
            contenedorTituloCuarto.setAttribute("class", "link-dark");
            contenedorTituloCuarto.setAttribute("href", objeto["cientifico"]["enlaceMasInformacion"]);
            contenedorTituloQuinto = document.createElement("h5");
            contenedorTituloQuinto.setAttribute("class", "badge bg-dark bg-gradient rounded-pill mb-2");
            contenedorTituloQuinto.appendChild(document.createTextNode("Más información del ciéntifico..."));
            contenedorTituloCuarto.appendChild(contenedorTituloQuinto);
            contenedorTituloCuarto2 = document.createElement("a");
            contenedorTituloCuarto2.setAttribute("class", "link-dark");
            contenedorTituloCuarto2.setAttribute("href", objeto["contribucion"]["enlaceMasInformacion"]);
            contenedorTituloQuinto2 = document.createElement("h5");
            contenedorTituloQuinto2.setAttribute("class", "badge bg-dark bg-gradient rounded-pill mb-2");
            contenedorTituloQuinto2.appendChild(document.createTextNode("Más información de la contribución..."));
            contenedorTituloCuarto2.appendChild(contenedorTituloQuinto2);
            contenedorCardSegundo.appendChild(contenedorTituloTercero);
            contenedorCardSegundo.appendChild(contenedorTituloTercero2);
            contenedorCardSegundo.appendChild(contenedorTituloCuarto);
            contenedorCardSegundo.appendChild(contenedorTituloCuarto2);
            contenedorCardUltimo = document.createElement("div");
            contenedorCardUltimo.setAttribute("class", "card-footer p-1 pt-0 bg-transparent border-top-0");
            contenedorCardUltimo11 = document.createElement("div");
            contenedorCardUltimo11.setAttribute("class", "d-flex align-items-end justify-content-between");
            contenedorCardUltimo12 = document.createElement("div");
            contenedorCardUltimo12.setAttribute("class", "d-flex  flex-column align-items-center");
            contenedorCardUltimo13 = document.createElement("p");
            contenedorCardUltimo13.setAttribute("class", "fw-bold");
            contenedorCardUltimo13.appendChild(document.createTextNode("Época: " + objeto.epoca));
            contenedorCardUltimo14 = document.createElement("p");
            contenedorCardUltimo14.setAttribute("class", "fw-bold");
            contenedorCardUltimo14.appendChild(document.createTextNode("Tipo de aportación: " + objeto.aportacion + "."));
            contenedorCardUltimo12.appendChild(contenedorCardUltimo13);
            contenedorCardUltimo12.appendChild(contenedorCardUltimo14);
            contenedorCardUltimo11.appendChild(contenedorCardUltimo12);
            contenedorCardUltimo.appendChild(contenedorCardUltimo11);
            contenedorCardPrimero.appendChild(imagenCientifico);
            contenedorCardPrimero.appendChild(contenedorCardSegundo);
            contenedorCardPrimero.appendChild(contenedorCardUltimo);
            contenedorCardInicial.appendChild(contenedorCardPrimero);
            nodoPadre.appendChild(contenedorCardInicial);
        }
    } else if (data.length == 0) {
        document.getElementById("resultadoBusqueda").style.display = "none";
        document.getElementById("mensaje").style.display = "block";
        if (document.getElementById("nodoTextMensaje").hasChildNodes()) {
            document.getElementById("nodoTextMensaje").removeChild(document.getElementById("nodoTextMensaje").firstChild);
        }
        document.getElementById("nodoTextMensaje").appendChild(document.createTextNode("No se han encontrado relaciones entre científicos y contribuciones con estas características."));
    } else {
        document.getElementById("resultadoBusqueda").style.display = "none";
        document.getElementById("mensaje").style.display = "block";
        if (document.getElementById("nodoTextMensaje").hasChildNodes()) {
            document.getElementById("nodoTextMensaje").removeChild(document.getElementById("nodoTextMensaje").firstChild);
        }
        document.getElementById("nodoTextMensaje").appendChild(document.createTextNode("Algo inesperado ha ocurrido."));
    }
    eliminarAnterioresResultados();
}

function prepararResultados(data, nodoPadre) {
    document.getElementById("resultadoBusqueda").style.display = "block";
    document.getElementById("mensaje").style.display = "none";
    if (document.getElementById("nodoTextMensaje").hasChildNodes()) {
        document.getElementById("nodoTextMensaje").removeChild(document.getElementById("nodoTextMensaje").firstChild);
    }
    document.getElementById("mensaje").appendChild(document.createTextNode(""));
    if (document.getElementById("resultadosBusqueda").hasChildNodes()) {
        document.getElementById("resultadosBusqueda").removeChild(document.getElementById("resultadosBusqueda").firstChild);
    }
    if (data.length == 1) {
        document.getElementById("resultadosBusqueda").appendChild(document.createTextNode("Se ha obtenido: " + data.length + " resultado."));

    } else {
        document.getElementById("resultadosBusqueda").appendChild(document.createTextNode("Se han obtenido: " + data.length + " resultados."));
    }
    while (nodoPadre.hasChildNodes()) {
        nodoPadre.removeChild(nodoPadre.firstChild);
    }
}

const generarEspera = () => {
    eliminarAnterioresResultados();
    const articuloResultado = document.createElement('article');
    articuloResultado.setAttribute('class', 'articulo');
    articuloResultado.setAttribute('id', 'articuloResultado');
    const parrafo = document.createElement("p");
    parrafo.setAttribute("class", "blockquote-footer");
    parrafo.appendChild(document.createTextNode("Revisando base de datos"));
    articuloResultado.appendChild(parrafo);
    const elementoEspera = document.createElement('div');
    elementoEspera.setAttribute('id', 'loading');
    const elemento1 = document.createElement('div');
    elemento1.setAttribute('class', 'line');
    const elemento2 = document.createElement('div');
    elemento2.setAttribute('class', 'line');
    const elemento3 = document.createElement('div');
    elemento3.setAttribute('class', 'line');
    elementoEspera.appendChild(elemento1);
    elementoEspera.appendChild(elemento2);
    elementoEspera.appendChild(elemento3);

    articuloResultado.appendChild(elementoEspera);
    document.getElementById('espera').appendChild(articuloResultado);
}

const eliminarAnterioresResultados = () => {
    if (document.getElementById('articuloResultado')) {
        document.getElementById('espera').removeChild(document.getElementById('articuloResultado'));
        sessionStorage.removeItem('pokeActual');
    }
}
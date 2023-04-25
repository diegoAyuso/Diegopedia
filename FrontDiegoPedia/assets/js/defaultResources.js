window.addEventListener('load', () => {
    const glightbox = GLightbox({
        selector: '.glightbox'
    });
    insertarRegistrosBuscadorSencillo();
});

function insertarRegistrosBuscadorSencillo() {
    const nodoCientificosGeneralesBuscadorSencillo = document.getElementById("nodoCientificosGeneralesBuscadorSencillo");
    const nodoCientificosGeometrasBuscadorSencillo = document.getElementById("nodoCientificosGeometrasBuscadorSencillo");
    const nodoCientificosFisicosBuscadorSencillo = document.getElementById("nodoCientificosFisicosBuscadorSencillo");
    const nodoCientificosAnalistasBuscadorSencillo = document.getElementById("nodoCientificosAnalistasBuscadorSencillo");
    const nodoCientificosTeoricosBuscadorSencillo = document.getElementById("nodoCientificosTeoricosBuscadorSencillo");
    service.getCientificosAll().then(data => { insertarEnBuscadorSencillo(data, nodoCientificosGeneralesBuscadorSencillo); });
    service.getCientificosByRamaContribucion("Geometria").then(data => { insertarEnBuscadorSencillo(data, nodoCientificosGeometrasBuscadorSencillo); });
    service.getCientificosByRamaContribucion("Cosmologia").then(data => { insertarEnBuscadorSencillo(data, nodoCientificosFisicosBuscadorSencillo); });
    service.getCientificosByRamaContribucion("Analisis").then(data => { insertarEnBuscadorSencillo(data, nodoCientificosAnalistasBuscadorSencillo); });
    service.getCientificosByRamaContribucion("Mecanica").then(data => { insertarEnBuscadorSencillo(data, nodoCientificosTeoricosBuscadorSencillo); });

}
function insertarEnBuscadorSencillo(data, nodoReferenciaPadre) {
    if (data.lenght != 0) {
        let contenedorCientificoBuscadorSencillo, enlaceGlightbox, imagenCientifico, tituloCientifico, contenedorEnlaceCientifico, enlaceCientifico, nacionalidadCientifico, contador = 0;
        for (objeto of data) {
            contador++;
            contenedorCientificoBuscadorSencillo = document.createElement("div");
            contenedorCientificoBuscadorSencillo.setAttribute("class", "col-lg-4 contenedorCientificoBuscadorSencillo-item");

            if (contador % 3 == 2) {
                contenedorCientificoBuscadorSencillo.style.borderLeft = "4px solid #000";
                contenedorCientificoBuscadorSencillo.style.borderRight = "4px solid #000";
            }
            enlaceGlightbox = document.createElement("a");
            enlaceGlightbox.setAttribute("href", objeto.enlaceFoto);
            enlaceGlightbox.setAttribute("class", "glightbox");
            imagenCientifico = document.createElement("img");
            imagenCientifico.setAttribute("src", objeto.enlaceFoto);
            imagenCientifico.setAttribute("class", "contenedorCientificoBuscadorSencillo-img img-fluid");
            imagenCientifico.setAttribute("alt", objeto.apellidos);
            tituloCientifico = document.createElement("h4");
            tituloCientifico.appendChild(document.createTextNode(objeto.nombre + " " + objeto.apellidos));
            contenedorEnlaceCientifico = document.createElement("p");
            contenedorEnlaceCientifico.setAttribute("class", "contenedorCientificoBuscadorSencillo-Enlace");
            enlaceCientifico = document.createElement("a");
            enlaceCientifico.setAttribute("href", objeto.enlaceMasInformacion);
            enlaceCientifico.appendChild(document.createTextNode("Más información..."));
            contenedorEnlaceCientifico.appendChild(enlaceCientifico);
            nacionalidadCientifico = document.createElement("p");
            nacionalidadCientifico.setAttribute("class", "contenedorCientificoBuscadorSencillo-Nacionalidad");
            nacionalidadCientifico.appendChild(document.createElement("a").appendChild(document.createTextNode(objeto.nacionalidad)));
            contenedorCientificoBuscadorSencillo.appendChild(enlaceGlightbox);
            contenedorCientificoBuscadorSencillo.appendChild(imagenCientifico);
            contenedorCientificoBuscadorSencillo.appendChild(tituloCientifico);
            contenedorCientificoBuscadorSencillo.appendChild(enlaceCientifico);
            contenedorCientificoBuscadorSencillo.appendChild(nacionalidadCientifico);
            nodoReferenciaPadre.appendChild(contenedorCientificoBuscadorSencillo);
        }
    }
}
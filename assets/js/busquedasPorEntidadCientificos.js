window.addEventListener('load', () => {
    document.getElementById("btnBuscarCientificos").addEventListener("click", () => { tratarDeBuscar(); });
    document.getElementById("btnClearCientificos").addEventListener("click", () => {
        document.getElementById("campoBuscarCientificos").value = "";
    });
    document.getElementById("btnBuscarCientificos").click();
});

function tratarDeBuscar() {
    const nodoPadreCientificos = document.getElementById("referenciaCientificosBusqueda");
    if (document.getElementById("campoBuscarCientificos").value === "") {
        service.getCientificosAll().then(data => { insertarEnBuscador(data, nodoPadreCientificos); });
        if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
            document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
        }
        document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando todos los científicos."));

    } else {
        if (tipoComparadorCientificos.options[tipoComparadorCientificos.selectedIndex].value === "nombre") {
            service.getAllCientificosByNombre(document.getElementById("campoBuscarCientificos").value).then(data => { insertarEnBuscador(data, nodoPadreCientificos); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando científicos cuyo nombre contiene la cadena '" + document.getElementById("campoBuscarCientificos").value + "'."));
        } else if (tipoComparadorCientificos.options[tipoComparadorCientificos.selectedIndex].value === "apellidos") {
            service.getAllCientificosByApellidos(document.getElementById("campoBuscarCientificos").value).then(data => { insertarEnBuscador(data, nodoPadreCientificos); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando científicos cuyos apellidos contienen la cadena '" + document.getElementById("campoBuscarCientificos").value + "'."));
        } else if (tipoComparadorCientificos.options[tipoComparadorCientificos.selectedIndex].value === "nacionalidad") {
            service.getAllCientificosByNacionalidad(document.getElementById("campoBuscarCientificos").value).then(data => { insertarEnBuscador(data, nodoPadreCientificos); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando científicos cuya nacionalidad contiene la cadena '" + document.getElementById("campoBuscarCientificos").value + "'."));
        } else if (tipoComparadorCientificos.options[tipoComparadorCientificos.selectedIndex].value === "fechaNacimiento") {
            service.getAllCientificosByNacimiento(document.getElementById("campoBuscarCientificos").value).then(data => { insertarEnBuscador(data, nodoPadreCientificos); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando científicos cuya fecha de nacimiento contiene la cadena '" + document.getElementById("campoBuscarCientificos").value + "'."));
        } else if (tipoComparadorCientificos.options[tipoComparadorCientificos.selectedIndex].value === "fechaDefuncion") {
            service.getAllCientificosByDefuncion(document.getElementById("campoBuscarCientificos").value).then(data => { insertarEnBuscador(data, nodoPadreCientificos); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando científicos cuya fecha de defunción contiene la cadena '" + document.getElementById("campoBuscarCientificos").value + "'."));
        }
    }
}
async function insertarEnBuscador(data, nodoPadre) {
    if (data.length != 0) {
        prepararResultados(data, nodoPadre);
        let contenedorCardInicial, contenedorCardPrimero, imagenCientifico, contenedorCardSegundo, contenedorTituloTercero, contenedorTituloCuarto, contenedorTituloQuinto, contenedorCardUltimo, contenedorCardUltimo11, contenedorCardUltimo12, contenedorCardUltimo13, contenedorCardUltimo14, contenedorCardUltimo15, contador = 0;
        for (objeto of data) {
            contador++;
            contenedorCardInicial = document.createElement("div");
            contenedorCardInicial.setAttribute("class", "col-lg-4 mb-5");
            if (sessionStorage.getItem("nombreUsuario") !== null) {
                let enlaceEstrella = document.createElement("button");
                let iEstrella = document.createElement("i");
                iEstrella.setAttribute("class", "bi bi-star-fill");
                enlaceEstrella.classList.toggle("mostrarFav");
                let resultado = await service.getAllRelacionesUsuarioCientificoByNombreUsuario(sessionStorage.getItem("nombreUsuario")).then(relaciones => {
                    let idCientificosFavoritos = relaciones.map(relacion => relacion.cientifico.id.toString());
                    if (idCientificosFavoritos.length !== 0) {
                        if (idCientificosFavoritos.includes(objeto.id.toString())) {
                            iEstrella.classList.toggle("favoritoSi");
                        } else {
                            iEstrella.classList.toggle("favoritoNo");
                        }
                    } else {
                        iEstrella.classList.toggle("favoritoNo");
                    }
                });
                enlaceEstrella.appendChild(iEstrella);
                (function (objeto) {
                    enlaceEstrella.addEventListener("click", function () {
                        if (iEstrella.classList.contains("favoritoNo")) {
                            iEstrella.classList.toggle("favoritoNo");
                            iEstrella.classList.toggle("favoritoSi");
                            service.getAllUsuariosByNombreFull(sessionStorage.getItem("nombreUsuario")).then(usuarios => {
                                service.addUsuarioCientifico({ id: 0, usuario: usuarios[0], cientifico: objeto }).then(data => { data });
                            });
                        } else {
                            iEstrella.classList.toggle("favoritoSi");
                            iEstrella.classList.toggle("favoritoNo");
                            service.getAllRelacionesUsuarioCientificoByNombreUsuario(sessionStorage.getItem("nombreUsuario")).then(relaciones => {
                                service.deleteUsuarioCientifico(relaciones.filter(elemento => objeto.id === elemento.cientifico.id)[0].id).then(data => { data });
                            });
                        }
                    });
                })(objeto);
                contenedorCardInicial.appendChild(enlaceEstrella);
            }
            contenedorCardPrimero = document.createElement("div");
            contenedorCardPrimero.setAttribute("class", "card h-100 shadow border-0");
            if (contador % 3 == 2) {
                contenedorCardPrimero.style.borderLeft = "4px solid #000";
                contenedorCardPrimero.style.borderRight = "4px solid #000";
            }
            imagenCientifico = document.createElement("img");
            imagenCientifico.setAttribute("src", objeto.enlaceFoto);
            imagenCientifico.setAttribute("class", "card-img-top");
            imagenCientifico.setAttribute("alt", objeto.apellidos);
            contenedorCardSegundo = document.createElement("div");
            contenedorCardSegundo.setAttribute("class", "card-body p-4");
            contenedorTituloTercero = document.createElement("div");
            contenedorTituloTercero.setAttribute("class", "blockquote-footer");
            contenedorTituloTercero.appendChild(document.createTextNode(objeto.apellidos + ", " + objeto.nombre));
            contenedorTituloCuarto = document.createElement("a");
            contenedorTituloCuarto.setAttribute("class", "link-dark");
            contenedorTituloCuarto.setAttribute("href", objeto.enlaceMasInformacion);
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
            contenedorCardUltimo13.appendChild(document.createTextNode("Fecha nacimiento: " + objeto.fechaNacimiento));
            contenedorCardUltimo14 = document.createElement("p");
            contenedorCardUltimo14.setAttribute("class", "fw-bold");
            if (objeto.fechaDefuncion === null) {
                contenedorCardUltimo14.appendChild(document.createTextNode("Fecha defuncion: vivo."));

            } else {
                contenedorCardUltimo14.appendChild(document.createTextNode("Fecha defuncion: " + objeto.fechaDefuncion + "."));
            }
            contenedorCardUltimo15 = document.createElement("p");
            contenedorCardUltimo15.setAttribute("class", "fw-bold");
            contenedorCardUltimo15.appendChild(document.createTextNode("Nacionalidad: " + objeto.nacionalidad + "."));
            contenedorCardUltimo12.appendChild(contenedorCardUltimo13);
            contenedorCardUltimo12.appendChild(contenedorCardUltimo14);
            contenedorCardUltimo12.appendChild(contenedorCardUltimo15);
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
        document.getElementById("nodoTextMensaje").appendChild(document.createTextNode("No se han encontrado científicos con estas características."));
    } else {
        document.getElementById("resultadoBusqueda").style.display = "none";
        document.getElementById("mensaje").style.display = "block";
        if (document.getElementById("nodoTextMensaje").hasChildNodes()) {
            document.getElementById("nodoTextMensaje").removeChild(document.getElementById("nodoTextMensaje").firstChild);
        }
        document.getElementById("nodoTextMensaje").appendChild(document.createTextNode("Algo inesperado ha ocurrido."));
    }
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
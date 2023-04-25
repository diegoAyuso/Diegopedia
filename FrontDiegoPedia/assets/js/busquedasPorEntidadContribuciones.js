window.addEventListener('load', () => {
    document.getElementById("btnBuscarContribuciones").addEventListener("click", () => { tratarDeBuscar(); });
    document.getElementById("btnClearContribuciones").addEventListener("click", () => {
        document.getElementById("campoBuscarContribuciones").value = "";
    });
    document.getElementById("btnBuscarContribuciones").click();
});

function tratarDeBuscar(event) {
    const nodoPadreContribuciones = document.getElementById("referenciaContribucionesBusqueda");
    if (document.getElementById("campoBuscarContribuciones").value === "") {
        service.getContribucionesAll().then(data => { insertarEnBuscador(data, nodoPadreContribuciones); });
        if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
            document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
        }
        document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando todas las contribuciones."));
    } else {
        if (tipoComparadorContribuciones.options[tipoComparadorContribuciones.selectedIndex].value === "nombre") {
            service.getAllContribucionesByNombre(document.getElementById("campoBuscarContribuciones").value).then(data => { insertarEnBuscador(data, nodoPadreContribuciones); });
            console.log(document.getElementById("especificacionesBusqueda").hasChildNodes());
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando contribuciones cuyo nombre contiene la cadena '" + document.getElementById("campoBuscarContribuciones").value + "'."));
        } else if (tipoComparadorContribuciones.options[tipoComparadorContribuciones.selectedIndex].value === "campo") {
            service.getAllContribucionesByCampo(document.getElementById("campoBuscarContribuciones").value).then(data => { insertarEnBuscador(data, nodoPadreContribuciones); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando contribuciones cuyo campo contiene la cadena '" + document.getElementById("campoBuscarContribuciones").value + "'."));
        } else if (tipoComparadorContribuciones.options[tipoComparadorContribuciones.selectedIndex].value === "rama") {
            service.getAllContribucionesByRama(document.getElementById("campoBuscarContribuciones").value).then(data => { insertarEnBuscador(data, nodoPadreContribuciones); });
            if (document.getElementById("especificacionesBusqueda").hasChildNodes()) {
                document.getElementById("especificacionesBusqueda").removeChild(document.getElementById("especificacionesBusqueda").firstChild);
            }
            document.getElementById("especificacionesBusqueda").appendChild(document.createTextNode("Mostrando contribuciones cuya rama contiene la cadena '" + document.getElementById("campoBuscarContribuciones").value + "'."));
        }
    }

}
async function insertarEnBuscador(data, nodoPadre) {
    if (data.length != 0) {
        prepararResultados(data, nodoPadre);
        let contenedorCardInicial, contenedorCardPrimero, imagenCientifico, contenedorCardSegundo, contenedorTituloTercero, contenedorTituloCuarto, contenedorTituloQuinto, contenedorCardUltimo, contenedorCardUltimo11, contenedorCardUltimo12, contenedorCardUltimo13, contenedorCardUltimo14, contador = 0;
        for (objeto of data) {
            contador++;
            contenedorCardInicial = document.createElement("div");
            contenedorCardInicial.setAttribute("class", "col-lg-4 mb-5");
            if (sessionStorage.getItem("nombreUsuario") !== null) {
                let enlaceEstrella = document.createElement("button");
                let iEstrella = document.createElement("i");
                iEstrella.setAttribute("class", "bi bi-star-fill");
                enlaceEstrella.classList.toggle("mostrarFav");
                let resultado = await service.getAllRelacionesUsuarioContribucionByNombreUsuario(sessionStorage.getItem("nombreUsuario")).then(relaciones => {
                    let idContribucionesFavoritas = relaciones.map(relacion => relacion.contribucion.id.toString());
                    if (idContribucionesFavoritas.length !== 0) {
                        if (idContribucionesFavoritas.includes(objeto.id.toString())) {
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
                                service.addUsuarioContribucion({ id: 0, usuario: usuarios[0], contribucion: objeto }).then(data => { data });
                            });
                        } else {
                            iEstrella.classList.toggle("favoritoSi");
                            iEstrella.classList.toggle("favoritoNo");
                            service.getAllRelacionesUsuarioContribucionByNombreUsuario(sessionStorage.getItem("nombreUsuario")).then(relaciones => {
                                service.deleteUsuarioContribucion(relaciones.filter(elemento => objeto.id === elemento.contribucion.id)[0].id).then(data => { data });
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
            if (objeto.rama === "Álgebra") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesAlgebra.jpg");
            } else if (objeto.rama === "Análisis") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesAnalisis.jpg");
            } else if (objeto.rama === "Astrofísica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesAstrofisica.jpg");
            } else if (objeto.rama === "Cálculo") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCalculo.jpg");
            } else if (objeto.rama === "Cosmología") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCosmologia.jpg");
            } else if (objeto.rama === "Dinámica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesDinamica.png");
            } else if (objeto.rama === "Electromagnetismo") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesElectromagnetismo.jpg");
            } else if (objeto.rama === "Estadística") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesEstadistica.jpg");
            } else if (objeto.rama === "Física cuántica de campos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCampos.jpg");
            } else if (objeto.rama === "Física de partículas") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesParticulas.jpg");
            } else if (objeto.rama === "Física Estadística") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesEstadistica.png");
            } else if (objeto.rama === "Genética") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesGenetica.jpg");
            } else if (objeto.rama === "Geometría") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesGeometria.jpg");
            } else if (objeto.rama === "Lógica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesLogica.jpg");
            } else if (objeto.rama === "Mecánica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesMecanica.jpg");
            } else if (objeto.rama === "Mecánica Clásica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesMecanicaClasica.jpg");
            } else if (objeto.rama === "Mecánica Cuántica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCuantica.png");
            } else if (objeto.rama === "Mecánica Cuántica Relativista") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCuanticaRelativista.jpg");
            } else if (objeto.rama === "Mecánica Teórica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesMecanicaTeorica.jpg");
            } else if (objeto.rama === "Óptica") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesOptica.jpg");
            } else if (objeto.rama === "Relatividad") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesRelatividad.jpg");
            } else if (objeto.rama === "Sistemas dinámicos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesSistemas.jpg");
            } else if (objeto.rama === "Teoría de conjuntos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesConjuntos.jpg");
            } else if (objeto.rama === "Teoría de grupos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesGrupos.png");
            } else if (objeto.rama === "Teoría de juegos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesJuegos.jpg");
            } else if (objeto.rama === "Teoría de nudos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesNudos.jpg");
            } else if (objeto.rama === "Teoría de Números") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesNumeros.jpg");
            } else if (objeto.rama === "Teoría del caos") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesCaos.jpg");
            } else if (objeto.rama === "Topología") {
                imagenCientifico.setAttribute("src", "assets/ims/contribucionesTopologia.jpg");
            }
            imagenCientifico.setAttribute("class", "card-img-top");
            imagenCientifico.setAttribute("alt", "Imagen genérica de contribuciones");
            contenedorCardSegundo = document.createElement("div");
            contenedorCardSegundo.setAttribute("class", "card-body p-4");
            contenedorTituloTercero = document.createElement("div");
            contenedorTituloTercero.setAttribute("class", "blockquote-footer");
            contenedorTituloTercero.appendChild(document.createTextNode(objeto.nombre));
            contenedorTituloCuarto = document.createElement("a");
            contenedorTituloCuarto.setAttribute("class", "link-dark");
            contenedorTituloCuarto.setAttribute("href", objeto.enlaceMasInformacion);
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
            contenedorCardUltimo13.appendChild(document.createTextNode("Campo: " + objeto.campo + "."));
            contenedorCardUltimo14 = document.createElement("p");
            contenedorCardUltimo14.setAttribute("class", "fw-bold");
            contenedorCardUltimo14.appendChild(document.createTextNode("Rama: " + objeto.rama + "."));
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
        document.getElementById("nodoTextMensaje").appendChild(document.createTextNode("No se han encontrado contribuciones con estas características."));
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
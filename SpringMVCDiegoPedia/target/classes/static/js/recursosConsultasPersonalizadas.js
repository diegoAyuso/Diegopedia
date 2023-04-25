let campoSeleccionado = "";
window.addEventListener("load", () => {
    document.getElementById("mensajeInfo").style.display = "none";
    if (document.getElementById("tipoDeRegistrosObtenidos") !== null) {
        reordenarFormulario();
        const selectTablaRegistrosListener1 = document.getElementById("tipoDeRegistrosObtenidos").addEventListener("change", () => {
            reordenarFormulario()
        });
        const botonEnviarFormularioBusqueda = document.getElementById("botonBusqueda").addEventListener("click", () => {
            document.getElementById("mensajeInfo").style.display = "none";
        });

        const selectTablaRegistrosListener2 = document.getElementById("tipoDeRegistrosObtenidos").addEventListener("change", () => {
            mostrarInformacion()
        });
        const inputCampoBuscar = document.getElementById("campoBuscar").addEventListener("change", () => {
            mostrarInformacion()
        });
        const tipoComparadorCamposListener1 = document.getElementById("tipoComparadorCampos").addEventListener("change", () => {
            mostrarInformacion()
        });
        const tipoComparadorCamposListener2 = document.getElementById("tipoComparadorCampos").addEventListener("change", () => {
            reordenarFormulario()
        });
    }
});

function obtenerParametro() {
    const callingURL = document.URL;
    const cgiString = callingURL.substring(callingURL.indexOf('?') + 1, callingURL.length);
    if (cgiString.indexOf('#') != -1) {
        cgiString = cgiString.slice(0, cgiString.indexOf('#'));
    }
    const arrayParams = cgiString.split('&');
    let valor = "";
    for (let i = 0; i < arrayParams.length; i++) {
        if (arrayParams[i].substring(0, arrayParams[i].indexOf('=')) === "tipoComparadorCampos") {
            valor = arrayParams[i].substring(arrayParams[i].indexOf('=') + 1, arrayParams[i].length);
        }
    }
    return valor;
}

function obtenerCampoSeleccionado() {
    return document.getElementById("tipoComparadorCampos").value;
}

const mostrarInformacion = () => {
    if (document.getElementById("campoBuscar").value !== "") {
        document.getElementById("mensajeInfo").style.display = "block";
        let mensaje = "";
        if (document.getElementById("tipoDeRegistrosObtenidos").options[0].value == "contribuciones") {
            //campo ajeno
            if (document.getElementById("tipoDeRegistrosObtenidos").value == "cientificos") {
                //como es campo ajeno se invierten los registros devueltos
                mensaje += "Se buscarán todas aquellas contribuciones relacionadas tales que";
                mensaje += " hayan sido realizadas por los científicos que tengan incluido en su campo '" + document.getElementById("tipoComparadorCampos").value + "' la coincidencia '" + document.getElementById("campoBuscar").value + "'.";

            } else if (document.getElementById("tipoDeRegistrosObtenidos").value == "contribuciones") {
                //como es campo ajeno se invierten los registros devueltos
                mensaje += "Se buscarán todos aquellos cientificos relacionados tales que";
                mensaje += " hayan contribuido en las contribuciones que tengan incluido en su campo '" + document.getElementById("tipoComparadorCampos").value + "' la coincidencia '" + document.getElementById("campoBuscar").value + "'.";
            }
        } else if (document.getElementById("tipoDeRegistrosObtenidos").options[0].value == "cientificos") {
            //campo propio
            if (document.getElementById("tipoDeRegistrosObtenidos").value == "cientificos") {
                //como es campo propio valor y registros coinciden
                mensaje += "Se buscarán todos aquellos cientificos relacionados tales que";
            } else if (document.getElementById("tipoDeRegistrosObtenidos").value == "contribuciones") {
                //como es campo propio valor y registros coinciden
                mensaje += "Se buscarán todas aquellas contribuciones relacionadas tales que";
            }
            mensaje += " su campo '" + document.getElementById("tipoComparadorCampos").value + "' contenga la coincidencia '" + document.getElementById("campoBuscar").value + "'.";
        }
        document.getElementById("mensajeInfo").innerHTML = mensaje;
    }
};
const reordenarFormulario = () => {
    const selectTablaRegistros = document.getElementById("tipoDeRegistrosObtenidos");
    const selectTablaCampos = document.getElementById("tipoComparadorCampos");
    campoSeleccionado = obtenerCampoSeleccionado();
    while (selectTablaCampos.firstChild) {
        selectTablaCampos.removeChild(selectTablaCampos.firstChild);
    }
    if (selectTablaRegistros.value === "cientificos") {
        //Campo propio devolviendo cientificos
        adjuntarOpcionesCientificos();
    } else if (selectTablaRegistros.value === "contribuciones") {
        //Campo propio devolviendo contribuciones
        adjuntarOpcionesContribuciones();
    }
};

function adjuntarOpcionesCientificos() {
    const selectTablaCampos = document.getElementById("tipoComparadorCampos");
    let camposCientificos = ["nombre", "apellidos", "nacionalidad", "fechaNacimiento", "fechaDefuncion", "enlaceMasInformacion", "enlaceFoto"];
    let opcion;
    for (let i = 0; i < camposCientificos.length; i++) {
        opcion = document.createElement("option");
        opcion.setAttribute("value", camposCientificos[i]);
        if (campoSeleccionado === "") {
            if (obtenerParametro() === camposCientificos[i]) {
                opcion.setAttribute("selected", "");
            }
        } else if (campoSeleccionado === camposCientificos[i]) {
            opcion.setAttribute("selected", "");
        }
        switch (i) {
            case 0:
                opcion.appendChild(document.createTextNode("Nombre"));
                break;
            case 1:
                opcion.appendChild(document.createTextNode("Apellidos"));
                break;
            case 2:
                opcion.appendChild(document.createTextNode("Nacionalidad"));
                break;
            case 3:
                opcion.appendChild(document.createTextNode("Fecha de nacimiento"));
                break;
            case 4:
                opcion.appendChild(document.createTextNode("Fecha de defunción"));
                break;
            case 5:
                opcion.appendChild(document.createTextNode("Enlace para más información"));
                break;
            case 6:
                opcion.appendChild(document.createTextNode("Enlace de foto"));
                break;
        }

        selectTablaCampos.appendChild(opcion);
    }
};

function adjuntarOpcionesContribuciones() {
    const selectTablaCampos = document.getElementById("tipoComparadorCampos");
    let camposContribuciones = ["nombre", "campo", "rama", "enlaceMasInformacion"];
    let opcion;
    for (let i = 0; i < camposContribuciones.length; i++) {
        opcion = document.createElement("option");
        opcion.setAttribute("value", camposContribuciones[i]);
        if (campoSeleccionado === "") {
            if (obtenerParametro() === camposContribuciones[i]) {
                opcion.setAttribute("selected", "");
            }
        } else if (campoSeleccionado === camposContribuciones[i]) {
            opcion.setAttribute("selected", "");
        }

        switch (i) {
            case 0:
                opcion.appendChild(document.createTextNode("Nombre"));
                break;
            case 1:
                opcion.appendChild(document.createTextNode("Campo"));
                break;
            case 2:
                opcion.appendChild(document.createTextNode("Rama"));
                break;
            case 3:
                opcion.appendChild(document.createTextNode("Enlace para más información"));
                break;
        }
        selectTablaCampos.appendChild(opcion);
    }
};
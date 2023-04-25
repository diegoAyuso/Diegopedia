window.addEventListener('load', () => {
    document.getElementById("eliminarCuenta").addEventListener("click", () => {
        document.getElementById("sectionEliminarCuenta").style.display = "flex";
    });
    document.getElementById("mostrarPE").addEventListener("click", function () {
        var tipo = document.getElementById("passwordEliminar");
        var iconoP = document.getElementById("iconoPE");
        if (tipo.type == "password") {
            tipo.type = "text";
            iconoP.setAttribute("class", "bi bi-eye-fill")
        } else {
            tipo.type = "password";
            iconoP.setAttribute("class", "bi bi-eye-slash-fill")
        }
    });
    document.getElementById("eliminar").addEventListener("click", () => {
        const contenedorErrores = document.getElementById("contenedorErroresEliminar");
        const errores = document.getElementById("erroresEliminar");
        if (document.getElementById("usuarioEliminar").validity.valid && document.getElementById("passwordEliminar").validity.valid) {
            if (document.getElementById("usuarioEliminar").value === sessionStorage.getItem("nombreUsuario")) {
                service.getAllUsuariosByNombreFull(document.getElementById("usuarioEliminar").value).then(data => {
                    if (data[0].password === document.getElementById("passwordEliminar").value) {
                        contenedorErrores.style.display = "none";
                        document.getElementById("modal3").style.display = "flex";
                    } else {
                        contenedorErrores.style.display = "flex";
                        while (errores.hasChildNodes()) {
                            errores.removeChild(errores.firstChild);
                        }
                        let parrafo;
                        parrafo = document.createElement("p");
                        parrafo.appendChild(document.createTextNode("Se han encontrado los siguientes errores:"));
                        errores.appendChild(parrafo);
                        parrafo = document.createElement("p");
                        parrafo.appendChild(document.createTextNode("-Credenciales incorrectas."));
                        errores.appendChild(parrafo);

                    }
                });
            } else {
                contenedorErrores.style.display = "flex";
                while (errores.hasChildNodes()) {
                    errores.removeChild(errores.firstChild);
                }
                let parrafo;
                parrafo = document.createElement("p");
                parrafo.appendChild(document.createTextNode("Se han encontrado los siguientes errores:"));
                errores.appendChild(parrafo);
                parrafo = document.createElement("p");
                parrafo.appendChild(document.createTextNode("-Credenciales incorrectas."));
                errores.appendChild(parrafo);
            }

        } else {
            contenedorErrores.style.display = "flex";
            while (errores.hasChildNodes()) {
                errores.removeChild(errores.firstChild);
            }
            let parrafo;
            parrafo = document.createElement("p");
            parrafo.appendChild(document.createTextNode("Se han encontrado los siguientes errores:"));
            errores.appendChild(parrafo);
            if (document.getElementById("usuarioEliminar").value === "") {
                parrafo = document.createElement("p");
                parrafo.appendChild(document.createTextNode("-El usuario no puede estar vacío."));
                errores.appendChild(parrafo);
            }
            if (document.getElementById("passwordEliminar").value === "") {
                parrafo = document.createElement("p");
                errores.appendChild(document.createTextNode("-El campo de contraseña no puede estar vacío."));
                errores.appendChild(parrafo);
            }
        }
    });
    document.getElementById("eliminarDeVerdad").addEventListener("click", () => {
        document.getElementById("modal3").style.display = "none";
        document.getElementById("modal4").style.display = "flex";
        service.getAllUsuariosByNombreFull(sessionStorage.getItem("nombreUsuario")).then(data => {
            service.removeUsuario(data[0].id).then(dataDel => dataDel);
        });
        sessionStorage.removeItem("nombreUsuario");
        devolverNavegador();
    });
    document.getElementById("eliminarAbortar").addEventListener("click", () => {
        document.getElementById("modal3").style.display = "none";
    });
    document.getElementById("aceptarYaEliminado").addEventListener("click", () => {
        document.getElementById("modal4").style.display = "none";
    });
});


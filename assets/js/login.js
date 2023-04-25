window.addEventListener('load', () => {
    document.getElementById("mostrarP").addEventListener("click", function () {
        var tipo = document.getElementById("password");
        var iconoP = document.getElementById("iconoP");
        if (tipo.type == "password") {
            tipo.type = "text";
            iconoP.setAttribute("class", "bi bi-eye-fill")
        } else {
            tipo.type = "password";
            iconoP.setAttribute("class", "bi bi-eye-slash-fill")
        }
    });

    document.getElementById("buttonLog").addEventListener("click", () => {
        const contenedorErrores = document.getElementById("contenedorErrores");
        const errores = document.getElementById("errores");
        if (document.getElementById("usuario").validity.valid && document.getElementById("password").validity.valid) {
            service.getAllUsuariosByNombreFull(document.getElementById("usuario").value).then(data => {
                if (data.length === 0) {
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

                } else if (data[0].nombre !== undefined && data[0].password === document.getElementById("password").value) {
                    contenedorErrores.style.display = "none";
                    sessionStorage.setItem('nombreUsuario', document.getElementById("usuario").value);
                    document.getElementById("modal").style.display = "flex";
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
            if (document.getElementById("usuario").value === "") {
                parrafo = document.createElement("p");
                parrafo.appendChild(document.createTextNode("-El usuario no puede estar vacío."));
                errores.appendChild(parrafo);
            }
            if (document.getElementById("password").value === "") {
                parrafo = document.createElement("p");
                errores.appendChild(document.createTextNode("-El campo de contraseña no puede estar vacío."));
                errores.appendChild(parrafo);
            }
        }
    });
});
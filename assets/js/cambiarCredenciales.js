window.addEventListener('load', () => {
    document.getElementById("mostrarP").addEventListener("click", function () {
        let tipo = document.getElementById("password");
        let iconoP = document.getElementById("iconoP");
        if (tipo.type == "password") {
            tipo.type = "text";
            iconoP.setAttribute("class", "bi bi-eye-fill")
        } else {
            tipo.type = "password";
            iconoP.setAttribute("class", "bi bi-eye-slash-fill")
        }
    });
    document.getElementById("mostrarPR").addEventListener("click", function () {
        let tipo = document.getElementById("passwordR");
        let iconoPR = document.getElementById("iconoPR");
        if (tipo.type == "password") {
            tipo.type = "text";
            iconoPR.setAttribute("class", "bi bi-eye-fill")
        } else {
            tipo.type = "password";
            iconoPR.setAttribute("class", "bi bi-eye-slash-fill")
        }
    });

    document.getElementById("mostrarOP").addEventListener("click", function () {
        let tipo = document.getElementById("oldPass");
        let iconoPR = document.getElementById("iconoOP");
        if (tipo.type == "password") {
            tipo.type = "text";
            iconoPR.setAttribute("class", "bi bi-eye-fill")
        } else {
            tipo.type = "password";
            iconoPR.setAttribute("class", "bi bi-eye-slash-fill")
        }
    });
    document.getElementById("oldPass").addEventListener("change", () => {
        service.getAllUsuariosByNombreFull(sessionStorage.getItem("nombreUsuario")).then(data => { if (data[0].password !== document.getElementById("oldPass").value) { document.getElementById("oldPass").setCustomValidity("Tu contraseña antigua de acceso es incorrecta."); } else { document.getElementById("oldPass").setCustomValidity(""); } });
    });
    document.getElementById("password").addEventListener("change", () => {
        if (document.getElementById("password").value !== document.getElementById("passwordR").value) {
            document.getElementById("passwordR").setCustomValidity("Las contraseñas no coinciden.");
        } else {
            document.getElementById("passwordR").setCustomValidity("");
        }
    });
    document.getElementById("passwordR").addEventListener("change", () => {
        if (document.getElementById("password").value !== document.getElementById("passwordR").value) {
            document.getElementById("passwordR").setCustomValidity("Las nuevas contraseñas no coinciden.");
        } else {
            document.getElementById("passwordR").setCustomValidity("");
        }
    });
    document.getElementById("aceptarCambios").addEventListener("click", () => {
        document.getElementById("modal2").style.display = "none";
    });

    document.getElementById("buttonCambiar").addEventListener("click", () => {
        const contenedorErrores = document.getElementById("contenedorErrores");
        const errores = document.getElementById("errores");
        if (document.getElementById("password").validity.valid && document.getElementById("passwordR").validity.valid && document.getElementById("oldPass").validity.valid) {
            contenedorErrores.style.display = "none";
            service.getAllUsuariosByNombreFull(sessionStorage.getItem("nombreUsuario")).then(data => {
                sessionStorage.setItem("idUsuario", data[0].id);
                let nombre = sessionStorage.getItem("nombreUsuario");
                let password = document.getElementById("password").value;
                let id = sessionStorage.getItem("idUsuario");
                let datos = { id: id, nombre: nombre, password: password };
                service.updateUsuario(id, datos).then(data => {
                    document.getElementById("modal2").style.display = "flex";
                });
                sessionStorage.removeItem("idUsuario");
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
            if (!document.getElementById("oldPass").validity.valid && document.getElementById("oldPass").value === "") {
                parrafo = document.createElement("p");
                parrafo.appendChild(document.createTextNode("-El campo de la antigua contraseña no puede estar vacío."));
                errores.appendChild(parrafo);
            } else if (!document.getElementById("oldPass").validity.valid && document.getElementById("oldPass").value !== "") {
                parrafo = document.createElement("p");
                parrafo.appendChild(document.createTextNode("-La contraseña antigua es incorrecta."));
                errores.appendChild(parrafo);
            }
            if (!document.getElementById("password").validity.valid && document.getElementById("password").value === "") {
                parrafo = document.createElement("p");
                errores.appendChild(document.createTextNode("-El campo de contraseña no puede estar vacío."));
                errores.appendChild(parrafo);
            } else if (!document.getElementById("password").validity.valid && document.getElementById("password").value !== "") {
                parrafo = document.createElement("p");
                errores.appendChild(document.createTextNode("-La contraseña debe tener al menos entre 8 y 30 caracteres, al menos un dígito o carácter especial, al menos una minúscula y al menos una mayúscula. Puede tener otros símbolos."));
                errores.appendChild(parrafo);
            }
            if (!document.getElementById("passwordR").validity.valid) {
                parrafo = document.createElement("p");
                errores.appendChild(document.createTextNode("-Las contraseñas deben de coincidir."));
                errores.appendChild(parrafo);
            }
        }
    });
});
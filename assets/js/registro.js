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
    document.getElementById("mostrarPR").addEventListener("click", function () {
        var tipo = document.getElementById("passwordR");
        var iconoPR = document.getElementById("iconoPR");
        if (tipo.type == "password") {
            tipo.type = "text";
            iconoPR.setAttribute("class", "bi bi-eye-fill")
        } else {
            tipo.type = "password";
            iconoPR.setAttribute("class", "bi bi-eye-slash-fill")
        }
    });

    document.getElementById("usuario").addEventListener("change", () => {
        service.getAllUsuariosByNombreFull(document.getElementById("usuario").value).then(data => { if (data.length !== 0) { document.getElementById("usuario").setCustomValidity("Ya existe un usuario con dicho nombre."); } else { document.getElementById("usuario").setCustomValidity(""); } });
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
            document.getElementById("passwordR").setCustomValidity("Las contraseñas no coinciden.");
        } else {
            document.getElementById("passwordR").setCustomValidity("");
        }
    });

    document.getElementById("buttonRegistrar").addEventListener("click", () => {
        const contenedorErrores = document.getElementById("contenedorErrores");
        const errores = document.getElementById("errores");
        if (document.getElementById("password").validity.valid && document.getElementById("passwordR").validity.valid && document.getElementById("usuario").validity.valid && document.getElementById("terminos").validity.valid) {
            contenedorErrores.style.display = "none";
            let nombre = document.getElementById("usuario").value;
            let password = document.getElementById("password").value;
            const datos = { id: 0, nombre: nombre, password: password };
            service.addUsuario(datos).then(data => {
                sessionStorage.setItem('nombreUsuario', document.getElementById("usuario").value);
                document.getElementById("modal").style.display = "flex";
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
            if (!document.getElementById("usuario").validity.valid && document.getElementById("usuario").value === "") {
                parrafo = document.createElement("p");
                parrafo.appendChild(document.createTextNode("-El usuario no puede estar vacío."));
                errores.appendChild(parrafo);
            } else if (!document.getElementById("usuario").validity.valid && document.getElementById("usuario").value !== "") {
                parrafo = document.createElement("p");
                parrafo.appendChild(document.createTextNode("-Ya existe un usuario con dicho nombre."));
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
            if (!document.getElementById("terminos").validity.valid) {
                parrafo = document.createElement("p");
                errores.appendChild(document.createTextNode("-Debes de aceptar los términos y condiciones."));
                errores.appendChild(parrafo);
            }
        }
    });
});
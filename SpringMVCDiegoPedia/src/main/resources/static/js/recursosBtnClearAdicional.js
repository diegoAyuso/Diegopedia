window.addEventListener("load", () => {
    if (document.getElementById("btnClear") !== null) {
        const clear = document.getElementById("btnClear").addEventListener("click", () => {
            clearCampoBuscar()
        });
    }
});

const clearCampoBuscar = () => {
    document.getElementById("campoBuscar").value = '';
    document.getElementById("mensajeInfo").style.display = "none";
};
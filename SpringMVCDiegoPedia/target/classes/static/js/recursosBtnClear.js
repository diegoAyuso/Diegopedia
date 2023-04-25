window.addEventListener("load", () => {
    const clear = document.getElementById("btnClear").addEventListener("click", () => {
        clearCampoBuscar()
    });
});

const clearCampoBuscar = () => {
    document.getElementById("campoBuscar").value = '';
};
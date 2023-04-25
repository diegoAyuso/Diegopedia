window.addEventListener("load", () => {
    comprobarCheckBox();
    const checkbox = document.getElementById("checkboxValorDefuncion").addEventListener("change", () => {
        comprobarCheckBox()
    });
});

const comprobarCheckBox = () => {
    if (document.getElementById("checkboxValorDefuncion").checked) {
        document.getElementById("labelCheckBoxIdentificador").innerHTML = "Sí";
        document.getElementById("parrafoFechaDefuncion").style.display = 'none';
        document.getElementById("fechaDefuncion").value = "";
    } else {
        document.getElementById("labelCheckBoxIdentificador").innerHTML = "No";
        document.getElementById("parrafoFechaDefuncion").style.display = '';
    }
};
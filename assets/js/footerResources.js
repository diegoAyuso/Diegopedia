window.addEventListener('load', () => {
    if (getCurrentURL().substring(getCurrentURL().indexOf("#") + 1, getCurrentURL().length) === "clickCosmologos") {
        document.getElementById("clickCosmologos").click();
    } else if (getCurrentURL().substring(getCurrentURL().indexOf("#") + 1, getCurrentURL().length) === "clickTodos") {
        document.getElementById("clickTodos").click();
    } else if (getCurrentURL().substring(getCurrentURL().indexOf("#") + 1, getCurrentURL().length) === "clickGeometras") {
        document.getElementById("clickGeometras").click();
    } else if (getCurrentURL().substring(getCurrentURL().indexOf("#") + 1, getCurrentURL().length) === "clickAnalistas") {
        document.getElementById("clickAnalistas").click();
    } else if (getCurrentURL().substring(getCurrentURL().indexOf("#") + 1, getCurrentURL().length) === "clickTeoricos") {
        document.getElementById("clickTeoricos").click();
    }

    const siClickTodos = document.getElementById("siClickTodos").addEventListener("click", () => {
        document.getElementById("clickTodos").click();
    });
    const siClickGeometras = document.getElementById("siClickGeometras").addEventListener("click", () => {
        document.getElementById("clickGeometras").click();

    });
    const siClickAnalistas = document.getElementById("siClickAnalistas").addEventListener("click", () => {
        document.getElementById("clickAnalistas").click();

    });
    const siClickCosmologos = document.getElementById("siClickCosmologos").addEventListener("click", () => {
        document.getElementById("clickCosmologos").click();

    });
    const siClickTeoricos = document.getElementById("siClickTeoricos").addEventListener("click", () => {
        document.getElementById("clickTeoricos").click();

    });
});

function getCurrentURL() {
    return window.location.href;
}
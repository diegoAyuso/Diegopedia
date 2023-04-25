window.addEventListener('load', () => {
    const menuItems = document.getElementsByClassName("menuItem");
    const submenuItems = document.getElementsByClassName("submenuItem");
    const button1 = document.querySelectorAll(".button1");
    const button2 = document.querySelectorAll(".button2");
    const button3 = document.querySelectorAll(".button3");
    const radioBs1 = document.getElementsByClassName("radioCarousel1");
    const radioBs2 = document.getElementsByClassName("radioCarousel2");
    const radioBs3 = document.getElementsByClassName("radioCarousel3");
    let actual1 = 0;
    let actual2 = 0;
    let actual3 = 0;
    const displayBotones1 = () => {
        if (actual1 === 0) {
            document.getElementById("flechaIzquierda1").style.display = 'none';
        } else if (actual1 === radioBs1.length - 1) {
            document.getElementById("flechaDerecha1").style.display = 'none';
        } else {
            document.getElementById("flechaDerecha1").style.display = 'block';
            document.getElementById("flechaIzquierda1").style.display = 'block';
        }
    }
    const displayBotones2 = () => {
        if (actual2 === 0) {
            document.getElementById("flechaIzquierda2").style.display = 'none';
        } else if (actual2 === radioBs2.length - 1) {
            document.getElementById("flechaDerecha2").style.display = 'none';
        } else {
            document.getElementById("flechaDerecha2").style.display = 'block';
            document.getElementById("flechaIzquierda2").style.display = 'block';
        }
    }
    const displayBotones3 = () => {
        if (actual3 === 0) {
            document.getElementById("flechaIzquierda3").style.display = 'none';
        } else if (actual3 === radioBs2.length - 1) {
            document.getElementById("flechaDerecha3").style.display = 'none';
        } else {
            document.getElementById("flechaDerecha3").style.display = 'block';
            document.getElementById("flechaIzquierda3").style.display = 'block';
        }
    }
    displayBotones1();
    displayBotones2();
    displayBotones3();
    const gotoPrev1 = () => {
        if (actual1 > 0) {
            radioBs1[actual1 - 1].checked = 'true';
            actual1--;
        }
        displayBotones1();
    }
    const gotoNext1 = () => {
        if (actual1 < radioBs1.length - 1) {
            radioBs1[actual1 + 1].checked = 'true';
            actual1++;
        }
        displayBotones1();
    }
    const gotoPrev2 = () => {
        if (actual2 > 0) {
            radioBs2[actual2 - 1].checked = 'true';
            actual2--;
        }
        displayBotones2();
    }
    const gotoNext2 = () => {
        if (actual2 < radioBs2.length - 1) {
            radioBs2[actual2 + 1].checked = 'true';
            actual2++;
        }
        displayBotones2();
    }
    const gotoPrev3 = () => {
        if (actual3 > 0) {
            radioBs3[actual3 - 1].checked = 'true';
            actual3--;
        }
        displayBotones3();
    }
    const gotoNext3 = () => {
        if (actual3 < radioBs3.length - 1) {
            radioBs3[actual3 + 1].checked = 'true';
            actual3++;
        }
        displayBotones3();
    }


    for (let i = 0; i < menuItems.length; i++) {
        menuItems[i].addEventListener('click', (event) => {
            if (window.matchMedia("(max-width: 1230px)").matches) {
                for (let i = 0; i < menuItems.length; i++) {
                    menuItems[i].style.display = 'none';
                }
                for (let i = 0; i < submenuItems.length; i++) {
                    submenuItems[i].style.display = 'none';
                }
            }
        });
    }

    document.getElementById("menuOculto").addEventListener("mouseover", () => {
        for (let i = 0; i < menuItems.length; i++) {
            menuItems[i].style.display = 'block';
        }
    });

    for (let i = 0; i < submenuItems.length; i++) {
        submenuItems[i].addEventListener('click', (event) => {
            for (let i = 0; i < submenuItems.length; i++) {
                submenuItems[i].style.display = 'none';
            }
            if (window.matchMedia("(max-width: 1230px)").matches) {
                for (let i = 0; i < menuItems.length; i++) {
                    menuItems[i].style.display = 'none';
                }
            }
        });
    }

    document.getElementById("aSubmenuCompetencias").addEventListener('mouseover', () => {
        for (let i = 0; i < submenuItems.length; i++) {
            submenuItems[i].style.display = 'block';
        }
    });

    document.getElementById("aSubmenuCompetencias").addEventListener('click', () => {
        for (let i = 0; i < submenuItems.length; i++) {
            submenuItems[i].style.display = 'none';
        }
    });

    for (let i = 0; i < button1.length; i++) {
        button1[i].addEventListener("click", () => i == 0 ? gotoPrev1() : gotoNext1());
    }

    for (let i = 0; i < button2.length; i++) {
        button2[i].addEventListener("click", () => i == 0 ? gotoPrev2() : gotoNext2());
    }

    for (let i = 0; i < button3.length; i++) {
        button3[i].addEventListener("click", () => i == 0 ? gotoPrev3() : gotoNext3());
    }
});
window.addEventListener('resize', (event) => {
    const menuItems = document.getElementsByClassName("menuItem");
    const maxWidth = document.documentElement.clientWidth;
    if (window.matchMedia("(max-width: 1230px)").matches) {
        for (let i = 0; i < menuItems.length; i++) {
            menuItems[i].style.display = 'none';
        }
    } else {
        for (let i = 0; i < menuItems.length; i++) {
            menuItems[i].style.display = 'block';
        }
    }
});



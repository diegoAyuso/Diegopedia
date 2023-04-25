let mybuttonTop = document.getElementById("myBtnToTop");
let mybuttonBot = document.getElementById("myBtnToBot");
window.addEventListener("load", () => {
    mybuttonTop.addEventListener('click', () => topFunction());
    mybuttonBot.addEventListener('click', () => botFunction());
});

window.onscroll = function() {
    scrollFunction()
};

function scrollFunction() {
    if ((document.body.scrollTop > 0.5 * document.body.scrollHeight && document.body.scrollTop < document.body.scrollHeight) || (document.documentElement.scrollTop > 0.5 * document.body.scrollHeight && document.body.scrollTop < document.body.scrollHeight)) {
        mybuttonTop.style.display = "block";
        mybuttonBot.style.display = "none";
    } else {
        mybuttonTop.style.display = "none";
        mybuttonBot.style.display = "block";
    }
}

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

function botFunction() {
    window.scrollTo(0, document.body.scrollHeight);
}
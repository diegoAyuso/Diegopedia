window.addEventListener('load', () => {
    const carouselPrev = document.getElementsByClassName("carousel-control-prev");
    for (let prev of carouselPrev) {
        prev.addEventListener("click", (event) => {
            event.preventDefault();
        });
    }

    const carouselNext = document.getElementsByClassName("carousel-control-next");
    for (let next of carouselNext) {
        next.addEventListener("click", (event) => {
            event.preventDefault();
        });
    }
});
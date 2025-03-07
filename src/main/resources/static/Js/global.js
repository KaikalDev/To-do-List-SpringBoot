function iniciarLoader() {
    const loader = document.getElementById("loader");
    if (loader) {
        loader.style.display = "flex";
    }
}

function finalizarLoader() {
    setTimeout(() => {
        const loader = document.getElementById("loader");
        if (loader) {
            loader.style.display = "none";
        }
    }, 800);
}

document.addEventListener("DOMContentLoaded", finalizarLoader);

document.querySelectorAll("form").forEach(form => {
    form.addEventListener("submit", iniciarLoader);
});

window.onbeforeunload = function () {
    iniciarLoader();
};

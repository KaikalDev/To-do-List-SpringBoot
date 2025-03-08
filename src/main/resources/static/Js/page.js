function Edit(id) {
    iniciarLoader();
    setTimeout(() => {
        window.location.href = '/toggleStatus?id=' + id;
    }, 200);
}

function confirmDelete(id) {
    const confirmation = confirm('Are you sure you want to delete this task?');
    if (confirmation) {
        window.location.href = `/deleteTask?id=${id}`;
    }
}

document.getElementById("searchInput").addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        Buscar(event)
    }
});

function Buscar(event) {
    event.preventDefault();
    setTimeout(function () {
        document.getElementById("defaultButton").click();
    }, 800)
}

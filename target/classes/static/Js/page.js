document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("searchInput");
    const filterForm = document.getElementById("filterForm");

    setTimeout(() => {
        searchInput.addEventListener("input", () => {
                iniciarLoader();
                setTimeout(() => {
                    filterForm.submit();
                }, 200)
        });
    }, 5000)

});

document.getElementById("searchInput").addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        document.getElementById("defaultButton").click();
    }
});

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

function onlyOne(checkbox) {
    document.querySelectorAll('input[name="options"]').forEach((el) => {
        if (el !== checkbox) el.checked = false;
    });
}
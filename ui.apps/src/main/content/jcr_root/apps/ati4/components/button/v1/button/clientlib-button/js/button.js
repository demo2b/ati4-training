document.querySelectorAll('.button-wrapper button').forEach(button => {
    if(button.dataset && button.dataset.href) {
        button.addEventListener('click', (event) => {
            document.location.href = button.dataset.href;
        })
    }
})
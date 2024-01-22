let div = document.querySelectorAll('.flip-card')

div.forEach(div => {
        div.addEventListener("click", function() {
        div.classList.toggle("rotate");
    });
})
let div = document.querySelectorAll('.flip-card')

div.forEach(div => {
    let front = div.querySelector('.flip-card-front')
    let back = div.querySelector('.flip-card-back')
    front.innerHTML = '<h2>Word of the day</h2><p>FRONTEND</p>'
    back.innerHTML = '<h2>Word of the day</h2><p>BACKEND</p>'

    div.addEventListener("click", function() {
        div.classList.toggle("rotate");
    });
})
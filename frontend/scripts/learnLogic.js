function apiTest(lang, difficulty){
    const options = {
        method: 'GET',
    };

    fetch('http://localhost:8080/api/learn/pol-'+lang+'/'+difficulty, options)
        .then(response => response.json())
        .then(response => console.log(response))
        .catch(err => console.error(err));
}

//dodac 3 nowe divy do strony

function addNewElement(){
    let container = document.getElementsByClassName('test-content')[0];

    let  flipCard = document.createElement('div');
    flipCard.className = 'flip-card';

    let  flipCardInner = document.createElement('div');
    flipCardInner.className = 'flip-card-inner';

    let  flipCardFront = document.createElement('div');
    flipCardFront.className = 'flip-card-front';

    let  frontHeader = document.createElement('h2');
    frontHeader.textContent = 'Word of the day';
    flipCardFront.appendChild(frontHeader);

    let  flipCardBack = document.createElement('div');
    flipCardBack.className = 'flip-card-back';

    let  backHeader = document.createElement('h2');
    backHeader.textContent = 'Word of the day';
    flipCardBack.appendChild(backHeader);


    flipCardInner.appendChild(flipCardFront);
    flipCardInner.appendChild(flipCardBack);
    flipCard.appendChild(flipCardInner);
    container.appendChild(flipCard);
}

function startTest(){
    const lang = document.getElementById('lang').value;
    const level = document.querySelector('input[name="level"]:checked').value;

    apiTest(lang, level);

    console.log("test is started..." + lang + " " + level);
}
addNewElement()
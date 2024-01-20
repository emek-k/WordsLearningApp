const options = {
    method: 'GET',
};

fetch('https://cat-fact.herokuapp.com', options)
    .then(response => response.json())
    .then(response => console.log(response))
    .catch(err => console.error(err));

//dodac 3 nowe divy do strony

function addNewElement(){
    let container = document.getElementsByClassName('main-container')[0];

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

addNewElement();
addNewElement();
addNewElement();
addNewElement();
addNewElement();
addNewElement();
addNewElement();
addNewElement();
addNewElement();
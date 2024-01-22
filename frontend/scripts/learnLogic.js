class Question{
    question;
    options;
    answer;
    constructor(question, options, answer){
        this.question = question;
        this.options = options;
        this.answer = answer;
    }
}
questions = [];
userPoints = 0;
rotate = true;

function addNewElement(idName, front, back, correct, question){
    let container = document.getElementsByClassName('test-content')[0];

    let  flipCard = document.createElement('div');
    flipCard.className = 'flip-card';
    flipCard.id = idName;

    let  flipCardInner = document.createElement('div');
    flipCardInner.className = 'flip-card-inner';

    let  flipCardFront = document.createElement('div');
    flipCardFront.className = 'flip-card-front';

    let  frontHeader = document.createElement('h2');
    frontHeader.textContent = front;
    flipCardFront.appendChild(frontHeader);

    let  flipCardBack = document.createElement('div');
    flipCardBack.className = 'flip-card-back';

    let  backHeader = document.createElement('h2');
    backHeader.textContent = back;
    if(correct === true){
        flipCardBack.classList.add('correct');
    }
    flipCard.addEventListener("click", function() {
        if(rotate){
            flipCard.classList.toggle("rotate");
            rotate = false;
        }

        question.userAnswer = front;
        if(question.answer === front){
            userPoints++;
        }
    });
    flipCardBack.appendChild(backHeader);
    flipCardInner.appendChild(flipCardFront);
    flipCardInner.appendChild(flipCardBack);
    flipCard.appendChild(flipCardInner);
    container.appendChild(flipCard);
}
let currentQuestionIndex = 0;
function startTest(){
    const lang = document.getElementById('lang').value;
    const level = document.querySelector('input[name="level"]:checked').value;

    questions = [];
    currentQuestionIndex = 0;
    apiTest(lang, level);
    console.log("test is started..." + lang + " " + level);
}

function apiTest(lang, difficulty){
    const options = {
        method: 'GET',
    };

    fetch('http://localhost:8080/api/learn/pol-'+lang+'/'+difficulty, options)
        .then(response => response.json())
        .then(response =>{
            console.log(response);
            response.data.forEach((item) => {
                questions.push(
                    new Question(item.question, item.options, item.correctAnswer)
                );
            });
            showNextQuestion();
        })
        .catch(err => console.error(err));
}
function clearTest(){

    const questClass = document.getElementsByClassName('question')[0];
    while (questClass.firstChild) {
        questClass.removeChild(questClass.firstChild);
    }
    const options = document.getElementsByClassName('test-content')[0];

    while (options.firstChild) {
        options.removeChild(options.firstChild);
    }
}


function showNextQuestion() {
    clearTest();
    if (currentQuestionIndex >= questions.length) {
        showScore();
        return;
    }

    let question = questions[currentQuestionIndex];

    const questClass = document.getElementsByClassName('question')[0];
    let message = document.createElement('h1');
    message.textContent = question.question;
    let button = document.createElement('button');
    
    button.classList.add('btn');
    button.classList.add('btn-primary');
    button.textContent = 'Next Question';
    button.addEventListener("click", () => {
        currentQuestionIndex++;
        rotate = true;
        showNextQuestion();
    })
    questClass.appendChild(message);
    questClass.appendChild(button);

    question.options.forEach((item, index) => {
        let correct = false;
        if (item === question.answer) {
            correct = true;
        }
        addNewElement('option' + index, item, '', correct, question);
    })
}

function showScore() {
    const questClass = document.getElementsByClassName('question')[0];
    let message = document.createElement('h1');
    message.textContent = 'You scored ' + userPoints + '!';
    questClass.appendChild(message);
}
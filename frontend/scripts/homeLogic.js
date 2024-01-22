function apiTest(){
    const options = {
        method: 'GET',
    };

    fetch('http://localhost:8080/api/randomWord', options)
        .then(response => response.json())
        .then(response => {
            console.log(response);
            addFlashCard(response);
        })
        .catch(err => console.error(err));
}

function addFlashCard(response){
    const front = document.getElementsByClassName('flip-card-front')[0];
    const back = document.getElementsByClassName('flip-card-back')[0];

    let newFrontContent = document.createElement('p');
    let newBackContent = document.createElement('p');

    newFrontContent.textContent = response.name;
    newBackContent.textContent = response.translation + response.definition;

    front.append(newFrontContent);
    back.append(newBackContent);
}

apiTest()


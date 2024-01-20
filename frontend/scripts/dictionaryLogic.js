function apiGet(){
    const options = {
        method: 'GET',
    };

    fetch('https://cat-fact.herokuapp.com/facts', options)
        .then(response => response.json())
        .then(response => response.map(cat => console.log(cat.text)))
        .catch(err => console.error(err));
}



function dictionaryEng(){
    console.log("ENG DIC");
    apiGet()
}

function dictionaryFr(){
    console.log("FR DIC")
}

function dictionaryGe(){
    console.log("GE DIC")
}


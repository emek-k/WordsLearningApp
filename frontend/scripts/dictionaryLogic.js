// function apiGet(){
//     const options = {
//         method: 'GET',
//     };
//
//     fetch('https://kitsu.io/api/edge/trending/anime', options)
//         .then(response => response.json())
//         .then(response => response.map(cat => console.log(cat.title)))
//         .catch(err => console.error(err));
// }

class RowDetails{
    id = 1;
    polish = '#';
    english= '#';
    french = '#';
    german = '#';
    definition = '#';
    element = '#';
    constructor(row){
        if (row && typeof row === 'object') {
            this.id = row.id + 1|| 1;
            this.polish = row.polish || '#';
            this.english = row.english || '#';
            this.french = row.french || '#';
            this.german = row.german || '#';
            this.definition = row.definition || '#';
            this.element = row.element || '#';
        }
    }

    addRowToTable(){
        const tbody = document.getElementsByClassName('dictionary-tbody')[0];
        const newRow = document.createElement('tr');
        const thCell = document.createElement('th');
        thCell.setAttribute('scope', 'row');
        thCell.textContent = this.id;
        newRow.appendChild(thCell);
        for (let i = 0; i < 5; i++) {
            const cell = document.createElement('td');
            switch(i){
                case 0:
                    if(this.polish === undefined)
                        this.polish = '#';
                    cell.textContent = this.polish;
                    break;
                case 1:
                    cell.textContent = this.english;
                    break;
                case 2:
                    cell.textContent = this.french;
                    break;
                case 3:
                    cell.textContent = this.german;
                    break;
                case 4:
                    cell.textContent = this.definition;
                    break;
            }
            newRow.appendChild(cell);
    }
    tbody.appendChild(newRow);
    this.element = newRow;
    words.push(this)
    }

    clone(){
        return new RowDetails(this);
    }
}

function apiGet() {
    const options = {
        method: 'GET',
    };

    fetch('https://kitsu.io/api/edge/anime', options)
        .then(response => response.json())
        .then(response => displayTitles(response))
        .catch(err => console.error(err));
}

function displayTitles(apiResponse) {
    const titlesList = getTitles(apiResponse);
    titlesList.forEach(title => {
        console.log(title)
        addNewRow(title)
    });
}

function getTitles(apiResponse) {
    return apiResponse.data.map(anime => anime.attributes.titles.en);
}
function getLastId(){
    let lastRow = document.querySelector('.dictionary-tbody tr:last-child');
    if(lastRow == null){
        return 0;
    }
    let lastId = lastRow.querySelector('th').textContent;
    return parseInt(lastId);
}

let words = [];

function addNewRow(value) {
    let newRow;
    if(words.length === 0){
        newRow = new RowDetails();
    }else{
        newRow = words[words.length-1].clone();
    }
    newRow.polish = value;
    newRow.addRowToTable()
}

// function addNewRow(value){
//     let tbody = document.getElementsByClassName('dictionary-tbody')[0];
//     let newRow = document.createElement('tr');
//
//     let thCell = document.createElement('th');
//     thCell.setAttribute('scope', 'row');
//     thCell.textContent = (getLastId() + 1).toString();
//     newRow.appendChild(thCell);
//
//     for (let i = 0; i < 5; i++) {
//         let cell = document.createElement('td');
//         switch(i){
//             case 0:
//                 if(value === undefined)
//                     value = "#";
//                 cell.textContent = value;
//                 break;
//             case 1:
//                 cell.textContent = '#';
//                 break;
//             case 2:
//                 cell.textContent = '#';
//                 break;
//             case 3:
//                 cell.textContent = '#';
//                 break;
//             case 4:
//                 cell.textContent = '#';
//                 break;
//         }
//         newRow.appendChild(cell);
//     }
//     tbody.appendChild(newRow);
//
//     words.push({text: value, element: newRow})
// }

let searchWord = document.getElementsByClassName('search-word')[0];
searchWord.addEventListener('input', (e) => {
    const value = e.target.value.toLowerCase();
    console.log(words)
    words.forEach(word => {
        const isVisible = word.polish.toLowerCase().includes(value) ||
                                    word.english.toLowerCase().includes(value) ||
                                    word.french .toLowerCase().includes(value) ||
                                    word.german .toLowerCase().includes(value) ||
                                    word.definition.toLowerCase().includes(value) ||
                                    word.id === Number(value);
        word.element.classList.toggle("hide", !isVisible);
    })

})

apiGet();
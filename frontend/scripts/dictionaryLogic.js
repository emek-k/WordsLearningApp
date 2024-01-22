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

    fetch('http://localhost:8080/api/dictionary/pol-eng', options)
        .then(response => response.json())
        .then(response => displayWords(response))
        .catch(err => console.error(err));
}

function displayWords(apiResponse) {
    const wordsList = apiResponse.dictionaryContent;

    if (typeof wordsList === 'object' && wordsList !== null) {
        Object.keys(wordsList).forEach(wordKey => {
            const word = wordsList[wordKey];
            console.log(word);
            addNewRow(word);
        });
    } else {
        console.error('Invalid format for wordsList:', wordsList);
    }
}

let words = [];

function addNewRow(word) {
    let newRow;
    if(words.length === 0){
        newRow = new RowDetails();
    }else{
        newRow = words[words.length-1].clone();
    }
    newRow.english = word.name;
    newRow.polish = word.translation;
    newRow.definition = word.definition;
    newRow.addRowToTable()
}


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
import 'bootstrap/dist/css/bootstrap.css'

const getCountryData = function(code){
    fetch('http://restcountries.eu/rest/v1/alpha?codes=' + code)
    .then( res => res.json() )
    .then( data => {
        document.getElementById('countryName').innerText = data[0].name;
        document.getElementById('countryPop').innerText = 'Population: ' + data[0].population;
        document.getElementById('countryArea').innerText = 'Area: ' + data[0].area;
        document.getElementById('countryBorders').innerText = 'Borders: ' + data[0].borders.join(", ")
    })
}

document.getElementById('map').addEventListener('click', event => {
    getCountryData(event.target.id);
})


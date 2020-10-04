import 'bootstrap/dist/css/bootstrap.css'
import jokes from "./jokes";

const getJokeFromAPI = function() {
    fetch('https://studypoints.info/jokes/api/jokes/period/hour')
    .then( res => res.json() )
    .then(data => {
        document.getElementById("3").innerHTML = data['joke'];
    });
}

//1)

document.getElementById("button").addEventListener("click",function(event){
    let input = document.getElementById("textin").value;
    if(isNaN( input ) ) {
        document.getElementById("1").innerHTML = "That's not a number!";
    } else {
        document.getElementById("1").innerHTML = jokes.getJokeById(input);
    }
});


document.getElementById("button2").addEventListener("click",function(){
    let input = document.getElementById("jokein").value;
    jokes.addJoke(input);
});

//2)
document.getElementById("gethourlybutton").addEventListener("click",getJokeFromAPI);
window.setInterval(getJokeFromAPI,3600000)

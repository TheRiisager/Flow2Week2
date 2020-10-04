const myList = [13,26,32,41,58,60,79,80,93,123]

let my_callback = function(param){
    return param % 2 == 0;
}

console.log( myList.filter(my_callback) );
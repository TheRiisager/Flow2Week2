
//1) Using existing functions that take callback functions
    const names = ['Jan','Bo','Nina','Pia','John'];

    //a)
    console.log('------------a------------');
    let newNames = names.filter( function(item) {
        return item.includes('a');
    } );
    console.log(newNames);

    //b)
    console.log('------------b------------');
    let revNames = names.map( function(item) {
        return item.split('').reverse().join('');
    });
    console.log(revNames);

    console.log('------------END OF 1------------');

//2) Implement user defined functions that take callbacks as an argument

    //a)
    console.log('------------a------------');
    const myfilter = function(array, callback) {
        let newArray = [];
        
        array.forEach(item => {
            if( callback(item) ) {
                newArray.push(item);
            }
        });
        return newArray;
    };

    let namesWithI = myfilter(names, function(item){
        return item.includes('i');
    });
    console.log(namesWithI);

    //b)
    console.log('------------b------------');
    const myMap = function(array, callback) {
        let newArray = []

        array.forEach(item => {
            newArray.push( callback(item) );
        });
        return newArray;
    };

    let revNames2 = myMap(names, function(item){
        return item.split('').reverse().join('');
    });
    console.log(revNames2);

    console.log('------------END OF 2------------');

//3) Getting really comfortable with filter and map
    const numbers = [1,3,5,10,11]

    //a) note: I think i get it in principle (there are more arguments for map than just a value), but figuring out exactly what operation to perform to get the described array seems like a waste of time...
    console.log('------------a------------');
    let resultNumbers = numbers.map( (item,index) => item * index );
    console.log(resultNumbers);

    //b)
    console.log('------------b------------');
    let namesNavString = '<nav>' + names.map(item => '<a href=\"\">' + item + '</a>').join('') + '</nav>';
    console.log(namesNavString);

    //c)
    console.log('------------c------------');
    const persons = [{name:"Hassan",phone:"1234567"}, {name: "Peter",phone: "675843"}, {name: "Jan", phone: "98547"},{name: "Boline", phone: "79345"}]
    let personTableString = '<table>' + persons.map( item =>'<tr><td>' + item.name + '</td><td>' + item.phone + '</td></tr>').join('') + '</table>';
    console.log(personTableString);

    console.log('------------END OF 3------------');

//4) Reduce

    //a)
    console.log('------------a------------');
    console.log(names.join('#'));

    //b)
    console.log('------------b------------');
    console.log( numbers.reduce( (total,num) => total+num,0 ) );

    //c)
    console.log('------------c------------');
    const members = [
        {name : "Peter", age: 18},
        {name : "Jan", age: 35},
        {name : "Janne", age: 25},
        {name : "Martin", age: 22}];
    
    let averageAge = members.reduce( (accumulator,member,index,array) => {
        let tmp = accumulator += member.age;
        if(index === array.length-1){
            return accumulator / array.length;
        }
        return tmp
    },0);
    console.log(averageAge)

    //d)
    console.log('------------d------------');
    const votes = [ "Biden","Trump","Biden","Biden","Trump","Trump","Biden","None"];

    let voteResult = votes.reduce( (total,item) => {
        if(total[item]){
            total[item]++;
        } else{
            total[item] =1;
        }
        return total;
    },{});
    console.log(voteResult);
    console.log('------------END OF 4------------');

//5) 'this' in Javascript

    //a) The output is undefined because 'this' is called outside the scope of the car's object constructor, thus referring to the window objects 'this', which has no brand.
    
    //b) The same as 'a', but this time 'this' references the button the eventListener is attached to, which also does not have anything called brand

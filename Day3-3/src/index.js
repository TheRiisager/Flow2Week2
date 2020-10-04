import 'bootstrap/dist/css/bootstrap.css'

const allUsersHeader = '<tr><th>ID</th><th>Name</th><th>Age</th><th>Gender</th><th>Email</th></tr>';

const fetchWithErrorHandling = function(res){
    if(!res.ok){
        return Promise.reject({status: res.status, fullError: res.json() })
    }

    return res.json();
}

const getUsers = function() {
    fetch('http://localhost:3333/api/users')
    .then(res => fetchWithErrorHandling(res))
    .then( data => {
        document.getElementById('allUsersTable').innerHTML = allUsersHeader + data.map(formatUsersToTd).join('');
    })
    .catch(err => {
        if(err.status){
            err.fullError.then(e=> console.log(e.detail))
          }
          else{console.log("Network error"); }
    });
}

const formatUsersToTd = function(item){
    return  '<tr>' + 
            '<td>' + item['id'] + '</td>' +
            '<td>' + item['name'] + '</td>' +
            '<td>' + item['age'] + '</td>' +
            '<td>' + item['gender'] + '</td>' +
            '<td>' + item['email'] + '</td>' +
            '</tr>'
}

const getUserByID = function() {
    let id = document.getElementById('idIn').value

    fetch('http://localhost:3333/api/users/' + id)
    .then(res => fetchWithErrorHandling(res))
    .then( data => {
        document.getElementById('getUserOut').innerHTML = Object.values(data).join(' ');
    })
    .catch(err => {
        if(err.status){
            err.fullError.then(e=> console.log(e.detail))
          }
          else{console.log("Network error"); }
    });
}

const addUser = function(event){
    event.preventDefault()
    let body = {
        name:   document.getElementById("name").value,
        age:    document.getElementById("age").value,
        gender: document.getElementById("gender").value,
        email:  document.getElementById("email").value
    };

    let options = {
        method: "POST",
        headers: {
            'Accept':'application/json',
            'Content-Type':'application/json'
        },
        body: JSON.stringify(body)
    }

    fetch('http://localhost:3333/api/users',options);
}

const editUser = function(event){
    event.preventDefault();
    let body = {
        name:   document.getElementById("name2").value,
        age:    document.getElementById("age2").value,
        gender: document.getElementById("gender2").value,
        email:  document.getElementById("email2").value
    };

    let options = {
        method: "PUT",
        headers: {
            'Accept':'application/json',
            'Content-Type':'application/json'
        },
        body: JSON.stringify(body)
    }
    fetch('http://localhost:3333/api/users/' + document.getElementById("idField").value,options);
}

const deleteUser = function(){
    let options = {
        method: "DELETE",
        headers: {
        'Content-Type': 'application/json'
        }
     }
     fetch('http://localhost:3333/api/users/' + document.getElementById('deleteId').value,options);
}

getUsers();
document.getElementById("getByIdButton").addEventListener("click",getUserByID);
document.getElementById("addForm").addEventListener('submit',addUser);
document.getElementById("editForm").addEventListener('submit',editUser);
document.getElementById("deleteButton").addEventListener('click',deleteUser);



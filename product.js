if (sessionStorage.getItem('state') == 'loggedIn'){   
    document.getElementById("logState").innerHTML = 'SIGN OUT';
}
function view(){
    const url = `http://localhost:8081/user/getProductByID/${sessionStorage.getItem('id')}`;
    let h = new Headers();
    let token = sessionStorage.getItem('accessToken');
    h.append('Authorization', `Bearer ${token}`);

    let request = new Request(url,{
        method: 'GET',
        headers: h,
        mode: 'cors'
    });
    fetch(request).then((response)=>{
            if (response.ok){
                return response.json();
            }
    }).then(e=>{
        document.getElementById('name').innerHTML =  e.name;
        document.getElementById('soil').innerHTML = e.soil;
        document.getElementById('quantity').innerHTML = 'Available: '+e.quantityAvailable;
        document.getElementById('season').innerHTML = e.season;
        document.getElementById('category').innerHTML = e.category;
        document.getElementById('price').innerHTML = '$' + e.price;
        document.getElementById('description').innerHTML = 'Description: ' + e.description;
        document.getElementById('img').src = `img/${e.id}.png`;
    })
}
function log(){
    if (sessionStorage.getItem('state') == 'loggedIn'){
        sessionStorage.setItem('state','loggedOut');
        sessionStorage.removeItem('accessToken');
        sessionStorage.removeItem('role');
        document.getElementById("logState").innerHTML = 'SIGN IN';
    }
    else{
        window.location.href = '../verfication/login.html';
    }
}
view();
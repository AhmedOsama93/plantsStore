if (sessionStorage.getItem('state') == 'loggedIn'){   
    document.getElementById("logState").innerHTML = 'SIGN OUT';
}

function getInfo(){
const url = 'http://localhost:8081/user/getMyData'
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
        console.log(e);
        document.getElementById('email').innerHTML = e.username;
        document.getElementById('phone').innerHTML = e.phoneNo;
        document.getElementById('address').innerHTML = e.address1;
        document.getElementById('name').innerHTML = e.fName;
    })
}
getInfo();
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
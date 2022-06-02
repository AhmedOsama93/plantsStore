if (sessionStorage.getItem('state') == 'loggedIn'){   
    document.getElementById("logState").innerHTML = 'SIGN OUT';
}
function addProduct(){
    let chapter = sessionStorage.getItem('chapter');
    const url = 'http://localhost:8081/visitor/getProduct';
    let h = new Headers();

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
        console.log(e)
            for (var key in e){
                 if(e[key].season == chapter && e[key].quantityAvailable > 0){
                    var element = document.createElement('div');
                    let product =  `<div class ="div1"><div class="img">
                    <h3 class="prod">${e[key].name}</h3>
                    <h3 class="prod">Costs $${e[key].price}</h3>
                    <img src=\"img/${e[key].id}.png\" class=\"foto\" >
                    <button class=\"button1\">VIEW DETAILS</button>
                        <button class="button2" id = "${e[key].id}" onclick = "addToCart(+${e[key].id})">
                            ADD TO CART
                        </button>
                    </div></div>`;
                    element.innerHTML = product;
                    document.body.getElementsByClassName('grid-container')[0].appendChild(element);
                }          
            }  
    })
}
addProduct();
function addToCart(id){
    if(sessionStorage.getItem('accessToken') =='' || sessionStorage.getItem('accessToken') ==null){
        alert('you have to login before adding to cart');
    }
    else{
        let url = `http://localhost:8081/user/cart/${id}/1`;
        let h = new Headers();
        let token = sessionStorage.getItem('accessToken');
        h.append('Authorization', `Bearer ${token}`);
        let request = new Request(url,{
            method: 'POST',
            headers: h,
            mode: 'cors'
        });
        fetch(request).then((response)=>{
            var element = document.createElement('div');
                if (response.ok){
                    document.getElementById(id).innerHTML = 'ADDED';
                    document.getElementById(id).disabled = true;
                    return response.json();
                }
        }).then(e=>{
            // console.log(e);
            document.getElementsByClassName('cartSize')[0].innerHTML = e;
        })
    }
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
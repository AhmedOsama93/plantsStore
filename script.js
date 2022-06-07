if (sessionStorage.getItem('state') == 'loggedIn'){   
    document.getElementById("logState").innerHTML = 'SIGN OUT';
}
var json;
let chapter = sessionStorage.getItem('chapter');
function addProduct(){
    
    const url = 'http://localhost:8081/visitor/getProduct';
    let request = new Request(url,{
        method: 'GET',
        mode: 'cors'
    });
    fetch(request).then((response)=>{
            if (response.ok){
                return response.json();
            }
    }).then(e=>{
        json = e;
            for (var key in e){
                 if((e[key].season == chapter && e[key].quantityAvailable > 0) || (e[key].season == 'all year' && e[key].quantityAvailable > 0) || e[key].category == 'tools' ||e[key].category == 'fertilizers'){
                    var element = document.createElement('div');
                    let product =  `<div class ="div1"><div class="img">
                    <h3 class="prod">${e[key].name}</h3>
                    <h3 class="prod">Costs $${e[key].price}</h3>
                    <img src=\"img/${e[key].photo}.png\" class=\"foto\" >
                    <button class=\"button1\" id = "v${e[key].id}" onclick ="viewDetails(${e[key].id})">VIEW DETAILS</button>
                        <button class="button2" id = "${e[key].id}" onclick = "addToCart(${e[key].id})">
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
    if(sessionStorage.getItem('accessToken') =='' || sessionStorage.getItem('accessToken') == null){
        document.getElementById(id).innerHTML = 'Login to add to cart';
        document.getElementById(id).style =  `
        background-color:#F23030;
        border: 1px solid #F23030;
        box-shadow: 0 1px 2px 0 #F23030;`;
        document.getElementById(id).disabled = true;
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
                if (response.ok){
                    document.getElementById(id).innerHTML = 'ADDED';
                    document.getElementById(id).disabled = true;
                    return response.json();
                }
        }).then(e=>{
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
function viewDetails(id){
    
    if(sessionStorage.getItem('state') == 'loggedIn'){
        sessionStorage.setItem('id', id);
        window.location.href = "ProductDetails.html";     
    }
    else {
        document.getElementById('v'+id).innerHTML = 'Login to add to cart';
        document.getElementById('v'+id).style =  `
        background-color:#F23030;
        color:white;
        border: 1px solid #F23030;
        box-shadow: 0 1px 2px 0 #F23030;`;
        document.getElementById('v'+id).disabled = true;
    }
    
}
function search(){
    let query = document.getElementById('query').value;
    document.getElementsByClassName('grid-container')[0].innerHTML = "";
    for (var key in json){
        if(json[key].name === query){
        if(json[key].season == chapter && json[key].quantityAvailable > 0){
           var element = document.createElement('div');
           let product =  `<div class ="div1"><div class="img">
           <h3 class="prod">${json[key].name}</h3>
           <h3 class="prod">Costs $${json[key].price}</h3>
           <img src=\"img/${json[key].id}.png\" class=\"foto\" >
           <button class=\"button1\" onclick ="viewDetails(${json[key].id})">VIEW DETAILS</button>
               <button class="button2" id = "${json[key].id}" onclick = "addToCart(${json[key].id})">
                   ADD TO CART
               </button>
           </div></div>`;
           element.innerHTML = product;
           document.body.getElementsByClassName('grid-container')[0].appendChild(element);
       }       
    }   
   } 
    
}

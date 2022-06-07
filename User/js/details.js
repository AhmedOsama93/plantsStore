if (sessionStorage.getItem('state') == 'loggedIn'){   
    document.getElementById("logState").innerHTML = 'SIGN OUT';
}
function getOrders(){
    const url = 'http://localhost:8081/user/getAllOrdersForuser';
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
                return response.json();
            }
    }).then(e=>{
                 for (var key in e){
                    let cart = 
                    `<tr>
                        <td class="td1">
                            <div class="row">
                                <div class="col-md-3 text-left">
                                    <img src="../home/img/${e[key].id.productID}.png" alt=""
                                        class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                                </div>
                            </div>
                        </td>
                        <td class="td2">${e[key].state}</td>
                        <td class="td3">
                            <label type="number" id = "${e[key].quantity}" class="input1">${e[key].quantity}</label>
                        </td>

                    </tr>`;
                    var element = document.createElement('tr');
                    element.innerHTML = cart;
                    document.body.getElementsByClassName('tbody')[0].appendChild(element);  
                 }
                  
            })
           
}
getOrders();
function log(){
    if (sessionStorage.getItem('state') == 'loggedIn'){
        sessionStorage.setItem('state','loggedOut');
        sessionStorage.setItem('accessToken', '');
        
        document.getElementById("logState").innerHTML = 'SIGN IN';
    }
    else{
        window.location.href = '../verfication/login.html';
    }
}
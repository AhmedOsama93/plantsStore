if (sessionStorage.getItem('state') == 'loggedIn'){   
    document.getElementById("logState").innerHTML = 'SIGN OUT';
}
function getProducts(){
    const url = 'http://localhost:8081/user/cart/getCartItemsForUser';
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
    }).then(s=>{
        console.log(s)
            for (var key in s){
                const url = `http://localhost:8081/user/getProductByID/${s[key].id.productID}`;
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
                    console.log(e.quantity)
                    console.log(s[key].quantity)
                        let cart = 
                        `<tr>
                            <td class="td1">
                                <div class="row">
                                    <div class="col-md-3 text-left">
                                        <img src="../home/img/${e.id}.png" alt=""
                                            class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                                    </div>
                                </div>
                            </td>
                            <td class="td2">$${e.price}</td>
                            <td class="td2">
                                <input type="number" id = "${e.id}" onchange="quantity(${e.id})" class="input1" value=${s[key].quantity}>
                            </td>
                            <td class="td2">
                                <button class="order" id ="order${e.id}" onclick = "order(${e.id})">ORDER</button>
                            </td>
                            <td class="td3">
                                <button class="delete" id="delete" onclick = "deleteItem(${e.id})">DELETE</button>
                            </td>
                        </tr>`;
                        var element = document.createElement('tr');
                        element.innerHTML = cart;
                        document.body.getElementsByClassName('tbody')[0].appendChild(element);  
                      
                })
               
            }
    })
}
getProducts();
function quantity(id){
    let quantity = document.getElementById(id).value;
    let url = `http://localhost:8081/user/cartExactQuantity/${id}/${quantity}`;
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
                console.log (response.ok);
            }
    })
}

function order(id){
    let url = `http://localhost:8081/user/orderOneCartItem/${id}`;
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
                document.getElementById(`order${id}`).innerHTML = 'ORDERED';
                document.getElementById(`order${id}`).disabled = true;
            }
    })

}
function deleteItem(id){
    let url = `http://localhost:8081/user/cart/deleteOneCartItem/${id}`;
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
                window.location.href = 'cart.html';
            }
    })
}
function deleteAll(){
    let url = `http://localhost:8081/user/cart/deleteAllCartItem`;
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
                window.location.href = 'cart.html';
            }
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
if (sessionStorage.getItem('state') == 'loggedIn'){
    
    document.getElementById("state").innerHTML = 'SIGN OUT';
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
                    <td class="td2">${e[key]}0</td>
                    <td class="td2">
                        <input type="number" id = "${e[key].id.productID}" class="input1" value="1">
                    </td>
                    <td class="td2">
                        <button class="order" id ="order${e[key].id.productID}" onclick = "order(${e[key].id.productID})">ORDER</button>
                    </td>
                    <td class="td3">
                        <button class="delete" id="delete" onclick = "delete(${e[key].id.productID})">DELETE</button>
                    </td>
                </tr>`;
                var element = document.createElement('tr');
                element.innerHTML = cart;
                document.body.getElementsByClassName('tbody')[0].appendChild(element);        
            }
    })
}
getProducts();

function order(id){
    let quantity = document.getElementById(id).value;
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
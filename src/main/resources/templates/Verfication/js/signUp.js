function sign(){
    
    let fname = document.getElementById('fname').value;
    let lname = document.getElementById('lName').value;
    let username = document.getElementById('user').value;
    let password = document.getElementById('pass').value;
    let address = document.getElementById('address').value;
    let city = document.getElementById('city').value;
    let phone = document.getElementById('phone').phone;

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8081/visitor/registerNewUser";

    xhr.open("POST", url, true);

     xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.href = "verify.html";
        }
    };
    var data =JSON.stringify( {
        "getfName": fname,
        "getlName": lname,
        "username":username,
        "password": password,
        "verifyCode": "string",
        "verifyCodePassword": "string",
        "phoneNo": phone,
        "address1": address,
        "address2": "string",
        "city": city,
        "completeContactInfo": true,
        "numOfOrders": 0,
        "active": 0,
        "seller": true,
        "admin": true,
        "contactInfoComplete": true
      });
    xhr.send(data);
}

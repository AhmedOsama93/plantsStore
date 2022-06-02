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
        if(xhr.status !== 200){
            alert('Already Signed up!')
        }
    };
    var data =JSON.stringify( {
        "fName": fname,
        "lName": lname,
        "username":username,
        "password": password,
        "phoneNo": phone,
        "address1": address,
        "city": city,
      });
    xhr.send(data);
}

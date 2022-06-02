function validate(){
    let fname = document.getElementById('fname').value;
    let lname = document.getElementById('lName').value;
    let username = document.getElementById('user').value;
    let password = document.getElementById('pass').value;
    let address = document.getElementById('address').value;
    let city = document.getElementById('city').value;
    let phone = document.getElementById('phone').phone;


}

function signup() {
    var regName = /^[a-zA-Z]+$/;

    var regEmail = /^[a-zA-z0-9.-_]+@[a-zA-Z]+\.(com)$/;
    var isValidFirstName = false;
    var isValidLastName = false;
    var isValidEmail = false;
    var isValidPassword = false;
    var isValidConfirmation = false;
    var emailWarning = document.getElementById("emailWarning");

    var firstNameWarning = document.getElementById("firstNameWarning");
    var lastNameWarning = document.getElementById("lastNameWarning");
    var passwordWarning = document.getElementById("passwordWarning");
    var confirmationWarning = document.getElementById("confirmationWarning");
    let fname = document.getElementById('fname').value;
    let lname = document.getElementById('lName').value;
    let username = document.getElementById('user').value;
    let password = document.getElementById('pass').value;
    let address = document.getElementById('address').value;
    let city = document.getElementById('city').value;
    let phone = document.getElementById('phone').phone;

    if(regName.test(fname) || fname === ""){
        firstNameWarning.classList.remove("warning-hide");
        firstNameWarning.setAttribute('title', 'only alphabets are allowed')
        isValidFirstName = false;
    }
    else if (regName.test(fname)){
        firstNameWarning.classList.add("warning-hide");
        isValidFirstName = true;
    }
    else if(fname == "")
    {
        firstNameWarning.classList.remove("warning-hide");
        firstNameWarning.setAttribute('title', 'First name is required')
        isValidFirstName = false;
    }

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8081/visitor/registerNewUser";

    xhr.open("POST", url, true);

    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.href = "verify.html";
        }
        if (xhr.status !== 200) {

        }
    };
    var data = JSON.stringify({
        "fName": fname,
        "lName": lname,
        "username": username,
        "password": password,
        "phoneNo": phone,
        "address1": address,
        "city": city,
    });
    xhr.send(data);

}

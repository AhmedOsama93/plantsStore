var regName = /^[a-zA-Z]+$/;
var regEmail = /^[a-zA-z0-9.-_]+@[a-zA-Z]+\.(com)$/;
var isValidFirstName = false;
var isValidLastName = false;
var isValidEmail = false;
var isValidPassword = false;
var isValidConfirmation = false;
function onLoad(){
    var emailWarning = document.getElementById("emailWarning");
    var firstNameWarning = document.getElementById("firstNameWarning");
    var lastNameWarning = document.getElementById("lastNameWarning");
    var passwordWarning = document.getElementById("passwordWarning");
    var confirmationWarning = document.getElementById("confirmationWarning");
}
function onSubmitClick(){
    var firstName = document.getElementById("user").value;
    if(!regName.test(firstName) && firstName != ""){
        console.log("hhhhhhhh")
        firstNameWarning.classList.remove("warning-hide");
        firstNameWarning.setAttribute('title', 'only alphabets are allowed')
        isValidFirstName = false;
    }
    else if (regName.test(firstName)){
        firstNameWarning.classList.add("warning-hide");
        isValidFirstName = true;
    }
    else if(firstName == "")
    {
        firstNameWarning.classList.remove("warning-hide");
        firstNameWarning.setAttribute('title', 'First name is required')
        isValidFirstName = false;
    }

    var lastName = document.getElementById("lastName").value;
    if(!regName.test(lastName) && lastName != ""){
        lastNameWarning.classList.remove("warning-hide");
        lastNameWarning.setAttribute('title', 'only alphabets are allowed')
        isValidLastName = false;
    }
    else if (regName.test(lastName)){
        lastNameWarning.classList.add("warning-hide");
        isValidLastName = true;
    }
    else if(lastName == "")
    {
        lastNameWarning.classList.remove("warning-hide");
        lastNameWarning.setAttribute('title', 'Last name is required')
        isValidLastName = false;
    }

    var email = document.getElementById("email").value;
    if(!regEmail.test(email) && email != "")
    {
        emailWarning.classList.remove("warning-hide");
        emailWarning.setAttribute('title', 'Invalid email adress');
        isValidEmail = false;
    }
    else if(regEmail.test(email))
    {
        emailWarning.classList.remove("warning-hide");
        emailWarning.setAttribute('title', 'Email is already taken');
        isValidEmail = false;
    }
    else if(email == "")
    {
        emailWarning.classList.remove("warning-hide");
        emailWarning.setAttribute('title', 'Email is required');
        isValidEmail = false;
    }

    var pass = document.getElementById("pass").value;
    if(pass == ""){
        passwordWarning.classList.remove("warning-hide");
        passwordWarning.setAttribute('title', 'Password is required');
        isValidPassword = false;
    }
    else if(pass.length < 8)
    {
        passwordWarning.classList.remove("warning-hide");
        passwordWarning.setAttribute('title', 'Password must contain more than 7 characters');
        isValidPassword = false;
    }
    else{
        passwordWarning.classList.add("warning-hide");
        isValidPassword = true;
    }

    var con = document.getElementById("con").value;
    if(con != pass)
    {
        confirmationWarning.classList.remove("warning-hide");
        confirmationWarning.setAttribute('title', 'Password confirmation does not match');
        isValidConfirmation = false;
    }
    else if(con == "")
    {
        confirmationWarning.classList.remove("warning-hide");
        confirmationWarning.setAttribute('title', 'Confirmation is required');
        isValidConfirmation = false;
    }
    else
    {
        confirmationWarning.classList.add("warning-hide");
        isValidConfirmation = true;
    }
    if( isValidFirstName && isValidLastName  && isValidEmail  && isValidPassword  && isValidConfirmation )
    {
        alert("Success");

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
        "getfName": fname,
        "getlName": lname,
        "username":username,
        "password": password,
        "phoneNo": phone,
        "address1": address,
        "city": city,
      });
    xhr.send(data);
    }
}
var regName = /^[a-zA-Z]+$/;
var regPhone = /^[0-9]+$/;
var isValidFirstName = false;
var isValidLastName = false;

var isValidPhone = false;
var isValidCity = false;
var isValidAddress1 = false;
var isValidAddress2 = false;

function onLoad(){
    
    var firstNameWarning = document.getElementById("firstNameWarning");
    var lastNameWarning = document.getElementById("lastNameWarning");
    var phoneWarning = document.getElementById("phoneWarning");
    var cityWarning = document.getElementById("cityWarning");
    var address1 = document.getElementById("address1Warning");
    var address2 = document.getElementById("address2Warning");
}

function onSubmitClick(){
    var fname = document.getElementById("fname").value;
    if(!regName.test(fname) && fname != ""){
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

    var Lname = document.getElementById("Lname").value;
    if(!regName.test(Lname) && Lname != ""){
        lastNameWarning.classList.remove("warning-hide");
        lastNameWarning.setAttribute('title', 'only alphabets are allowed')
        isValidLastName = false;
    }
    else if (regName.test(Lname)){
        lastNameWarning.classList.add("warning-hide");
        isValidLastName = true;
    }
    else if(Lname == "")
    {
        lastNameWarning.classList.remove("warning-hide");
        lastNameWarning.setAttribute('title', 'Last name is required')
        isValidLastName = false;
    }

    var address1 = document.getElementById("address1").value;
    
     if(address1 == "")
    {
        address1Warning.classList.remove("warning-hide");
        address1Warning.setAttribute('title', 'Address is required')
        isValidAddress1 = false;
    }

    else {
        address1Warning.classList.add("warning-hide");
        isValidAddress1 = true;
    }

    var address2 = document.getElementById("address2").value;
    
     if(address2 == "")
    {
        address2Warning.classList.remove("warning-hide");
        address2Warning.setAttribute('title', 'Address is required')
        isValidAddress2 = false;
    }

    else {
        address2Warning.classList.add("warning-hide");
        isValidAddress2 = true;
    }
    
    var City = document.getElementById("City").value;
    if(!regName.test(City) && City != ""){
        cityWarning.classList.remove("warning-hide");
        cityWarning.setAttribute('title', 'only alphabets are allowed')
        isValidCity = false;
    }
    else if (regName.test(City)){
        cityWarning.classList.add("warning-hide");
        isValidCity = true;
    }
    else if(City == "")
    {
        cityWarning.classList.remove("warning-hide");
        cityWarning.setAttribute('title', 'City is required')
        isValidCity = false;
    }
    
    var phone = document.getElementById("phone").value;
    if(!regPhone.test(phone) && phone != "")
    {
        {
            phoneWarning.classList.remove("warning-hide");
            phoneWarning.setAttribute('title', 'Only numbers are allowed')
            isValidPhone = false;
        }
    }
    else if(phone == "")
    {
        phoneWarning.classList.remove("warning-hide");
        phoneWarning.setAttribute('title', 'Phone is required')
        isValidPhone = false;
    }
    else if(regPhone.test(phone))
    {
        {
            phoneWarning.classList.add("warning-hide");
            
            isValidPhone = true;
        }
    }

    if( isValidFirstName && isValidLastName   && isValidPhone  && isValidAddress1 && isValidAddress2 && isValidCity )
    {
        alert("Success");
        
    }
}
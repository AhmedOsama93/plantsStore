function verify(){
    let code = document.getElementById('code').value;
    let password1 = document.getElementById('password1').value;
    let password2 = document.getElementById('password2').value;
    
    const url = `http://localhost:8081/visitor/changeForgetPassword`;
    
    let h = new Headers();
    h.append('Accept', 'application/json');
    let formData = new FormData();

    formData.append("code", code);
    formData.append("newPassword1", password1);
    formData.append("newPassword2", password2);

    let request = new Request(url,{
        method: 'POST',
        headers: h,
        body: formData,
        mode:'cors'

    });
    fetch(request).then((response)=>{
            if (response.ok){
                window.location.href = "login.html";
            }
    }).catch(e=>{
        alert('Invalid Verification Code');
    })
}
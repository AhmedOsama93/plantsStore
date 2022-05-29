function login(){
    
    let email = document.getElementById('email').value;
    let password = document.getElementById('pass').value;
    
    const url = 'http://localhost:8081/login';
    
    let h = new Headers();
    h.append('Accept', 'application/json');

    let formData = new FormData();
    formData.append("username", email);
    formData.append("password", password);

    let request = new Request(url,{
        method: 'POST',
        headers: h,
        body: formData,
        mode:'cors'

    });
    fetch(request).then((response)=>{
        var element = document.createElement('div');
            if (response.ok){
                window.location.href = "../home/index.html";
                sessionStorage.setItem('state', 'loggedIn');
                return response.json();
            }
    }).then(e=>{
    sessionStorage.setItem('accessToken',e.access_token);
    console.log(sessionStorage.getItem('accessToken'));
            
    }).catch(e=>{
        alert('Invalid E-mail and Password');
    })

}
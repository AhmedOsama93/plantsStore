function verify(){
    let code = document.getElementById('code').value;
    
    const url = `http://localhost:8081/visitor/enterVerifyCode/${code}`;
    
    let h = new Headers();
    h.append('Accept', 'application/json');

    let request = new Request(url,{
        method: 'POST',
        headers: h,
        mode:'cors'

    });
    fetch(request).then((response)=>{
            if (response.ok){
                
                window.location.href = "../home/index.html";
                sessionStorage.setItem('state', 'loggedIn');
                return response.json();
            }
    }).then(e=>{
        console.log(e);
    sessionStorage.setItem('accessToken',e.access_token);
    console.log(sessionStorage.getItem('accessToken'));
            
    }).catch(e=>{
        alert('Invalid Verification Code');
    })

}
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
            if (response.ok){
                return response.json();
            }
    }).then(e=>{
        const url = 'http://localhost:8081/visitor/isAdmin';
        
        sessionStorage.setItem('accessToken',e.access_token);
                
        let h = new Headers();
        let token = sessionStorage.getItem('accessToken');
        h.append('Authorization', `Bearer ${token}`);

        let request = new Request(url,{
            method: 'GET',
            headers: h,
            mode:'cors',
        });
        fetch(request).then((response)=>{
            var element = document.createElement('div');
                if (response.ok){
                    return response.json();
                }
        }).then(e=>{
            if(e){
                window.location.href = "../admin/admin.html";
                sessionStorage.setItem('state', 'loggedIn');
                sessionStorage.setItem('role', 'admin');
            }
            else {
                window.location.href = "../home/index.html";
                sessionStorage.setItem('state', 'loggedIn');
                sessionStorage.setItem('role', 'user');
            }
        })
    }).catch(e=>{
        alert('Invalid E-mail and Password');
    })

}
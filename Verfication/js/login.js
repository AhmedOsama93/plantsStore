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
                if (response.ok){
                    return response.json();
                }
        }).then(e=>{
            if(!e){

                const url = 'http://localhost:8081/visitor/isUser';
                        
                let h = new Headers();
                let token = sessionStorage.getItem('accessToken');
                h.append('Authorization', `Bearer ${token}`);
        
                let request = new Request(url,{
                    method: 'GET',
                    headers: h,
                    mode:'cors',
                });
                fetch(request).then((response)=>{
                        if (response.ok){
                            return response.json();
                        }
                }).then(e=>{
                    if(e){
                        window.location.href = "../home/index.html";
                        sessionStorage.setItem('state', 'loggedIn');
                        sessionStorage.setItem('role', 'user');
                    }
                    else{
                        document.getElementById('email').style = `
                        border: 1px solid #F23030;
                        box-shadow: 0 1px 2px 0 #F23030;`;
                        document.getElementById('pass').style = `
                        border: 1px solid #F23030;
                        box-shadow: 0 1px 2px 0 #F23030;`;
                        window.location.href = "verifySignUp.html";
                    }
                })
            }
            else {
                window.location.href = "../admin/admin.html";
                sessionStorage.setItem('state', 'loggedIn');
                sessionStorage.setItem('role', 'admin');
            }
        })
    }).catch(e=>{
        document.getElementById('email').style = `
        border: 1px solid #F23030;
        box-shadow: 0 1px 2px 0 #F23030;
        `;
        document.getElementById('pass').style = `
        border: 1px solid #F23030;
        box-shadow: 0 1px 2px 0 #F23030;
        `;
        document.getElementById('warning').innerHTML = "<br>Invalid Email or Password!"
    })

}

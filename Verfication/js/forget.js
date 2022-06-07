
function send(){
    let email = document.getElementById('email').value;
    const url = `http://localhost:8081/visitor/forgetPasswordRequest/${email}`;
    let h = new Headers();
    let request = new Request(url,{
        method: 'POST',
        headers: h,
        mode: 'cors'
    });
    fetch(request).then((response)=>{
            if (response.ok){
                window.location.href = "verify.html";
            }
    }).then(e=>{
       
    })
}

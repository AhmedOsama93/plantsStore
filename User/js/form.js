function edit(){
    let fname = document.getElementById('fname').value;
    let lname = document.getElementById('Lname').value;
    let phone = document.getElementById('phone').value;
    let city = document.getElementById('City').value;
    let address1 = document.getElementById('address1').value;
    let address2 = document.getElementById('address2').value;
    let formData = new FormData();
    if(fname !== ''){
        formData.append("fName", fname);
    }
    if(lname !== ''){
        formData.append("lName", lname);
    }    
    if(address1 !== ''){
        formData.append("address1", address1);
    }
    if(city !== ''){
        formData.append("city", city);
    }
    if(phone !== ''){
        
        formData.append("phoneNo", phone);
    }
    if(address2 !== ''){
        formData.append("address2", address2);
    }

    const url = `http://localhost:8081/user/editUserForUser`;
    let h = new Headers();
    let token = sessionStorage.getItem('accessToken');
    h.append('Authorization', `Bearer ${token}`);
    let request = new Request(url,{
        method: 'POST',
        headers: h,
        body: formData,
        mode:'cors',
    });
    fetch(request).then((response)=>{
            if (response.ok){
                window.location.href = "profile.html";
            }
    })
}
function getInfo(){
    const url = 'http://localhost:8081/user/getMyData'
    let h = new Headers();
        let token = sessionStorage.getItem('accessToken');
        h.append('Authorization', `Bearer ${token}`);
    
        let request = new Request(url,{
            method: 'GET',
            headers: h,
            mode: 'cors'
        });
        fetch(request).then((response)=>{
                if (response.ok){
                    return response.json();
                }
        }).then(e=>{
            console.log(e)
             document.getElementById('City').value = e.city;
            document.getElementById('phone').value = e.phoneNo;
            document.getElementById('address1').value = e.address1;
             document.getElementById('Lname').value = e.lName;
            document.getElementById('fname').value = e.fName;
           
            
        })
    }
    getInfo();

if (sessionStorage.getItem('state') == 'loggedIn'){
    
    document.getElementById("logState").innerHTML = 'SIGN OUT';
}
if(sessionStorage.getItem('role') == 'admin'){
    var element = document.createElement('li');
    element.innerHTML = `<a href="../admin/admin.html">Admin Panel</a>`
    document.getElementsByClassName('ul')[0].append(element);
}
function chapter(chapter){
    window.location.href = 'home.html';
    sessionStorage.setItem('chapter', chapter);
    if (sessionStorage.getItem('chapter') == 'summer'){
        console.log(chapter)
    }
    if (sessionStorage.getItem('chapter') == 'winter'){
        console.log(chapter)
    }
    if (sessionStorage.getItem('chapter') == 'autumn'){
        console.log(chapter)
    }
    if (sessionStorage.getItem('chapter') == 'spring'){
        console.log(chapter)
    }
}
function log(){
    if (sessionStorage.getItem('state') == 'loggedIn'){
        sessionStorage.setItem('state','loggedOut');
        sessionStorage.removeItem('accessToken');
        sessionStorage.removeItem('role');
        
        document.getElementById("logState").innerHTML = 'SIGN IN';
    }
    else{
        window.location.href = '../verfication/login.html';
    }
}
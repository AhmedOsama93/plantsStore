if (sessionStorage.getItem('state') == 'loggedIn'){
    
    document.getElementById("state").innerHTML = 'SIGN OUT';
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



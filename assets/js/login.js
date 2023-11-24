var passwordImageElement = document.getElementById("passwordType");
var passwordElement = document.getElementById("password");
var forgotPasswordFrame = document.getElementById("forgotPassword");
var passwordWrong = document.getElementById("wrongPassword");

passwordImageElement.addEventListener("click",passwordTypeChange);

function passwordTypeChange(){
    if(passwordImageElement.src.endsWith("/assets/img/hidden.svg")){
        passwordImageElement.src = "../assets/img/openEye.svg";
        passwordElement.type = "text";
    }
    else {
        passwordImageElement.src = "../assets/img/hidden.svg";
        passwordElement.type = "password";
    }
}

function changeView() {
    var centerDiv = document.getElementsByClassName("container");
    if (forgotPasswordFrame.style.display === "flex") {
        forgotPasswordFrame.style.display = "none";
        centerDiv[0].style.filter = "blur(0px)";

    } else {
        forgotPasswordFrame.style.display = "flex";
        centerDiv[0].style.filter = "blur(3px)";
    }
}

function changeView2(){
    var centerDiv = document.getElementsByClassName("container");
    if (passwordWrong.style.display === "flex") {
        passwordWrong.style.display = "none";
        centerDiv[0].style.filter = "blur(0px)";

    } else {
        passwordWrong.style.display = "flex";
        centerDiv[0].style.filter = "blur(3px)";
    }
}

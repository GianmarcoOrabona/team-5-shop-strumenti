function validatePassword() {
  var password = document.getElementById("password").value;
  var confirmPassword = document.getElementById("confirm-password").value;
  if (password != confirmPassword) {
    alert("Le password non corrispondono!");
    return false;
  }
  return true;
}

document.getElementById("btn").addEventListener('click', function(e){
//e.preventDefault();
//alert("ciao")
if(!validatePassword() ){
   e.preventDefault();
}
})
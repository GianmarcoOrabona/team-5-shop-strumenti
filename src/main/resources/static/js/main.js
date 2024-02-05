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
if(!validatePassword() ){
   e.preventDefault();
}
})

function validateForm() {
 var name = document.getElementById("nome").value;
 var cognome = document.getElementById("cognome").value;
 var citta = document.getElementById("citta").value;
 var indirizzo = document.getElementById("indirizzo").value;
 var cap = document.getElementById("cap").value;
 if (name != "" && cognome != "" && citta != "" && indirizzo != "" && cap != ""){
     alert("Registrazione andata a buon fine");
     return false;
   }
   return true;
}

document.getElementById("btn").addEventListener('click', function(e){
if(validateForm() ){
   e.preventDefault();
   alert("Non tutti i campi sono stati compilati")
}
})


function validateForm() {
 var name = document.getElementById("nome").value;
 var cognome = document.getElementById("cognome").value;
 var citta = document.getElementById("citta").value;
 var indirizzo = document.getElementById("indirizzo").value;
 var cap = document.getElementById("cap").value;
 if (name != "" && cognome != "" && citta != "" && indirizzo != "" && cap != ""){
     alert("Registrazione andata a buon fine");
     return false;
   }
   return true;
}


document.getElementById("btn").addEventListener('click', function(e){
if(validateForm() ){
   e.preventDefault();
   alert("Non tutti i campi sono stati compilati")
}
})


function validateFormVendita() {
 var name = document.getElementById("fname").value;
 var cognome = document.getElementById("lname").value;
 var email = document.getElementById("email").value;
 var indirizzo = document.getElementById("address").value;
 var citta = document.getElementById("city").value;
 if (name != "" && cognome != "" && email != "" && indirizzo != "" && city != ""){
     alert("Aquisto andato a buon fine");
     return false;
   }
   return true;
}

document.getElementById("btnForm").addEventListener('click', function(e){
if(validateFormVendita() ){
   e.preventDefault();
   alert("Non tutti i campi sono stati compilati")
}
})

const passwordField = document.getElementById('password');
const showPasswordCheckbox = document.getElementById('showPassword');
  showPasswordCheckbox.addEventListener('change', function () {
            if (showPasswordCheckbox.checked) {
                passwordField.setAttribute('type', 'text');
            } else {
                passwordField.setAttribute('type', 'password');
            }
        });

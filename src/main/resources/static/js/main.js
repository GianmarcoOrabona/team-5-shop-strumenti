document.getElementById('liveToastBtn').addEventListener('click', function() {
  var toast = new bootstrap.Toast(document.getElementById('liveToast'))
  toast.show()
})

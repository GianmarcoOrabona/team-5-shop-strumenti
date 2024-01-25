const url = window.location.pathname;
const addButton = document.getElementById('addButton');

    if (url === '/admin/strumenti') {
        addButton.style.display = 'block';
    } else {
        addButton.style.display = 'none';
    }
    const editButton = document.getElementById('editButton');

        if (url === '/admin/strumenti') {
            editButton.style.display = 'block';
        } else {
            editButton.style.display = 'none';
        }

    let deliteButton = document.getElementById('deliteButton');

        if (url === '/admin/strumenti') {
            deliteButton.style.display = 'block';
        } else {
            deliteButton.style.display = 'none';
        }


    let buyButton = document.getElementById('buyButton');

    if (url === '/strumenti') {
      buyButton.style.display = 'block';
    } else {
      buyButton.style.display = 'none';
                }

    function enableButton(buttonId, link) {
        const url = window.location.pathname;
         if (url === link) {
                buttonId.style.display = 'block';
            } else {
                buttonId.style.display = 'none';
            }
    }
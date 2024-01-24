const url = window.location.pathname;
const addButton = document.getElementById('addButton');

    if (url === '/adimin/strumenti') {
        addButton.style.display = 'block';
    } else {
        addButton.style.display = 'none';
    }
    const editButton = document.getElementById('editButton');

        if (url === '/adimin/strumenti') {
            editButton.style.display = 'block';
        } else {
            editButton.style.display = 'none';
        }

    let deliteButton = document.getElementById('deliteButton');

        if (url === '/adimin/strumenti') {
            deliteButton.style.display = 'block';
        } else {
            deliteButton.style.display = 'none';
        }

function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const appId = document.getElementById('appId').value;

    const data = {
        email: email,
        password: password,
        appId: appId
    };
    fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => {
        // Display JWT Token
        document.getElementById('tokenSection').style.display = 'inline';
        document.getElementById('jwtToken').innerText = data.jwtToken;
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Login failed!');
    });
}

function copyToken() {
    const jwtToken = document.getElementById('jwtToken');

    // Create a textarea element to copy text to clipboard
    const textarea = document.createElement('textarea');
    textarea.value = jwtToken.innerText;
    document.body.appendChild(textarea);

    // Select and copy the text
    textarea.select();
    document.execCommand('copy');

    // Remove the textarea
    document.body.removeChild(textarea);

    alert('Token copied to clipboard!');
}

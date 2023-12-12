function registerApp() {
    // Get form data
    const appName = document.getElementById('appName').value;
    const callbackUrl = document.getElementById('callbackUrl').value;

    // Create app object
    const app = {
        name: appName,
        enabled: true,
        callbackUrl: callbackUrl
    };

    // Send POST request
    fetch('http://localhost:8080/app/createApp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(app),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        alert('App registered successfully!');
        document.getElementById('result').innerHTML = `<p>ApplicationId: </p> ${data.id}`
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Error registering app!');
    });
}
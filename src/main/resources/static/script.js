function registerUser() {
    // Get form data
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const name = document.getElementById('name').value;
    const role = document.getElementById('role').value;
    const appId = document.getElementById('appId').value;

    // Create user object
    const user = {
        email: email,
        password: password,
        name: name,
        role: role,
        enabled: true,
        appId: parseInt(appId)  // Convert to integer
    };

    // Send POST request
    fetch('http://localhost:8080/home/createUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        alert('User registered successfully!');
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Error registering user!');
    });
}

function authenticate() {
    const token = document.getElementById('token').value;
    const email = document.getElementById('email').value;
    const application = document.getElementById('application').value;

    const data = {
        token: token,
        emailId: email,
        appId: application
    };
    console.log(data);
    fetch('http://localhost:8080/auth/authenticate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(result => {
        document.getElementById('result').innerHTML = `<p><h2>Authenticated</h2>:</br> ${JSON.stringify(result)}</p>`;
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('result').innerHTML = '<p>Authentication failed!</p>';
    });
}

function fetchUserData() {
    const bearerToken = document.getElementById('bearerToken').value;

    fetch('http://localhost:8080/auth/admin', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${bearerToken}`,
            'Content-Type': 'application/json'
        },
        body: ''
    })
    .then(response => response.json())
    .then(data => {
        displayUserData(data);
    })
    .catch(error => {
        console.error('Error:', error);
        const tableBody = document.querySelector('#userTable');
        tableBody.innerHTML = 'Unauthorized!'
    });
}

function displayUserData(users) {
    const tableContainer = document.getElementById('tableContainer');
    tableContainer.style.visibility = "visible";
    const tableBody = document.querySelector('#userTable tbody');
    tableBody.innerHTML = ''; // Clear existing rows
    users.forEach(user => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${user.userId}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>${user.username}</td>
            <td><input type="checkbox" ${user.enabled ? 'checked' : ''}></td>

        `;
        tableBody.appendChild(row);
    });
}

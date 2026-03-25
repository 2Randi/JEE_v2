document.querySelector('#loginForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    const response = await fetch('/authenticate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        const result = await response.json();
        localStorage.setItem('jwt', result.jwt);
        alert('Login successful!');
        window.location.href = '/protected-page';
    } else {
        alert('Login failed!');
    }
});
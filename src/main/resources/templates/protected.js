const jwt = localStorage.getItem('jwt');
if (!jwt) {
    window.location.href = '/login';
} else {
    fetch('/some-protected-endpoint', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + jwt
        }
    }).then(response => {
        if (response.status === 401) {
            localStorage.removeItem('jwt');
            window.location.href = '/login';
        } else {
            return response.json();
        }
    }).then(data => {
        console.log(data);
    }).catch(error => {
        console.error(error);
    });
}
function logout() {
    localStorage.removeItem('jwt');
    alert('Déconnexion réussie!');
    window.location.href = '/login';
}
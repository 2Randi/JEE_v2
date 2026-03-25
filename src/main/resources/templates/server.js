
const express = require('express');
const jwt = require('jsonwebtoken');
const cookieParser = require('cookie-parser');
const app = express();

// Clé secrète pour signer le JWT (utilisez une clé forte et secrète)
const SECRET_KEY = 'votre_clé_secrète';
const PORT = 3000;

// Middleware pour vérifier la validité du jeton JWT
function verifyToken(req, res, next) {
  const token = req.cookies.jwtToken; // Récupérer le token depuis les cookies

  if (!token) {
    return res.status(403).send('Accès interdit. Token manquant.');
  }

  jwt.verify(token, SECRET_KEY, (err, decoded) => {
    if (err) {
      return res.status(401).send('Token invalide.');
    }
    req.user = decoded; // Ajouter les informations utilisateur à la requête
    next(); // Passer à la route suivante
  });
}

// Middleware pour vérifier si l'utilisateur est authentifié
app.use(cookieParser()); // Utiliser le cookie-parser pour lire les cookies
app.use(express.json());  // Pour analyser les JSON

// Route protégée qui nécessite une vérification du JWT
app.get('/dashboard', verifyToken, (req, res) => {
  res.send(`Bienvenue sur le dashboard, ${req.user.username}`);
});

// Route pour la déconnexion (effacer le cookie JWT)
app.post('/logout', (req, res) => {
  res.clearCookie('jwtToken'); // Supprimer le cookie JWT
  res.send('Déconnexion réussie');
});

// Route pour la connexion (pour générer un token JWT)
app.post('/login', (req, res) => {
  const { username, password } = req.body;

  // Vous devez valider l'utilisateur avec votre base de données ici
  if (username === 'utilisateur' && password === 'motdepasse') {
    // Créer un jeton JWT
    const token = jwt.sign({ username }, SECRET_KEY, { expiresIn: '1h' });
    res.cookie('jwtToken', token, { httpOnly: true, secure: false }); // Créer un cookie JWT
    return res.send('Connexion réussie');
  } else {
    return res.status(401).send('Identifiants invalides');
  }
});

// Démarrer le serveur
app.listen(PORT, () => {
  console.log(`Serveur démarré sur http://localhost:${PORT}`);
});

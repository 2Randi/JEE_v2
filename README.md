# Nom du projet 
*Gestion des campus universitaire de l'Université de Montpellier*

## Description
Ce projet est une application web développée avec Spring Boot, conçue pour explorer et mettre en pratique les concepts essentiels du framework Spring.
Il inclut :
- La configuration d’un projet Spring Boot
- La création de contrôleurs, services et modèles
- L’intégration d’une base de données via Spring Data JPA
- La mise en place de la sécurité avec Spring Security
- Encodage et hachage des mots de passe : BCrypt

Une architecture claire et évolutive

L’objectif est de fournir une application web complète, de bout en bout, tout en appliquant les bonnes pratiques du développement Java moderne.

## Technologies utilisées

| Technologie        | Rôle                          |
|-------------------|-------------------------------|
| Java              | Langage principal             |
| Spring Boot       | Framework backend             |
| Spring MVC        | Gestion des contrôleurs       |
| Spring Data JPA   | Accès aux données             |
| Hibernate         | ORM                           |
| Spring Security   | Sécurisation                  |
| JWT (JSON Web Token) | Authentification stateless           |
| Maven             | Gestion des dépendances       |
| MySQL / MariaDB   | Base de données             |
| HTML / CSS / JS   | Interface utilisateur         |
| Bootstrap         | Framework CSS pour le design  |


## Structure du projet

```
 📦 src
┣ 📂 main/
┃ ┣ 📂 java/
┃ ┃ ┗ 📂 fr.umontpellier.jeeS/
┃ ┃    ┣ 📂 Security
┃ ┃    ┣ 📂 Config
┃ ┃    ┣ 📂 controller
┃ ┃    ┣ 📂 service
┃ ┃    ┣ 📂 repository
┃ ┃    ┗ 📂 model
┃ ┗ 📂 resources/
┃    ┣ 📄 application.properties
┃    ┗ 📂 templates
┗ 📂 test/

```

 ## Installation & exécution
1️ - Cloner le projet

git clone https://github.com/2Randi/JEE_v2.git
cd jeeS

2️ - Configurer la base de données
Dans application.properties :

spring.datasource.url=jdbc:mysql://localhost:3306/jee
spring.datasource.username=root
spring.datasource.password=ton_mot_de_passe
spring.jpa.hibernate.ddl-auto=update

3️ - Lancer l’application
bash
mvn spring-boot:run
L’application sera disponible sur :
http://localhost:8080

## Sécurité
Le projet utilise Spring Security pour protéger les endpoints.

- rôles : administrateur, gestionnaire, étudiant
- Accès selon les rôles
- méthode d’authentification : JWT
- Mot de passe crypté : BCryptPasswordEncode

## Captures d’écran

<img width="1741" height="868" alt="um1" src="https://github.com/user-attachments/assets/cb1eb02b-67b3-42bb-a87a-c6179a7f07fb" />

<img width="447" height="457" alt="um3" src="https://github.com/user-attachments/assets/d5978696-0f84-43d0-91b8-883cf2ba6642" />

<img width="591" height="439" alt="um5" src="https://github.com/user-attachments/assets/77e48397-7ddd-4609-b10c-5ecbd5712011" />

<img width="1731" height="905" alt="um6" src="https://github.com/user-attachments/assets/b4293dd0-0289-4db7-ad8d-5abcf65fbcf1" />

## État du projet

- [x] Configuration Spring Boot
- [x] Architecture MVC
- [ ] Ajout de nouvelles fonctionnalités
- [ ] Tests unitaires
- [x] Documentation

## Auteur
RANDRIAMISAINA Tsiory (2Randi)  

Projet disponible sur GitHub :
https://github.com/2Randi/JEE_v2

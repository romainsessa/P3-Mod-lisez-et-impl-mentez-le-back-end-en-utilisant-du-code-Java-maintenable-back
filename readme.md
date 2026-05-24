# Estate

## Sommaire
- [Présentation du projet](#présentation-du-projet)
- [Instructions de lancement](#instructions-de-lancement)

---

## Présentation du projet

Application Angular et Java permettant de gérer des locations.

---

## Instructions de lancement

### Prérequis
- Node.js (version recommandée : LTS)
- Angular CLI
- Java 25
- Maven

### Installation Base de données
- Installer une base de données MySQL et démarrer sur le port 3306
- Déployer le script SQL sur une base de donnée nommée chatop

### Installation / Lancement back
- Mettre à jour les variables d'environnement : ${DB_USER} , ${DB_PASSWORD} , ${JWT_SECRET}
- Lancer la commande :
```bash
mvn spring-boot:run 
```

### Installation front
```bash
npm install
```

### Lancement front
```bash
ng serve
```

###  Ouvrir un navigateur à l’adresse
```bash
http://localhost:4200
```

### Swagger
```bash
http://localhost:3001/swagger-ui/index.html
```

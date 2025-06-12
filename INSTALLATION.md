# Installation du Système de Gestion Scolaire ISM

## Étapes d'installation avec phpMyAdmin

### 1. Créer la base de données

1. Ouvrez **phpMyAdmin** dans votre navigateur
2. Cliquez sur l'onglet **"Bases de données"**
3. Dans le champ "Nom de la base de données", tapez : `gestion_scolaire_ism`
4. Sélectionnez l'interclassement : `utf8mb4_general_ci`
5. Cliquez sur **"Créer"**

### 2. Importer le schéma et les données

1. Sélectionnez la base de données `gestion_scolaire_ism` que vous venez de créer
2. Cliquez sur l'onglet **"Importer"**
3. Cliquez sur **"Choisir un fichier"** et sélectionnez le fichier `database/schema.sql`
4. Laissez les options par défaut
5. Cliquez sur **"Exécuter"**

### 3. Vérifier l'installation

Après l'importation, vous devriez voir les tables suivantes dans votre base de données :
- `users` (3 utilisateurs de test)
- `classes` (5 classes)
- `modules` (5 modules)
- `etudiants` (5 étudiants)
- `inscriptions` (5 inscriptions)
- `demandes` (vide)
- `professeur_modules` (vide)
- `professeur_classes` (vide)

### 4. Configuration de la connexion

Modifiez le fichier `config/Database.php` selon votre configuration :

```php
$server = 'localhost:8889';        // Votre serveur MySQL
$dbname = 'gestion_scolaire_ism';  // Nom de la base de données
$username = 'root';                // Votre nom d'utilisateur MySQL
$password = 'root';                // Votre mot de passe MySQL
```

### 5. Comptes de test

Une fois l'installation terminée, vous pouvez vous connecter avec :

- **Responsable Pédagogique** : 
  - Email : `rp@ism.sn`
  - Mot de passe : `password`

- **Attaché de classe** :
  - Email : `attache@ism.sn`
  - Mot de passe : `password`

- **Professeur** :
  - Email : `prof@ism.sn`
  - Mot de passe : `password`

### 6. Démarrer l'application

1. Placez le projet dans votre dossier web (htdocs, www, etc.)
2. Accédez à l'application via : `http://localhost/votre-projet/public/`
3. Connectez-vous avec un des comptes de test

## Notes importantes

- Les mots de passe sont stockés en clair (non hashés) comme demandé
- La base de données contient des données de test pour faciliter les tests
- Assurez-vous que votre serveur web (Apache/Nginx) et MySQL sont démarrés
# Système de Gestion Scolaire ISM

## Description
Application web de gestion des inscriptions et de l'administration scolaire pour l'Institut Supérieur de Management (ISM).

## Fonctionnalités

### Responsable Pédagogique (RP)
- Créer et lister des classes (libellé, filière, niveau)
- Ajouter des professeurs et leurs modules
- Affecter des classes aux professeurs
- Traiter les demandes de suspension/annulation
- Consulter les statistiques de l'école

### Attaché de Classe
- Inscrire et réinscrire des étudiants
- Lister les étudiants par classe et année
- Consulter les demandes des étudiants

### Professeur
- Consulter ses classes et modules
- Lister ses étudiants

### Étudiant
- Formuler des demandes de suspension/annulation
- Consulter ses demandes

## Architecture

### Structure des dossiers
```
├── config/
│   └── Database.php          # Configuration base de données
├── src/
│   ├── models/              # Modèles de données
│   ├── controllers/         # Contrôleurs
│   ├── services/           # Services métier
│   └── repository/         # Accès aux données
├── views/                  # Vues HTML
├── public/                 # Point d'entrée et assets
└── database/              # Scripts SQL
```

### Technologies utilisées
- **Backend**: PHP 8+ avec architecture MVC
- **Base de données**: MySQL
- **Frontend**: Bootstrap 5, HTML5, CSS3, JavaScript
- **Serveur**: Apache/Nginx avec PHP

## Installation

### Prérequis
- PHP 8.0 ou supérieur
- MySQL 5.7 ou supérieur
- Serveur web (Apache/Nginx)

### Configuration
1. Cloner le projet
2. Configurer la base de données dans `config/Database.php`
3. Importer le schéma depuis `database/schema.sql`
4. Configurer le serveur web pour pointer vers le dossier `public/`

### Base de données
```sql
-- Créer la base de données
CREATE DATABASE gestion_scolaire_ism;

-- Importer le schéma
mysql -u root -p gestion_scolaire_ism < database/schema.sql
```

## Comptes de test
- **RP**: rp@ism.sn / password
- **Attaché**: attache@ism.sn / password  
- **Professeur**: prof@ism.sn / password

## Statistiques disponibles
- Effectif de l'école par année
- Répartition par sexe et par année
- Effectif par classe
- Répartition par sexe et par classe
- Nombre d'étudiants ayant suspendu/annulé par année

## Sécurité
- Authentification obligatoire pour toutes les fonctionnalités
- Contrôle d'accès basé sur les rôles
- Hashage des mots de passe avec password_hash()
- Protection contre les injections SQL avec PDO

## Développement
Le projet suit une architecture MVC avec séparation claire des responsabilités :
- **Models**: Représentation des données
- **Views**: Interface utilisateur
- **Controllers**: Logique de contrôle
- **Services**: Logique métier
- **Repository**: Accès aux données
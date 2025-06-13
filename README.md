# Système de Gestion Scolaire ISM - Version Java Console

## Description
Application console Java pour la gestion des inscriptions et de l'administration scolaire de l'Institut Supérieur de Management (ISM). Cette version utilise des fichiers JSON pour le stockage des données et une interface console interactive.

## Fonctionnalités

### 🔐 Authentification
- Connexion sécurisée par email/mot de passe
- Gestion des sessions utilisateur
- Contrôle d'accès basé sur les rôles

### 👨‍💼 Responsable Pédagogique (RP)
- ✅ Créer et lister des classes (libellé, filière, niveau)
- ✅ Gestion des professeurs et modules
- ✅ Affectation des classes aux professeurs
- ✅ Traitement des demandes de suspension/annulation
- ✅ Consultation des statistiques complètes

### 👨‍🏫 Attaché de Classe
- ✅ Inscription et réinscription des étudiants
- ✅ Liste des étudiants par classe et année
- ✅ Consultation des demandes des étudiants
- ✅ Recherche par matricule

### 👨‍🎓 Professeur
- 🚧 Consultation des classes assignées
- 🚧 Liste des modules enseignés
- 🚧 Consultation des étudiants

### 🎓 Étudiant
- 🚧 Formuler des demandes de suspension/annulation
- 🚧 Consulter ses demandes

## Architecture

### 📁 Structure du Projet
```
src/main/java/com/ism/
├── Main.java                    # Point d'entrée de l'application
├── controllers/                 # Contrôleurs (logique de présentation)
│   ├── AuthController.java
│   ├── DashboardController.java
│   ├── ClasseController.java
│   ├── EtudiantController.java
│   ├── InscriptionController.java
│   ├── ProfesseurController.java
│   └── DemandeController.java
├── services/                    # Services (logique métier)
│   ├── AuthService.java
│   ├── ClasseService.java
│   ├── EtudiantService.java
│   ├── InscriptionService.java
│   └── DemandeService.java
├── repositories/                # Repositories (accès aux données)
│   ├── UserRepository.java
│   ├── ClasseRepository.java
│   ├── EtudiantRepository.java
│   ├── InscriptionRepository.java
│   └── DemandeRepository.java
├── models/                      # Modèles de données
│   ├── User.java
│   ├── Classe.java
│   ├── Etudiant.java
│   ├── Inscription.java
│   ├── Demande.java
│   └── Module.java
└── utils/                       # Utilitaires
    ├── ConsoleUtils.java        # Utilitaires d'affichage console
    ├── SessionManager.java      # Gestion des sessions
    └── JsonFileManager.java     # Gestion des fichiers JSON
```

### 💾 Stockage des Données
Les données sont stockées dans des fichiers JSON dans le dossier `data/` :
- `users.json` - Utilisateurs du système
- `classes.json` - Classes disponibles
- `etudiants.json` - Étudiants inscrits
- `inscriptions.json` - Inscriptions des étudiants
- `demandes.json` - Demandes de suspension/annulation

## Installation et Exécution

### Prérequis
- ☕ Java 17 ou supérieur
- 📦 Maven 3.6 ou supérieur

### 🚀 Compilation et Exécution
```bash
# Cloner le projet
git clone <repository-url>
cd gestion-scolaire-console

# Compiler le projet
mvn clean compile

# Exécuter l'application
mvn exec:java -Dexec.mainClass="com.ism.Main"

# Ou créer un JAR exécutable
mvn clean package
java -jar target/gestion-scolaire-console-1.0.0.jar
```

## 🔑 Comptes de Test

L'application est pré-configurée avec les comptes suivants :

| Rôle | Email | Mot de passe | Fonctionnalités |
|------|-------|--------------|-----------------|
| **RP** | `rp@ism.sn` | `password` | Toutes les fonctionnalités administratives |
| **Attaché** | `attache@ism.sn` | `password` | Gestion des étudiants et inscriptions |
| **Professeur** | `prof@ism.sn` | `password` | Consultation des classes et étudiants |

## 📊 Statistiques Disponibles

### Statistiques Générales
- 👥 Total des étudiants actifs
- 🏫 Total des classes
- 👨‍🏫 Total des professeurs
- 📚 Total des modules

### Statistiques Détaillées
- 📅 **Effectif par année scolaire**
- 👫 **Répartition par sexe et année**
- 🏫 **Effectif par classe**
- 👥 **Répartition par sexe et classe**
- ⚠️ **Suspensions et annulations par année**

## 🎨 Interface Console

L'application utilise une interface console riche avec :
- 🎨 **Tableaux formatés** pour l'affichage des données
- 🎯 **Navigation par menus** intuitive
- ✅ **Messages colorés** (succès, erreur, avertissement)
- 📋 **Formulaires interactifs** pour la saisie
- 🔄 **Gestion des sessions** transparente

## 🛠️ Technologies Utilisées

- **☕ Java 17** - Langage de programmation
- **📦 Maven** - Gestionnaire de dépendances
- **🔄 Jackson** - Sérialisation/désérialisation JSON
- **📁 Architecture MVC** - Séparation des responsabilités
- **💾 Stockage JSON** - Persistance des données

## 🔒 Sécurité

- ✅ **Authentification obligatoire** pour toutes les fonctionnalités
- ✅ **Contrôle d'accès basé sur les rôles**
- ✅ **Gestion des sessions** sécurisée
- ✅ **Validation des données** d'entrée

## 🚧 Fonctionnalités en Développement

- 📚 Gestion complète des modules
- 👨‍🏫 Interface professeur complète
- 🎓 Interface étudiant complète
- 📧 Système de notifications
- 📈 Graphiques statistiques avancés
- 🔍 Recherche avancée multi-critères

## 📝 Utilisation

1. **Démarrer l'application**
2. **Se connecter** avec un des comptes de test
3. **Naviguer** dans les menus selon votre rôle
4. **Utiliser les fonctionnalités** disponibles
5. **Consulter les statistiques** en temps réel

## 🤝 Contribution

Pour contribuer au projet :
1. Fork le repository
2. Créer une branche feature
3. Commiter les changements
4. Pousser vers la branche
5. Créer une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

---

**🎓 Institut Supérieur de Management (ISM) - 2025**
```

## 🎯 Fonctionnalités Implémentées

✅ **Architecture MVC complète**
✅ **Authentification et gestion des sessions**
✅ **Gestion des classes** (création, liste, recherche)
✅ **Gestion des étudiants** (ajout, liste, recherche)
✅ **Système d'inscriptions** complet
✅ **Statistiques détaillées** selon le cahier des charges
✅ **Interface console riche** avec tableaux formatés
✅ **Stockage JSON** avec données de test
✅ **Contrôle d'accès par rôles**

## 🚀 Prêt à l'utilisation !

L'application est maintenant prête. Vous pouvez la compiler et l'exécuter pour tester toutes les fonctionnalités implémentées !
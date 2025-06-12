-- Base de données pour le système de gestion scolaire ISM
-- Créer la base de données
CREATE DATABASE IF NOT EXISTS gestion_scolaire_ism;
USE gestion_scolaire_ism;

-- Table des utilisateurs (RP, Attachés, Professeurs)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('RP', 'ATTACHE', 'PROFESSEUR', 'ETUDIANT') NOT NULL,
    dateCreation DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Table des classes
CREATE TABLE classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    filiere VARCHAR(100) NOT NULL,
    niveau VARCHAR(50) NOT NULL,
    dateCreation DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Table des modules
CREATE TABLE modules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    code VARCHAR(20) UNIQUE NOT NULL,
    coefficient INT DEFAULT 1,
    dateCreation DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Table des étudiants
CREATE TABLE etudiants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricule VARCHAR(50) UNIQUE NOT NULL,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    adresse TEXT NOT NULL,
    sexe ENUM('M', 'F') NOT NULL,
    dateNaissance DATE,
    dateCreation DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Table des inscriptions
CREATE TABLE inscriptions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    etudiant_id INT NOT NULL,
    classe_id INT NOT NULL,
    annee_scolaire VARCHAR(20) NOT NULL,
    statut ENUM('ACTIVE', 'SUSPENDUE', 'ANNULEE') DEFAULT 'ACTIVE',
    date_inscription DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id),
    FOREIGN KEY (classe_id) REFERENCES classes(id),
    UNIQUE KEY unique_inscription (etudiant_id, annee_scolaire)
);

-- Table des demandes (suspension/annulation)
CREATE TABLE demandes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    etudiant_id INT NOT NULL,
    type ENUM('SUSPENSION', 'ANNULATION') NOT NULL,
    motif TEXT NOT NULL,
    etat ENUM('EN_ATTENTE', 'ACCEPTEE', 'REFUSEE') DEFAULT 'EN_ATTENTE',
    date_demande DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_traitement DATETIME NULL,
    traite_par INT NULL,
    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id),
    FOREIGN KEY (traite_par) REFERENCES users(id)
);

-- Table de liaison professeur-module
CREATE TABLE professeur_modules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    professeur_id INT NOT NULL,
    module_id INT NOT NULL,
    dateCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (professeur_id) REFERENCES users(id),
    FOREIGN KEY (module_id) REFERENCES modules(id),
    UNIQUE KEY unique_prof_module (professeur_id, module_id)
);

-- Table de liaison professeur-classe
CREATE TABLE professeur_classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    professeur_id INT NOT NULL,
    classe_id INT NOT NULL,
    module_id INT NOT NULL,
    dateCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (professeur_id) REFERENCES users(id),
    FOREIGN KEY (classe_id) REFERENCES classes(id),
    FOREIGN KEY (module_id) REFERENCES modules(id)
);

-- Insertion des données de test avec mots de passe en clair
INSERT INTO users (nom, prenom, email, password, role) VALUES
('DIOP', 'Amadou', 'rp@ism.sn', 'password', 'RP'),
('FALL', 'Fatou', 'attache@ism.sn', 'password', 'ATTACHE'),
('NDIAYE', 'Moussa', 'prof@ism.sn', 'password', 'PROFESSEUR');

INSERT INTO classes (libelle, filiere, niveau) VALUES
('L3 Informatique A', 'Informatique', 'L3'),
('L3 Informatique B', 'Informatique', 'L3'),
('L2 Gestion A', 'Gestion', 'L2'),
('M1 Marketing', 'Marketing', 'M1'),
('L1 Comptabilité', 'Comptabilité', 'L1');

INSERT INTO modules (nom, code, coefficient) VALUES
('Programmation Web', 'PROG_WEB', 3),
('Base de Données', 'BDD', 3),
('Gestion de Projet', 'GEST_PROJ', 2),
('Marketing Digital', 'MARK_DIG', 2),
('Comptabilité Générale', 'COMPTA_GEN', 3);

INSERT INTO etudiants (matricule, nom, prenom, adresse, sexe) VALUES
('ISM2024001', 'SARR', 'Aissatou', 'Dakar, Plateau', 'F'),
('ISM2024002', 'BA', 'Mamadou', 'Thiès, Centre', 'M'),
('ISM2024003', 'SECK', 'Mariama', 'Saint-Louis, Nord', 'F'),
('ISM2024004', 'DIOUF', 'Ibrahima', 'Kaolack, Centre', 'M'),
('ISM2024005', 'KANE', 'Aminata', 'Ziguinchor, Sud', 'F');

INSERT INTO inscriptions (etudiant_id, classe_id, annee_scolaire) VALUES
(1, 1, '2024-2025'),
(2, 1, '2024-2025'),
(3, 2, '2024-2025'),
(4, 3, '2024-2025'),
(5, 4, '2024-2025');
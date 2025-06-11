<?php
require_once "../src/models/Etudiant.php";
require_once "../config/Database.php";

class EtudiantRepository {
    public function __construct() {
        Database::connexion();
    }

    public function insert(Etudiant $etudiant): int {
        try {
            $matricule = $etudiant->getMatricule();
            $nom = $etudiant->getNom();
            $prenom = $etudiant->getPrenom();
            $adresse = $etudiant->getAdresse();
            $sexe = $etudiant->getSexe();
            $dateCreation = $etudiant->getDateCreation()->format("Y-m-d H:i:s");
            
            $sql = "INSERT INTO `etudiants` (`matricule`, `nom`, `prenom`, `adresse`, `sexe`, `dateCreation`) 
                    VALUES ('$matricule', '$nom', '$prenom', '$adresse', '$sexe', '$dateCreation')";
            return Database::getPdo()->exec($sql);
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return 0;
    }

    public function selectAll(string $nom = "", int $offset = 0, int $size = 10): array {
        try {
            $where = "";
            if($nom != "") {
                $where = "WHERE nom LIKE '%$nom%' OR prenom LIKE '%$nom%'";
            }
            $sql = "SELECT * FROM etudiants $where ORDER BY dateCreation DESC LIMIT $offset, $size";
            $cursor = Database::getPdo()->query($sql);
            $etudiants = [];
            while ($row = $cursor->fetch()) {
                $etudiants[] = Etudiant::of($row);
            }
            return $etudiants;
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return [];
    }

    public function selectByMatricule(string $matricule): Etudiant|null {
        try {
            $sql = "SELECT * FROM etudiants WHERE matricule = '$matricule'";
            $cursor = Database::getPdo()->query($sql);
            if($row = $cursor->fetch()) {
                return Etudiant::of($row);
            }
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return null;
    }

    public function selectById(int $id): Etudiant|null {
        try {
            $sql = "SELECT * FROM etudiants WHERE id = $id";
            $cursor = Database::getPdo()->query($sql);
            if($row = $cursor->fetch()) {
                return Etudiant::of($row);
            }
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return null;
    }
}
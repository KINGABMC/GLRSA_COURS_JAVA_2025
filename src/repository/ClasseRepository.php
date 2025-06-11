<?php
require_once "../src/models/Classe.php";
require_once "../config/Database.php";

class ClasseRepository {
    public function __construct() {
        Database::connexion();
    }

    public function insert(Classe $classe): int {
        try {
            $libelle = $classe->getLibelle();
            $filiere = $classe->getFiliere();
            $niveau = $classe->getNiveau();
            $dateCreation = $classe->getDateCreation()->format("Y-m-d H:i:s");
            
            $sql = "INSERT INTO `classes` (`libelle`, `filiere`, `niveau`, `dateCreation`) 
                    VALUES ('$libelle', '$filiere', '$niveau', '$dateCreation')";
            return Database::getPdo()->exec($sql);
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return 0;
    }

    public function selectAll(string $filiere = "", int $offset = 0, int $size = 10): array {
        try {
            $where = "";
            if($filiere != "") {
                $where = "WHERE filiere LIKE '%$filiere%'";
            }
            $sql = "SELECT * FROM classes $where ORDER BY dateCreation DESC LIMIT $offset, $size";
            $cursor = Database::getPdo()->query($sql);
            $classes = [];
            while ($row = $cursor->fetch()) {
                $classes[] = Classe::of($row);
            }
            return $classes;
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return [];
    }

    public function selectById(int $id): Classe|null {
        try {
            $sql = "SELECT * FROM classes WHERE id = $id";
            $cursor = Database::getPdo()->query($sql);
            if($row = $cursor->fetch()) {
                return Classe::of($row);
            }
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return null;
    }
}
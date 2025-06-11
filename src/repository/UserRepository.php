<?php
require_once "../src/models/User.php";
require_once "../config/Database.php";

class UserRepository {
    public function __construct() {
        Database::connexion();
    }

    public function insert(User $user): int {
        try {
            $nom = $user->getNom();
            $prenom = $user->getPrenom();
            $email = $user->getEmail();
            $password = password_hash($user->getPassword(), PASSWORD_DEFAULT);
            $role = $user->getRole();
            $dateCreation = $user->getDateCreation()->format("Y-m-d H:i:s");
            
            $sql = "INSERT INTO `users` (`nom`, `prenom`, `email`, `password`, `role`, `dateCreation`) 
                    VALUES ('$nom', '$prenom', '$email', '$password', '$role', '$dateCreation')";
            return Database::getPdo()->exec($sql);
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return 0;
    }

    public function findByEmail(string $email): User|null {
        try {
            $sql = "SELECT * FROM users WHERE email = '$email'";
            $cursor = Database::getPdo()->query($sql);
            if($row = $cursor->fetch()) {
                return User::of($row);
            }
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return null;
    }

    public function selectByRole(string $role): array {
        try {
            $sql = "SELECT * FROM users WHERE role = '$role'";
            $cursor = Database::getPdo()->query($sql);
            $users = [];
            while ($row = $cursor->fetch()) {
                $users[] = User::of($row);
            }
            return $users;
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return [];
    }
}
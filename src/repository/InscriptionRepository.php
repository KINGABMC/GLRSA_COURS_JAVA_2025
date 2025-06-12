<?php
require_once "../src/models/Inscription.php";
require_once "../config/Database.php";

class InscriptionRepository {
    public function __construct() {
        Database::connexion();
    }

    public function insert(Inscription $inscription): int {
        try {
            $etudiantId = $inscription->getEtudiantId();
            $classeId = $inscription->getClasseId();
            $anneeScolaire = $inscription->getAnneeScolaire();
            $statut = $inscription->getStatut();
            $dateInscription = $inscription->getDateInscription()->format("Y-m-d H:i:s");
            
            $sql = "INSERT INTO `inscriptions` (`etudiant_id`, `classe_id`, `annee_scolaire`, `statut`, `date_inscription`) 
                    VALUES ($etudiantId, $classeId, '$anneeScolaire', '$statut', '$dateInscription')";
            return Database::getPdo()->exec($sql);
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return 0;
    }

    public function selectByClasseAndAnnee(int $classeId, string $anneeScolaire): array {
        try {
            $sql = "SELECT i.*, e.matricule, e.nom, e.prenom, e.adresse, e.sexe 
                    FROM inscriptions i 
                    JOIN etudiants e ON i.etudiant_id = e.id 
                    WHERE i.classe_id = $classeId AND i.annee_scolaire = '$anneeScolaire' 
                    AND i.statut = 'ACTIVE'";
            $cursor = Database::getPdo()->query($sql);
            $inscriptions = [];
            while ($row = $cursor->fetch()) {
                $inscriptions[] = $row;
            }
            return $inscriptions;
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return [];
    }

    public function checkExistingInscription(int $etudiantId, string $anneeScolaire): bool {
        try {
            $sql = "SELECT COUNT(*) as count FROM inscriptions 
                    WHERE etudiant_id = $etudiantId AND annee_scolaire = '$anneeScolaire'";
            $cursor = Database::getPdo()->query($sql);
            if($row = $cursor->fetch()) {
                return $row['count'] > 0;
            }
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return false;
    }

    public function getStatistiquesCompletes(): array {
        try {
            // 1. Statistiques générales
            $totalEtudiants = $this->getTotalEtudiants();
            $totalClasses = $this->getTotalClasses();
            $totalProfesseurs = $this->getTotalProfesseurs();
            $totalModules = $this->getTotalModules();

            // 2. Effectif par année scolaire
            $sql1 = "SELECT annee_scolaire, COUNT(*) as effectif 
                     FROM inscriptions WHERE statut = 'ACTIVE' 
                     GROUP BY annee_scolaire 
                     ORDER BY annee_scolaire DESC";
            $cursor1 = Database::getPdo()->query($sql1);
            $effectifParAnnee = [];
            while ($row = $cursor1->fetch()) {
                $effectifParAnnee[] = $row;
            }

            // 3. Répartition par sexe et année
            $sql2 = "SELECT i.annee_scolaire, e.sexe, COUNT(*) as nombre 
                     FROM inscriptions i 
                     JOIN etudiants e ON i.etudiant_id = e.id 
                     WHERE i.statut = 'ACTIVE' 
                     GROUP BY i.annee_scolaire, e.sexe 
                     ORDER BY i.annee_scolaire DESC, e.sexe";
            $cursor2 = Database::getPdo()->query($sql2);
            $repartitionSexeAnnee = [];
            while ($row = $cursor2->fetch()) {
                $repartitionSexeAnnee[] = $row;
            }

            // 4. Effectif par classe
            $sql3 = "SELECT c.libelle, c.filiere, c.niveau, COUNT(i.id) as effectif 
                     FROM classes c 
                     LEFT JOIN inscriptions i ON c.id = i.classe_id AND i.statut = 'ACTIVE' 
                     GROUP BY c.id, c.libelle, c.filiere, c.niveau 
                     ORDER BY c.filiere, c.niveau";
            $cursor3 = Database::getPdo()->query($sql3);
            $effectifParClasse = [];
            while ($row = $cursor3->fetch()) {
                $effectifParClasse[] = $row;
            }

            // 5. Répartition par sexe et classe
            $sql4 = "SELECT c.libelle, c.filiere, e.sexe, COUNT(*) as nombre 
                     FROM inscriptions i 
                     JOIN etudiants e ON i.etudiant_id = e.id 
                     JOIN classes c ON i.classe_id = c.id 
                     WHERE i.statut = 'ACTIVE' 
                     GROUP BY c.id, c.libelle, c.filiere, e.sexe 
                     ORDER BY c.filiere, c.libelle, e.sexe";
            $cursor4 = Database::getPdo()->query($sql4);
            $repartitionSexeClasse = [];
            while ($row = $cursor4->fetch()) {
                $repartitionSexeClasse[] = $row;
            }

            // 6. Suspensions et annulations par année
            $sql5 = "SELECT i.annee_scolaire,
                            SUM(CASE WHEN i.statut = 'SUSPENDUE' THEN 1 ELSE 0 END) as suspensions,
                            SUM(CASE WHEN i.statut = 'ANNULEE' THEN 1 ELSE 0 END) as annulations
                     FROM inscriptions i 
                     WHERE i.statut IN ('SUSPENDUE', 'ANNULEE')
                     GROUP BY i.annee_scolaire 
                     ORDER BY i.annee_scolaire DESC";
            $cursor5 = Database::getPdo()->query($sql5);
            $suspensionsAnnulations = [];
            while ($row = $cursor5->fetch()) {
                $suspensionsAnnulations[] = $row;
            }

            return [
                'totalEtudiants' => $totalEtudiants,
                'totalClasses' => $totalClasses,
                'totalProfesseurs' => $totalProfesseurs,
                'totalModules' => $totalModules,
                'effectifParAnnee' => $effectifParAnnee,
                'repartitionSexeAnnee' => $repartitionSexeAnnee,
                'effectifParClasse' => $effectifParClasse,
                'repartitionSexeClasse' => $repartitionSexeClasse,
                'suspensionsAnnulations' => $suspensionsAnnulations
            ];
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return [];
    }

    private function getTotalEtudiants(): int {
        try {
            $sql = "SELECT COUNT(DISTINCT etudiant_id) as total FROM inscriptions WHERE statut = 'ACTIVE'";
            $cursor = Database::getPdo()->query($sql);
            if($row = $cursor->fetch()) {
                return $row['total'];
            }
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return 0;
    }

    private function getTotalClasses(): int {
        try {
            $sql = "SELECT COUNT(*) as total FROM classes";
            $cursor = Database::getPdo()->query($sql);
            if($row = $cursor->fetch()) {
                return $row['total'];
            }
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return 0;
    }

    private function getTotalProfesseurs(): int {
        try {
            $sql = "SELECT COUNT(*) as total FROM users WHERE role = 'PROFESSEUR'";
            $cursor = Database::getPdo()->query($sql);
            if($row = $cursor->fetch()) {
                return $row['total'];
            }
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return 0;
    }

    private function getTotalModules(): int {
        try {
            $sql = "SELECT COUNT(*) as total FROM modules";
            $cursor = Database::getPdo()->query($sql);
            if($row = $cursor->fetch()) {
                return $row['total'];
            }
        } catch (\PDOException $ex) {
            print $ex->getMessage()."\n";
        }
        return 0;
    }

    // Méthodes existantes...
    public function getStatistiques(): array {
        return $this->getStatistiquesCompletes();
    }
}
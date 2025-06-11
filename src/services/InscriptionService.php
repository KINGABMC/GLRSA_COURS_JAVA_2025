<?php
require_once "../src/repository/InscriptionRepository.php";

class InscriptionService {
    private InscriptionRepository $inscriptionRepository;

    public function __construct() {
        $this->inscriptionRepository = new InscriptionRepository();
    }

    public function inscrireEtudiant(Inscription $inscription): bool {
        // Vérifier si l'étudiant n'est pas déjà inscrit cette année
        if ($this->inscriptionRepository->checkExistingInscription(
            $inscription->getEtudiantId(), 
            $inscription->getAnneeScolaire()
        )) {
            return false; // Déjà inscrit
        }
        
        return $this->inscriptionRepository->insert($inscription) > 0;
    }

    public function getEtudiantsParClasse(int $classeId, string $anneeScolaire): array {
        return $this->inscriptionRepository->selectByClasseAndAnnee($classeId, $anneeScolaire);
    }

    public function getStatistiques(): array {
        return $this->inscriptionRepository->getStatistiques();
    }
}
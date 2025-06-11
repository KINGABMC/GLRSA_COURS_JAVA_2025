<?php
require_once "../src/repository/EtudiantRepository.php";

class EtudiantService {
    private EtudiantRepository $etudiantRepository;

    public function __construct() {
        $this->etudiantRepository = new EtudiantRepository();
    }

    public function addEtudiant(Etudiant $etudiant): void {
        $this->etudiantRepository->insert($etudiant);
    }

    public function getEtudiants(string $nom = ""): array {
        return $this->etudiantRepository->selectAll($nom);
    }

    public function getEtudiantByMatricule(string $matricule): Etudiant|null {
        return $this->etudiantRepository->selectByMatricule($matricule);
    }

    public function getEtudiantById(int $id): Etudiant|null {
        return $this->etudiantRepository->selectById($id);
    }
}
<?php
require_once "../src/repository/ClasseRepository.php";

class ClasseService {
    private ClasseRepository $classeRepository;

    public function __construct() {
        $this->classeRepository = new ClasseRepository();
    }

    public function addClasse(Classe $classe): void {
        $this->classeRepository->insert($classe);
    }

    public function getClasses(string $filiere = ""): array {
        return $this->classeRepository->selectAll($filiere);
    }

    public function getClasseById(int $id): Classe|null {
        return $this->classeRepository->selectById($id);
    }
}
<?php
require_once "../src/services/ClasseService.php";
require_once "../src/models/Classe.php";

class ClasseController {
    private ClasseService $classeService;

    public function __construct() {
        $this->classeService = new ClasseService();
        $this->handleRequest();
    }

    private function handleRequest() {
        $action = $_REQUEST["action"] ?? 'list-classe';
        
        switch ($action) {
            case 'form-classe':
                $this->showFormClasse();
                break;
            case 'list-classe':
                $this->showListClasse();
                break;
            case 'save-classe':
                $this->saveClasse();
                break;
            default:
                $this->showListClasse();
                break;
        }
    }

    public function showListClasse() {
        $filiere = $_REQUEST["filiere"] ?? "";
        $classes = $this->classeService->getClasses($filiere);
        
        require_once "../views/layout/header.html.php";
        require_once "../views/classe/liste.html.php";
        require_once "../views/layout/footer.html.php";
    }

    public function showFormClasse() {
        require_once "../views/layout/header.html.php";
        require_once "../views/classe/form.html.php";
        require_once "../views/layout/footer.html.php";
    }

    public function saveClasse() {
        $libelle = $_REQUEST["libelle"];
        $filiere = $_REQUEST["filiere"];
        $niveau = $_REQUEST["niveau"];
        
        $classe = new Classe($libelle, $filiere, $niveau);
        $this->classeService->addClasse($classe);
        
        header("location:index.php?controller=classe&action=list-classe");
    }
}
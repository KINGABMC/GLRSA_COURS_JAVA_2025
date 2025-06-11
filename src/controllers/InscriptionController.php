<?php
require_once "../src/services/InscriptionService.php";
require_once "../src/services/EtudiantService.php";
require_once "../src/services/ClasseService.php";
require_once "../src/models/Inscription.php";

class InscriptionController {
    private InscriptionService $inscriptionService;
    private EtudiantService $etudiantService;
    private ClasseService $classeService;

    public function __construct() {
        $this->inscriptionService = new InscriptionService();
        $this->etudiantService = new EtudiantService();
        $this->classeService = new ClasseService();
        $this->handleRequest();
    }

    private function handleRequest() {
        $action = $_REQUEST["action"] ?? 'form-inscription';
        
        switch ($action) {
            case 'form-inscription':
                $this->showFormInscription();
                break;
            case 'save-inscription':
                $this->saveInscription();
                break;
            case 'list-etudiants-classe':
                $this->showEtudiantsClasse();
                break;
            default:
                $this->showFormInscription();
                break;
        }
    }

    public function showFormInscription() {
        $classes = $this->classeService->getClasses();
        
        require_once "../views/layout/header.html.php";
        require_once "../views/inscription/form.html.php";
        require_once "../views/layout/footer.html.php";
    }

    public function saveInscription() {
        $matricule = $_REQUEST["matricule"];
        $classeId = $_REQUEST["classe_id"];
        $anneeScolaire = $_REQUEST["annee_scolaire"];
        
        $etudiant = $this->etudiantService->getEtudiantByMatricule($matricule);
        
        if (!$etudiant) {
            $error = "Étudiant non trouvé avec ce matricule";
            $classes = $this->classeService->getClasses();
            require_once "../views/layout/header.html.php";
            require_once "../views/inscription/form.html.php";
            require_once "../views/layout/footer.html.php";
            return;
        }
        
        $inscription = new Inscription($etudiant->getId(), $classeId, $anneeScolaire);
        
        if ($this->inscriptionService->inscrireEtudiant($inscription)) {
            $success = "Inscription réussie";
        } else {
            $error = "Étudiant déjà inscrit pour cette année";
        }
        
        $classes = $this->classeService->getClasses();
        require_once "../views/layout/header.html.php";
        require_once "../views/inscription/form.html.php";
        require_once "../views/layout/footer.html.php";
    }

    public function showEtudiantsClasse() {
        $classeId = $_REQUEST["classe_id"];
        $anneeScolaire = $_REQUEST["annee_scolaire"];
        
        $classe = $this->classeService->getClasseById($classeId);
        $etudiants = $this->inscriptionService->getEtudiantsParClasse($classeId, $anneeScolaire);
        
        require_once "../views/layout/header.html.php";
        require_once "../views/inscription/liste-etudiants.html.php";
        require_once "../views/layout/footer.html.php";
    }
}
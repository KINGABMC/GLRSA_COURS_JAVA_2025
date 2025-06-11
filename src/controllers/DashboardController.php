<?php
require_once "../src/services/InscriptionService.php";

class DashboardController {
    private InscriptionService $inscriptionService;

    public function __construct() {
        $this->inscriptionService = new InscriptionService();
        $this->handleRequest();
    }

    private function handleRequest() {
        $action = $_REQUEST["action"] ?? 'dashboard';
        
        switch ($action) {
            case 'dashboard':
                $this->showDashboard();
                break;
            case 'statistiques':
                $this->showStatistiques();
                break;
            default:
                $this->showDashboard();
                break;
        }
    }

    public function showDashboard() {
        require_once "../views/layout/header.html.php";
        require_once "../views/dashboard/index.html.php";
        require_once "../views/layout/footer.html.php";
    }

    public function showStatistiques() {
        $statistiques = $this->inscriptionService->getStatistiques();
        
        require_once "../views/layout/header.html.php";
        require_once "../views/dashboard/statistiques.html.php";
        require_once "../views/layout/footer.html.php";
    }
}
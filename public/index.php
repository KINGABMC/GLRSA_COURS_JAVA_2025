<?php
session_start();

// Vérification de l'authentification pour toutes les pages sauf login
$controller = $_REQUEST["controller"] ?? 'auth';
if ($controller !== 'auth' && !isset($_SESSION['user'])) {
    header("location:index.php?controller=auth&action=login-form");
    exit;
}

switch ($controller) {
    case 'auth':
        require_once "../src/controllers/AuthController.php";
        $controllerInstance = new AuthController();
        break;
    case 'dashboard':
        require_once "../src/controllers/DashboardController.php";
        $controllerInstance = new DashboardController();
        break;
    case 'classe':
        require_once "../src/controllers/ClasseController.php";
        $controllerInstance = new ClasseController();
        break;
    case 'etudiant':
        require_once "../src/controllers/EtudiantController.php";
        $controllerInstance = new EtudiantController();
        break;
    case 'inscription':
        require_once "../src/controllers/InscriptionController.php";
        $controllerInstance = new InscriptionController();
        break;
    default:
        require_once "../src/controllers/DashboardController.php";
        $controllerInstance = new DashboardController();
        break;
}
?>
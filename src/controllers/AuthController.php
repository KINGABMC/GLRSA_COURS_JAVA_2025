<?php
require_once "../src/services/AuthService.php";
require_once "../src/models/User.php";

class AuthController {
    private AuthService $authService;

    public function __construct() {
        $this->authService = new AuthService();
        $this->handleRequest();
    }

    private function handleRequest() {
        $action = $_REQUEST["action"] ?? 'login-form';
        
        switch ($action) {
            case 'login-form':
                $this->showLoginForm();
                break;
            case 'login':
                $this->login();
                break;
            case 'logout':
                $this->logout();
                break;
            default:
                $this->showLoginForm();
                break;
        }
    }

    public function showLoginForm() {
        require_once "../views/auth/login.html.php";
    }

    public function login() {
        $email = $_REQUEST["email"];
        $password = $_REQUEST["password"];
        
        $user = $this->authService->login($email, $password);
        
        if ($user) {
            $_SESSION['user'] = $user;
            header("location:index.php?controller=dashboard");
        } else {
            $error = "Email ou mot de passe incorrect";
            require_once "../views/auth/login.html.php";
        }
    }

    public function logout() {
        $this->authService->logout();
        header("location:index.php?controller=auth&action=login-form");
    }
}
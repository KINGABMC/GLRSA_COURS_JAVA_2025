<?php
require_once "../src/repository/UserRepository.php";

class AuthService {
    private UserRepository $userRepository;

    public function __construct() {
        $this->userRepository = new UserRepository();
    }

    public function login(string $email, string $password): User|null {
        $user = $this->userRepository->findByEmail($email);
        // Comparaison directe des mots de passe en clair
        if ($user && $user->getPassword() === $password) {
            return $user;
        }
        return null;
    }

    public function register(User $user): bool {
        return $this->userRepository->insert($user) > 0;
    }

    public function isLoggedIn(): bool {
        return isset($_SESSION['user']);
    }

    public function getCurrentUser(): User|null {
        return $_SESSION['user'] ?? null;
    }

    public function logout(): void {
        unset($_SESSION['user']);
        session_destroy();
    }
}
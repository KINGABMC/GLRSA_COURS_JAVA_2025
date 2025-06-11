<?php 
class User{
    private int $id;
    private string $nom;
    private string $prenom;
    private string $email;
    private string $password;
    private string $role; // RP, ATTACHE, PROFESSEUR, ETUDIANT
    private DateTime $dateCreation;

    public function __construct(string $nom="", string $prenom="", string $email="", string $password="", string $role="")
    {
        $this->nom = $nom;
        $this->prenom = $prenom;
        $this->email = $email;
        $this->password = $password;
        $this->role = $role;
        $this->dateCreation = new DateTime();
    }

    public function getNomComplet(): string {
        return $this->prenom . ' ' . $this->nom;
    }

    public function getDateToString(): string {
        return $this->dateCreation->format("d-m-Y");
    }

    public static function of($row): User {
        $user = new User();
        $user->setId($row['id']);
        $user->setNom($row['nom']);
        $user->setPrenom($row['prenom']);
        $user->setEmail($row['email']);
        $user->setPassword($row['password']);
        $user->setRole($row['role']);
        $user->setDateCreation(new DateTime($row['dateCreation']));
        return $user;
    }

    // Getters and Setters
    public function getId(): int { return $this->id; }
    public function setId(int $id): void { $this->id = $id; }
    
    public function getNom(): string { return $this->nom; }
    public function setNom(string $nom): void { $this->nom = $nom; }
    
    public function getPrenom(): string { return $this->prenom; }
    public function setPrenom(string $prenom): void { $this->prenom = $prenom; }
    
    public function getEmail(): string { return $this->email; }
    public function setEmail(string $email): void { $this->email = $email; }
    
    public function getPassword(): string { return $this->password; }
    public function setPassword(string $password): void { $this->password = $password; }
    
    public function getRole(): string { return $this->role; }
    public function setRole(string $role): void { $this->role = $role; }
    
    public function getDateCreation(): DateTime { return $this->dateCreation; }
    public function setDateCreation(DateTime $dateCreation): void { $this->dateCreation = $dateCreation; }
}
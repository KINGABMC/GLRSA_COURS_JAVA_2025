<?php 
class Etudiant{
    private int $id;
    private string $matricule;
    private string $nom;
    private string $prenom;
    private string $adresse;
    private string $sexe; // M ou F
    private DateTime $dateNaissance;
    private DateTime $dateCreation;

    public function __construct(string $nom="", string $prenom="", string $adresse="", string $sexe="")
    {
        $this->matricule = $this->generateMatricule();
        $this->nom = $nom;
        $this->prenom = $prenom;
        $this->adresse = $adresse;
        $this->sexe = $sexe;
        $this->dateCreation = new DateTime();
    }

    private function generateMatricule(): string {
        return 'ISM' . date('Y') . uniqid();
    }

    public function getNomComplet(): string {
        return $this->prenom . ' ' . $this->nom;
    }

    public function getDateToString(): string {
        return $this->dateCreation->format("d-m-Y");
    }

    public static function of($row): Etudiant {
        $etudiant = new Etudiant();
        $etudiant->setId($row['id']);
        $etudiant->setMatricule($row['matricule']);
        $etudiant->setNom($row['nom']);
        $etudiant->setPrenom($row['prenom']);
        $etudiant->setAdresse($row['adresse']);
        $etudiant->setSexe($row['sexe']);
        if(isset($row['dateNaissance'])) {
            $etudiant->setDateNaissance(new DateTime($row['dateNaissance']));
        }
        $etudiant->setDateCreation(new DateTime($row['dateCreation']));
        return $etudiant;
    }

    // Getters and Setters
    public function getId(): int { return $this->id; }
    public function setId(int $id): void { $this->id = $id; }
    
    public function getMatricule(): string { return $this->matricule; }
    public function setMatricule(string $matricule): void { $this->matricule = $matricule; }
    
    public function getNom(): string { return $this->nom; }
    public function setNom(string $nom): void { $this->nom = $nom; }
    
    public function getPrenom(): string { return $this->prenom; }
    public function setPrenom(string $prenom): void { $this->prenom = $prenom; }
    
    public function getAdresse(): string { return $this->adresse; }
    public function setAdresse(string $adresse): void { $this->adresse = $adresse; }
    
    public function getSexe(): string { return $this->sexe; }
    public function setSexe(string $sexe): void { $this->sexe = $sexe; }
    
    public function getDateNaissance(): DateTime { return $this->dateNaissance; }
    public function setDateNaissance(DateTime $dateNaissance): void { $this->dateNaissance = $dateNaissance; }
    
    public function getDateCreation(): DateTime { return $this->dateCreation; }
    public function setDateCreation(DateTime $dateCreation): void { $this->dateCreation = $dateCreation; }
}
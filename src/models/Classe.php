<?php 
class Classe{
    private int $id;
    private string $libelle;
    private string $filiere;
    private string $niveau;
    private DateTime $dateCreation;

    public function __construct(string $libelle="", string $filiere="", string $niveau="")
    {
        $this->libelle = $libelle;
        $this->filiere = $filiere;
        $this->niveau = $niveau;
        $this->dateCreation = new DateTime();
    }

    public function getDateToString(): string {
        return $this->dateCreation->format("d-m-Y");
    }

    public static function of($row): Classe {
        $classe = new Classe();
        $classe->setId($row['id']);
        $classe->setLibelle($row['libelle']);
        $classe->setFiliere($row['filiere']);
        $classe->setNiveau($row['niveau']);
        $classe->setDateCreation(new DateTime($row['dateCreation']));
        return $classe;
    }

    // Getters and Setters
    public function getId(): int { return $this->id; }
    public function setId(int $id): void { $this->id = $id; }
    
    public function getLibelle(): string { return $this->libelle; }
    public function setLibelle(string $libelle): void { $this->libelle = $libelle; }
    
    public function getFiliere(): string { return $this->filiere; }
    public function setFiliere(string $filiere): void { $this->filiere = $filiere; }
    
    public function getNiveau(): string { return $this->niveau; }
    public function setNiveau(string $niveau): void { $this->niveau = $niveau; }
    
    public function getDateCreation(): DateTime { return $this->dateCreation; }
    public function setDateCreation(DateTime $dateCreation): void { $this->dateCreation = $dateCreation; }
}
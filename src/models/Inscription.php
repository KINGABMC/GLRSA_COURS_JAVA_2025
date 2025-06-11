<?php 
class Inscription{
    private int $id;
    private int $etudiantId;
    private int $classeId;
    private string $anneeScolaire;
    private string $statut; // ACTIVE, SUSPENDUE, ANNULEE
    private DateTime $dateInscription;

    public function __construct(int $etudiantId=0, int $classeId=0, string $anneeScolaire="")
    {
        $this->etudiantId = $etudiantId;
        $this->classeId = $classeId;
        $this->anneeScolaire = $anneeScolaire;
        $this->statut = 'ACTIVE';
        $this->dateInscription = new DateTime();
    }

    public function getDateToString(): string {
        return $this->dateInscription->format("d-m-Y");
    }

    public static function of($row): Inscription {
        $inscription = new Inscription();
        $inscription->setId($row['id']);
        $inscription->setEtudiantId($row['etudiant_id']);
        $inscription->setClasseId($row['classe_id']);
        $inscription->setAnneeScolaire($row['annee_scolaire']);
        $inscription->setStatut($row['statut']);
        $inscription->setDateInscription(new DateTime($row['date_inscription']));
        return $inscription;
    }

    // Getters and Setters
    public function getId(): int { return $this->id; }
    public function setId(int $id): void { $this->id = $id; }
    
    public function getEtudiantId(): int { return $this->etudiantId; }
    public function setEtudiantId(int $etudiantId): void { $this->etudiantId = $etudiantId; }
    
    public function getClasseId(): int { return $this->classeId; }
    public function setClasseId(int $classeId): void { $this->classeId = $classeId; }
    
    public function getAnneeScolaire(): string { return $this->anneeScolaire; }
    public function setAnneeScolaire(string $anneeScolaire): void { $this->anneeScolaire = $anneeScolaire; }
    
    public function getStatut(): string { return $this->statut; }
    public function setStatut(string $statut): void { $this->statut = $statut; }
    
    public function getDateInscription(): DateTime { return $this->dateInscription; }
    public function setDateInscription(DateTime $dateInscription): void { $this->dateInscription = $dateInscription; }
}
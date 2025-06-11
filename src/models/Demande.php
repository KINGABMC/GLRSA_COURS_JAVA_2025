<?php 
class Demande{
    private int $id;
    private int $etudiantId;
    private string $type; // SUSPENSION, ANNULATION
    private string $motif;
    private string $etat; // EN_ATTENTE, ACCEPTEE, REFUSEE
    private DateTime $dateDemande;
    private DateTime $dateTraitement;

    public function __construct(int $etudiantId=0, string $type="", string $motif="")
    {
        $this->etudiantId = $etudiantId;
        $this->type = $type;
        $this->motif = $motif;
        $this->etat = 'EN_ATTENTE';
        $this->dateDemande = new DateTime();
    }

    public function getDateDemandeToString(): string {
        return $this->dateDemande->format("d-m-Y");
    }

    public function getDateTraitementToString(): string {
        return $this->dateTraitement ? $this->dateTraitement->format("d-m-Y") : '';
    }

    public static function of($row): Demande {
        $demande = new Demande();
        $demande->setId($row['id']);
        $demande->setEtudiantId($row['etudiant_id']);
        $demande->setType($row['type']);
        $demande->setMotif($row['motif']);
        $demande->setEtat($row['etat']);
        $demande->setDateDemande(new DateTime($row['date_demande']));
        if($row['date_traitement']) {
            $demande->setDateTraitement(new DateTime($row['date_traitement']));
        }
        return $demande;
    }

    // Getters and Setters
    public function getId(): int { return $this->id; }
    public function setId(int $id): void { $this->id = $id; }
    
    public function getEtudiantId(): int { return $this->etudiantId; }
    public function setEtudiantId(int $etudiantId): void { $this->etudiantId = $etudiantId; }
    
    public function getType(): string { return $this->type; }
    public function setType(string $type): void { $this->type = $type; }
    
    public function getMotif(): string { return $this->motif; }
    public function setMotif(string $motif): void { $this->motif = $motif; }
    
    public function getEtat(): string { return $this->etat; }
    public function setEtat(string $etat): void { $this->etat = $etat; }
    
    public function getDateDemande(): DateTime { return $this->dateDemande; }
    public function setDateDemande(DateTime $dateDemande): void { $this->dateDemande = $dateDemande; }
    
    public function getDateTraitement(): ?DateTime { return $this->dateTraitement; }
    public function setDateTraitement(DateTime $dateTraitement): void { $this->dateTraitement = $dateTraitement; }
}
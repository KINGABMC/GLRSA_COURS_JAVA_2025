<?php 
class Module{
    private int $id;
    private string $nom;
    private string $code;
    private int $coefficient;
    private DateTime $dateCreation;

    public function __construct(string $nom="", string $code="", int $coefficient=1)
    {
        $this->nom = $nom;
        $this->code = $code;
        $this->coefficient = $coefficient;
        $this->dateCreation = new DateTime();
    }

    public static function of($row): Module {
        $module = new Module();
        $module->setId($row['id']);
        $module->setNom($row['nom']);
        $module->setCode($row['code']);
        $module->setCoefficient($row['coefficient']);
        $module->setDateCreation(new DateTime($row['dateCreation']));
        return $module;
    }

    // Getters and Setters
    public function getId(): int { return $this->id; }
    public function setId(int $id): void { $this->id = $id; }
    
    public function getNom(): string { return $this->nom; }
    public function setNom(string $nom): void { $this->nom = $nom; }
    
    public function getCode(): string { return $this->code; }
    public function setCode(string $code): void { $this->code = $code; }
    
    public function getCoefficient(): int { return $this->coefficient; }
    public function setCoefficient(int $coefficient): void { $this->coefficient = $coefficient; }
    
    public function getDateCreation(): DateTime { return $this->dateCreation; }
    public function setDateCreation(DateTime $dateCreation): void { $this->dateCreation = $dateCreation; }
}
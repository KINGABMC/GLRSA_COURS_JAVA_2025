package com.ism.services;

import com.ism.models.Etudiant;
import com.ism.models.Inscription;
import com.ism.models.Classe;
import com.ism.repositories.InscriptionRepository;
import com.ism.repositories.EtudiantRepository;
import com.ism.repositories.ClasseRepository;
import com.ism.repositories.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class InscriptionService {
    private InscriptionRepository inscriptionRepository = new InscriptionRepository();
    private EtudiantRepository etudiantRepository = new EtudiantRepository();
    private ClasseRepository classeRepository = new ClasseRepository();
    private UserRepository userRepository = new UserRepository();

    public void inscrireEtudiant(Inscription inscription) {
        inscriptionRepository.save(inscription);
    }

    public boolean isEtudiantInscrit(int etudiantId, String anneeScolaire) {
        return inscriptionRepository.existsByEtudiantAndAnnee(etudiantId, anneeScolaire);
    }

    public List<Etudiant> getEtudiantsParClasse(int classeId, String anneeScolaire) {
        List<Inscription> inscriptions = inscriptionRepository.findByClasseAndAnnee(classeId, anneeScolaire);
        return inscriptions.stream()
                .map(inscription -> etudiantRepository.findById(inscription.getEtudiantId()))
                .filter(etudiant -> etudiant != null)
                .collect(Collectors.toList());
    }

    public List<Inscription> getAllInscriptions() {
        return inscriptionRepository.findAll();
    }

    public Map<String, Object> getStatistiques() {
        Map<String, Object> stats = new HashMap<>();
        
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<Classe> classes = classeRepository.findAll();
        
        // Compter les étudiants actifs (inscrits)
        long totalEtudiants = inscriptions.stream()
                .filter(i -> "ACTIVE".equals(i.getStatut()))
                .map(Inscription::getEtudiantId)
                .distinct()
                .count();
        
        stats.put("totalEtudiants", totalEtudiants);
        stats.put("totalClasses", classes.size());
        stats.put("totalProfesseurs", userRepository.countByRole("PROFESSEUR"));
        stats.put("totalModules", 0); // À implémenter avec ModuleRepository
        
        return stats;
    }

    public Map<String, Integer> getEffectifParAnnee() {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        return inscriptions.stream()
                .filter(i -> "ACTIVE".equals(i.getStatut()))
                .collect(Collectors.groupingBy(
                    Inscription::getAnneeScolaire,
                    Collectors.collectingAndThen(Collectors.counting(), Math::toIntExact)
                ));
    }

    public Map<String, Map<String, Integer>> getRepartitionSexeAnnee() {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        Map<String, Map<String, Integer>> result = new HashMap<>();
        
        for (Inscription inscription : inscriptions) {
            if (!"ACTIVE".equals(inscription.getStatut())) continue;
            
            Etudiant etudiant = etudiantRepository.findById(inscription.getEtudiantId());
            if (etudiant == null) continue;
            
            String annee = inscription.getAnneeScolaire();
            String sexe = etudiant.getSexe();
            
            result.computeIfAbsent(annee, k -> new HashMap<>())
                  .merge(sexe, 1, Integer::sum);
        }
        
        return result;
    }

    public Map<String, Integer> getEffectifParClasse() {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        Map<Integer, Integer> effectifParClasseId = inscriptions.stream()
                .filter(i -> "ACTIVE".equals(i.getStatut()))
                .collect(Collectors.groupingBy(
                    Inscription::getClasseId,
                    Collectors.collectingAndThen(Collectors.counting(), Math::toIntExact)
                ));
        
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : effectifParClasseId.entrySet()) {
            Classe classe = classeRepository.findById(entry.getKey());
            if (classe != null) {
                result.put(classe.getLibelle(), entry.getValue());
            }
        }
        
        return result;
    }

    public Map<String, Map<String, Integer>> getRepartitionSexeClasse() {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        Map<String, Map<String, Integer>> result = new HashMap<>();
        
        for (Inscription inscription : inscriptions) {
            if (!"ACTIVE".equals(inscription.getStatut())) continue;
            
            Etudiant etudiant = etudiantRepository.findById(inscription.getEtudiantId());
            Classe classe = classeRepository.findById(inscription.getClasseId());
            
            if (etudiant == null || classe == null) continue;
            
            String classeNom = classe.getLibelle();
            String sexe = etudiant.getSexe();
            
            result.computeIfAbsent(classeNom, k -> new HashMap<>())
                  .merge(sexe, 1, Integer::sum);
        }
        
        return result;
    }

    public Map<String, Map<String, Integer>> getSuspensionsAnnulationsParAnnee() {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        Map<String, Map<String, Integer>> result = new HashMap<>();
        
        for (Inscription inscription : inscriptions) {
            if ("ACTIVE".equals(inscription.getStatut())) continue;
            
            String annee = inscription.getAnneeScolaire();
            String statut = inscription.getStatut();
            
            result.computeIfAbsent(annee, k -> new HashMap<>())
                  .merge(statut, 1, Integer::sum);
        }
        
        return result;
    }
}
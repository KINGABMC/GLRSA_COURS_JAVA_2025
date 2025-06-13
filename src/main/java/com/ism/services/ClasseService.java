package com.ism.services;

import com.ism.models.Classe;
import com.ism.repositories.ClasseRepository;
import java.util.List;

public class ClasseService {
    private ClasseRepository classeRepository = new ClasseRepository();

    public void addClasse(Classe classe) {
        classeRepository.save(classe);
    }

    public List<Classe> getClasses(String filiere) {
        if (filiere == null || filiere.trim().isEmpty()) {
            return classeRepository.findAll();
        }
        return classeRepository.findByFiliere(filiere);
    }

    public Classe getClasseById(int id) {
        return classeRepository.findById(id);
    }
}
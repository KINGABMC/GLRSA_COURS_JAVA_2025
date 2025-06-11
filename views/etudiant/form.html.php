<main class="container my-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">
                        <i class="fas fa-user-plus me-2"></i>Ajouter un nouvel étudiant
                    </h5>
                </div>
                <div class="card-body">
                    <form action="index.php" method="post">
                        <input type="hidden" name="controller" value="etudiant">
                        <input type="hidden" name="action" value="save-etudiant">
                        
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="nom" class="form-label">Nom *</label>
                                <input type="text" class="form-control" name="nom" id="nom" 
                                       placeholder="Nom de famille" required>
                            </div>
                            
                            <div class="col-md-6 mb-3">
                                <label for="prenom" class="form-label">Prénom *</label>
                                <input type="text" class="form-control" name="prenom" id="prenom" 
                                       placeholder="Prénom" required>
                            </div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="adresse" class="form-label">Adresse *</label>
                            <textarea class="form-control" name="adresse" id="adresse" rows="3" 
                                      placeholder="Adresse complète" required></textarea>
                        </div>
                        
                        <div class="mb-4">
                            <label for="sexe" class="form-label">Sexe *</label>
                            <select class="form-select" name="sexe" id="sexe" required>
                                <option value="">Sélectionner le sexe</option>
                                <option value="M">Masculin</option>
                                <option value="F">Féminin</option>
                            </select>
                        </div>
                        
                        <div class="alert alert-info">
                            <i class="fas fa-info-circle me-2"></i>
                            <strong>Note :</strong> Le matricule sera généré automatiquement lors de l'enregistrement.
                        </div>
                        
                        <div class="d-flex justify-content-between">
                            <a href="index.php?controller=etudiant&action=list-etudiant" class="btn btn-secondary">
                                <i class="fas fa-arrow-left me-1"></i>Retour
                            </a>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save me-1"></i>Enregistrer l'étudiant
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
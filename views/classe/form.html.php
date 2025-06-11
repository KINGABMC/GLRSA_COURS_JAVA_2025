<main class="container my-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">
                        <i class="fas fa-chalkboard me-2"></i>Créer une nouvelle classe
                    </h5>
                </div>
                <div class="card-body">
                    <form action="index.php" method="post">
                        <input type="hidden" name="controller" value="classe">
                        <input type="hidden" name="action" value="save-classe">
                        
                        <div class="mb-3">
                            <label for="libelle" class="form-label">Libellé de la classe *</label>
                            <input type="text" class="form-control" name="libelle" id="libelle" 
                                   placeholder="Ex: L3 Informatique A" required>
                            <div class="form-text">Nom complet de la classe</div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="filiere" class="form-label">Filière *</label>
                            <select class="form-select" name="filiere" id="filiere" required>
                                <option value="">Sélectionner une filière</option>
                                <option value="Informatique">Informatique</option>
                                <option value="Gestion">Gestion</option>
                                <option value="Marketing">Marketing</option>
                                <option value="Comptabilité">Comptabilité</option>
                                <option value="Ressources Humaines">Ressources Humaines</option>
                                <option value="Commerce International">Commerce International</option>
                            </select>
                        </div>
                        
                        <div class="mb-4">
                            <label for="niveau" class="form-label">Niveau *</label>
                            <select class="form-select" name="niveau" id="niveau" required>
                                <option value="">Sélectionner un niveau</option>
                                <option value="L1">Licence 1 (L1)</option>
                                <option value="L2">Licence 2 (L2)</option>
                                <option value="L3">Licence 3 (L3)</option>
                                <option value="M1">Master 1 (M1)</option>
                                <option value="M2">Master 2 (M2)</option>
                            </select>
                        </div>
                        
                        <div class="d-flex justify-content-between">
                            <a href="index.php?controller=classe&action=list-classe" class="btn btn-secondary">
                                <i class="fas fa-arrow-left me-1"></i>Retour
                            </a>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save me-1"></i>Créer la classe
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
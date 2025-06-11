<main class="container my-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">
                        <i class="fas fa-user-plus me-2"></i>Inscription d'un étudiant
                    </h5>
                </div>
                <div class="card-body">
                    <?php if(isset($error)): ?>
                        <div class="alert alert-danger" role="alert">
                            <i class="fas fa-exclamation-triangle me-2"></i><?= $error ?>
                        </div>
                    <?php endif; ?>
                    
                    <?php if(isset($success)): ?>
                        <div class="alert alert-success" role="alert">
                            <i class="fas fa-check-circle me-2"></i><?= $success ?>
                        </div>
                    <?php endif; ?>
                    
                    <form action="index.php" method="post">
                        <input type="hidden" name="controller" value="inscription">
                        <input type="hidden" name="action" value="save-inscription">
                        
                        <div class="mb-3">
                            <label for="matricule" class="form-label">Matricule de l'étudiant *</label>
                            <input type="text" class="form-control" name="matricule" id="matricule" 
                                   placeholder="Ex: ISM2024..." required>
                            <div class="form-text">Saisissez le matricule de l'étudiant à inscrire</div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="classe_id" class="form-label">Classe *</label>
                            <select class="form-select" name="classe_id" id="classe_id" required>
                                <option value="">Sélectionner une classe</option>
                                <?php foreach ($classes as $classe): ?>
                                    <option value="<?= $classe->getId() ?>">
                                        <?= htmlspecialchars($classe->getLibelle()) ?> - 
                                        <?= htmlspecialchars($classe->getFiliere()) ?> 
                                        (<?= htmlspecialchars($classe->getNiveau()) ?>)
                                    </option>
                                <?php endforeach; ?>
                            </select>
                        </div>
                        
                        <div class="mb-4">
                            <label for="annee_scolaire" class="form-label">Année scolaire *</label>
                            <select class="form-select" name="annee_scolaire" id="annee_scolaire" required>
                                <option value="">Sélectionner l'année</option>
                                <option value="2024-2025" selected>2024-2025</option>
                                <option value="2025-2026">2025-2026</option>
                            </select>
                        </div>
                        
                        <div class="alert alert-warning">
                            <i class="fas fa-exclamation-triangle me-2"></i>
                            <strong>Important :</strong> Un étudiant ne peut être inscrit qu'une seule fois par année scolaire.
                        </div>
                        
                        <div class="d-flex justify-content-between">
                            <a href="index.php?controller=dashboard" class="btn btn-secondary">
                                <i class="fas fa-arrow-left me-1"></i>Retour
                            </a>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save me-1"></i>Inscrire l'étudiant
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            
            <!-- Section pour consulter les étudiants d'une classe -->
            <div class="card mt-4">
                <div class="card-header">
                    <h6 class="mb-0">
                        <i class="fas fa-list me-2"></i>Consulter les étudiants d'une classe
                    </h6>
                </div>
                <div class="card-body">
                    <form action="index.php" method="get">
                        <input type="hidden" name="controller" value="inscription">
                        <input type="hidden" name="action" value="list-etudiants-classe">
                        
                        <div class="row g-3">
                            <div class="col-md-6">
                                <select class="form-select" name="classe_id" required>
                                    <option value="">Sélectionner une classe</option>
                                    <?php foreach ($classes as $classe): ?>
                                        <option value="<?= $classe->getId() ?>">
                                            <?= htmlspecialchars($classe->getLibelle()) ?>
                                        </option>
                                    <?php endforeach; ?>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <select class="form-select" name="annee_scolaire" required>
                                    <option value="2024-2025">2024-2025</option>
                                    <option value="2025-2026">2025-2026</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-outline-primary w-100">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
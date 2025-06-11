<main class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2><i class="fas fa-chalkboard me-2"></i>Gestion des Classes</h2>
        <a href="index.php?controller=classe&action=form-classe" class="btn btn-primary">
            <i class="fas fa-plus me-1"></i>Nouvelle Classe
        </a>
    </div>
    
    <!-- Formulaire de recherche -->
    <div class="card mb-4">
        <div class="card-body">
            <form action="index.php" method="get" class="row g-3">
                <input type="hidden" name="controller" value="classe">
                <input type="hidden" name="action" value="list-classe">
                
                <div class="col-md-6">
                    <label for="filiere" class="form-label">Rechercher par filière</label>
                    <input type="text" class="form-control" name="filiere" id="filiere" 
                           placeholder="Ex: Informatique, Gestion..." value="<?= $_REQUEST['filiere'] ?? '' ?>">
                </div>
                
                <div class="col-md-6 d-flex align-items-end">
                    <button type="submit" class="btn btn-outline-primary">
                        <i class="fas fa-search me-1"></i>Rechercher
                    </button>
                </div>
            </form>
        </div>
    </div>
    
    <!-- Liste des classes -->
    <div class="card">
        <div class="card-header">
            <h5 class="mb-0">Liste des Classes</h5>
        </div>
        <div class="card-body">
            <?php if(empty($classes)): ?>
                <div class="text-center py-4">
                    <i class="fas fa-inbox fa-3x text-muted mb-3"></i>
                    <p class="text-muted">Aucune classe trouvée</p>
                </div>
            <?php else: ?>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>Libellé</th>
                                <th>Filière</th>
                                <th>Niveau</th>
                                <th>Date de création</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php foreach ($classes as $classe): ?>
                                <tr>
                                    <td>
                                        <strong><?= htmlspecialchars($classe->getLibelle()) ?></strong>
                                    </td>
                                    <td>
                                        <span class="badge bg-primary"><?= htmlspecialchars($classe->getFiliere()) ?></span>
                                    </td>
                                    <td><?= htmlspecialchars($classe->getNiveau()) ?></td>
                                    <td><?= $classe->getDateToString() ?></td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a href="index.php?controller=inscription&action=list-etudiants-classe&classe_id=<?= $classe->getId() ?>&annee_scolaire=2024-2025" 
                                               class="btn btn-sm btn-outline-info">
                                                <i class="fas fa-users me-1"></i>Étudiants
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            <?php endforeach; ?>
                        </tbody>
                    </table>
                </div>
            <?php endif; ?>
        </div>
    </div>
</main>
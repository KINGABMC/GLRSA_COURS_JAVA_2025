<main class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2><i class="fas fa-users me-2"></i>Gestion des Étudiants</h2>
        <a href="index.php?controller=etudiant&action=form-etudiant" class="btn btn-primary">
            <i class="fas fa-user-plus me-1"></i>Nouvel Étudiant
        </a>
    </div>
    
    <!-- Formulaire de recherche -->
    <div class="card mb-4">
        <div class="card-body">
            <form action="index.php" method="get" class="row g-3">
                <input type="hidden" name="controller" value="etudiant">
                <input type="hidden" name="action" value="list-etudiant">
                
                <div class="col-md-6">
                    <label for="nom" class="form-label">Rechercher par nom ou prénom</label>
                    <input type="text" class="form-control" name="nom" id="nom" 
                           placeholder="Nom ou prénom de l'étudiant..." value="<?= $_REQUEST['nom'] ?? '' ?>">
                </div>
                
                <div class="col-md-6 d-flex align-items-end">
                    <button type="submit" class="btn btn-outline-primary">
                        <i class="fas fa-search me-1"></i>Rechercher
                    </button>
                </div>
            </form>
        </div>
    </div>
    
    <!-- Liste des étudiants -->
    <div class="card">
        <div class="card-header">
            <h5 class="mb-0">Liste des Étudiants</h5>
        </div>
        <div class="card-body">
            <?php if(empty($etudiants)): ?>
                <div class="text-center py-4">
                    <i class="fas fa-user-graduate fa-3x text-muted mb-3"></i>
                    <p class="text-muted">Aucun étudiant trouvé</p>
                </div>
            <?php else: ?>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>Matricule</th>
                                <th>Nom complet</th>
                                <th>Adresse</th>
                                <th>Sexe</th>
                                <th>Date d'inscription</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php foreach ($etudiants as $etudiant): ?>
                                <tr>
                                    <td>
                                        <code><?= htmlspecialchars($etudiant->getMatricule()) ?></code>
                                    </td>
                                    <td>
                                        <strong><?= htmlspecialchars($etudiant->getNomComplet()) ?></strong>
                                    </td>
                                    <td><?= htmlspecialchars($etudiant->getAdresse()) ?></td>
                                    <td>
                                        <?php if($etudiant->getSexe() == 'M'): ?>
                                            <span class="badge bg-primary">Masculin</span>
                                        <?php else: ?>
                                            <span class="badge bg-pink">Féminin</span>
                                        <?php endif; ?>
                                    </td>
                                    <td><?= $etudiant->getDateToString() ?></td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <button class="btn btn-sm btn-outline-info" title="Voir détails">
                                                <i class="fas fa-eye"></i>
                                            </button>
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
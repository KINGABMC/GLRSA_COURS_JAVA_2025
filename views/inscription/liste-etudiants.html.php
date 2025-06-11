<main class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>
            <i class="fas fa-users me-2"></i>Étudiants inscrits
            <?php if(isset($classe)): ?>
                - <?= htmlspecialchars($classe->getLibelle()) ?>
            <?php endif; ?>
        </h2>
        <a href="index.php?controller=inscription&action=form-inscription" class="btn btn-secondary">
            <i class="fas fa-arrow-left me-1"></i>Retour aux inscriptions
        </a>
    </div>
    
    <?php if(isset($classe)): ?>
    <div class="card mb-4">
        <div class="card-body">
            <div class="row">
                <div class="col-md-3">
                    <strong>Classe :</strong> <?= htmlspecialchars($classe->getLibelle()) ?>
                </div>
                <div class="col-md-3">
                    <strong>Filière :</strong> <?= htmlspecialchars($classe->getFiliere()) ?>
                </div>
                <div class="col-md-3">
                    <strong>Niveau :</strong> <?= htmlspecialchars($classe->getNiveau()) ?>
                </div>
                <div class="col-md-3">
                    <strong>Année :</strong> <?= htmlspecialchars($_REQUEST['annee_scolaire']) ?>
                </div>
            </div>
        </div>
    </div>
    <?php endif; ?>
    
    <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <h5 class="mb-0">Liste des étudiants inscrits</h5>
            <span class="badge bg-primary"><?= count($etudiants) ?> étudiant(s)</span>
        </div>
        <div class="card-body">
            <?php if(empty($etudiants)): ?>
                <div class="text-center py-4">
                    <i class="fas fa-user-graduate fa-3x text-muted mb-3"></i>
                    <p class="text-muted">Aucun étudiant inscrit dans cette classe pour cette année</p>
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
                                <th>Statut</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php foreach ($etudiants as $etudiant): ?>
                                <tr>
                                    <td>
                                        <code><?= htmlspecialchars($etudiant['matricule']) ?></code>
                                    </td>
                                    <td>
                                        <strong><?= htmlspecialchars($etudiant['prenom'] . ' ' . $etudiant['nom']) ?></strong>
                                    </td>
                                    <td><?= htmlspecialchars($etudiant['adresse']) ?></td>
                                    <td>
                                        <?php if($etudiant['sexe'] == 'M'): ?>
                                            <span class="badge bg-primary">Masculin</span>
                                        <?php else: ?>
                                            <span class="badge bg-pink">Féminin</span>
                                        <?php endif; ?>
                                    </td>
                                    <td>
                                        <span class="badge bg-success">Actif</span>
                                    </td>
                                </tr>
                            <?php endforeach; ?>
                        </tbody>
                    </table>
                </div>
                
                <!-- Statistiques rapides -->
                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="card bg-light">
                            <div class="card-body text-center">
                                <h5 class="text-primary">
                                    <?= count(array_filter($etudiants, fn($e) => $e['sexe'] == 'M')) ?>
                                </h5>
                                <small class="text-muted">Étudiants masculins</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card bg-light">
                            <div class="card-body text-center">
                                <h5 class="text-pink">
                                    <?= count(array_filter($etudiants, fn($e) => $e['sexe'] == 'F')) ?>
                                </h5>
                                <small class="text-muted">Étudiantes féminines</small>
                            </div>
                        </div>
                    </div>
                </div>
            <?php endif; ?>
        </div>
    </div>
</main>
<main class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2><i class="fas fa-chart-bar me-2"></i>Statistiques de l'École</h2>
        <a href="index.php?controller=dashboard" class="btn btn-secondary">
            <i class="fas fa-arrow-left me-1"></i>Retour au tableau de bord
        </a>
    </div>

    <!-- Statistiques générales -->
    <div class="row g-4 mb-4">
        <div class="col-md-3">
            <div class="card bg-primary text-white">
                <div class="card-body text-center">
                    <i class="fas fa-users fa-2x mb-2"></i>
                    <h4><?= $statistiques['totalEtudiants'] ?? 0 ?></h4>
                    <p class="mb-0">Total Étudiants</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card bg-success text-white">
                <div class="card-body text-center">
                    <i class="fas fa-chalkboard fa-2x mb-2"></i>
                    <h4><?= $statistiques['totalClasses'] ?? 0 ?></h4>
                    <p class="mb-0">Total Classes</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card bg-info text-white">
                <div class="card-body text-center">
                    <i class="fas fa-user-tie fa-2x mb-2"></i>
                    <h4><?= $statistiques['totalProfesseurs'] ?? 0 ?></h4>
                    <p class="mb-0">Professeurs</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card bg-warning text-white">
                <div class="card-body text-center">
                    <i class="fas fa-book fa-2x mb-2"></i>
                    <h4><?= $statistiques['totalModules'] ?? 0 ?></h4>
                    <p class="mb-0">Modules</p>
                </div>
            </div>
        </div>
    </div>

    <div class="row g-4">
        <!-- Effectif par année -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0"><i class="fas fa-calendar me-2"></i>Effectif par Année Scolaire</h5>
                </div>
                <div class="card-body">
                    <?php if(empty($statistiques['effectifParAnnee'])): ?>
                        <p class="text-muted text-center">Aucune donnée disponible</p>
                    <?php else: ?>
                        <div class="table-responsive">
                            <table class="table table-sm">
                                <thead>
                                    <tr>
                                        <th>Année Scolaire</th>
                                        <th class="text-end">Effectif</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php foreach($statistiques['effectifParAnnee'] as $annee): ?>
                                        <tr>
                                            <td><?= htmlspecialchars($annee['annee_scolaire']) ?></td>
                                            <td class="text-end">
                                                <span class="badge bg-primary"><?= $annee['effectif'] ?></span>
                                            </td>
                                        </tr>
                                    <?php endforeach; ?>
                                </tbody>
                            </table>
                        </div>
                    <?php endif; ?>
                </div>
            </div>
        </div>

        <!-- Répartition par sexe et année -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0"><i class="fas fa-venus-mars me-2"></i>Répartition par Sexe et Année</h5>
                </div>
                <div class="card-body">
                    <?php if(empty($statistiques['repartitionSexeAnnee'])): ?>
                        <p class="text-muted text-center">Aucune donnée disponible</p>
                    <?php else: ?>
                        <div class="table-responsive">
                            <table class="table table-sm">
                                <thead>
                                    <tr>
                                        <th>Année</th>
                                        <th>Sexe</th>
                                        <th class="text-end">Nombre</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php foreach($statistiques['repartitionSexeAnnee'] as $repartition): ?>
                                        <tr>
                                            <td><?= htmlspecialchars($repartition['annee_scolaire']) ?></td>
                                            <td>
                                                <?php if($repartition['sexe'] == 'M'): ?>
                                                    <span class="badge bg-primary">Masculin</span>
                                                <?php else: ?>
                                                    <span class="badge bg-pink">Féminin</span>
                                                <?php endif; ?>
                                            </td>
                                            <td class="text-end"><?= $repartition['nombre'] ?></td>
                                        </tr>
                                    <?php endforeach; ?>
                                </tbody>
                            </table>
                        </div>
                    <?php endif; ?>
                </div>
            </div>
        </div>

        <!-- Effectif par classe -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0"><i class="fas fa-chalkboard-teacher me-2"></i>Effectif par Classe</h5>
                </div>
                <div class="card-body">
                    <?php if(empty($statistiques['effectifParClasse'])): ?>
                        <p class="text-muted text-center">Aucune donnée disponible</p>
                    <?php else: ?>
                        <div class="table-responsive">
                            <table class="table table-sm">
                                <thead>
                                    <tr>
                                        <th>Classe</th>
                                        <th>Filière</th>
                                        <th class="text-end">Effectif</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php foreach($statistiques['effectifParClasse'] as $classe): ?>
                                        <tr>
                                            <td><?= htmlspecialchars($classe['libelle']) ?></td>
                                            <td>
                                                <span class="badge bg-secondary"><?= htmlspecialchars($classe['filiere']) ?></span>
                                            </td>
                                            <td class="text-end">
                                                <span class="badge bg-info"><?= $classe['effectif'] ?></span>
                                            </td>
                                        </tr>
                                    <?php endforeach; ?>
                                </tbody>
                            </table>
                        </div>
                    <?php endif; ?>
                </div>
            </div>
        </div>

        <!-- Répartition par sexe et classe -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0"><i class="fas fa-users me-2"></i>Répartition par Sexe et Classe</h5>
                </div>
                <div class="card-body">
                    <?php if(empty($statistiques['repartitionSexeClasse'])): ?>
                        <p class="text-muted text-center">Aucune donnée disponible</p>
                    <?php else: ?>
                        <div class="table-responsive">
                            <table class="table table-sm">
                                <thead>
                                    <tr>
                                        <th>Classe</th>
                                        <th>Sexe</th>
                                        <th class="text-end">Nombre</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php foreach($statistiques['repartitionSexeClasse'] as $repartition): ?>
                                        <tr>
                                            <td><?= htmlspecialchars($repartition['libelle']) ?></td>
                                            <td>
                                                <?php if($repartition['sexe'] == 'M'): ?>
                                                    <span class="badge bg-primary">Masculin</span>
                                                <?php else: ?>
                                                    <span class="badge bg-pink">Féminin</span>
                                                <?php endif; ?>
                                            </td>
                                            <td class="text-end"><?= $repartition['nombre'] ?></td>
                                        </tr>
                                    <?php endforeach; ?>
                                </tbody>
                            </table>
                        </div>
                    <?php endif; ?>
                </div>
            </div>
        </div>

        <!-- Suspensions et annulations -->
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0"><i class="fas fa-exclamation-triangle me-2"></i>Suspensions et Annulations par Année</h5>
                </div>
                <div class="card-body">
                    <?php if(empty($statistiques['suspensionsAnnulations'])): ?>
                        <p class="text-muted text-center">Aucune donnée disponible</p>
                    <?php else: ?>
                        <div class="row">
                            <?php foreach($statistiques['suspensionsAnnulations'] as $stat): ?>
                                <div class="col-md-4 mb-3">
                                    <div class="card bg-light">
                                        <div class="card-body text-center">
                                            <h6><?= htmlspecialchars($stat['annee_scolaire']) ?></h6>
                                            <div class="row">
                                                <div class="col-6">
                                                    <h5 class="text-warning"><?= $stat['suspensions'] ?></h5>
                                                    <small>Suspensions</small>
                                                </div>
                                                <div class="col-6">
                                                    <h5 class="text-danger"><?= $stat['annulations'] ?></h5>
                                                    <small>Annulations</small>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            <?php endforeach; ?>
                        </div>
                    <?php endif; ?>
                </div>
            </div>
        </div>
    </div>

    <!-- Graphiques (optionnel avec Chart.js) -->
    <div class="row mt-4">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0"><i class="fas fa-chart-pie me-2"></i>Résumé Visuel</h5>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-6">
                            <canvas id="sexeChart" width="400" height="200"></canvas>
                        </div>
                        <div class="col-md-6">
                            <canvas id="filiereChart" width="400" height="200"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
// Graphique répartition par sexe
<?php if(!empty($statistiques['repartitionSexeAnnee'])): ?>
const sexeData = <?= json_encode($statistiques['repartitionSexeAnnee']) ?>;
const sexeLabels = [...new Set(sexeData.map(item => item.sexe))];
const sexeCounts = sexeLabels.map(sexe => 
    sexeData.filter(item => item.sexe === sexe)
           .reduce((sum, item) => sum + parseInt(item.nombre), 0)
);

new Chart(document.getElementById('sexeChart'), {
    type: 'doughnut',
    data: {
        labels: sexeLabels.map(s => s === 'M' ? 'Masculin' : 'Féminin'),
        datasets: [{
            data: sexeCounts,
            backgroundColor: ['#007bff', '#e83e8c']
        }]
    },
    options: {
        responsive: true,
        plugins: {
            title: {
                display: true,
                text: 'Répartition par Sexe'
            }
        }
    }
});
<?php endif; ?>

// Graphique par filière
<?php if(!empty($statistiques['effectifParClasse'])): ?>
const filiereData = <?= json_encode($statistiques['effectifParClasse']) ?>;
const filiereLabels = [...new Set(filiereData.map(item => item.filiere))];
const filiereCounts = filiereLabels.map(filiere => 
    filiereData.filter(item => item.filiere === filiere)
              .reduce((sum, item) => sum + parseInt(item.effectif), 0)
);

new Chart(document.getElementById('filiereChart'), {
    type: 'bar',
    data: {
        labels: filiereLabels,
        datasets: [{
            label: 'Effectif',
            data: filiereCounts,
            backgroundColor: '#28a745'
        }]
    },
    options: {
        responsive: true,
        plugins: {
            title: {
                display: true,
                text: 'Effectif par Filière'
            }
        }
    }
});
<?php endif; ?>
</script>
<main class="container my-4">
    <div class="row">
        <div class="col-12">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2><i class="fas fa-tachometer-alt me-2"></i>Tableau de bord</h2>
                <div class="text-muted">
                    <i class="fas fa-calendar me-1"></i><?= date('d/m/Y') ?>
                </div>
            </div>
        </div>
    </div>
    
    <div class="row g-4">
        <?php if($_SESSION['user']->getRole() == 'RP'): ?>
        <!-- Cartes pour Responsable Pédagogique -->
        <div class="col-md-6 col-lg-3">
            <div class="card bg-primary text-white h-100">
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <div>
                            <h6 class="card-title">Classes</h6>
                            <h3 class="mb-0">Gérer</h3>
                        </div>
                        <div class="align-self-center">
                            <i class="fas fa-chalkboard fa-2x opacity-75"></i>
                        </div>
                    </div>
                </div>
                <div class="card-footer bg-primary border-0">
                    <a href="index.php?controller=classe&action=list-classe" class="text-white text-decoration-none">
                        Voir les classes <i class="fas fa-arrow-right ms-1"></i>
                    </a>
                </div>
            </div>
        </div>
        
        <div class="col-md-6 col-lg-3">
            <div class="card bg-success text-white h-100">
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <div>
                            <h6 class="card-title">Statistiques</h6>
                            <h3 class="mb-0">Analyser</h3>
                        </div>
                        <div class="align-self-center">
                            <i class="fas fa-chart-bar fa-2x opacity-75"></i>
                        </div>
                    </div>
                </div>
                <div class="card-footer bg-success border-0">
                    <a href="index.php?controller=dashboard&action=statistiques" class="text-white text-decoration-none">
                        Voir les stats <i class="fas fa-arrow-right ms-1"></i>
                    </a>
                </div>
            </div>
        </div>
        <?php endif; ?>
        
        <?php if($_SESSION['user']->getRole() == 'ATTACHE' || $_SESSION['user']->getRole() == 'RP'): ?>
        <!-- Cartes pour Attaché de classe -->
        <div class="col-md-6 col-lg-3">
            <div class="card bg-info text-white h-100">
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <div>
                            <h6 class="card-title">Étudiants</h6>
                            <h3 class="mb-0">Gérer</h3>
                        </div>
                        <div class="align-self-center">
                            <i class="fas fa-users fa-2x opacity-75"></i>
                        </div>
                    </div>
                </div>
                <div class="card-footer bg-info border-0">
                    <a href="index.php?controller=etudiant&action=list-etudiant" class="text-white text-decoration-none">
                        Liste étudiants <i class="fas fa-arrow-right ms-1"></i>
                    </a>
                </div>
            </div>
        </div>
        
        <div class="col-md-6 col-lg-3">
            <div class="card bg-warning text-white h-100">
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <div>
                            <h6 class="card-title">Inscriptions</h6>
                            <h3 class="mb-0">Nouveau</h3>
                        </div>
                        <div class="align-self-center">
                            <i class="fas fa-user-plus fa-2x opacity-75"></i>
                        </div>
                    </div>
                </div>
                <div class="card-footer bg-warning border-0">
                    <a href="index.php?controller=inscription&action=form-inscription" class="text-white text-decoration-none">
                        Inscrire étudiant <i class="fas fa-arrow-right ms-1"></i>
                    </a>
                </div>
            </div>
        </div>
        <?php endif; ?>
    </div>
    
    <!-- Section d'accueil -->
    <div class="row mt-5">
        <div class="col-12">
            <div class="card">
                <div class="card-body text-center py-5">
                    <i class="fas fa-graduation-cap fa-4x text-primary mb-4"></i>
                    <h3>Bienvenue dans le système de gestion scolaire ISM</h3>
                    <p class="lead text-muted">
                        Gérez efficacement les inscriptions, classes et étudiants de votre établissement
                    </p>
                    
                    <div class="row mt-4">
                        <div class="col-md-4">
                            <div class="feature-box">
                                <i class="fas fa-chalkboard-teacher fa-2x text-primary mb-3"></i>
                                <h5>Gestion des Classes</h5>
                                <p class="text-muted">Créez et organisez vos classes par filière et niveau</p>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="feature-box">
                                <i class="fas fa-user-graduate fa-2x text-success mb-3"></i>
                                <h5>Suivi des Étudiants</h5>
                                <p class="text-muted">Gérez les inscriptions et le suivi des étudiants</p>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="feature-box">
                                <i class="fas fa-chart-line fa-2x text-info mb-3"></i>
                                <h5>Statistiques</h5>
                                <p class="text-muted">Analysez les données de votre établissement</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
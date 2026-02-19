Mediscreen - Système de Gestion de Santé
Description du Projet

Mediscreen est une solution de microservices destinée aux praticiens pour centraliser les informations patients, leurs notes médicales, et évaluer instantanément le risque de diabète. L'architecture est conçue pour être scalable, sécurisée et éco-responsable.

Fonctionnalités Clés

    Patient Management : Gestion complète du profil patient (CRUD).

    Notes Médicales : Historique des consultations stocké sur base documentaire (NoSQL).

    Diabetes Risk Engine : Algorithme prédictif basé sur des déclencheurs cliniques.

    Sécurité Centralisée : Authentification Basic Auth via Gateway.

    Interface Responsive : Dashboard praticien avec Thymeleaf.

Architecture Technique

Le système est découpé en 4 modules autonomes communiquant via une Gateway :
Service	Port	Technologie	Rôle
Gateway	8082	Spring Cloud Gateway	Point d'entrée unique & Sécurité
Frontend	8084	Thymeleaf / Spring	Interface utilisateur
Patient-Back	8081	MySQL / Hibernate	Gestion des données patients
Doctor-Notes	8083	MongoDB	Stockage des notes cliniques
Diabetes-Risk	8085	Java 17	Calculateur de risque métier

Engagement Green Code & Sobriété Numérique

Ce projet intègre des principes de développement durable pour réduire l'empreinte environnementale du logiciel :

    Optimisation des Ressources (Singleton Pattern) : Utilisation de l'injection de dépendances pour RestTemplate, évitant la multiplication d'instances en mémoire.

    Sobriété Réseau : Centralisation de la sécurité sur la Gateway pour éviter des calculs redondants (CPU) sur chaque microservice.

    Green Testing : Utilisation de tests de tranches (@WebMvcTest) et de mocks pour réduire le temps de build et la consommation énergétique des serveurs de CI/CD.

    Conteneurisation Optimisée : Utilisation d'images Docker légères pour limiter l'utilisation du stockage et de la bande passante.

Installation et Lancement
Pré-requis

    Docker & Docker Compose

    Java 17 (pour le développement)

    Maven 3.8+

Démarrage Rapide

    Cloner le dépôt :
    Bash

    git clone [url-du-repo]
    cd p9_java_springboot

    Compiler le projet :
    Bash

    mvn clean install -DskipTests

    Lancer avec Docker :
    Bash

    docker-compose up --build

    Accès :
    Ouvrez http://localhost:8082 (Gateway)
    Identifiants : docteur_admin / mediscreen2026

Tests et Qualité

Chaque service dispose d'une suite de tests unitaires et d'intégration :
Bash

# Pour lancer tous les tests
mvn test

Couverture : Services métiers, Contrôleurs REST, et Sécurité Gateway.

Structure du Code

Le projet suit les standards de clean code :

    DTOs : Transfert de données sécurisé et validation via @Valid.

    Global Exception Handling : Centralisation des erreurs pour une API robuste.

    Separation of Concerns : Logique métier strictement isolée dans les @Service.
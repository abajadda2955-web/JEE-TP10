TP Spring Security - Formulaire de Connexion Personnalisé
---
Objectif du TP

Personnaliser le mécanisme d'authentification de Spring Security en remplaçant la page de connexion par défaut par un formulaire HTML sur mesure avec :

    Gestion des messages d'erreur

    Messages de confirmation de déconnexion

    Redirections personnalisées selon le rôle

    Différenciation des accès (ADMIN / USER)

Technologies utilisées

    Spring Boot 3.3.x

    Spring Security

    Spring Web

    Thymeleaf

    Spring DevTools

---
Captures d'écran

Page de connexion avec messages

<img width="742" height="630" alt="login" src="https://github.com/user-attachments/assets/600113ab-36d1-4e3f-9fbe-9e6cb110be00" />


Affichage des messages :

    ❌ Erreur en cas d'identifiants incorrects

    ✅ Confirmation après déconnexion

---

Page d'accueil après connexion

<img width="743" height="467" alt="home" src="https://github.com/user-attachments/assets/a67d6f63-9236-4cb2-bc0a-851a42e14cd1" />


Redirection après authentification réussie vers /home avec liens vers les espaces
---

Espace utilisateur

<img width="739" height="347" alt="user-dash" src="https://github.com/user-attachments/assets/f5027473-e58a-47c0-8453-1ca3dec46028" />


Accessible aux rôles USER et ADMIN
---

Espace administrateur

<img width="745" height="506" alt="admin-dash" src="https://github.com/user-attachments/assets/f5e99ab2-21a1-4b97-bf04-779fda80e487" />

Réservé au rôle ADMIN avec message d'avertissement
---
Structure du projet

```

src/main/java/ma/fstg/security/
├── SpringSecurityDemoApplication.java
├── config/
│   └── SecurityConfig.java          # Configuration Spring Security
└── web/
    └── AuthController.java           # Gestion des vues

src/main/resources/
├── application.properties            # Configuration (port 8083)
└── templates/
    ├── login.html                    # Formulaire de connexion
    ├── home.html                     # Page d'accueil
    ├── user-dashboard.html           # Espace utilisateur
    └── admin-dashboard.html          # Espace administrateur
```

Flux d'authentification

    Accès à une page protégée → Redirection vers /login

    Saisie des identifiants → Envoi vers /authenticate

    Spring Security vérifie les identifiants

    Succès → Redirection vers /home

    Échec → Retour /login?error=true avec message d'erreur

    Déconnexion → /login?logout=true avec message de confirmation

Fonctionnalités implémentées

    ✅ Formulaire de connexion personnalisé avec CSS

    ✅ Gestion des messages d'erreur et de déconnexion

    ✅ Redirection après authentification réussie

    ✅ Protection des URLs selon les rôles

    ✅ Page de déconnexion fonctionnelle


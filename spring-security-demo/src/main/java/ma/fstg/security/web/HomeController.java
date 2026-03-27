package ma.fstg.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Page d'accueil - tout le monde la voit après connexion
    // Je mets un message un peu long pour que ce soit clair
    @GetMapping("/")
    public String home() {
        return "Bienvenue sur la page principale ! Vous êtes bien connecté. " +
                "Pour tester, allez sur /user/dashboard ou /admin/dashboard selon votre compte.";
    }

    // Espace réservé aux utilisateurs normaux
    // Les admins peuvent aussi venir ici
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "🎯 ESPACE UTILISATEUR - Vous avez les droits USER ou ADMIN. " +
                "Depuis ici, vous pouvez consulter vos informations personnelles. " +
                "Si vous voulez voir la zone admin, essayez /admin/dashboard (si vous avez les droits).";
    }

    // Espace ultra privé - uniquement pour les admins
    // Si un simple user essaie d'y aller, Spring Security le bloque
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "🔐 PANEL ADMINISTRATEUR - Accès restreint. " +
                "Félicitations, vous avez le rôle ADMIN. " +
                "Vous pouvez tout gérer ici, mais soyez prudent !";
    }
}
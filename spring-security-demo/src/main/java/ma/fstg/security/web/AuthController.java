package ma.fstg.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // Affiche la page de connexion personnalisée
    // C'est celle qu'on a créée en HTML
    @GetMapping("/login")
    public String afficherFormulaireConnexion() {
        return "login";
    }

    // Page d'accueil après connexion réussie
    // L'utilisateur arrive ici après avoir tapé ses identifiants
    @GetMapping("/home")
    public String afficherAccueil() {
        return "home";
    }

    // Tableau de bord administrateur
    // Seuls les utilisateurs avec le rôle ADMIN peuvent y accéder
    @GetMapping("/admin/dashboard")
    public String afficherDashboardAdmin() {
        return "admin-dashboard";
    }

    // Tableau de bord utilisateur standard
    // Accessible aux rôles USER et ADMIN
    @GetMapping("/user/dashboard")
    public String afficherDashboardUser() {
        return "user-dashboard";
    }
}
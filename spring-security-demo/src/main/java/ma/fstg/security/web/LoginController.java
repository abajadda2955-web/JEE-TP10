package ma.fstg.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Quand quelqu'un va sur /login, on affiche notre  page
    // Spring Security nous redirige automatiquement ici si on essaie d'accéder
    // à une page protégée sans être connecté
    @GetMapping("/login")
    public String afficherLogin() {
        // Le nom "login" correspond au fichier login.html dans templates
        return "login";
    }
}
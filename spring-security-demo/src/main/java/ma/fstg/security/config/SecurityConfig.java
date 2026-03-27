package ma.fstg.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Ici on crée les comptes de test comme dans le TP9
    // Je garde les mêmes identifiants pour la continuité
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}1234")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("{noop}1111")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    // La grosse différence avec le TP1 est ici
    // On ajoute la gestion des URLs de login personnalisées
    // et les redirections après connexion/déconnexion
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Les règles d'accès aux URLs
                .authorizeHttpRequests(auth -> auth
                        // La page de login et les fichiers CSS sont accessibles à tous (même non connectés)
                        .requestMatchers("/login", "/css/**").permitAll()
                        // Zone admin : réservée aux admins
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Zone user : accessible aux users et admins
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        // Tout le reste nécessite une authentification
                        .anyRequest().authenticated()
                )

                // 2. Configuration du formulaire de connexion personnalisé
                .formLogin(form -> form
                        .loginPage("/login")                    // Notre page HTML personnalisée
                        .loginProcessingUrl("/authenticate")    // Spring va traiter cette URL automatiquement
                        .defaultSuccessUrl("/home", true)       // Après connexion réussie, direction /home
                        .failureUrl("/login?error=true")        // Si erreur, on revient sur login avec un paramètre
                        .permitAll()                            // Tout le monde peut voir la page de login
                )

                // 3. Configuration de la déconnexion
                .logout(logout -> logout
                        .logoutUrl("/logout")                   // URL pour se déconnecter
                        .logoutSuccessUrl("/login?logout=true") // Après déconnexion, retour login avec message
                        .permitAll()                            // Tout le monde peut se déconnecter
                );

        return http.build();
    }
}
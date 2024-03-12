package version2.version.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import version2.version.Models.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // méthodes personnalisées si nécessaire
}

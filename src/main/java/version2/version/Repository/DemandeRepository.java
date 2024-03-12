package version2.version.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import version2.version.Models.Demande;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
    List<Demande> findByChefHierarchiqueIdAndStatut(Long chefHierarchiqueId, String statut);

    // méthodes personnalisées si nécessaire
}

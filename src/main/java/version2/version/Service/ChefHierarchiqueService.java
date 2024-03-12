package version2.version.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import version2.version.Models.Demande;

import java.util.List;

@Service
public class ChefHierarchiqueService {

    private final DemandeService demandeService;
    private final UtilisateurService utilisateurService;

    @Autowired
    public ChefHierarchiqueService(DemandeService demandeService, UtilisateurService utilisateurService) {
        this.demandeService = demandeService;
        this.utilisateurService = utilisateurService;
    }

    // Méthode pour récupérer les demandes à traiter par le chef hiérarchique
    public List<Demande> getDemandesEnAttentePourChefHierarchique(Long chefHierarchiqueId) {
        // Vérifier si l'utilisateur est bien un chef hiérarchique
        if (!utilisateurService.isChefHierarchique(chefHierarchiqueId)) {
            throw new DemandeService.UnauthorizedException("Vous n'êtes pas autorisé à accéder à ces demandes.");
        }

        // Récupérer les demandes en attente de validation par ce chef hiérarchique
        return demandeService.getDemandesEnAttentePourChefHierarchique(chefHierarchiqueId);
    }

    // Méthode pour approuver une demande spécifique par le chef hiérarchique
    public Demande approuverDemande(Long chefHierarchiqueId, Long demandeId) {
        // Vérifier si l'utilisateur est bien un chef hiérarchique
        if (!utilisateurService.isChefHierarchique(chefHierarchiqueId)) {
            throw new DemandeService.UnauthorizedException("Vous n'êtes pas autorisé à approuver cette demande.");
        }

        // Approuver la demande spécifique
        return demandeService.approuverDemandeParChefHierarchique(chefHierarchiqueId, demandeId);
    }

    // Méthode pour rejeter une demande spécifique par le chef hiérarchique
    public Demande rejeterDemande(Long chefHierarchiqueId, Long demandeId) throws ChangeSetPersister.NotFoundException {
        // Vérifier si l'utilisateur est bien un chef hiérarchique
        if (!utilisateurService.isChefHierarchique(chefHierarchiqueId)) {
            throw new DemandeService.UnauthorizedException("Vous n'êtes pas autorisé à rejeter cette demande.");
        }

        // Rejeter la demande spécifique
        return demandeService.rejeterDemandeParChefHierarchique(chefHierarchiqueId, demandeId);
    }
}
package version2.version.Service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import version2.version.Models.Demande;
import version2.version.Models.Utilisateur;
import version2.version.Repository.DemandeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService {

    private final DemandeRepository demandeRepository;

    @Autowired
    public DemandeService(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }

    public Demande creerDemande(Demande demande) {
        // Implémentez ici la logique pour créer une nouvelle demande
        return demandeRepository.save(demande);
    }

    public Demande getDemandeById(Long id) {
        // Implémentez ici la logique pour récupérer une demande par son ID
        return demandeRepository.findById(id).orElse(null);
    }


    public boolean supprimerDemande(Long id) {
        // Implémentez ici la logique pour supprimer une demande par son ID
        Demande demande = demandeRepository.findById(id).orElse(null);
        if (demande != null) {
            demandeRepository.delete(demande);
            return true;
        } else {
            return false;
        }
    }

    public Demande modifierDemande(Long id, Demande demandeDetails) throws ChangeSetPersister.NotFoundException {
        // Récupérer l'identifiant de l'utilisateur actuel
        long userId = UtilisateurService.getCurrentUserId();

        Demande existingDemande = demandeRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Vérifier si l'utilisateur actuel est autorisé à modifier la demande
        if (!existingDemande.getUtilisateur()) {
            throw new UnauthorizedException("Vous n'êtes pas autorisé à modifier cette demande.");
        }

        // Mettre à jour les détails de la demande existante avec les nouvelles valeurs
        existingDemande.setTypeDemande(demandeDetails.getTypeDemande());
        existingDemande.setTypeDemande(demandeDetails.getTypeDemande());
        // Continuez avec d'autres attributs...

        // Enregistrez la demande mise à jour dans le repository
        return demandeRepository.save(existingDemande);
    }

    public static class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }

    public void traiterDemande(Demande demande, Utilisateur utilisateur) {
        if (utilisateur.isChefHierarchique()) {
            // Logique pour traiter la demande en tant que chef hiérarchique
        } else {
            throw new UnauthorizedException("L'utilisateur n'est pas autorisé à traiter cette demande.");
        }
    }

    public List<Demande> getDemandesEnAttentePourChefHierarchique(Long chefHierarchiqueId) {
        // Implémentez ici la logique pour récupérer les demandes en attente pour le chef hiérarchique
        // par exemple, vous pouvez interroger la base de données pour récupérer les demandes en attente pour ce chef
        return demandeRepository.findByChefHierarchiqueIdAndStatut(chefHierarchiqueId, "EN_ATTENTE");
    }


    public Demande approuverDemandeParChefHierarchique(Long chefHierarchiqueId, Long demandeId) {
        // Implémentez ici la logique pour approuver une demande spécifique par le chef hiérarchique
        // par exemple, vous pouvez rechercher la demande par son ID et mettre à jour son statut pour "APPROUVE"
        Demande demande = demandeRepository.findById(demandeId).orElse(null);
        if (demande != null && demande.getChefHierarchiqueId().equals(chefHierarchiqueId)) {
            demande.setStatut("APPROUVE");
            return demandeRepository.save(demande);
        } else {
            throw new DemandeService.UnauthorizedException("Vous n'êtes pas autorisé à approuver cette demande.");
        }
    }
    public Demande rejeterDemandeParChefHierarchique(Long chefHierarchiqueId, Long demandeId) throws ChangeSetPersister.NotFoundException {
        // Vérifier si l'utilisateur est bien un chef hiérarchique
        if (!UtilisateurService.isChefHierarchique(chefHierarchiqueId)) {
            throw new UnauthorizedException("Vous n'êtes pas autorisé à rejeter cette demande.");
        }

        // Récupérer la demande à rejeter
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Mettre à jour le statut de la demande à "rejetée"
        demande.setStatut("rejetée");

        // Enregistrer les modifications dans la base de données
        return demandeRepository.save(demande);
    }

}


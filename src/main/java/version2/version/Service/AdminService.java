package version2.version.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import version2.version.Models.Demande;
import version2.version.Models.Utilisateur;
import version2.version.Repository.DemandeRepository;
import version2.version.Repository.UtilisateurRepository;

import java.util.List;

@Service
public class AdminService {

    private final DemandeRepository demandeRepository;
    private final UtilisateurRepository userRepository;

    @Autowired
    public AdminService(DemandeRepository demandeRepository, UtilisateurRepository userRepository) {
        this.demandeRepository = demandeRepository;
        this.userRepository = userRepository;
    }

    // Méthode pour récupérer toutes les demandes
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    // Méthode pour récupérer tous les utilisateurs
    public List<Utilisateur> getAllUsers() {
        return userRepository.findAll();
    }

    // Méthode pour approuver une demande spécifique
    public Demande approuverDemande(Long demandeId) throws ChangeSetPersister.NotFoundException {
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        // Logique pour approuver la demande
        demande.setStatut("Approuvée");
        return demandeRepository.save(demande);
    }

    // Méthode pour rejeter une demande spécifique
    public Demande rejeterDemande(Long demandeId) throws ChangeSetPersister.NotFoundException {
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        // Logique pour rejeter la demande
        demande.setStatut("Rejetée");
        return demandeRepository.save(demande);
    }
}

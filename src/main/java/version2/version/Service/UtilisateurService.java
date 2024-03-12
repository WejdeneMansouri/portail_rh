package version2.version.Service;

import org.springframework.stereotype.Service;
import version2.version.Models.Utilisateur;
import version2.version.Repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public static long getCurrentUserId() {
        return 0;
    }

    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        // Ajoutez ici la logique pour créer un nouvel utilisateur
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur getUtilisateurById(Long id) {
        // Ajoutez ici la logique pour récupérer un utilisateur par son ID
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateur) {
        // Ajoutez ici la logique pour modifier un utilisateur existant
        Utilisateur utilisateurExistant = utilisateurRepository.findById(id).orElse(null);
        if (utilisateurExistant != null) {
            // Mettez à jour les propriétés de l'utilisateur existant avec celles fournies
            // dans l'objet utilisateur
            utilisateurExistant.setNom(utilisateur.getNom());

            // Ajoutez d'autres propriétés à mettre à jour si nécessaire
            return utilisateurRepository.save(utilisateurExistant);
        } else {
            return null;
        }
    }

    public boolean supprimerUtilisateur(Long id) {
        // Ajoutez ici la logique pour supprimer un utilisateur par son ID
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (utilisateur != null) {
            utilisateurRepository.delete(utilisateur);
            return true;
        } else {
            return false;
        }
    }

    private static boolean chefHierarchique;

    // Autres attributs et méthodes de la classe Utilisateur...

    public static boolean isChefHierarchique(Long chefHierarchiqueId) {
        return chefHierarchique;
    }

    public void setChefHierarchique(boolean chefHierarchique) {
        this.chefHierarchique = chefHierarchique;
    }
}

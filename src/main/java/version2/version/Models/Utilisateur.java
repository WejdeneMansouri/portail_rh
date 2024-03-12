package version2.version.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String prenom;

    private boolean chefHierarchique;
    // autres attributs

    public String getNom() {
        return nom;
    }


    // Autres attributs et méthodes de la classe Utilisateur...

    public boolean isChefHierarchique() {
        return chefHierarchique;
    }

    public void setChefHierarchique(boolean chefHierarchique) {
        this.chefHierarchique = chefHierarchique;
    }

    public Long getChefHierarchiqueId() {
        if (chefHierarchique) {
            return id; // Retourne l'ID du chef hiérarchique
        } else {
            return null; // Retourne null si l'utilisateur n'est pas un chef hiérarchique
        }
    }
    // getters et setters
}
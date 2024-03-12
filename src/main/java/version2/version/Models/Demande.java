package version2.version.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Demande {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeDemande;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
    }


    public boolean getUtilisateur() {
        return false;
    }
    private boolean chefHierarchique;

    public Long getChefHierarchiqueId() {
        if (chefHierarchique) {
            return id; // Retourne l'ID du chef hiérarchique
        } else {
            return null; // Retourne null si l'utilisateur n'est pas un chef hiérarchique
        }
    }

    public void setStatut(String approuve) {
    }
}

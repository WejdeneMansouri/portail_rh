package version2.version.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import version2.version.Models.Demande;
import version2.version.Service.DemandeService;

@RestController
@RequestMapping("/demandes")
public class DemandeController {

    private final DemandeService demandeService;

    @Autowired
    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demande> getDemandeById(@PathVariable Long id) {
        Demande demande = demandeService.getDemandeById(id);
        return ResponseEntity.ok(demande);
    }

    @PostMapping
    public ResponseEntity<Demande> creerDemande(@RequestBody Demande demande) {
        Demande createdDemande = demandeService.creerDemande(demande);
        return new ResponseEntity<>(createdDemande, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Demande> modifierDemande(@PathVariable Long id, @RequestBody Demande demandeDetails) {
        Demande updatedDemande = demandeService.modifierDemande(id, demandeDetails);
        return ResponseEntity.ok(updatedDemande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDemande(@PathVariable Long id) {
        demandeService.supprimerDemande(id);
        return ResponseEntity.noContent().build();
    }
}
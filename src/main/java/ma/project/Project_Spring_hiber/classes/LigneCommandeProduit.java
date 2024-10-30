package ma.project.Project_Spring_hiber.classes;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LigneCommandeProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantite;
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;
}

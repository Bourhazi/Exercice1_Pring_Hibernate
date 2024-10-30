package ma.project.Project_Spring_hiber.classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String reference;
    private float prix;
    @ManyToOne
    @JoinColumn(name="categorie_id")
    private Categorie categorie;

    @OneToMany(mappedBy = "produit")
    private List<LigneCommandeProduit> lignesCommande;

}

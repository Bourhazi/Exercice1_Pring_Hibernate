package ma.project.Project_Spring_hiber.classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Categorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String code;
    private String libelle;
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;

}

package ma.project.Project_Spring_hiber.classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommandeProduit> lignesCommande;
}

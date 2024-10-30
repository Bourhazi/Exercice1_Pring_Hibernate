package ma.project.Project_Spring_hiber.Test;


import ma.project.Project_Spring_hiber.classes.Categorie;
import ma.project.Project_Spring_hiber.classes.Commande;
import ma.project.Project_Spring_hiber.classes.LigneCommandeProduit;
import ma.project.Project_Spring_hiber.classes.Produit;
import ma.project.Project_Spring_hiber.service.CategorieService;
import ma.project.Project_Spring_hiber.service.CommandeService;
import ma.project.Project_Spring_hiber.service.ProduitService;
import ma.project.Project_Spring_hiber.service.LigneCommandeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ma.project.Project_Spring_hiber.util.HibernateConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestProduitService {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

            ProduitService produitService = context.getBean(ProduitService.class);
            CategorieService categorieService = context.getBean(CategorieService.class);
            CommandeService commandeService = context.getBean(CommandeService.class);
            LigneCommandeService ligneCommandeService = context.getBean(LigneCommandeService.class);

            Categorie categorie = new Categorie();
            categorie.setCode("CAT001");
            categorie.setLibelle("Électronique");
            categorieService.create(categorie);

            Produit produit1 = new Produit();
            produit1.setReference("ES12");
            produit1.setPrix(120);
            produit1.setCategorie(categorie);
            produitService.create(produit1);

            Produit produit2 = new Produit();
            produit2.setReference("ZR85");
            produit2.setPrix(100);
            produit2.setCategorie(categorie);
            produitService.create(produit2);

            Produit produit3 = new Produit();
            produit3.setReference("EE85");
            produit3.setPrix(200);
            produit3.setCategorie(categorie);
            produitService.create(produit3);

            System.out.println("\nTest de getById :");
            Produit produit = produitService.getById(produit1.getId());
            System.out.println("Produit récupéré par ID : Référence = " + produit.getReference() + ", Prix = " + produit.getPrix());

            System.out.println("\nTest de getAll :");
            List<Produit> allProduits = produitService.getAll();
            System.out.println("Tous les produits :");
            for (Produit p : allProduits) {
                System.out.println("Référence = " + p.getReference() + ", Prix = " + p.getPrix());
            }

            System.out.println("\nTest de getProduitsByCategorie :");
            List<Produit> produitsParCategorie = produitService.getProduitsByCategorie(categorie.getId());
            System.out.println("Produits dans la catégorie " + categorie.getLibelle() + " :");
            for (Produit p : produitsParCategorie) {
                System.out.println("Référence = " + p.getReference() + ", Prix = " + p.getPrix());
            }

            Commande commande = new Commande();
            commande.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-03-14"));
            commandeService.create(commande);

            LigneCommandeProduit ligne1 = new LigneCommandeProduit();
            ligne1.setCommande(commande);
            ligne1.setProduit(produit1);
            ligne1.setQuantite(7);
            ligneCommandeService.create(ligne1);

            LigneCommandeProduit ligne2 = new LigneCommandeProduit();
            ligne2.setCommande(commande);
            ligne2.setProduit(produit2);
            ligne2.setQuantite(14);
            ligneCommandeService.create(ligne2);

            LigneCommandeProduit ligne3 = new LigneCommandeProduit();
            ligne3.setCommande(commande);
            ligne3.setProduit(produit3);
            ligne3.setQuantite(5);
            ligneCommandeService.create(ligne3);

            System.out.println("\nTest de getProduitsCommandesEntreDates :");
            Date dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01");
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31");
            List<Produit> produitsEntreDates = produitService.getProduitsCommandesEntreDates(dateDebut, dateFin);
            System.out.println("Produits commandés entre " + dateDebut + " et " + dateFin + " :");
            for (Produit p : produitsEntreDates) {
                System.out.println("Référence = " + p.getReference() + ", Prix = " + p.getPrix());
            }

            System.out.println("\nTest de getProduitsDansCommande :");
            List<Produit> produitsDansCommande = produitService.getProduitsDansCommande(commande.getId());
            System.out.println("Produits dans la commande ID " + commande.getId() + " :");
            for (Produit p : produitsDansCommande) {
                System.out.println("Référence = " + p.getReference() + ", Prix = " + p.getPrix());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


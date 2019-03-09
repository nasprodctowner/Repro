package exoRepro.entity;

import javax.persistence.*;

@Entity
@Table(name="test.POLY")
public class Poly {


    private int idPoly;
    private String titre;
    private int nbPages;
    private String nomDemandeur;
    private Commande laCommande;
    private Demandeur demandeur;


    public Poly(String titre, int nbPages, String nomDemandeur) {
        this.titre = titre;
        this.nbPages = nbPages;
        this.nomDemandeur = nomDemandeur;
    }

    public Poly() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdPoly() {
        return idPoly;
    }

    public void setIdPoly(int idPoly) {
        this.idPoly = idPoly;
    }


    @Column(unique = true)
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public String getNomDemandeur() {
        return nomDemandeur;
    }

    public void setNomDemandeur(String nomDemandeur) {
        this.nomDemandeur = nomDemandeur;
    }

    @OneToOne(mappedBy = "lePoly", fetch = FetchType.LAZY)
    public Commande getLaCommande() {
        return laCommande;
    }

    public void setLaCommande(Commande laCommande) {
        this.laCommande = laCommande;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    public Demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }
}

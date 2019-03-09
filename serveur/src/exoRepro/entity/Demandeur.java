package exoRepro.entity;


import javax.persistence.*;

@Entity
@Table(name = "test.DEMANDEUR")
public class Demandeur {

    private int idDemandeur;
    private String nom;
    private String mail;

    public Demandeur(String nom, String mail) {
        this.nom = nom;
        this.mail = mail;
    }

    public Demandeur() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdDemandeur() {
        return idDemandeur;
    }

    public void setIdDemandeur(int idDemandeur) {
        this.idDemandeur = idDemandeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

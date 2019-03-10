package exoRepro.entity;


import javax.persistence.*;

@Entity
@Table(name = "test.DEMANDEUR")
public class Demandeur {

    private int id;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

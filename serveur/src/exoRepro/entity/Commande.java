package exoRepro.entity;

import javax.persistence.*;

@Entity
@Table(name="test.COMMANDE")
public class Commande {


    private int numero;
    private String etat;
    private Poly lePoly;

    public Commande(String etat) {
        this.etat = etat;
    }

    public Commande() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPoly")
    public Poly getLePoly() {
        return lePoly;
    }

    public void setLePoly(Poly lePoly) {
        this.lePoly = lePoly;
    }
}

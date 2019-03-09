package exoRepro.entity;

import javax.persistence.*;

@Entity
@Table(name = "test.UTILISATEUR_REPRO")
public class Utilisateur {

    private int idUtilisateur;
    private String login;
    private String password;


    public Utilisateur(int idUtilisateur, String login, String password) {
        this.idUtilisateur = idUtilisateur;
        this.login = login;
        this.password = password;
    }


    public Utilisateur() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

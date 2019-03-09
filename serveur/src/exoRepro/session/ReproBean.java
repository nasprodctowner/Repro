package exoRepro.session;

import exoRepro.entity.Commande;
import exoRepro.entity.Demandeur;
import exoRepro.entity.Poly;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Stateful(mappedName = "repro")
public class ReproBean implements ReproItf{


    @PersistenceContext(unitName="reproPU")
    private EntityManager em;

    private boolean isConnected = false;

    @Override
    public String authentification(String login, String password) {

        String connected = "DISCONNECT!";
        try {
            List utilisateur =  em.createQuery ("SELECT u FROM Utilisateur u WHERE u.login = :login AND u.password = :password ")
                    .setParameter("login",login)
                    .setParameter("password",password)
                    .getResultList();

            isConnected = !utilisateur.isEmpty();
            if(isConnected) connected = "CONNECTION OK !";

        }catch (Exception e){
            System.out.println("Erreur");
        }

        return connected;

    }

    @Override
    public int depot(String titrePoly, int nbPages, String nomDemandeur) {

        int i = 0;
        if(isConnected){

            try {
                Commande commande = new Commande("Commande");
                Poly poly = new Poly(titrePoly,nbPages,nomDemandeur);
                Demandeur demandeur = new Demandeur();
                demandeur.setNom(nomDemandeur);

                em.persist(demandeur);

                poly.setDemandeur(demandeur);
                em.persist(poly);


                commande.setLePoly(poly);
                em.persist(commande);


                i = (int) em.createQuery ("SELECT p.laCommande.numero FROM Poly p WHERE p.titre = :titre").setParameter("titre",titrePoly).getSingleResult();

            }catch (Exception e){
                System.out.println("Erreur");
            }
        }else {
            return 0;
        }
        return i;
    }

    @Override
    public Collection<Object[]> consulterEtat(String titrePoly) {

        Collection<Object[]> liste = null;
        if (isConnected){

                try {

                    if(!titrePoly.equals("")){
                        liste =  em.createQuery ("SELECT p.titre, p.laCommande.etat FROM Poly p WHERE p.titre = :titre")
                                .setParameter("titre",titrePoly)
                                .getResultList();

                    }else {
                        liste =  em.createQuery ("SELECT p.titre, p.laCommande.etat FROM Poly p")
                                .getResultList();
                    }

                }catch (Exception e){
                    System.out.println("Erreur");
                }

        }else {
            return null;
        }
        return liste;
    }

    @Override
    public int updateEtat(String titrePoly, String newEtat) {

        int numCommande = 0;

        if (isConnected){

            if (newEtat.equals("livre")){

                  Poly poly = (Poly) em.createQuery ("SELECT p FROM Poly p WHERE p.titre = :titre")
                            .setParameter("titre",titrePoly)
                            .getSingleResult();

                  Commande commande = poly.getLaCommande();
                  commande.setEtat(newEtat);
                  commande.setLePoly(null);

                  poly.setLaCommande(null);

                  numCommande = em.createQuery ("DELETE FROM Poly p WHERE p.titre = :titre").setParameter("titre",titrePoly).executeUpdate();

             }

            else {

                    numCommande = (int) em.createQuery ("SELECT p.laCommande.numero FROM Poly p WHERE p.titre = :titre")
                            .setParameter("titre",titrePoly)
                            .getSingleResult();


                    Commande commande = em.find(Commande.class, numCommande);
                    commande.setEtat(newEtat);
                    em.persist(commande);
                }
        }else {
            return 0;
        }
        return numCommande;
    }
}

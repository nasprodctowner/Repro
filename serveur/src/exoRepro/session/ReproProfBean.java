package exoRepro.session;

import exoRepro.entity.Commande;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;


@Stateless(mappedName = "reproProf")
public class ReproProfBean implements ReproProfItf {

    @PersistenceContext(unitName="reproPU")
    private EntityManager em;

    @Override
    public String consulterEtatByIdCommande(int idCommande) {

        String etat = "N'existe pas" ;
            try {

                     etat = em.find(Commande.class, idCommande).getEtat();

                } catch(Exception e){
                System.out.println("Erreur");
            }
        return etat;
    }
}

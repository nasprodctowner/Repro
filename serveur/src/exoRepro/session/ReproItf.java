package exoRepro.session;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface ReproItf {
    public String authentification(String login, String passwd);
    public int depot(String titrePoly, int nbPages, String nomDemandeur);
    public Collection<Object[]>  consulterEtat(String titrePoly);
    public int updateEtat(String titrePoly, String newEtat);
}

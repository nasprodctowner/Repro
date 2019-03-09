package exoRepro.session;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface ReproProfItf {

    public String consulterEtatByIdCommande(int idCommande);
}

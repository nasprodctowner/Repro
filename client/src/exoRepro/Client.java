package exoRepro;

import exoRepro.session.ReproItf;
import exoRepro.session.ReproProfItf;

import javax.naming.InitialContext;
import java.util.Collection;
import java.util.Scanner;

public class Client {

    private static exoRepro.session.ReproItf repro;
    private static exoRepro.session.ReproProfItf reproProf;

    public static void main(String[] args) throws Exception {

        try {
            InitialContext ctx = new InitialContext();
            repro = (ReproItf) ctx.lookup("repro");
            reproProf = (ReproProfItf) ctx.lookup("reproProf");

        } catch (Exception ex) {
            System.err.println("erreur dans le client prbl nommage");
            ex.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);


        char reponse = 'O';

        while(reponse == 'O'){
            System.out.println("Nom");
            String login = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Mot de passe ?");
            String passwd = scanner.nextLine();
            scanner.nextLine();

            if (repro.authentification(login, passwd).equals("CONNECTION OK !")) {


                System.out.println("Bienvenu ! ");


                System.out.println("Titre Poly à créer ? ");
                String titrePoly = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Nom demandeur ?");
                String nomDemandeur = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Nombre de page ?");
                int nbPages = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Votre numéro de commande est : "+repro.depot(titrePoly,nbPages,nomDemandeur));


                System.out.println("Nom du poly à mettre à jour ?");
                String nomPoly = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Nouvel état ?");
                String newEtat = scanner.nextLine();
                scanner.nextLine();

                if(repro.updateEtat(nomPoly,newEtat) == 1) System.out.println("Le poly a été supprimé");
                else System.out.println("L'état de la commande "+repro.updateEtat(nomPoly,newEtat)+" a été mis à jour");


                System.out.println("Quel poly voulez vous consulter ?");
                String nomPolyAConsulter = scanner.nextLine();
                scanner.nextLine();

                Collection<Object[]> laListe = repro.consulterEtat(nomPolyAConsulter);
                for (Object[] obj : laListe) {
                    String titre = (String) obj[0];
                    String etat = (String) obj[1];
                    System.out.println(titre + "     " + etat);
                }


                break;
            }else {
                System.out.println("Nop ! Voulez-vous réessayer ? (O/N)");
                reponse = scanner.nextLine().charAt(0);
            }

        }


        /*
        repro.authentification("nas","khatir");
        System.out.println(repro.updateEtat("bb","livre"));
        */




    }
}
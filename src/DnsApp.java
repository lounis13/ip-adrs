import java.io.  *;
import java.util.*;

public class DnsApp {

    static String nomFichier(){
        /* Le fichier propertie ou se trouve le nom de fichier text */
        Properties propfic  = null ;
        /* Le nom de fichier text */
        String nomFichier   = null ;
        /* Straeam */
        FileInputStream fis = null ;
        /* Fichier */
        File fich     = null ;
        try{
            /* Chemin de ficher properties*/
            fich = new File("fic.properties");
            fis  = new FileInputStream(fich);
            /* Cree properties */
            propfic =new Properties();
            /* Charge maintenant les propriétés */
            propfic.load(fis);
            /* recuperer le nom de fichier */
            nomFichier = propfic.getProperty("nomFichier");
            //fermeture de fichier
            fis.close();
        }catch (Exception e)
        { e.printStackTrace(); }

        return  nomFichier ;
    }

    public static void main(String[] args) throws IOException {
        /* Recuperer le nom de fichier de fic.properties*/
        String fichierTxt = nomFichier();
        /* Charger La Base de donnée */
        Dns dns = new Dns(fichierTxt);
        /* Tant que le user ,'a pas saisie 'quit' */
        while (true) {
            /* Scanner pour Lire l'info saisie par l'utilisateur */
            Scanner sc = new Scanner(System.in);
            /* Affichage*/
            System.out.println("\n -----------------------------------------------------");
            System.out.println("| Saisie (Ip) ou (Nom) ou (ls [-a] domaine ) ou (quit) : ");
            System.out.println(" -----------------------------------------------------");
            /* Lire l'info saisie par l'utilisateur */
            String str = sc.nextLine(); 
            /* entrer sans rien saisie */
            if(str.length() == 0 ) continue;
            /* DnsTui*/
            DnsTUI dnsTUI = new DnsTUI(str);
            /* Action a faire */
            DoAction action = dnsTUI.nextCommande();
            /* Set le dns */
            action.setDns(dns);
            /* Récupérer la  commande */
            dnsTUI.commande = action;
            /* Afficher les resultats */
            dnsTUI.affiche();
        }

    }


}

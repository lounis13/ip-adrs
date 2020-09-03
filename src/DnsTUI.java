
/** The Invoker class */
public class DnsTUI {

    /* intefece commade */
    Commande commande ;
    /* la saisie de l'utilisateur */
    String userSaisie ;
    
    /* constructeur recuperer la saisie */
    public DnsTUI(String userSaisie){
        this.userSaisie = userSaisie;
    }

    /**    - analyser le texte saisi par l’utilisateur -
     * @return  un objet implémentant l’interface Commande (Action)
     */
    DoAction nextCommande()
    {
        char c = this.userSaisie.charAt(0);
        //
        if(this.userSaisie.equals("quit"))
            return new DoAction("Quitter application.",userSaisie) ;
        /* l'utilisateur a saisé un Ip donc il recherche le Nom*/
        if ( c >= '0' && c<= '9')
            return new DoAction("rechercher Nom",userSaisie) ;
        // si l'utilisateur  a saisie un Nom ou ls dommaine
        return  (this.userSaisie.length()>3 && this.userSaisie.substring(0, 3).equals("ls ")) ? (new DoAction("rechercher Machines",userSaisie)) // rchercher des machines
                                                                                              : (new DoAction("rechercher Ip",userSaisie));      // rchercher un ip

    }
    /**
     * - affiche le resultat -
     */
    void affiche() {
        commande.execute();
    }
}

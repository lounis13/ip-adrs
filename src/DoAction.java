
/** Concrete Command */
public class DoAction implements Commande {
    /* action a faire */
    public  String faire ;
    /* la saisie de l'utulisateur */
    public String userSisie ;
    /* Dns pour faire les action */ 
    private Dns dns      ;

    /* recuperer l'action a faire et la saisie de utilisateur */
    public DoAction(String faire, String userSisie)
    {
        this.userSisie = userSisie ;
        this.faire     = faire     ;
    }

    /* */
    public void setDns(Dns dns)
    {
        this.dns = dns ;
    }

    /**
     *     - execute (interface commande )
     */
    public void execute() {
        /* rechercher une IP */
        if(this.faire.equals("rechercher Ip"))
            dns.rechercherIP(userSisie);

        /* Rechercher un Nom */
        else if(this.faire.equals("rechercher Nom"))
            dns.rechercherNom(userSisie);

        /* Rechercher des Machines*/
        else if(this.faire.equals("rechercher Machines"))
            dns.rechercherMachines(userSisie);

        /* Quitter l'Application */
        else
            dns.quitterApplication();
    }



}

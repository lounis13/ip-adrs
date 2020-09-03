import java.io.*;
import java.util.*;


public class Dns {

    // deux hashMap pour recuperer les Info en temp constant //
   
    /*Hashmap AdreeseIp NomMachine*/
    HashMap <AdresseIP , NomMachine> adresseIPNomMachineHashMap ;
    /*Hashmap NomMAchine AdresseIp*/
    HashMap <NomMachine, AdresseIP > nomMachineAdresseIPHashMap ;
    
    /**
     * @param cheminFichier le chemin de fichier text (BDD.txt)
     * @throws IOException
     */
    public Dns(String cheminFichier) throws IOException {
        // Buffer pour charger la base de Donnée
        BufferedReader lecteurAvecBuffer = null;
        try {
            /*cree un buffer*/
            lecteurAvecBuffer = new BufferedReader(new FileReader(cheminFichier));

            this.adresseIPNomMachineHashMap = new HashMap<AdresseIP,NomMachine>();
            this.nomMachineAdresseIPHashMap = new HashMap<NomMachine,AdresseIP>();
            /* String pour recuperer les lignes */
            String ligne = null ;
                                                        /*fin de ficher*/
            while((ligne = lecteurAvecBuffer.readLine())!= null)
            {
                /* ligne ==> strs[0] = NomMachine , strs[1]= AdresseIp */
                String []strs   = ligne.split(" ");
                /*Charger la base de Donnée */
                this.nomMachineAdresseIPHashMap.put( new NomMachine(strs[0])  ,
                                                     new AdresseIP (strs [1]));
                this.adresseIPNomMachineHashMap.put( new AdresseIP (strs [1]) ,
                                                     new NomMachine(strs[0])) ;
            }
            /* Fermé le Fichier  */
            lecteurAvecBuffer.close();

        /* Si le fichier n'exite pas */
        }catch (FileNotFoundException exc) {
            System.out.println("Erreur le fichier '"+cheminFichier+"' n'existe pas\n\n");
            /*Quitter le programme*/
            System.exit(0);
        }
    }

    /**    - recuperer DnsItem selon adresseIp - 
     * @param   nomMachine
     * @return  null si le nom de la machine n'exite pas dans la bdd
     *          si non retourner DnsItem avec NomMachine donnée
     */
    DnsItem getItem(NomMachine nomMachine) {
        
        return (this.nomMachineAdresseIPHashMap.containsKey(nomMachine))
                ? new DnsItem(nomMachine,
                             this.nomMachineAdresseIPHashMap.get(nomMachine))
                : null;
    }

    /**
     *      - recuperer DnsItem selon adresseIp -
     * @param   adresseIP
     * @return  null si l' Adresse Ip  n'exite pas dans la bdd
     *      *   si non retourner DnsItem avec NomMachine donnée
     */
    DnsItem getItem(AdresseIP adresseIP) {

        return (this.adresseIPNomMachineHashMap.containsKey(adresseIP))
                ? new DnsItem(this.adresseIPNomMachineHashMap.get(adresseIP),
                                adresseIP)
                : null ;
    }
    /**
     *      - retourner une liste d’items à partir d’un nom de domaine -
     * @param  String nom domaine 
     * @return Liste d’items
     */
    ArrayList <DnsItem> getItems(String domaine){
        /* Liste d’items */
        ArrayList<DnsItem> listDnsItem = new ArrayList<DnsItem>();
        /* Parcourer la hashmap*/
        for (NomMachine key : this.nomMachineAdresseIPHashMap.keySet())
            /* Si les domaines sont equiv */
            if(key.dommaineCompare(domaine))
                /* ajouter dans la liste */
                listDnsItem.add(new DnsItem(key,
                                            this.nomMachineAdresseIPHashMap.get(key)));
        return listDnsItem;
    }

    /**
     *          - Rechercher un Ip -
     * @param nomMachine
     */
    void rechercherIP(String nomMachine)
    {
        System.out.println("\t\t¤ Rechrecher IP ¤ \n");
        /* DnsItems */
        DnsItem dnsItem = this.getItem(new NomMachine(nomMachine));
        
        if (dnsItem != null)
            System.out.println("> l' IP de la machine '"+nomMachine+"' est  :"+dnsItem.adresseIP);
        else
            System.out.println("> Le Nom Machine'"+nomMachine+"' n'existe pas !");
    }

    /**
     *        - Rechercher un Nom -
     * @param adresseIP
     */
    void rechercherNom(String adresseIP)
    {
        System.out.println("\t\t¤ Rechrecher Nom Machine ¤\n");
        AdresseIP ip = new AdresseIP(adresseIP);
        // Test si l'adresse ip saisie est Bonne
        if(ip.stringIp != null) {
            DnsItem dnsItem = this.getItem(ip);
            if ((dnsItem == null))
                System.out.println("> IP saisie n'existe pas dans la BDD");
            else
                System.out.println("> le Nom machine de l'adresse IP '" + adresseIP + "' est : " + dnsItem.nomMachine);

        }
    }
    /**
     *          - Rechercher des machines -
     * @param lsDomaine
     */
    void rechercherMachines(String lsDomaine) {
        ArrayList<DnsItem> listMachine = null ;
        try {
            System.out.println("\t\t¤ rechrecher Machines ¤\n");

            if(lsDomaine.length()>5 &&lsDomaine.substring(3, 5).equals("-a")) {
            /* On commence de 6 par ce que "ls -a" (pos 06)*/
                listMachine = (this.getItems(lsDomaine.substring(6)));
                /* Tri la liste selon les adresse Ip */
                Collections.sort(listMachine, new AdresseIP());
                
                /* Afficher la liste des machines : Adresse Ip (trie) nom Machine*/
                DnsItem.AfficheAdresseIpNomMachine(listMachine);
                }
            /*Tri liste selon les noms Des Machines*/
            else {
                /*on commence de la pos 3 "ls_".len = 3*/
                listMachine = this.getItems(lsDomaine.substring(3));

                /*(new NomMachine) est un comparator pour preciser
                   que le tri fait selon les Noms des machines    */
                Collections.sort(listMachine, new NomMachine());

                /* Afficher la liste des machines : Nom fichier (trie) Adresse Ip*/
                DnsItem.AfficheNomMAchineAdresseIp(listMachine);
            }
            /*si liste est vide */
            if(listMachine.size() == 0 )
                System.out.println("> Domaine saisie n'existe pas ! ");
            /* si ya une erreur d'usage*/
            }catch (Exception e) {
                System.err.println("> Erreur Saisie [usage ls [-a] domaine ]");
            }finally {
                /* on liberer la liste dans tous les cas */
                listMachine.clear();
            }
    }

    /**
     *       - quitter l'application -
     */
    void quitterApplication()
    {
        /* vider Les HashMap*/
        this.adresseIPNomMachineHashMap.clear();
        this.nomMachineAdresseIPHashMap.clear();
        /* affichage */
        System.out.println("Fin De l'Application");
        /* fin de l'appli */
        System.exit(0);
    }



}

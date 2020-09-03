import java.util.*;

public class DnsItem implements Comparable <DnsItem>{

    /* Nom Machine */
    NomMachine nomMachine ;
    /* Adresse Ip */
    AdresseIP  adresseIP  ;

    /**
     *      - Constructeur -  
     * @param Nom machine 
     * @param Adresse Ip 
     */
    public DnsItem(NomMachine nomMachine,AdresseIP adresseIP)
    {
        this.nomMachine = nomMachine ;
        this.adresseIP  = adresseIP  ;
    }
    /* */
    public  String toString ()
    {
        return "\n "+this.nomMachine.stringNom+" \t"+this.adresseIP.stringIp;
    }

    /* compare to de l'interface comarator ( pour trie 'collections.sort (List)')*/
    public int compareTo(DnsItem o) {
        /* on compare les les nom de machine */
        return this.nomMachine.stringNom.compareTo(o.nomMachine.stringNom);
    }

    /* afficherge nom machine : adresse Ip */
    static void AfficheNomMAchineAdresseIp(ArrayList <DnsItem> dnsItemArrayList) {
        for (DnsItem dnsItem : dnsItemArrayList)
            System.out.println(dnsItem.nomMachine+"\t"+dnsItem.adresseIP);
    }
    /*affichage adresse ip : nom Machine  */
    static void AfficheAdresseIpNomMachine(ArrayList <DnsItem> dnsItemArrayList) {
        for (DnsItem dnsItem : dnsItemArrayList)
            System.out.println(dnsItem.adresseIP+"\t"+dnsItem.nomMachine);
    }

}

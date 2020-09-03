import java.util.*;

/**
 * la class AdresseIp implements l'interface Comparator
 * pour pour faire une  comparaison entre deux Objet de
 * DnsItem (pour trie la liste des DnsItem selon les Ip)
 */
public class AdresseIP implements Comparator<DnsItem> {

    /* Const Max */
    static final int IP_INT_MAX = 255 ;
    /* Const Min */
    static final int IP_INT_MIN = 0   ;
    /*les 4 nombre de l'adresse Ip */
    private int nbr1 , nbr2, nbr3, nbr4;
    /*adresse Ip : String */
    String stringIp = null ;

    /* Contructeur vide */
    AdresseIP(){}

    /**
     * Constructeur qui test si une IP donnée en String
     * est valide  si c'est le cas on va remplire les
     * attributs . si non afficher un lessage d'err
     * @param s String
     */
    public AdresseIP(String s)
    {
        /* Test si ip est valide*/
        if(bonneAdresseIp(s))
            this.stringIp = s ;

    }

    /**
     *        - Tester si Ip est Valide ou pas -
     * @param s String ip deonnée
     * @return boolean true Ip valide , false si non
     */
    boolean bonneAdresseIp(String s)
    {
        ArrayList<String> list = NomMachine.stringToList(s) ;
        /*Si Ip n'a pas 4 nombre */
        if(list.size() != 4 ){
            System.err.println("Erreur saise [ Taille IP != 4 nombres ]") ;
            return  false ;
        }
        /*Si il existe un caractere ![0-9] dans Ip */
        try {
            this.nbr1 = Integer.parseInt(list.get(0));
            this.nbr2 = Integer.parseInt(list.get(1));
            this.nbr3 = Integer.parseInt(list.get(2));
            this.nbr4 = Integer.parseInt(list.get(3));
        }catch (Exception e)
        {
            System.err.println("erreur saise (Type (IP != INT) !");
            return false ;
        }
        /*Test l'itervale*/
        boolean b=(nbr1 >= IP_INT_MIN && nbr1 <= IP_INT_MAX)&&
                  (nbr2 >= IP_INT_MIN && nbr2 <= IP_INT_MAX)&&
                  (nbr3 >= IP_INT_MIN && nbr3 <= IP_INT_MAX)&&
                  (nbr4 >= IP_INT_MIN && nbr4 <= IP_INT_MAX);
        if(b== false)
            System.err.println("Erreur saisie[ Min : 0 , Max : 255 ]");

        /* Return True */
        return b;
    }
    /*to String*/
    public String toString()
    {
        return this.stringIp ;
    }

    /* */
    public boolean equals(Object o) {
        /* c' est les reference sont les memes */
        if (this == o) return true;
        /*si ne sont pas de meme instance */
        if (!(o instanceof AdresseIP)) return false;
        /* caster l'objet o */
        AdresseIP adresseIP = (AdresseIP) o;
        /* tester si  les deux string sont equiva*/
        return stringIp.equals(adresseIP.stringIp);
    }
    /* */
    public int hashCode() {
        return Objects.hash(stringIp);
    }

    /**
     *          - compare (implements Comparator<DnsItem> ) pour utiliser 
     *            la methode Collections.sort (ArrayList  ) et les trie selon 
     *            les AdresseIp  -
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(DnsItem o1, DnsItem o2) {
        return o1.adresseIP.stringIp.compareTo(o2.adresseIP.stringIp);
    }
}


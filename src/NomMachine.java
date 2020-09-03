import java.util.*;

/**
 * la class NomMAchine  implements l'interface Comparator
 * pour pour faire une  comparaison entre deux Objet de
 * DnsItem (pour trie la liste des DnsItem selon les nom des Machine )
 */
public class NomMachine implements Comparator<DnsItem> {
    /* liste des mots (Machine->Domaine->local) */
    private ArrayList<String> mots ;
    /* Nom machine en String */
    public String stringNom ;

    /*Constructeur vide*/
    public NomMachine(){}

    /**
     * - constructeur recuperer les liste de mot a partir d'un str -
     * @param stringNom
     */
    public NomMachine(String stringNom)
    {   /*String a ArrayListe*/
        this.mots = stringToList(stringNom);
        this.stringNom = stringNom ;
    }

    /**
     *         - String to array list equiv a split ("[.]")- 
     * @param s
     * @return
     */
    static  ArrayList <String> stringToList(String s)
    {   /* array list */
        ArrayList<String> mots = new ArrayList<String>();
        /* chaine vide  */
        String tmp = "" ;
        int cpt = 0 ;
        for (int i = 0 ; i < s.length() ; ++i)
        {
            char c = s.charAt(i) ;
            /* si en trouve un point on recupere la cahaine avant */
            if (c == '.')
            {   cpt++ ;
                /* ajoute la chaine dans la lsite des mots */
                mots.add(tmp);
                /* initialiser tmp */
                tmp = ""; continue;
            }
            /* si diff de '.' on concat c a tmp */
            tmp += c ;
        }
        /* on ajoute le dernier mot di il existe */
        mots.add(tmp);
        /* returner la liste */
        return  mots ;
    }



    /**
     *      - to string - 
     * @return
     */
    public String toString() {
        return this.stringNom;
    }

    /**
     *   - on test si un domaine appartiens a un nom mchines  - 
     * @param dommaine
     * @return boolean
     */
    public boolean dommaineCompare(String dommaine)
    {
        /* on recuperer la liste  de domaine */
        ArrayList <String > str = stringToList(dommaine);
        /* test si le domaine a plus d'un mot par exmple 'domaine.com' */
        return (str.size() == 1 ) ?  ( this.mots.get(1).equals(dommaine))
                                  :  ((this.mots.get(1).equals(str.get(0)))
                                  && ( this.mots.get(2).equals(str.get(1))));

    }

       /**
     *        - tester si deux objet sont equival - 
     * @param o objet a comparer 
     * @return boolean  
     */
    public boolean equals(Object o) {
        /* c' est les reference sont les memes */
        if (this == o) return true;
        /*si ne sont pas de meme instance */
        if (!(o instanceof NomMachine)) return false;
        /* caster l'objet o */
        NomMachine nom = (NomMachine) o;
        /* tester si  les deux string sont equiva*/
        return stringNom.equals(nom.stringNom);
    }

    /**
     *        - pour la comapraison dans une hash(Map ... get (key) )  - 
     * @return int 
     */
    public int hashCode() {
        return Objects.hash(stringNom);
    }

    /**
     *          - compare (implements Comparator<DnsItem> ) pour utiliser 
     *            la methode Collections.sort (ArrayList  ) et les trie selon 
     *            les Nom des machines -
     * @param o1
     * @param o2
     * @return
     */
    public int compare(DnsItem o1, DnsItem o2) {
        return  o1.nomMachine.stringNom.compareTo(
                o2.nomMachine.stringNom);
    }
}

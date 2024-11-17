/**
 *
 * @author Manal Alhihi
 */
public class Term {
    String word;
    boolean [] docIDS ;
    int [] ranked;

    public Term() {
        word = "";
        docIDS = new boolean [50];
        ranked = new int [50];
        for (int i = 0 ; i < docIDS.length ; i++)
        {
            docIDS [i] = false;
            ranked[i] = 0;
        }
    }

    public Term(String word, int [] rank)
    {
        this.word = word;
        docIDS = new boolean [50];
        ranked = new int [50];
        for (int i = 0 ; i < rank.length ; i++)
            if (rank[i] != 0)
            {
                docIDS [i] = true;
                ranked[i] = rank[i];
            }
    }
    
    public void add_docID ( int docID)
    {
        docIDS[docID] = true;
        ranked[docID] ++;
    }

    public void setWord(String word)
    {
        this. word = word; 
    }
    
    public String getWord()
    {
         return word;
    }
    
    public boolean [] getDocs ()
    {
        boolean [] test = new boolean [ranked.length];
        
        for ( int i = 0 ; i < test.length ; i++)
            test[i] = docIDS[i];
        
        return test;
    }

    public int [] getRanked()
    {
        int[] test = new int [ranked.length];
        for ( int i = 0 ; i < test.length ; i++)
            test[i] = ranked[i];
        return test;
    }
    
   @Override
    public String toString() {
        String docs = "";
        for (int i = 0, j = 0 ; i < docIDS.length; i++)
            if ( docIDS[i] != false)
            {
                if ( j == 0)
                {
                    docs += " " + String.valueOf(i) ;
                    j++;
                }
                else
                {
                    docs += ", " + String.valueOf(i) ;
                    j++;
                }
            }
        return word + "[" + docs + ']';
    }
    
}

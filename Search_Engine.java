
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Manal Alhihi
 */
public class Search_Engine {
    int tokens = 0;
    int vocab = 0;
    
    Index index;
    Inverted_Index invertedindex;
    Inverted_Index_BST invertedindexBST;
    Inverted_Index_AVL invertedindexAVL;
    
    Inverted_Index_BST test;
    
    /*=================================================================================
    constractor 
    */
    public Search_Engine ()
    {
        index = new Index();
        invertedindex = new Inverted_Index();
        invertedindexBST = new Inverted_Index_BST();
        invertedindexAVL = new Inverted_Index_AVL();
        
        test = new Inverted_Index_BST();
    }
    
    /*=================================================================================
    Document Processing: 
    o Read documents from a CSV file. 
    o Split the text into a list of words based on whitespace. 
    o Convert all text to lowercase 
    o Preprocess the text by removing stop-words (e.g., "the," "is," "and"). The list of 
    stop-words will be given to you. 
    o Remove all punctuations and non-alphanumerical characters 
    o Proceed to build the index     
     */
    public void LoadData (String stopFile, String fileName)
    {
            try{
                File stopfile = new File (stopFile);
                Scanner reader = new Scanner (stopfile).useDelimiter("\\Z");
                String stops = reader.next();
                
                stops = stops.replaceAll("\n", " ");
                
                File docsfile = new File(fileName);
                Scanner reader1 = new Scanner (docsfile);
                String line = reader1.nextLine();
                
                for ( int lineID = 0 ; lineID <50 ; lineID ++ ) 
                {
                    line = reader1.nextLine().toLowerCase();
                    
                    int pos = line.indexOf(',');
                    int docID = Integer.parseInt( line .substring(0, pos));

                    String data = line.substring(pos+1, line.length() - pos).trim();
                    data = data.substring(0, data.length() -1);

                    data = data.toLowerCase();
                    data =  data.replaceAll("[\']", " ");
                    data = data.replaceAll("[^a-zA-Z0-9]", " ").trim() ;

                    String [] words = data.split(" "); // --1

                    for (int i = 0; i < words.length ; i++)
                    {
                        String word = words[i].trim(); //--2
                
                        if ( word.compareToIgnoreCase("") != 0)
                            tokens ++;
                        
                        this.test.addNew(docID, word);
                                
                        if ( ! stops.contains(word + " ")) //--3
                         {
                            this.index.addDocument(docID, word);
                            this.invertedindex.addNew(docID, word);
                            this.invertedindexBST.addNew(docID, word);
                            this.invertedindexAVL.addNew(docID, word);
                        }
                    }

                   // this.index.printDocment(docID);
                    //System.out.println("");
                }
                //this.invertedindex.printDocment();
                //this.invertedindexBST.printDocument();
                //this.invertedindexAVL.printDocument();

                vocab = test.size();
      
                System.out.println("tokens " + tokens);
                System.out.println("vocabs " + vocab);
                
                reader.close();
                reader1.close();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    public LinkedList<Integer> Boolean_Retrieval(String str , int DSType)
    {
        LinkedList<Integer> docs = new LinkedList<Integer> ();
        switch (DSType)
        {
            case 1 :
                docs = index.AND_OR_Function(str);
                break;
            case 2 :
                docs = invertedindex.AND_OR_Function(str);
                break;
            case 3:
                docs = invertedindexBST.AND_OR_Function(str);
                break;
            case 4:
                docs = invertedindexAVL.AND_OR_Function(str);
                break;
            default :
                System.out.println("Bad data structure");
                
        }
        return docs;
    }
        
    public void Ranked_Retrieval(String str , int DSType)
    {
        switch (DSType)
        {
            case 1 :
                index.TF(str);
                break;
            case 2 :
                invertedindex.TF(str);
                break;
            case 3:
                invertedindexBST.TF(str);
                break;
            case 4:
                invertedindexAVL.TF(str);
                break;
            default :
                System.out.println("Bad data structure");
        }
    }
    

}

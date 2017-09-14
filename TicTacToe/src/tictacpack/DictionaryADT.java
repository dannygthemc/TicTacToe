package tictacpack;
//absract class for the dictionary data structure
public interface DictionaryADT 
{
    public int insert (DictEntry pair) throws DictionaryException; //adds a value to the dictionary

    public void remove (String config) throws DictionaryException; //removes a value from the dictionary

    public int find (String config); //finds a value 

    public int numElements(); //returns number of elements in dictionary
}

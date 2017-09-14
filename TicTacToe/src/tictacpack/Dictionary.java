package tictacpack;
/*this class implements a Dictionary data structure design to hold objects of class DictEntry
 * the structure consists of an array of LinkedLists
 * it utilizes a hashing function to assign each new element a Spot in the array
 * when collisions occur, the list that corresponds to the hash extends and the new value is added onto the end of it
 *  
 */

public class Dictionary implements DictionaryADT {
	
	private LinkedList[] T;  // T is an array of DictEntries 
	private int M; //holds size of array
	
	public Dictionary(int size){
		T = new LinkedList[size]; //instantiates the array of selected size
		M = size; //holds value of size
		
		//initiates each LinkedList in the array
		for(int i =0; i <= (M-1); i++){
			T[i] = new LinkedList();
		}
		
		
	}
	//inserts a new DictEntry into the table
	//if this entry already exists, throws an exception
	//if a collision is produced, returns 1
	//otherwise returns 0
	public int insert(DictEntry pair) throws DictionaryException{
		int hashval = hash(pair.getConfig()); //gets the hash value of the config of the current DictEntry
		int track = 0; //keeps track of whether or not a collision is produced. 1 if so, 0 otherwise
		//if the entry is already in the Table, throws an exception
		if(find(pair.getConfig()) != -1){
			throw new DictionaryException("DictEntry already in Table, cannot be re-added");
		}
		else{
			//if a collision is produced changes val of track to 1
			if(T[hashval].length() != 0){
				track =1;
			}
			T[hashval].add(pair); //adds the current DictEntry to the Table
		}
		return track;
	}
	/*
	 * removes an item from the Dictionary(non-Javadoc)
	 * @see DictionaryADT#remove(java.lang.String)
	 * throws an exception if the selected config is not in the dictionary
	 * otherwise removes the data from the related List
	 */
	public void remove(String config) throws DictionaryException {
		int hashval = hash(config); //gets hash value of the desired config
		
		//if the entry is not in the Table, throws an exception
		if(find(config) == -1){
			throw new DictionaryException("DictEntry not in table, cannot be removed");
		}
		//otherwise, removes the config from its corresponding list
		else{
			T[hashval].remove(config);
		}
	}
	//searches the hashtable to see if the suggested config is already in the table
	//returns the corresponding score if found, returns -1 otherwise
	public int find(String config){
		int found = -1;
		int hashval = hash(config); //gets the hash value of the current config
		if (T[hashval].length() == 0){
			//do nothing
		}
		else{
			found = T[hashval].search(config);
		}
		return found;
	}
	//returns the number of elements in the Table
	public int numElements(){
		int total = 0;
		
		//for every LinkedList in the table, checks the length, i.e the number of nodes,
		//which will correspond to the number of unique entries in that list
		//adds this number to the total
		for(int l =0; l < (M-1); l++){
			total = total + T[l].length();
		}
		return total;
	}
	//hashfunction to create a (hopefully) unique code for a given string
	private int hash(String config){
		char[] array = config.toCharArray(); //converts string into a char array
		int hash = array[0];
		int arrayLength = array.length; //holds length of char array
		int temp = 0;
		//applies the hashing function to each character of the char array
		for(int i=1; i < arrayLength; i++){
			
			temp = Character.getNumericValue(array[i]);
			if(temp < 0){
				temp = temp * -1;
			}
			hash = ( (hash + ( ( (temp * (29^(i) ) ) * (5^(i) ) ) ) ) %M );
		}
		
		return hash;
	}

}

package tictacpack;
//this class represents data to be added into a dictionary, it has a string and integer assigned to it
public class DictEntry {

	private String configuration; //holds the string pattern
	private int value; //holds the score associate with the pattern
	
	//instantiates an object of the class
	public DictEntry(String config, int score){
		
		configuration =config;
		value = score;
		
	}
	//returns the string held
	public String getConfig(){
		return configuration;
	}
	//returns the integer
	public int getScore(){
		return value;
	}
}

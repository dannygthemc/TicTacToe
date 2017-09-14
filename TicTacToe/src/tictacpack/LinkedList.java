package tictacpack;

//used to instantiate a linked list which holds DictEntries
//to allow for separate chaining in Dictionary class
public class LinkedList {

	
	private LinearNode<DictEntry> head;	//represents current end of the list
	private LinearNode<DictEntry> node; //represents currently accessed node in the list
	private int length;
	
	//creates a new linked list
	public LinkedList(){
		head = null;
		length = 0;
	}
	
	//adds a new entry to the linked list
	public void add(DictEntry one){
		
		node = new LinearNode<DictEntry>(one); //creates a new temporary node with the dictentry in it
		node.setNext(head); //point this node to the head
		head = node; //sets this node as the new head
		length++; //adds to length
		
	}
	
	//returns number of nodes currently in the linkedlist
	public int length(){
		
		return length;
	}

	//compares each configuration of the DictEntry in each node to the value being searched for
	//returns score of corresponding config if found
	//otherwise returns -1
	public int search(String config){
		
		int score = -1; //holds value of score, -1 if not found
		LinearNode<DictEntry> current = head; //sets current as head
		LinearNode<DictEntry> tempNode; //temp holder for each LinearNode
		
		
		//compares the desired config to each node in the list 
		for (int i = 0; i < length; i++){
			tempNode = current;
			if(tempNode.getElement().getConfig().equals(config)){
				score = tempNode.getElement().getScore();
			}
			current = current.getNext();
		}
		
		return score;
	}
	
	//searches for a specific config within the list
	//if the config is found, adjusts the list accordingly
	public void remove(String config){
		LinearNode<DictEntry> current = head; //sets current as head
		LinearNode<DictEntry> tempNode; //temp holder for each LinearNode
		LinearNode<DictEntry> tempTwo = current;//temp holder
		
		//compares the desired config to each node in the list 
		for (int i = 0; i < length; i++){
			tempNode = current.getNext();
			
			if(current.getElement().getConfig() == config){
				
				//if the first element is the one being removed, reset the next element to be the head
				if(current == head){
					head = tempNode;
				}
				//otherwise, remove the current element from the list by resetting the previous node to have 
				//the node after the current one as it's next node.
				else{
					tempTwo.setNext(tempNode);
				}
				
			}
			tempTwo = current;
			current = current.getNext();
		}
		length--; //subtracts from legnth
	}
}

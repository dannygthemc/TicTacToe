package tictacpack;
/*
 * this class represent a TicTacToe board and provides methods for the game to be played
 */


public class TicTacToe {

	char[][] gameBoard; //holds value of slots on gameboard
	int size; //holds size of board
	int line; //holds val of max line to be reach
	int lvls; //holds val of max number of branches 
	
	/*
	 * constructs an instance of the class
	 */
	public TicTacToe(int board_size, int inline, int max_levels){
		
		size = board_size;
		line = inline;
		lvls = max_levels;
		gameBoard = new char[size][size];
		
		//initializes each slot in the board to be blank
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				gameBoard[i][j] = ' ';
			}
		}
	}
	/*
	 * creates a dictionary of specified length
	 */
	public Dictionary createDictionary(){
		Dictionary dict = new Dictionary(4919);
		return dict;
	}
	//converts the current gameboard to a string
	public String toString(){
		String temp = "";
		char t;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				t = gameBoard[i][j];
				temp += t;
			}
		}
		
		return temp;
	}
	//check the dictionary to see if the configuration is already in it
	//returns the score if it is, returns -1 elsewise
	public int repeatedConfig(Dictionary configs){
		int score = -1;
		String game = toString();
		
		score = configs.find(game);
		return score;
	}
	//inserts a new configuration into the dictionary
	public void insertConfig(Dictionary configs, int score){
		String game = toString();
		DictEntry pair = new DictEntry(game, score);
		
		try{
		configs.insert(pair);
		}catch (DictionaryException e){
			System.out.println("that configuration is already in the dictionary");
		}
	}
	//stores a player's character in the board
	public void storePlay(int row, int col, char symbol){
		gameBoard[row][col] = symbol;
	}
	//checks if specified square is empty
	public boolean squareIsEmpty (int row, int col){
		char temp = gameBoard[row][col];
		boolean check;
		
		if(temp == ' '){//compares current character to blank space
			check = true;
		}
		else{
			check = false;
		}
		return check;
	}
	//checks if the current board represents a draw
	public boolean isDraw(){
		boolean check = true;
		//checks for empty squares, if none emty, may be a draw
		//if any empty, game is not over, can;t be a draw
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(squareIsEmpty(i,j) == true){
					check = false;
				}
			}
		}
		//checks for winners
		//if any winners, no draw
		if(wins('O') == true){
			check = false;
		}
		if(wins('X') == true){
			check = false;
		}
		return check;
	}
	//evaluates the current state of the board for the computer
	public int evalBoard(){
		int score = 1;
		
		if(wins('O') == true)//if computer wins, 3
			score =3;
		if(isDraw() == true)//if draw 2
			score = 2;
		if(wins('X') == true)//if human wins, 0
			score =0;
		//1 otherwise, meaning game not finished
		return score;
	}
	//checks if the player corresponding to the specified character has won the game
	public boolean wins (char symbol){
		boolean win = false; //holds val of whether or not game is won
		int check = 0; //check int 
		char current; //keeps track of current square
		int i2; //keeps track of relative squares
		int j2; // ""
		//iterates through entire gameBoard
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				current = gameBoard[i][j];
				
				/*
				 * if symbol is found in a square checks to right, left, above, below and diagonals for a line
				 */
				if(current == symbol){
					//code for checking above
					i2 = i;
					j2 = j;
					check = 0;
					for(i2 = i; i2 > (i - line) ; i2--){//moves up as many squares as could form a line
							
							if(i2 >= 0){ //if specified square is within bounds
								if(gameBoard[i2][j2] ==symbol){ //check if it holds symbol
									check++; //add to holder if it does
								}
								else{
									break;
								}
							}
							else{
								break;
							}
					}
					if(check == line){//if check was added to as many times as spaces needed to form a line
						win = true; //we have a winner
					}
					
					//code for checking below
					i2 = i;
					j2 = j;
					check = 0;
					for(i2 = i; i2 < (i + line) ; i2++){//moves down as many squares as needed to form a line
							
							if(i2 < size){//if specified square is in bounds
								if(gameBoard[i2][j2] ==symbol){//check if it holds the symbol
									check++; //add to the holder if it does
								}
								else{
									break;
								}
							}
							else{
								break;
							}
					}
					if(check == line){//if check was added to as many times as spaces needed to form a line
						win = true; //we have a winner
					}
					
					
					
					//code for checking to the left
					i2 = i;
					j2 = j;
					check = 0;
					for(j2 = j; j2 > (j2 - line) ; j2--){//moves left as many squares as could form a line
							
							if(j2 >= 0){ //if specified square is within bounds
								if(gameBoard[i2][j2] ==symbol){ //check if it holds symbol
									check++; //add to holder if it does
								}
								else{
									break;
								}
							}
							else{
								break;
							}
					}
					if(check == line){//if check was added to as many times as spaces needed to form a line
						win = true; //we have a winner
					}
					//code for checking to the right
					i2 = i;
					j2 = j;
					check = 0;
					for(j2 = j; j2 < (j2 + line) ; j2++){//moves right as many squares as could form a line
							
							if(j2 < size){ //if specified square is within bounds
								if(gameBoard[i2][j2] ==symbol){ //check if it holds symbol
									check++; //add to holder if it does
								}
								else{
									break;
								}
							}
							else{
								break;
							}
					}
					if(check == line){//if check was added to as many times as spaces needed to form a line
						win = true; //we have a winner
					}
					
					
					//code for checking upper right diagonal
					i2 = i;
					j2 = j;
					check = 0;
					for(j2 = j; j2 < (j2 + line) && i2 > (i2 - line) ; j2++, i2--){//moves up and right as many squares as could form a line
							
							if(j2 < size && i2 >= 0){ //if specified square is within bounds
								if(gameBoard[i2][j2] ==symbol){ //check if it holds symbol
									check++; //add to holder if it does
								}
								else{
									break;
								}
							}
							else{
								break;
							}
					}
					if(check == line){//if check was added to as many times as spaces needed to form a line
						win = true; //we have a winner
					}
					//code for checking upper left diagonal
					i2 = i;
					j2 = j;
					check = 0;
					for(j2 = j; j2 > (j2 - line)  && i2 > (i2-line) ; j2--, i2--){//moves up and left as many squares as could form a line
							
							if(j2 >= 0 && i2>=0){ //if specified square is within bounds
								if(gameBoard[i2][j2] ==symbol){ //check if it holds symbol
									check++; //add to holder if it does
								}
								else{
									break;
								}
							}
							else{
								break;
							}
					}
					if(check == line){//if check was added to as many times as spaces needed to form a line
						win = true; //we have a winner
					}
					//code for checking lower left diagonal
					i2 = i;
					j2 = j;
					check = 0;
					for(j2 = j; j2 > (j2 - line) && i2 < (i2 + line) ; j2--, i2++){//moves down and left as many squares as could form a line
							
							if(j2 >= 0 && i2 < size){ //if specified square is within bounds
								if(gameBoard[i2][j2] ==symbol){ //check if it holds symbol
									check++; //add to holder if it does
								}
								else{
									break;
								}	
							}
							else{
								break;
							}
					}
					if(check == line){//if check was added to as many times as spaces needed to form a line
						win = true; //we have a winner
					}
					//code for checking lower right diagonal
					i2 = i;
					j2 = j;
					check = 0;
					for(j2 = j; j2 < (j + line)  && i2 < (i +line) ; j2++, i2++){//moves down and right as many squares as could form a line
							
							if(j2 < size && i2 < size){ //if specified square is within bounds
								if(gameBoard[i2][j2] ==symbol){ //check if it holds symbol
									check++; //add to holder if it does
								}
								else{
									break;
								}
							}
							else{
								break;
							}
					}
					if(check == line){//if check was added to as many times as spaces needed to form a line
						win = true; //we have a winner
					}
					
					
			}
		}
	}
	return win;
	}
}


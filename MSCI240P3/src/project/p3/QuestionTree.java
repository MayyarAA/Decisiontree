package project.p3;

import java.util.Scanner;
import java.io.PrintStream;


public class QuestionTree {
	private static int  Wgame = -1;
	private static int games = -1;
	//private boolean answer=UserInterface.nextAnswer(input);
	private PrintStream output;
	private Scanner input;
	private QuestionNode root;
	//private boolean answer=UserInterface.nextAnswer(input);
	
// creating a counter method to count the amount of games played
public int games() {
		
		games++;
//the counter will increase by 1 everytime until the game is ended		
		return games ;
	}
//creating a counter method to count the amount of games won
	public int Wgame() {
// the counter will increase by 1 everytime the algrothim was correct
		Wgame++;
		return Wgame ;
	}


	
//creating constructer method that has 2 parematers of a Scanner and a PrintStream	
	
	public QuestionTree(Scanner input, PrintStream output) {
		// if the user does not want to use an exsiting fill then Jedi will be the base root
		this.root = new QuestionNode("Jedi"); // adding a base case of if no root then declare the root as Jedi
		this.output = output;
		this.input = input;
		
		
	}
	// the save method in order to save teh current tree state
	
	public void save(PrintStream output) {
		root = save(root, output);
	}
	
	// the load method in order to read another tree from a different file then the current one
	
	public void load(Scanner input) {
		root = loadh(input);
	}
	// the play method in order to actually play the game and do the guessing and asking of questions
	public void play() {
		
		playh(root);
		
	}
// the play methods private helper method, this method responds to the users answers
	//and traverses the binary tree accordingly ina pre-order traversial method
	private QuestionNode playh(QuestionNode keep) {
		String nNode ;
        QuestionNode nQN ;
        String nQuestion ;
        
// checking to see if a child
        if (keep.left == null && keep.right == null) { 
        	System.out.println("Would your object to happen to be " + keep.data + "?");
        	String result = input.nextLine();
        	// in the following condition statement the method will read the consule and depending on the
        	boolean answer = UserInterface.nextAnswer(input); 
        	
        	// users respond of yes or it will change the response until the game is won by the algorthim
			if (answer) {
        		output.print( keep.data);
        	
			}
			if (answer) {
				output.print("I win!");
				//creating new QuestionNode variable in order to be able to change the tree to get the 
                // correct the tree to be able to get the correct answer by
                // changing the current parents child into a new question and to get the correct leaf 
                // to be returned as the answer
				Wgame();
				
				games();
				
			} 
			// in the following condition statement the method will read the consule and depending on the
        	// users respond of yes or it will change the response until the game is won by the algorthim
			else if (!answer) {
				output.print("I lose. What is your object? "); 
				nNode=input.nextLine();
				boolean comeback2=UserInterface.nextAnswer(input);
				nQN=new QuestionNode(nNode);
				output.print("Type a yes/no question to distinguish your item from "+ root.data);
				nQuestion=input.nextLine();
				output.print("And what is the answer for your object ");
			
				String resu = input.nextLine();
				// in the following condition statement the method will read the consule and depending on the
	        	// users respond of yes or it will change the response until the game is won by the algorthim
				if (comeback2) {
                    QuestionNode info = new QuestionNode(keep.data);
                    //creating new QuestionNode variable in order to be able to change the tree to get the 
                    // correct the tree to be able to get the correct answer by
                    // changing the current parents child into a new question and to get the correct leaf 
                    // to be returned as the answer
                    QuestionNode userq = new QuestionNode(nQuestion, nQN, info);
                    keep = userq;
                 // in the following condition statement the method will read the consule and depending on the
                	// users respond of yes or it will change the response until the game is won by the algorthim
		        } else if (!comeback2) {
		        	
                    // correct the tree to be able to get the correct answer by
                    // changing the current parents child into a new question and to get the correct leaf 
                    // to be returned as the answer
			        QuestionNode hold = new QuestionNode(keep.data);
			        // using the different constructers intilizied above in the class for QuestionNode in order to
			        // be able to pass different types of paramteres 
                    QuestionNode different = new QuestionNode(nQuestion, hold, nQN);
                    
                    keep = different;
		        }
		        else {
                    return keep;
		        }
			}
        }
     // in the following condition statement the method will read the consule and depending on the
    	// users respond of yes or it will change the response until the game is won by the algorthim
        else { 
			output.print(  keep.data);
			
			String resul = input.nextLine();
			
			if (resul.charAt(0) == 'y' || resul.charAt(0) == 'Y') {
				//creating new QuestionNode variable in order to be able to change the tree to get the 
                // correct the tree to be able to get the correct answer by
                // changing the current parents child into a new question and to get the correct leaf 
                // to be returned as the answer
				keep.left = playh(keep.left);
			
			} else if (resul.charAt(0) == 'n' || resul.charAt(0) == 'N') {
				// using the different constructers intilizied above in the class for QuestionNode in order to
		        // be able to pass different types of paramteres 
				keep.right = playh(keep.right);
			} 
		}
		return keep;
	}
	// the save private method the reads the lines in teh file and depending on the Q or A will create
	// a new QuestionNode variable, with the according left and right children
	private QuestionNode save(QuestionNode num1, PrintStream output) {
		if(num1 != null) {
			if (num1.left != null || num1.right != null) {
				output.println("Q:" + num1.data);
			}
			else {
				output.println("A:" + num1.data );
			}
			num1.left = save(num1.left, output);
			num1.right = save(num1.right, output);
		}
		return num1;
	}
	// the private first load helper method in order to create the scanned object into a string and depending 
	// whether or not it is a question or not it will build a treeNode or a leaf if it is an answer 
	private QuestionNode loadh(Scanner come) {
		
		String word = come.nextLine();
	
		
		
		if (loadhh(word) == 'Q') {
			
			return new QuestionNode(word, loadh(come), loadh(come));
		}
		else {
		
			return new QuestionNode(word);
		}
	}
	// the second private load helper method will turn the string read from the file into a 
	// character array in order to to be able to compare and see if the line being read is 
	// a question or a an answer
	//thus then creating a leaf or parent node
	private  char loadhh( String word) {
          char look = word.charAt(0);
		
		word = word.substring(2, word.length() );
		
		return look;
	}
	
	
}

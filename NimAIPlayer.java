/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

public class NimAIPlayer extends NimPlayer implements Testable{
    
    public NimAIPlayer (String username, String givenName, String familyName)  {
        super(username, givenName, familyName);
    }
    
    //overrides the removeStone
    public int removeStone() {
         return 0;
    }
    
    //overrides and overloads the removeStone
    public int removeStone(int getUpper, int getLeft) {
        //calculates the number of stones to be removed to win
        int stoneRemoval = (getLeft - 1)/(getUpper+1);
        int removeStone = stoneRemoval * (getUpper+1) + 1;
        
        int stoneCheck = getLeft - removeStone;
        //checks if the number of stones being removed is valid
        System.out.println(stoneCheck);
        if (stoneCheck <= getUpper && stoneCheck <= getLeft && stoneCheck >= 1)  {
            return removeStone;
        } else  {
            return getLeft - 1;
        }
    }
    
    //not implemented
    public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
}

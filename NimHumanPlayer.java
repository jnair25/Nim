
public class NimHumanPlayer extends NimPlayer {
    public NimHumanPlayer (String username, String givenName, String familyName)  {
        super(username, givenName, familyName);
    }
    
    //returns the details of the instance as a string
    public String toString( )
    {
        return (getName( ) + "," + getFamilyName() + "," 
                    + getGivenName() + "," + getGames() + ","
                    + getWon() + "," + getHuman());
    }
    
    //overrides the removeStone
    public int removeStone() {
         return 0;
    }
    
    //overrides and overloads the removeStone
    public int removeStone(int stone, int getUpper, int getLeft) {
        try {
            if (stone > 0 && stone <= getUpper) {
                if (stone <= getUpper && stone > 0)  {
                    getLeft -= stone;
                }
            } else  {
                throw new IllegalArgumentException("Invalid move. You must remove between 1 and " + getUpper + " stones.");
            }
        } catch (IllegalArgumentException e)    {
            String message = e.getMessage();
            System.out.println(message);
        }
        return getLeft;
    }
   
}

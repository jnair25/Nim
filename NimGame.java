import java.util.Scanner;

public class NimGame {
    private int left;
    private int upper;

     //returns the stones left in the game
     public int getLeft()  {
         return left;
     }

     //changes the stones left value
     public void setLeft(int left)   {
         this.left = left;
     }

     //returns the upper bound of stone removal
     public int getUpper()  {
         return upper;
     }

     //sets the upper stone removal limit
     public void setUpper(int upper)   {
         this.upper = upper;
     }
     

    //initiates the game of Nim
    public void startGame(Scanner keyboard, String[] lineArray, NimPlayer play[], int playerCount)    {
        String[] details = lineArray[1].split(",");
        //initialises the key variables needed to play the game
        Boolean playerOneExists = false;
        Boolean playerTwoExists = false;
        boolean tryAgain;
        int oneIndex = 0;
        int twoIndex = 0;
        int validStones = 0;
        NimHumanPlayer human = new NimHumanPlayer(null,null,null);
        NimAIPlayer computer = new NimAIPlayer(null,null,null);

        for (int i=0; i<playerCount+1; i++)   {
            if (details[2].equals(play[i].getName()))    {
                playerOneExists = true;
                //reocrds the index in the array where the instance
                //of the player 1 exists
                oneIndex = i;
            } else if (details[3].equals(play[i].getName()))   {
                playerTwoExists = true;
                twoIndex = i;
            } else if (playerOneExists == true && playerTwoExists == true)  {
                break;
            }
        }
        if (playerOneExists == false | playerTwoExists == false)   {
            System.out.println("One of the players does not exist.");
        } else  {
            //sets the initial stones and upper limit of stone removal
            setLeft(Integer.parseInt(details[0]));
            setUpper(Integer.parseInt(details[1]));

            System.out.println("\nInitial stone count: " + getLeft());
            System.out.println("Maximum stone removal: " + getUpper());
            System.out.println("Player 1: " + play[oneIndex].getGivenName() + " " 
                    + play[oneIndex].getFamilyName());
            System.out.println("Player 2: " + play[twoIndex].getGivenName() + " " 
                    + play[twoIndex].getFamilyName());

            //adds game played to the player's record
            play[oneIndex].setGames(1);
            play[twoIndex].setGames(1);

            //the game starts with Player One's turn if it is an AI
            Boolean one = true;
            if (play[twoIndex].getHuman() == false) {
                one = false;
            }

            while (getLeft() > 0) {
                //check which Player's turn
                if (one == true)   {
                    tryAgain = true;
                    //checks whether the player needs to type in another input
                    while (tryAgain == true)    {
                        //stones remaining
                        System.out.print("\n" + getLeft() + " stones left:");
                        for (int i = 1; i <= getLeft(); i++) {
                            System.out.print(" *");
                        }
                        System.out.println("\n" + play[oneIndex].getGivenName() 
                            +  "'s turn - remove how many?" );
                        if (play[oneIndex].getHuman() == true)  {
                            int stone = keyboard.nextInt();
                            validStones = human.removeStone(stone, getUpper(), getLeft());
                            if (validStones != getLeft())   {
                                setLeft(validStones);
                                tryAgain = false;
                            } else  {
                                tryAgain = true;
                            }
                        } else  {
                            setLeft(computer.removeStone(getUpper(), getLeft()));
                            tryAgain = false;
                        }
                        one = false;
                    }
                } else  {
                    tryAgain = true;
                    while (tryAgain == true)    {
                        System.out.print("\n" + getLeft() + " stones left:");
                        for (int i = 1; i <= getLeft(); i++) {
                            System.out.print(" *");
                        }
                        System.out.println("\n" + play[twoIndex].getGivenName() 
                            +  "'s turn - remove how many?" );
                        if (play[twoIndex].getHuman() == true)  {
                            int stone = keyboard.nextInt();
                            validStones = human.removeStone(stone, getUpper(), getLeft());
                            if (validStones != getLeft())   {
                                setLeft(validStones);
                                tryAgain = false;
                            } else  {
                                tryAgain = true;
                            }
                        } else  {
                            setLeft(computer.removeStone(getUpper(), getLeft()));
                            tryAgain = false;
                        }
                    }
                    one = true;
                }
            }

            System.out.println("\nGame Over");

            //prints the winner and adds the win to the player's record
            if (one == true)   {
                play[oneIndex].setWon(1);
                System.out.println(play[oneIndex].getGivenName() + " " 
                        + play[oneIndex].getFamilyName() + " wins!");
            } else  {
                play[twoIndex].setWon(1);
                System.out.println(play[twoIndex].getGivenName() + " " 
                        + play[twoIndex].getFamilyName() + " wins!");
            }
        }
    }
}

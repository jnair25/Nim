/**
 * The Nim program allows multiple people to play the game of Nim
 * 
 * @author Jayakrishna Nair 975655
 * @version 3.0
 * @since 2018-05-30
 */

import java.io.*;
import java.util.Scanner;

public class Nimsys {
    
    public Scanner keyboard = new Scanner(System.in);
    
    //variable used to take input from the players
    private String line;
    
    private NimPlayer [] play;
    
    private NimGame gamePlay = new NimGame();
    //variable used to identify the position of the last active instance of
    //NimPlayer in the array
    private int playerCount = 1;
    
    //reads information from the file and instantiates it
    private void fileReader()  {
        String file = "players.dat";
        String bufferLine = null;
        try {
             FileReader reader = new FileReader(file);
             BufferedReader buffer = new BufferedReader(reader);
            for (int i=0; i<100; i++)   {
                bufferLine = buffer.readLine();
                String[] bufferArray = bufferLine.split(",");
                if (!"null".equals(bufferArray[0])) {
                    play[i].setName(bufferArray[0]);
                    play[i].setFamilyName(bufferArray[1]);
                    play[i].setGivenName(bufferArray[2]);
                    play[i].setGames(Integer.parseInt(bufferArray[3]));
                    play[i].setWon(Integer.parseInt(bufferArray[4]));
                    play[i].setHuman(Boolean.parseBoolean(bufferArray[5]));
                    playerCount += 1;
                }
            }
            buffer.close();
        } catch(FileNotFoundException e) {
            //if no file exists, a new file is created
            fileCreator();
        } catch(IOException e)  {
            System.out.println("Problem with file output.");
        } catch(NullPointerException e) {
            //ignores NullPointerExceptions
        }
    }
    
    //creates a new file
    private void fileCreator()  {
        try {
            File f = new File("players.dat");
            f.createNewFile();
        } catch(IOException e) {
            System.out.println("Problem with file output.");
        }
    }
    
    //writes the information onto the file
    private void fileWriter(int lineNumber)  {
        String file = "players.dat";
        try {
            FileWriter printer = new FileWriter(file);
            BufferedWriter bufferPrinter = new BufferedWriter(printer);
            for (int i=0; i<lineNumber; i++)  {
                 bufferPrinter.write(play[i].toString());
                 bufferPrinter.newLine();
            }
            bufferPrinter.close();
        }
        catch(IOException e)
        {
         System.out.println("Problem with file output.");
        }
    }
    
    //instantiates NimPlayer into an array
    private void sys()  {
        play = new NimPlayer[100];
        for (int i = 0; i < 100; i++)   {
            play[i] = new NimHumanPlayer(null, null, null);
        }
    }
    
    //takes in the relevant command from the player
    private void printCommand() {
        System.out.print("\n$");
        line = keyboard.nextLine();
        if (!"".equals(line))   {
            command();
        } else  {
            //clears the buffer in the scanner
            line = keyboard.nextLine();
            command();
        }
    }

    //directs the player's command to the right method
    private void command()  {
        String[] lineArray = line.split(" ");
        while (!"exit".equals(lineArray[0]))    {
            try {
                if ("addplayer".equals(lineArray[0])) {
                    addPlayer(lineArray);
                } else if ("addaiplayer".equals(lineArray[0]))   {
                    addAIPlayer(lineArray);
                } else if ("removeplayer".equals(lineArray[0]))   {
                    removePlayer(lineArray);
                } else if ("editplayer".equals(lineArray[0]))   {
                    editPlayer(lineArray);
                } else if ("startgame".equals(lineArray[0]))    {
                    gamePlay.startGame(keyboard, lineArray, play, playerCount);
                } else if ("displayplayer".equals(lineArray[0]))    {
                    displayPlayer(lineArray);
                } else if (("resetstats").equals(lineArray[0]))   {
                    resetStats(lineArray);
                } else if (("rankings").equals(lineArray[0]))   {
                    rankings();
                } else  {
                    //throws an exception if the command is not recognised
                    throw new IllegalArgumentException("'" + lineArray[0] + "'" + " is not a valid command.");
                }
                printCommand();
                
            } catch (ArrayIndexOutOfBoundsException ex) {
                //catches the exception if there correnct number of arguments is not supplied
                System.out.println("Incorrect number of arguments supplied to command.");
                printCommand();
                
            } catch (IllegalArgumentException e)    {
                String message = e.getMessage();
                System.out.println(message);
                printCommand();
            }
        }
        fileWriter(playerCount-1);
        System.out.print("\n");
        System.exit(0);
    }
    
    //adds players to program
    private void addPlayer(String[] lineArray)  {
        String[] details = lineArray[1].split(",");
        //checks if the players exist
        if (playerExist() == true)   {
            System.out.println("The player already exists.");
        } else  if (details.length == 3) {
            //adds details of the player to the game
            play[playerCount-1].setName(details[0]);
            play[playerCount-1].setGivenName(details[2]);
            play[playerCount-1].setFamilyName(details[1]);
            play[playerCount-1].setHuman(true);
            playerCount += 1;
        } else  {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    //adds players to program
    private void addAIPlayer(String[] lineArray)  {
        String[] details = lineArray[1].split(",");
        //checks if the players exist
        if (playerExist() == true)   {
            System.out.println("The player already exists.");
        } else if (details.length == 3) {
            //adds details of the player to the game
            play[playerCount-1].setName(details[0]);
            play[playerCount-1].setGivenName(details[2]);
            play[playerCount-1].setFamilyName(details[1]);
            play[playerCount-1].setHuman(false);
            playerCount += 1;
        } else  {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    //checks if players exist
    private boolean playerExist()  {
        String[] lineArray = line.split(" ");
        String[] details = lineArray[1].split(",");
        for (int i=0; i<playerCount; i++)   {
                if (details[0].equals(play[i].getName()))    {
                    return true;
                }
            }
        return false;
    }
    
    //removes players from the game
    private void removePlayer(String[] lineArray)   {
        //removes all players from the game
        if (lineArray.length == 1)  {
            System.out.println("Are you sure you want to remove all players? (y/n)");
            String answer = keyboard.nextLine();
            if ("y".equals(answer)) {
                for (int j=0; j<playerCount;j++)    {
                    play[j].setName(null);
                    play[j].setGivenName(null);
                    play[j].setFamilyName(null);
                    play[j].setGames(0);
                    play[j].setWon(0);
                    playerCount = 1;
                }
            }
        } else {
            if (playerExist() == false)   {
                System.out.println("The player does not exist.");
            //removes a specific player    
            } else  {
                for (int j=0; j<playerCount;j++)    {
                    if (play[j].getName().equals(lineArray[1]))    {
                        play[j].setName(null);
                        play[j].setGivenName(null);
                        play[j].setFamilyName(null);
                        play[j].setGames(0);
                        play[j].setWon(0);
                        break;
                    }
                }
                //ensures there's no null elements between active instances of
                //NimPlayer
                for (int j=0; j<playerCount;j++)    {
                    if (play[j].getName() == null && play[j+1].getName() != null)    {
                            for (int k=j; k<playerCount-j; k++) {
                                play[k] = play[k+1];
                            }
                    }
                }
                playerCount -= 1;
            }
        }
    }
    
    //edits player details in the program
    private void editPlayer(String[] lineArray)   {
        String[] details = lineArray[1].split(",");
        if (playerExist() == false)   {
            System.out.println("The player does not exist.");
        } else if (details.length == 3) {
            for (int j=0; j<playerCount;j++)    {
                if (play[j].getName().equals(details[0]))    {
                    play[j].setGivenName(details[2]);
                    play[j].setFamilyName(details[1]);
                    break;
                }
            }
        } else  {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    
    
    //sorts the data in an array
    private void selectionSort(int input)   {
        for (int i=0; i<playerCount; i++)   {
            int index = i;
            for (int j=i+1;j<playerCount;j++)   {
                //sorts the data alphabetically
                if (input == 0) {
                    if (play[i].getName() != null && play[j].getName() != null && 
                            play[j].getName().compareTo(play[index].getName()) < 0)   {
                        index = j;
                        }
                //sorts the data numerically in descending order
                } else if (input == 1)  {
                    if (play[i].getName() != null && play[j].getName() != null
                            && play[j].getWins() > play[index].getWins()) {
                        index = j;
                    //sorts the equal wins in alphabetical order    
                    } else if (play[j].getWins() == play[index].getWins()) {
                        if (play[i].getName() != null && play[j].getName() != null && 
                            play[j].getName().compareTo(play[index].getName()) < 0)   {
                        index = j;
                        }
                    }
                //sorts the data numerically in ascending order
                } else if (input == 2)  {
                    if (play[i].getName() != null && play[j].getName() != null
                            && play[j].getWins() < play[index].getWins()) {
                        index = j; 
                    } else if (play[j].getWins() == play[index].getWins()) {
                        if (play[i].getGivenName() != null && play[j].getGivenName() != null && 
                            play[j].getGivenName().compareTo(play[index].getGivenName()) < 0)   {
                        index = j;
                        }
                    }
                }
            }
        NimPlayer temp = play[i];
        play[i] = play[index];
        play[index] = temp;    
        }
    }
    
    //displays the player's information
    private void displayPlayer(String[] lineArray)    {
        Boolean playerExists = false;
        int index = 0;
        //displays all players' information
        if (lineArray.length == 1)  {
            selectionSort(0);
            for (int i=0; i<playerCount; i++)   {
                if (play[i].getName() != null)  {
                    System.out.println(play[i].getName() + ","
                    + play[i].getGivenName() + "," + play[i].getFamilyName() 
                    + "," + play[i].getGames() + " games," + play[i].getWon() + " wins");
                }
            }
        //displays a specific player's information
        } else {
            for (int i=0; i<playerCount; i++)   {
                if (lineArray[1].equals(play[i].getName()))    {
                    playerExists = true;
                    index = i;
                    break;
                }
            }
            if (playerExists == false)   {
                System.out.println("The player does not exist.");
            } else  {
                System.out.println(play[index].getName() + ","
                    + play[index].getGivenName() + "," + play[index].getFamilyName() 
                    + "," + play[index].getGames() + " games," + play[index].getWon() + " wins");
            }
        }
    }
    
    //resets the stats of players
    private void resetStats(String[] lineArray)   {
        //resets the stats of all players
        if (lineArray.length == 1)  {
            System.out.println("Are you sure you want to reset all player statistics? (y/n)");
            String answer = keyboard.nextLine();
            if ("y".equals(answer)) {
                for (int j=0; j<playerCount;j++)    {
                    play[j].setGames(0);
                    play[j].setWon(0);
                }
            }
        //resets the stats of a specific player
        } else {
            if (playerExist() == false)   {
                System.out.println("The player does not exist.");
            } else  {
                for (int j=0; j<playerCount;j++)    {
                    if (play[j].getName().equals(lineArray[1]))    {
                        play[j].setGames(0);
                        play[j].setWon(0);
                        break;
                    }
                }
            }
        }
    }
    
    //computes the rankings of the players
    private void rankings()  {
        String[] lineArray = line.split(" ");
        //sorts the ranking of players
        if (lineArray.length == 1)  {
            selectionSort(1);
        } else if ("asc".equals(lineArray[1]))  {
            selectionSort(2);
        } else  {
            selectionSort(1);
        }
        for (int i=0; i<playerCount; i++)   {
            if (play[i].getName() != null)  {
                System.out.print(play[i].getWins() + "%");  
                if (String.valueOf(play[i].getWins()).length() == 1)    {
                    System.out.print("   | ");
                    System.out.printf("%02d",play[i].getGames());
                    System.out.print(" games | " + play[i].getGivenName() 
                            + " " + play[i].getFamilyName());
                } else if (String.valueOf(play[i].getWins()).length() == 2) {
                    System.out.print("  | ");
                    System.out.printf("%02d",play[i].getGames());
                    System.out.print(" games | " + play[i].getGivenName() 
                            + " " + play[i].getFamilyName());
                } else {
                    System.out.print(" | ");
                    System.out.printf("%02d",play[i].getGames());
                    System.out.print(" games | " + play[i].getGivenName() 
                            + " " + play[i].getFamilyName());
                }
            System.out.print("\n");
            }
        }
    }
    
    public static void main(String[] args)   { 
        
        //creates an instance of Nimsys
        Nimsys nimsys = new Nimsys();
        nimsys.sys();
        nimsys.fileReader();
        System.out.println("Welcome to Nim");
        
        nimsys.printCommand();
    }    
}

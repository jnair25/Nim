public abstract class NimPlayer {

    private String username;
    private String givenName;
    private String familyName;
    private boolean human;
    private int games;
    private int won;
    private double wins;
    
    public NimPlayer(String username, String givenName, String familyName)  {
        this.username = username;
        this.familyName = familyName;
        this.givenName = givenName;
    }
    
    //returns the player's usernames 
    public String getName()   {
        return username;
    }
    
    //sets the username
    public void setName(String username)    {
        this.username = username;
    }
    
    //returns the player's Given Name
    public String getGivenName()   {
        return givenName;
    }
    
    //sets the given name
    public void setGivenName(String givenName)    {
        this.givenName = givenName;
    }
    
    
    //returns the player's Family Name
    public String getFamilyName()  {
        return familyName;
    }
    
    //sets the family name
    public void setFamilyName(String familyName)    {
        this.familyName = familyName;
    }
    
    public boolean getHuman ()   {
        return human;
    }
    
    public void setHuman(boolean human)   {
        this.human = human;
    }
    
    //returns the number of games played
    public int getGames()  {
        return games;
    }
    
    //adds or removes the games played
    public void setGames(int games)    {
        if (games != 0) {
            this.games += games;
        } else  {
            this.games = games;
        }
    }
    
    //returns the number of games won
    public int getWon()    {
        return won; 
    }
    
    //adds or removes wins
    public void setWon(int won)    {
        if (won != 0) {
            this.won += won;
        } else  {
            this.won = won;
        }
    }
    
    //returns the percentage of wins
    public int getWins()  {
        wins = ((won * 1.0) / (games * 1.0)) * 100;
        return (int) Math.ceil(wins);
    }

     public abstract int removeStone();
}

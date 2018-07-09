This program was created as part of a project for one of the subjects taken at the University of Melbourne. This is a two-player game and the rules are as follows:

* The game begins with a number of objects (e.g., stones placed on a table).
* Each player takes turns removing stones from the table.
* On each turn, a player must remove at least one stone. In addition, there is an upper bound on the number of stones that can be removed in a single turn. For example, if this upper bound is 3, a player has the choice of removing 1, 2 or 3 stones on each turn.
* The game ends when there are no more stones remaining. The player who removes the last stone, loses. The other player is, of course, the winner.
* Both the initial number of stones, and the upper bound on the number that can be removed, can be varied from game to game, and must be chosen before a game commences.

To play the game for the first time, players must be added to the system and this can be achieved by using the right commands. The following commands can be used when the system is idle, i.e. not in the middle of a game:

* Syntax: addplayer username,last_name,first_name
o This will add a player to the system
o e.g. addplayer dspoon,Spooner,Del

* Syntax: addaiplayer username,last_name,first_name
o This will add a bot that is controlled by the program and is designed to always win
o e.g addaiplayer irobot,sonny,sonny

* Syntax: removeplayer [username]
o This will remove player(s) from the system
o For a specific player: e.g. removeplayer dspoon
o For all players: e.g. removeplayer 

* Syntax: editplayer username,last_name,first_name
o This will edit player information
o e.g. editplayer dspoon

* Syntax: resetstats [username]
o This will reset the stats of player(s)
o For a specific player: e.g. resetstats dspoon
o For all players: e.g. resetstats

* Syntax: displayplayer [username]
o Displays the stats of player(s)
o For a specific player: e.g. displayplayer dspoon
o For all players: e.g. displayplayer

* Syntax: rankings [asc | desc]
o Displays the rankings of the players on the system
o For ascending order: e.g. rankings asc
o For descending order: e.g. rankings desc

* Syntax: startgame initialstones,upperbound,username1,username2
o This initiates a game of nim
o e.g. startgame 10,3,dspoon,scalvin

* Syntax: exit
o Exits the system

Upon exiting the program, all player stats and information will be stored under the filename: players.dat. 
When the program is opened again, it should automatically read the information from that file. 

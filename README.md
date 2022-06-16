The /src and /test folders are kept private to adhere to academic integrity. You can reach out to me if you have any queries. You can still run the jar file available in /res

### About/Overview

We are implementing a game, similar to Kill Dr. Lucky. The game has a world of its own, which contains spaces (rooms), weapons (items) in each room, (human-controlled and/or computer-controlled) players that play the game and a target character. The final outcome of the game is to kill the target character. This program creates the world, all the spaces in the world and weapons in each space by taking inputs from a text file in a specified format. The players will be created by the user in the beginning of the game. The players try to kill the target character and the player who hits the killing shot wins. There is a target character's pet that affects the visibility of the space it is in. The target character and the pet move around the world after every turn of the player.



### List of Features

The features of this program are:

1.  The world is created.
2.  All the spaces of the world, weapons in each space, target character and the pet are created.
3.  The neighboring spaces of a certain space are calculated and saved in the particular space.
4.  Checks for overlapping spaces.
5.  The players will be created before the game.
6.  The player can move to another space, pick a weapon from his space, attack the target character, move the pet and look around from his space to make his turn.
7.  The target character and the pet move through the spaces as and when a turn is made by the player.
8.  The target character comes to the initial space with index 0 after traversing all the spaces.
9.  The target's pet moves around the world using DFS traversal algorithm.
10. Before the start of the game, the player can:
    1.  Create a human controlled player.
    2.  Create a computer controlled player.
11. Once the game starts, a graphical representation of the world is shown, with spaces, players in the respective spaces and the target character in his space.
12. When the player starts the turn, information regarding his space is shown which includes, the name of the space he is in, the neighbors of the space, the weapons in the space, the players in the space and the location of the target character in the space.
    1. The player should press the 'm' key to move to a neighboring space and click on the space in graphical representation, the movement of the player is graphically shown.
    2. The player should press the 'p' key to pick a weapon from the space, a prompt lets him choose the weapon he wants to pick, after which a prompt confirms the pick.
    3. The player should press the 'a' key to attack the target, a prompt lets him pick the weapon he wants to attack with, after which a prompt confirms the attack, if sucessful, else shows the issue that occurred.
    4. The player should press the 'x' key to move the pet and click on the space in graphical representation, a prompt confirms the movement of the pet.
    5. The player should press the 'l' key to look around, a window appears that contains all the information about look around.
    6. Finally, the player should press the 'q' key to quit the game.
13. Every player gets a turn in the order in which the player was added.
14. The maximum number of turns is considered after which the game ends, whether or not the game is complete.
15. The game also ends when the target character dies, the winner player is decided.
16. When the game ends, a prompt appears that shows why theb game ended and the winner, if any.
17. Clear testing suites are provided for all the aspects of the project.
18. Clear instructions are provided when the user wishes to do something.



### How to Run

A text file is required which contains the specifications of the world in a particular format. The jar file and the text file should be in the same directory.

**To run the jar file:**

Go to the directory in which the jar file recides.

Type the command java -jar world.jar filename.txt maxTurns (here, world is the name of the jar file, filename is the name of the txt file, maxTurns is an integer that specifies the maximum number of turns allowed in the game, after which the game stops.) 

**Example:** java -jar M3.jar mansion.txt 20



### How to Use the Program

Once the game starts, a graphical representation of the world is shown, with spaces, players in the respective spaces and the target character in his space. 

When the player starts the turn, information regarding his space is shown which includes, the name of the space he is in, the neighbors of the space, the weapons in the space, the players in the space and the location of the target character in the space.
1.  The player should press the 'm' key to move to a neighboring space and click on the space in graphical representation, the movement of the player is graphically shown.
2.  The player should press the 'p' key to pick a weapon from the space, a prompt lets him choose the weapon he wants to pick, after which a prompt confirms the pick.
3.  The player should press the 'a' key to attack the target, a prompt lets him pick the weapon he wants to attack with, after which a prompt confirms the attack, if sucessful, else shows the issue that occurred.
4.  The player should press the 'x' key to move the pet and click on the space in graphical representation, a prompt confirms the movement of the pet.
5.  The player should press the 'l' key to look around, a window appears that contains all the information about look around.
6.  The player should press the 'q' key to quit the game.



### Example Runs

A video submisison containing the runs will be uploaded.



### Design/Model Changes

We added a view to the implementation and edited the controller to take graphical inputs instead of a console-based inputs.



### Assumptions

The following assumptions are made in the project:
1.  The file given will be in the format specified and not in a totally different format.
2.  The user should be smart enough to be able to analyze the prompts and do the needful.


### Limitations

The following limitations exist in the project:
1.  The game cannot restart when a previous game is running.
2.  The game does not restart after one game ends, the JAR has to be run again.



### Citations

Reference for creating buffered image and graphics: "How to usedrawRectmethodinjava.awt.Graphics". Available: https://www.tabnine.com/code/java/methods/java.awt.Graphics/drawRect [Accessed Feb 9th 2022].

Reference for random numbers: "Class Random". Available: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Random.html [Accessed Mar 1st 2022].

References for HashMap implementation: "Class HashMap<K,V>". Available: https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html [Accessed Feb 22nd 2022]. "HashMap in Java with Examples". Available: https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/ [Accessed Feb 22nd 2022].

Reference for variable arguments: "Variable Arguments (Varargs) in Java". Available: https://www.geeksforgeeks.org/variable-arguments-varargs-in-java/ [Accessed Feb 27th 2022].

References for DFS traversal: "Java Graph Tutorial – How To Implement Graph Data Structure". Available: https://www.softwaretestinghelp.com/java-graph-tutorial/ [Accessed Mar 22th 2022]. "Depth First Search or DFS for a Graph". Available: https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/ [Accessed Mar 22th 2022]. "Data Structure - Depth First Traversal. Available: https://www.tutorialspoint.com/data_structures_algorithms/depth_first_traversal.htm [Accessed Mar 22th 2022].

Reference for Scroll Pane: "How to Use Scroll Panes". Available: https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html [Accessed Apr 19th 2022].

Reference for Swing: "Trail: Creating a GUI With Swing". Available: https://docs.oracle.com/javase/tutorial/uiswing/  [Accessed Apr 15th 2022].




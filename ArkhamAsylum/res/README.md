# CS 5010 Semester Final Project

This repo represents the coursework for CS 5010, the Spring 2022 Edition!

**Name:** Subhankar Shah | Mohit Rajendra Mhamunkar

**Email:** shah.sub@northeastern.edu | mhamunkar.m@northeastern.edu

**Preferred Name:** Subhankar Shah | Mohit Mhamunkar

### About/Overview

The problem statement that was given to us was to create a world containing spaces or rooms and there are few items that
can be used as weapons. These weapons are associated with each rooms that can be used only in that room. The weapons
have power that can be used to damage the Target. The target is a moving character which moves around the world between
different spaces. There is a health associated with the target. There is a pet which also wonders around the world.
If there is a pet in the room that room is technically not visible to the players. The GUI is added to the following application 
and we have an Introductory Screen with abilites to load new maps or call for an existing map, along with thatwe can add upto 
10 Human & Computer Players in Any form of Combination.

### List of Features

TARGET:

1. The Target can move from one space to another but only monotonically fashion which means that it moves to the next
   room in the room list.
2. There is a health associated with the target.
3. The health of the target character can be reduced by the player who are attacking it.
4. I have implemented decrease health feature of the target.
5. The target can move back to the starting point after reaching the end of the room in the list.
6. Get the deatils of the Target using toString() function.
7. The Target GUI is represented as a Joker Image which traverses around the whole world after every turn.

SPACES:

1. Each spaces has its own ID, NAME and where it is located.
2. The location data is stored in the Pair data structure.
3. We can identify the neighbors of the spaces.
4. We can identify who can see into the spaces.
5. We can identify the number of rooms in the world.
6. We can identify the items that can be used in the room.
7. We can identidy if the target is there in the room or not.
8. We can determine if there are any overlapping spaces in the world. In this case it throws IllegalArgumentException.
9. The program check for all boudary cases to identify if the spaces are in the given boundary or not.
10. Get the deatils of the Room using toString() function.

ITEMS:

1. Each items has its power.
2. Each item can be used as a weapon to attack the target. 
3. Once the item is used to attack the target it cannot be used again and is treated as an evidence. 
4. Implemented toString to get the details of the item.
5. The details of the item may consist of the Name, Power and the location where it can be used.
6. Implemented equals and hashCode functionality of the items.
7. Implemented if the particular item has been used or not.

WORLD:

1. World can determine the neighbors of each space.
2. It can identify who can look into the spaces.
3. The target is being used in the world.
4. Implemented Map using BufferedImage in the world.
5. Move the target around in the Map.
6. Move the target around in the world.
7. Determine where we can find the items in the world.

PLAYER: 

1. Added players in the world. 
2. A player can pick upto set number of items. 
3. User can provide total number of items that can be picked. 
4. Player can look around to see the items present in the room. 
5. Player can look around to see the neighbouring spaces. 
6. Players can move to the neighbouring spaces. 
7. Once the player picks an item from the space the item is no longer available in the space. 
8. Player uses the item to attack target character. 
9. A player can be either a computer player or a human player. 
10. The player who kills the target wins the game.
11. The player GUI is represented with icons and can move around the world by a mouse click.
12. The player can Pick an Item, Look Around, Attempt Kill & Move Pet with the press of a key.
13. The player in turn can click on itself and view it's description.

PET:

1. Pet can wonder in the world in DFS fasion. 
2. If pet is there in any room the room is invisible to other players. 
3. Players can move the pet from one place to another. 

DISPLAY THE INFORMATION OF THE CURRENT PLAYER

1. Display name of the current player. 
2. Display the location of the current player. 
3. Display current items with the current player. 

DISPLAY THE WORLD INFORMATION

1. Display the information of the world. 
2. Display the information of the Target location.
3. Display the total number of players in the world.
4. Display Hints for every player.
5. Display Current Players turn.
6. Display the set of moves available.
7. Display the pet'c current location in hints.

DISPLAY THE INFORMATION OF THE SPECIFIED PLAYER

1. Display name of the specified player. 
2. Display the location of the specified player. 
3. Display current items with the specified player. 

DISPLAY THE INFORMATION OF THE SPECIFIED SPACE

1. Different players in the space
2. If target character present in the space.

ATTACK TARGET CHARACTER

1. If the player and the target is in the same space and no one is watching from the neighboring space then player can successfully attack target character. 
2. If another player is watching the attack from the neighboring room then the attack is of no use.

MOVE PET 

1. Player can move the pet from 1 location to another for there own advantage. 
2. If the pet is there in the space the space is invisible to other players in look around command. 

PICK AN ITEM

1. Pick an item which is present in the room.
2. If an item has been picked then it cannot be picked by any other player. 

LOOK AROUND

1. This command is used to get information of the current space and also the neighboring space. 
2. The information contains as follows: Players present in the current and neighboring space. Items present in the current and the neighboring space. Location of the target and also location of the pet. 

MOVE PLAYER. 

1. Player can move to the neighboring spaces on mouse click

### How to Run

Please find the JAR file inside the res folder. Please keep the mansion.txt file in the same folder.

Follow the steps to run the program:

1. Navigate to res folder.
2. Run the following command on Windows machine: java -jar milestone4.jar mansion.txt <Maximum number of turns allowed> . For maximum number of turns you can put any integer. You will able to see the output of the program. Please find the generated map with the name: worldImage.png. Please find the example output of the execution of the program in the res folder. 
3. EXECUTION OF THE JAR FILE ON WINDOWS: java -jar milestone4.jar mansion.txt 10

### How to Use the Program

After running the program you can see the Introductory View with the name of the creators and a button to view the About Us Section. It also has a Menu Bar with options to start game with existing map, new map or to quit the game. The new map feature allows you to use a map with the same format as specified any changes in the format will result in error and display of a prompt asking you to choose a new file or run with an existing file. After the file is accepted the you have an option of adding human and computer players in any combination with limit of 10 players and a start game button which initializes the game with a minimum of 1 player. After we start the game we can see the main game window with scroll bar options and resizable features to acomodate all sizes of maps. The right section has hints and the current player in turns name, along with a list of moves avaialable and how to run them. At any point of time you may choose to quit the game by closing the window or by selecting quit in the menubar above. After the total number of moves are reached or a player sucessfully kills the target character it displays prompt with the appropriate end condition and closes the game.

### Example Runs

We have provided the screensnaps of the Example GUI Runs which gives the look and asthetichs of the program. This can be found in the res folder by name of UI Designs Folder.

### Design/Model Changes

1. Implemented Facade Pattern, using an intermediate class called ArkhamAsylumImpl which interacts with other classes
   inside the model.
2. The ArkhamAsylumImpl is the single point of contact between the driver and the game.
3. Had to make these changes to make the classes in the module loosely coupled with each other.
4. Added extra class called PlayerImpl and Player interface class. 
5. Created Command design pattern. And showcased the same on the UML.
6. Added extra command class to move pet and also to attack the target. 
7. Added Pet class to track the pet movement. 
8. Implemented DFS for pet traversal. 
9. Implemented a class to create graphical representation of target and players.
10. Implemented interpretation of mouse clicks on the world image.
11. Reseted the model when a new map file is provided.
12. Updated hints to provide concise and accurate data.
13. The UML has been updated to the latest implementation by the name of Milestone 4 UML.pdf

### Assumptions

1. We have made the assumption that the health of the target character may decrease at some point of time.
2. We have made an assumption that when someone gives look around command then he is only looking around his/her space and his/her neighbours. 
3. We have made assumptions that when someone gives display world information command then we have to show the location of the Target character.
4. We have made assumption that the computer player cannot move the pet. 
5. We have made an assumption that pet will start from the begining if all the space has been visited.  
6. We have made an assumption that if players are present in neighbouring space with the pet the model won't display the graphical image of the following players.
7. We have made an assumption that a click near the vicinity of the player in turn will display the player's descrption due to the small size graphical creation of player icons.
8. We have made an assumption that the game ends after the maximum number of turns have been reached or a plyer sucessfully kills the target character.
9. We have made an assumption that teh whole frame is resizable with the ability to resize each and every individual panel in the main game screen.

### Limitations

1. Computer player is not able to move the pet.
2. Due to small size of player we give player description on mouse click near vicinity of player's graphical representation.

### Citations
1. Used stackoverflow for some references. 
2. The Board Games, “How to play Dr. Lucky in Under 5 minutes” youtube.com, Jun. 06, 2017. [Video]. Available: https://youtu.be/IZQPIxYH-oc. [Accessed Feb. 04, 2022].
3. A Visual Guide to Swing Components web.mit.edu. [WebPage]. Available: https://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html. [Accessed Apr. 14, 2022].


package controller;

/**
 * Feature interface class invoke different methods on the controller.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface Features {

  /**
   * Add a player into the world using the parameters provided by the users.
   * 
   * @param playerName Name of the individual player.
   * @param isComputerPlayer It is used to identify if the player is a computer or not.
   * @param roomName the location where the player is located in the world.
   * @param totalNumber maximum number of items a player can carry.
   */
  void addPlayer(String playerName, boolean isComputerPlayer, String roomName, int totalNumber);

  /**
   * This function is used to close the add player screen and start the game.
   */
  void startGame();

  /**
   * This function is used to open the pop-up of the add player dialog box.
   */
  void openAddPlayers();

  /**
   * This function is used to open the splash screen.
   */
  void showFirstPage();

  /**
   * This function is used to open the file choose to custom world configuration.
   */
  void openFileChooser();

  /**
   * Handle mouse click to identify if it is a move player command of player description command.
   * 
   * @param x coordinate in the map.
   * @param y coordinate in the map.
   */
  void mouseClicked(int x, int y);

  /**
   * Used to show the show up to pick the items available in the room.
   */
  void pickAnItem();

  /**
   * Used to show the dialog box with the look around information.
   */
  void lookAround();

  /**
   * Used to show the dialog box with an item to choose an item for attack.
   */
  void attackTarget();

  /**
   * Executes the pop dialog to choose the location where the pet is to be moved.
   */
  void movePet();

  /**
   * This function is used to Quit the game gracefully when the user will click quit game.
   */
  void quitGame();
}

package view;


import controller.Features;
import java.io.File;
import java.util.List;

/**
 * This interface represents the facade view class.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 *
 */
public interface Iview {

  /**
   * This method sets the features of view class.
   * 
   * @param feature Controller
   */
  void setFeature(Features feature);
  
  /**
   * This method handles the closing of Add Player Info Frame.
   */
  void closeAddPlayerFrame();
  
  /**
   * This method creates the main game frame.
   */
  void showGameView();
  
  /**
   * This is a call back method for pick item command.
   * 
   * @param list LIst of items in a space
   * @return Item choosen
   */
  String pickItemView(List<String> list);
  
  /**
   * This method displays a message in a JOptionPane.
   * 
   * @param message Message to display
   */
  void showDialogMessage(String message);
  
  /**
   * This is a method for look around command view display.
   * 
   * @param info Look Around information
   */
  void lookAroundView(String info);
  
  /**
   * This is a method for move pet command view display.
   * 
   * @param list List of spaces
   * @return Space choosen to move
   */
  String movePetView(List<String> list);
  
  /**
   * This is a method for kill attempt command view display.
   * 
   * @param list List of items with a player
   * @return Items choosen
   */
  String chooseItemToAttack(List<String> list);
  
  /**
   * This method refreshes components after every turn.
   */
  void refresh();
  
  /**
   * THis method closes the game frame after end of moves or a winner.
   */
  void closeGame();
  
  /**
   * This method displays the intro frame.
   */
  void showIntro();
  
  /**
   * This method displays the player addition frame.
   */
  void showAddPlayers();
  
  /**
   * This method closes the intro frame.
   */
  void closeIntro();
  
  /**
   * This is a call back method for choosing the file for a new game.
   * 
   * @return File type
   */
  File chooseFile();
  
  /**
   * This method displays the Joption Pane for an error.
   * 
   * @param error Error handled message
   */
  void showError(String error);
  
  /**
   * This method updates the name of current players turn.
   * 
   * @param message Hint and players turn
   */
  void showCurrentPlayerTurn(String message);
  
  /**
   * This method handles quit game.
   * 
   * @return return value of handling
   */
  int quitGame();
}

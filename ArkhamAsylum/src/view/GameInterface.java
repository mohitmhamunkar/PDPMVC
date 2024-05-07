package view;

import controller.Features;
import java.util.List;

/**
 * This class in an interface of the main game frame.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 *
 */
public interface GameInterface {
  
  /**
   * This method displays the frame of the game with all the elements.
   */
  void show();
  
  /**
   * This method displays the JOptionPane for choice of items in a command.
   * 
   * @param command Name of Commmand
   * @param list List of items.
   * @return The Item choiced
   */
  String showCommand(String command, List<String> list);
  
  /**
   * This method creates a callback to the controller for listening actions.
   * 
   * @param feature Controller
   */
  void setFeatures(Features feature);
  
  /**
   * This method displays a dialog box with look around info.
   * 
   * @param info Look around info
   */
  void showLookAround(String info);
  
  /**
   * This method updates the panels on every turn.
   */
  void updatePanels();
  
  /**
   * This method disposes the frame on end of turns or if a player wins.
   */
  void closeGame();
  
  /**
   * This method displays current player's turn.
   * 
   * @param message Current Player turn name.
   */
  void showCurrentPlayerTurn(String message);
}

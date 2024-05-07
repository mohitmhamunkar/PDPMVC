package view;

import javax.swing.JOptionPane;

/**
 * This class displays the error occurred in the following game.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 *
 */
public class Dialogs {
  
  /**
   * This method displays the error dialog box.
   * 
   * @param error Error message
   */
  public void showError(String error) {
    JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
  }
  
  /**
   * This method displays a game completed Dialog Box.
   * 
   * @param message Message for game end
   */
  public void showEndGame(String message) {
    JOptionPane.showMessageDialog(null, message, "Game Ended", JOptionPane.INFORMATION_MESSAGE);
  }
  
  /**
   * This method displays the quit dialog box.
   * 
   * @return Option Pressed.
   */
  public int showQuit() {
    int x = JOptionPane.showConfirmDialog(null, "Are Your Sure You Want To Quit",
        "Quit", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
    return x;
  }
  
  /**
   * This method displays the message dialog box.
   * 
   * @param message Message to display
   */
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
  }
}

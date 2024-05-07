import controller.Features;
import java.io.File;
import java.util.List;
import view.Iview;

/**
 * This class is used to mock the facade ArkhamAsylum view
 * This class represents check if the function is being called properly.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 *
 */
public class InvalidMockView implements Iview {
  private StringBuilder log;
  private final int uniqueCode;
 
  /**
   * This is the constructor of the class.
   * 
   * @param log StringBuilder logs the input values.
   * @param uniqueCode is used to identify the unique values.
   */
  public InvalidMockView(StringBuilder log, int uniqueCode) {
    if (log == null || uniqueCode == 0) {
      throw new IllegalArgumentException("Nulll Mock View Argumnets.");
    }
    this.log = log;
    this.uniqueCode = uniqueCode;
  }
  
  @Override
  public void setFeature(Features feature) {
    if (feature == null) {
      throw new IllegalArgumentException("Features Can't Be Null");
    }
    log.append("setFeature invoked! Uniquecode: " + uniqueCode);
  }

  @Override
  public void closeAddPlayerFrame() {
    log.append("closeAddPlayerFrame invoked! Uniquecode: " + uniqueCode);
  }

  @Override
  public void showGameView() {
    log.append("showGameView invoked! Uniquecode: " + uniqueCode);
  }

  @Override
  public String pickItemView(List<String> list) {
    if (list == null) {
      throw new IllegalArgumentException("list Can't Be Null");
    }
    log.append("pickItemView invoked! Uniquecode: " + uniqueCode);
    return "" + uniqueCode;
  }

  @Override
  public void showDialogMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Message Can't Be Null");
    }
    log.append("showDialogMessage invoked! Uniquecode: " + uniqueCode + " Message: " + message);
  }

  @Override
  public void lookAroundView(String info) {
    if (info == null) {
      throw new IllegalArgumentException("Look Around Info Can't Be Null");
    }
    log.append("lookAroundView invoked! Uniquecode: " + uniqueCode + " Message: " + info);
  }

  @Override
  public String movePetView(List<String> list) {
    if (list == null) {
      throw new IllegalArgumentException("List Can't Be Null");
    }
    log.append("movePetView invoked! Uniquecode: " + uniqueCode);
    return "" + uniqueCode;
  }

  @Override
  public String chooseItemToAttack(List<String> list) {
    if (list == null) {
      throw new IllegalArgumentException("List Can't Be Null");
    }
    log.append("chooseItemToAttack invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public void refresh() {
    log.append("refresh invoked! Uniquecode: " + uniqueCode);
  }

  @Override
  public void closeGame() {
    log.append("closeGame invoked! Uniquecode: " + uniqueCode);
  }

  @Override
  public void showIntro() {
    log.append("showIntro invoked! Uniquecode: " + uniqueCode);
  }

  @Override
  public void showAddPlayers() {
    log.append("showAddPlayers invoked! Uniquecode: " + uniqueCode);
  }

  @Override
  public void closeIntro() {
    log.append("closeIntro invoked! Uniquecode: " + uniqueCode);
  }

  @Override
  public File chooseFile() {
    log.append("chooseFile invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public void showError(String error) {
    if (error == null) {
      throw new IllegalArgumentException("Error Can't Be Null");
    }
    log.append("showError invoked! Uniquecode: " + uniqueCode + " Error: " + error);
  }

  @Override
  public void showCurrentPlayerTurn(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Message Can't Be Null");
    }
    log.append("showCurrentPlayerTurn invoked! Uniquecode: " + uniqueCode);
  }

  @Override
  public int quitGame() {
    log.append("quitGame invoked! Uniquecode: " + uniqueCode);

    return uniqueCode;
  }

}

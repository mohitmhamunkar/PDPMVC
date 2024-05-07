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
public class MockView implements Iview {
  private StringBuilder log;
  private final int uniqueCode;
  
  /**
   * This is the constructor of the class.
   * 
   * @param log StringBuilder logs the input values.
   * @param uniqueCode is used to identify the unique values.
   */
  public MockView(StringBuilder log, int uniqueCode) {
    if (log == null || uniqueCode == 0) {
      throw new IllegalArgumentException("Nulll Mock View Argumnets.");
    }
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void setFeature(Features feature) {
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
    log.append("pickItemView invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public void showDialogMessage(String message) {
    log.append("showDialogMessage invoked! Uniquecode: " + uniqueCode + " Message: " + message);
  }

  @Override
  public void lookAroundView(String info) {
    log.append("lookAroundView invoked! Uniquecode: " + uniqueCode + " Message: " + info);
  }

  @Override
  public String movePetView(List<String> list) {
    log.append("movePetView invoked! Uniquecode: " + uniqueCode);
    return null;
  }

  @Override
  public String chooseItemToAttack(List<String> list) {
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
    log.append("showError invoked! Uniquecode: " + uniqueCode + " Error: " + error);
  }

  @Override
  public void showCurrentPlayerTurn(String message) {
    log.append("showCurrentPlayerTurn invoked! Uniquecode: " + uniqueCode);
  }
  
  @Override
  public int quitGame() {
    log.append("quitGame invoked! Uniquecode: " + uniqueCode);

    return uniqueCode;
  }

}

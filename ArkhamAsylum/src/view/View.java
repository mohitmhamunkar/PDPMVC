package view;

import controller.Features;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.world.ReadOnlyInterface;

/**
 * This calss represents the facade view used by the controller to interact with the frames.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 *
 */
public class View implements Iview {

  private ReadOnlyInterface model;
  private PlayerAddition playerAddition;
  private GameInterface game;
  private IntroInterface intro;
  private Features features;
  private Dialogs dialogMessage;

  /**
   * This is the constructor of the class.
   *
   * @param model Read only Model
   */
  public View(ReadOnlyInterface model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.model = model;
    playerAddition = new PlayerAdditionView(model);
    game = new GameView(model);
    intro = new IntroView();
    dialogMessage = new Dialogs();
  }
  
  @Override
  public void showIntro() {
    intro.show();
  }
  
  @Override
  public void closeIntro() {
    intro.hide();
  }
  
  @Override
  public void showAddPlayers() {
    playerAddition.show();
  }

  @Override
  public void setFeature(Features feature) {
    if (feature == null) {
      throw new IllegalArgumentException("Features Can't Be Null");
    }
    this.features = feature;
    intro.setFeatures(features);
    playerAddition.setFeature(features);
    game.setFeatures(features);
  }

  @Override
  public void closeAddPlayerFrame() {
    playerAddition.hide();
  }
  
  @Override
  public String pickItemView(List<String> list) {
    if (list == null) {
      throw new IllegalArgumentException("list Can't Be Null");
    }
    return game.showCommand("Pick Item", list);
  }
  
  @Override
  public File chooseFile() {
    File map = null;
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Choose Model Map");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    chooser.setFileFilter(filter);
    chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    int result = chooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      map = chooser.getSelectedFile();
    }
    return map;
  }
  
  @Override
  public void lookAroundView(String info) {
    if (info == null) {
      throw new IllegalArgumentException("Look Around Info Can't Be Null");
    }
    game.showLookAround(info);
  }
  
  @Override
  public void showDialogMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Message Can't Be Null");
    }
    dialogMessage.showMessage(message);
  }
  
  @Override
  public void showError(String error) {
    if (error == null) {
      throw new IllegalArgumentException("Error Can't Be Null");
    }
    dialogMessage.showError(error);
  }

  @Override
  public void showCurrentPlayerTurn(String message) {
    game.showCurrentPlayerTurn(message);
  }

  @Override
  public String movePetView(List<String> list) {
    if (list == null) {
      throw new IllegalArgumentException("List Can't Be Null");
    }
    return game.showCommand("Move Pet", list);
  }

  @Override
  public String chooseItemToAttack(List<String> list) {
    if (list == null) {
      throw new IllegalArgumentException("List Can't Be Null");
    }
    return game.showCommand("Choose item to attack the target", list);
  }

  @Override
  public void refresh() {
    game.updatePanels();
  }
  
  @Override
  public void closeGame() {
    game.closeGame();
  }
  
  @Override
  public int quitGame() {
    int option = dialogMessage.showQuit();
    return option;
  }

  @Override
  public void showGameView() {
    game.show();
  }


}

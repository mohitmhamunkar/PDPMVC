package controller;

import controller.command.AddPlayer;
import controller.command.AttackTargetCommand;
import controller.command.CommandInterface;
import controller.command.LookAroundCommand;
import controller.command.MovePetCommand;
import controller.command.MovePlayerCommand;
import controller.command.PickAnItemCommand;
import controller.command.ShowPlayerDescription;
import java.io.File;
import model.ArkhamAsylumGameCreatorInterface;
import model.ArkhamAsylumGameWorldCreator;
import model.world.ArkhamAsylumWorld;
import model.world.ReadOnlyInterface;
import view.Iview;
import view.View;

/**
 * Controller of the game.
 * This class controls what need to be showed to the users.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class ArkhamAsylumFeatures implements Features {

  ArkhamAsylumWorld model;
  Iview view;

  /**
   * Constructor of the game to initialize the model and the view.
   * @param model  of the game. Controller uses this to execute the commands.
   * @param view of the game. Controller tells the view of the different elements to be showed.
   */
  public ArkhamAsylumFeatures(ArkhamAsylumWorld model, Iview view) {

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.model = model;
    this.view = view;
    this.view.setFeature(this);
    showFirstPage();
  }

  @Override
  public void showFirstPage() {
    view.showIntro();
  }

  @Override
  public void addPlayer(String playerName, boolean isComputerPlayer,
      String roomName, int totalNumberItem) {
    if (playerName == null || playerName.isEmpty() || roomName == null
        || roomName.isEmpty() || totalNumberItem < 0) {
      view.showError("Player addition parameters not valid");
    } else {
      try {
        CommandInterface addPlayer = new AddPlayer(
                playerName,
                isComputerPlayer,
                roomName,
                totalNumberItem
        );
        addPlayer.execute(model, view);
      } catch (IllegalArgumentException e) {
        view.showError(e.getMessage());
      }
    }
  }

  @Override
  public void startGame() {
    model.createWorldMap();
    view.closeAddPlayerFrame();
    view.showGameView();
    checkIfComputerPlayerTurn();
  }

  private void checkIfComputerPlayerTurn() {
    if (model.isComputerPlayer()) {
      computerPlayerTurn();
    } else {
      updateView();
    }
  }

  @Override
  public void quitGame() {
    int result = view.quitGame();
    if (result == 0) {
      System.exit(result);
    }
  }

  private void computerPlayerTurn() {
    while (model.isComputerPlayer()) {
      updateView();
      computerTurn();
      if (model.isGameOver()) {
        if (!model.getWinner().isEmpty()) {
          view.closeGame();
          view.showDialogMessage(String.format("Player Won: %s", model.getWinner()));
          closeGame();
          break;
        } else {
          view.closeGame();
          view.showDialogMessage("Max Number Of Turns Reached!\n Target Lives Another Day.");
          closeGame();
          break;
        }
      }
    }
  }

  @Override
  public void openAddPlayers() {
    view.closeIntro();
    view.showAddPlayers();
  }

  @Override
  public void openFileChooser() {
    File map = view.chooseFile();
    if (map != null) {
      try {
        ArkhamAsylumGameCreatorInterface arkhamAsylumDriver =
            new ArkhamAsylumGameWorldCreator(map.getPath(), 10);
        ArkhamAsylumWorld gameWorld = arkhamAsylumDriver.getArkhamAsylumWorld();
        this.model = gameWorld;
        Iview iv = new View((ReadOnlyInterface) model);
        view.closeIntro();
        this.view = iv;
        view.setFeature(this);
        view.showAddPlayers();
      } catch (IllegalArgumentException e) {
        view.showError("Invalid File Type, Make Another Choice");
      }
    } else {
      view.showError("Please Try Again or Choose Existing Map.");
    }
  }

  private void closeGame() {
    System.exit(0);
  }

  private void checkIfGameIsOver() {
    if (model.isGameOver()) {
      if (!model.getWinner().isEmpty()) {
        view.closeGame();
        view.showDialogMessage(String.format("Player Won: %s", model.getWinner()));
        closeGame();
      } else {
        view.closeGame();
        view.showDialogMessage("Max Number Of Turns Reached!\n Target Lives Another Day.");
        closeGame();
      }
    } else {
      checkIfComputerPlayerTurn();
    }
  }

  @Override
  public void mouseClicked(int x, int y) {
    String spaceLocation = model.processMouseClick(x, y);

    if (spaceLocation != null
            && !spaceLocation.isEmpty()
            && !spaceLocation.equals(model.getCurrentPlayerLocation())) {
      try {
        CommandInterface movePlayer = new MovePlayerCommand(spaceLocation);
        movePlayer.execute(model, view);
      } catch (IllegalArgumentException ie) {
        view.showDialogMessage("Unable To Move Player");
      }
    } else if (spaceLocation != null
            && !spaceLocation.isEmpty()
            && spaceLocation.equals(model.getCurrentPlayerLocation())) {
      showPlayerDescription();
    }
    checkIfGameIsOver();
  }

  private void showPlayerDescription() {
    CommandInterface showPlayerDescription = new ShowPlayerDescription();
    showPlayerDescription.execute(model, view);
  }

  private void computerTurn() {
    view.showDialogMessage("Computer Player, Made A Move!");
    checkIfGameIsOver();
  }

  @Override
  public void attackTarget() {
    CommandInterface attackTargetCommand = new AttackTargetCommand();
    attackTargetCommand.execute(model, view);
    checkIfGameIsOver();
  }

  @Override
  public void pickAnItem() {
    CommandInterface pickAnItemCommand = new PickAnItemCommand();
    pickAnItemCommand.execute(model, view);
    checkIfGameIsOver();
  }

  @Override
  public void lookAround() {
    CommandInterface lookAroundCommand = new LookAroundCommand();
    lookAroundCommand.execute(model, view);
    checkIfGameIsOver();
  }

  @Override
  public void movePet() {
    CommandInterface movePetCommand = new MovePetCommand();
    movePetCommand.execute(model, view);
    checkIfGameIsOver();
  }

  private void updateView() {
    model.createWorldMap();
    view.refresh();
    StringBuilder builder = new StringBuilder(model.getHint());
    builder.append(model.getTurn(false, false));
    builder.append("</b></html>");
    view.showCurrentPlayerTurn(builder.toString());
  }
}

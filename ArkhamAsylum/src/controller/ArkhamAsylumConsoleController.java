package controller;

import controller.commands.AddPlayer;
import controller.commands.AttackTargetCharacterCommand;
import controller.commands.CreateWorldMap;
import controller.commands.DisplayInfoOfCurrentPlayer;
import controller.commands.DisplaySpecificSpaceInformation;
import controller.commands.DisplayWorldInfo;
import controller.commands.LookAround;
import controller.commands.MovePetCommand;
import controller.commands.MovePlayer;
import controller.commands.PickUpItemCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import model.world.ArkhamAsylumWorld;

/**
 * Main console controller class which initiates the call the command classes.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class ArkhamAsylumConsoleController implements ArkhamAsylumController {

  private final Scanner inputScanner;
  private final Appendable outputAppendable;
  private final Map<String, Function<Scanner, ArkhamAsylumGameCommand>> gameCommandBeforeStart;
  private final Map<String, Function<Scanner, ArkhamAsylumGameCommand>> gameCommandAfterStart;
  private final int maxTurn;

  /**
   * Constructor class which initialises the ArkhamAsylumConsoleController attributes.
   * Checks if the attributes are correct or not and throws IllegalArgumentException.
   *
   * @param inputReadable    is used to get the data from the user.
   * @param outputAppendable is used to output the data to the user.
   * @param maxTurn          stores the maximum number of turns allowed by the players
   */
  public ArkhamAsylumConsoleController(
          Readable inputReadable,
          Appendable outputAppendable,
          int maxTurn) {
    if (inputReadable == null
            || outputAppendable == null
            || maxTurn < 0
            || maxTurn == Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }

    this.outputAppendable = outputAppendable;
    inputScanner = new Scanner(inputReadable);
    this.maxTurn = maxTurn;

    gameCommandBeforeStart = new HashMap<>();
    gameCommandAfterStart = new HashMap<>();

    // Hashmap before start of the game.
    // Add players in the room.
    gameCommandBeforeStart.put("1", s -> new AddPlayer(inputScanner, outputAppendable));

    // Create world map:
    gameCommandBeforeStart.put("2", s -> new CreateWorldMap(outputAppendable));

    // Hashmap after the start of the game:

    // Move player to the neighbouring room
    gameCommandAfterStart.put("1", s -> new MovePlayer(inputScanner, outputAppendable));

    // Look Around:
    gameCommandAfterStart.put("2", s -> new LookAround(outputAppendable));

    // Pick an item:
    gameCommandAfterStart.put("3", s -> new PickUpItemCommand(inputScanner, outputAppendable));

    // Move Pet:
    gameCommandAfterStart.put("4", s -> new MovePetCommand(inputScanner, outputAppendable));

    // Attack target character:
    gameCommandAfterStart.put("5", s -> new AttackTargetCharacterCommand(
            inputScanner,
            outputAppendable)
    );

    // World information:
    gameCommandAfterStart.put("6", s -> new DisplayWorldInfo(outputAppendable));

    // Display player information:
    gameCommandAfterStart.put("7", s -> new DisplayInfoOfCurrentPlayer(outputAppendable));

    gameCommandAfterStart.put("8", s -> new DisplaySpecificSpaceInformation(
            inputScanner,
            outputAppendable
    ));
  }

  @Override
  public void playArkhamGame(ArkhamAsylumWorld model) {

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    StringBuilder gameSetupCommandBuilder = new StringBuilder();
    gameSetupCommandBuilder.append("Please enter from one of the following commands:\n");
    gameSetupCommandBuilder.append("Press 1. to add player.\n");
    gameSetupCommandBuilder.append("Press 2. to create world map.\n");
    gameSetupCommandBuilder.append("Press 3. to start game.\n");

    try {

      outputAppendable.append(gameSetupCommandBuilder.toString());
      while (inputScanner.hasNext()) {

        ArkhamAsylumGameCommand arkhamAsylumGameCommand;
        String in = inputScanner.nextLine();

        if ("q".equalsIgnoreCase(in.trim()) || "quit".equalsIgnoreCase(in.trim())) {
          return;
        }

        // Check if there is any player in the world before starting the game:
        if ("3".equalsIgnoreCase(in)) {
          if (model.getNumberOfHumanPlayer() > 0) {
            startGame(model);
          } else {
            outputAppendable.append("Please add at least one human player\n");
            outputAppendable.append(gameSetupCommandBuilder.toString());
          }
        }

        Function<Scanner,
                ArkhamAsylumGameCommand> commandPrompt =
                gameCommandBeforeStart.getOrDefault(in, null);

        if (commandPrompt != null) {
          arkhamAsylumGameCommand = commandPrompt.apply(inputScanner);
          arkhamAsylumGameCommand.execute(model);
        }

        if (!"3".equalsIgnoreCase(in)) {
          outputAppendable.append(gameSetupCommandBuilder.toString());
        }
      }
    } catch (IOException io) {
      throw new IllegalArgumentException("Not able to append\n");
    }
  }

  private void startGame(ArkhamAsylumWorld model) {

    int totalNumberOfTurns = 0;

    StringBuilder gameCommandBuilder = new StringBuilder();
    gameCommandBuilder.append("Please enter from one of the following commands:\n");
    gameCommandBuilder.append("Press 1. to move player.[TURN]\n");
    gameCommandBuilder.append("Press 2. to look around. [TURN]\n");
    gameCommandBuilder.append("Press 3. to pick an item. [TURN]\n");
    gameCommandBuilder.append("Press 4. to move Pet to another location. [TURN]\n");
    gameCommandBuilder.append("Press 5. to ATTACK target. [TURN]\n");
    gameCommandBuilder.append("Press 6. to display world information.\n");
    gameCommandBuilder.append("Press 7. to display player information.\n");
    gameCommandBuilder.append("Press 8. to display specific space information\n");

    try {
      outputAppendable.append("------------- HINT ------------\n");
      outputAppendable.append(model.getHint());
      outputAppendable.append("-------------------------------\n");
      outputAppendable.append(gameCommandBuilder.toString());
      while (true) {

        if (model.isGameOver()) {
          if (!model.getWinner().isEmpty()) {
            outputAppendable.append(String.format("Player won: %s\n", model.getWinner()));
          } else {
            outputAppendable.append("Max turn reached!\n");
          }
          outputAppendable.append("------------- GAME OVER ------------\n");
          return;
        } else {
          if (model.isComputerPlayer()) {
            outputAppendable.append(String.format("%s\n", model.getTurn(false, false)));
            outputAppendable.append(gameCommandBuilder.toString());
          } else {
            outputAppendable.append(String.format("%s\n", model.getTurn(false, false)));
          }
        }

        ArkhamAsylumGameCommand arkhamAsylumGameCommand;

        String in = "";
        if (!model.isComputerPlayer()) {
          in = inputScanner.nextLine();
        }


        if ("q".equalsIgnoreCase(in) || "quit".equalsIgnoreCase(in)) {
          return;
        }

        Function<Scanner,
                ArkhamAsylumGameCommand> commandPrompt =
                gameCommandAfterStart.getOrDefault(in, null);

        if (commandPrompt != null) {
          arkhamAsylumGameCommand = commandPrompt.apply(inputScanner);
          arkhamAsylumGameCommand.execute(model);
        }

        if ("1".equalsIgnoreCase(in)
                || "2".equalsIgnoreCase(in)
                || "3".equalsIgnoreCase(in)) {
          totalNumberOfTurns++;
        }

        outputAppendable.append("------------- HINT ------------\n");
        outputAppendable.append(model.getHint());
        outputAppendable.append("-------------------------------\n");
        outputAppendable.append(gameCommandBuilder.toString());
      }
    } catch (IOException io) {
      throw new IllegalArgumentException("Not able to append\n");
    }
  }
}

package controller.commands;

import controller.ArkhamAsylumGameCommand;
import java.io.IOException;
import java.util.Scanner;
import model.world.ArkhamAsylumWorld;

/**
 * Command class to add different players in the game.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class AddPlayer implements ArkhamAsylumGameCommand {

  private final Scanner in;
  private final Appendable outputAppendable;

  /**
   * Constructor class which initialises the AddPlayer attributes.
   *
   * @param inputScanner     is used to get the data from the user.
   * @param outputAppendable is used to output the data to the user.
   */
  public AddPlayer(Scanner inputScanner, Appendable outputAppendable) {

    if (inputScanner == null || outputAppendable == null) {
      throw new IllegalArgumentException("Scanner or outputAppendable cannot be null");
    }

    in = inputScanner;
    this.outputAppendable = outputAppendable;
  }

  @Override
  public void execute(ArkhamAsylumWorld model) {
    if (model == null) {
      throw new IllegalArgumentException("ArkhamAsylumWorld model cannot be null");
    }

    boolean isValidArguments = false;

    while (!isValidArguments) {
      int maximumNumberOfItemsInteger = 0;
      int roomLocationInteger = 0;
      boolean isComputerPlayer = false;

      try {
        outputAppendable.append("Is this a computer player (Y/N):\n");
        String isComputerPlayerString = "";

        while (true) {
          isComputerPlayerString = in.nextLine();
          if ("y".trim().equalsIgnoreCase(isComputerPlayerString.trim())) {
            isComputerPlayer = true;
            break;
          } else if ("n".trim().equalsIgnoreCase(isComputerPlayerString.trim())) {
            isComputerPlayer = false;
            break;
          } else {
            outputAppendable.append("Please enter either Y/N");
          }
        }

        outputAppendable.append("Please enter the name of the player: \n");
        String playerName = in.nextLine();

        outputAppendable.append("Please enter the room location: \n");
        String roomLocation = "";
        roomLocation = in.nextLine();

        outputAppendable.append("Specify the maximum number of the items: \n");
        String maximumNumberOfItems = "";

        while (true) {
          try {
            maximumNumberOfItems = in.nextLine();
            maximumNumberOfItemsInteger = Integer.parseInt(maximumNumberOfItems);
            break;
          } catch (NumberFormatException ne) {
            outputAppendable.append(
                    String.format("Please enter a valid format limit: %s\n",
                            maximumNumberOfItems));
          }
        }

        // Model Add player:
        try {
          model.addPlayer(
                  playerName,
                  roomLocation,
                  maximumNumberOfItemsInteger,
                  isComputerPlayer
          );

          isValidArguments = true;
          outputAppendable.append("Player Added!\n");
          break;
        } catch (IllegalArgumentException is) {
          outputAppendable.append("Unable to add a player. Please check parameters.\n");
        }
      } catch (IOException io) {
        throw new IllegalArgumentException("Not able to append\n");
      }
    }
  }
}

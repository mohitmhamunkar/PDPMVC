package controller.commands;

import controller.ArkhamAsylumGameCommand;
import java.io.IOException;
import java.util.Scanner;
import model.world.ArkhamAsylumWorld;

/**
 * This command class is used to command the model to Move to the neighbouring space.
 */
public class MovePlayer implements ArkhamAsylumGameCommand {

  private final Scanner in;
  private final Appendable outputAppendable;

  /**
   * Constructor of the MovePlayer to initialise appendable.
   *
   * @param outputAppendable is used to store and show the message to the users.
   * @param inputScanner     is used to get the information from input stream
   */
  public MovePlayer(Scanner inputScanner, Appendable outputAppendable) {

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

      try {
        outputAppendable.append("Please enter the neighbouring space name: \n");

        String roomLocationString = "";

        roomLocationString = in.nextLine();

        try {
          model.movePlayerToNeighboringRoom(roomLocationString);
          isValidArguments = true;
          outputAppendable.append("Player successfully moved\n");
          break;
        } catch (IllegalArgumentException ia) {
          outputAppendable.append("Please enter a valid neighbour:\n");
        }
      } catch (IOException io) {
        throw new IllegalArgumentException("Not able to append\n");
      }
    }
  }
}

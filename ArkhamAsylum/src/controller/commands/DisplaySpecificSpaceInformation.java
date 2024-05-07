package controller.commands;

import controller.ArkhamAsylumGameCommand;
import java.io.IOException;
import java.util.Scanner;
import model.world.ArkhamAsylumWorld;

/**
 * This command class is used to command the model to Move to pick an item.
 */
public class DisplaySpecificSpaceInformation implements ArkhamAsylumGameCommand {

  private final Scanner in;
  private final Appendable outputAppendable;

  /**
   * Constructor class which initialises the PickUpItemCommand attributes.
   *
   * @param inputScanner     is used to get the data from the user.
   * @param outputAppendable is used to output the data to the user.
   */
  public DisplaySpecificSpaceInformation(Scanner inputScanner, Appendable outputAppendable) {

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
        outputAppendable.append("Please enter the valid space name: \n");

        String specificSpaceInformaiton = in.nextLine();

        try {
          String specificSpace = model.getSpaceInformation(specificSpaceInformaiton);
          outputAppendable.append(specificSpace);
          outputAppendable.append("\n");
          isValidArguments = true;
          break;
        } catch (IllegalArgumentException ia) {
          outputAppendable.append("Please enter valid Space name:\n");
        }

      } catch (IOException io) {
        throw new IllegalArgumentException("Not able to append\n");
      }
    }

  }
}

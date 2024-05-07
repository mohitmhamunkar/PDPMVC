package controller.commands;

import controller.ArkhamAsylumGameCommand;
import java.io.IOException;
import java.util.Scanner;
import model.world.ArkhamAsylumWorld;

/**
 * This command class is used to command the model to attack the target.
 */
public class AttackTargetCharacterCommand implements ArkhamAsylumGameCommand {
  private final Scanner in;
  private final Appendable outputAppendable;

  /**
   * Constructor class which initialises the MovePetCommand attributes.
   *
   * @param inputScanner     is used to get the data from the user.
   * @param outputAppendable is used to output the data to the user.
   */
  public AttackTargetCharacterCommand(Scanner inputScanner, Appendable outputAppendable) {

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
        outputAppendable.append("Please enter the item to attack: \n");

        String itemName = "";

        itemName = in.nextLine();

        try {
          if (model.attackTarget(itemName)) {
            isValidArguments = true;
            outputAppendable.append("Attack was successful.\n");
          } else {
            outputAppendable.append("Move failed. Someone is watching you! -__-\n");
            isValidArguments = true;
          }
          break;
        } catch (IllegalArgumentException ia) {
          outputAppendable.append(
                  "Please enter a valid item Name or check if Target is in the same space:\n"
          );
        }
      } catch (IOException io) {
        throw new IllegalArgumentException("Not able to append\n");
      }
    }
  }
}

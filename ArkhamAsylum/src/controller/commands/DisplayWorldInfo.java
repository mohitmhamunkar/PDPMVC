package controller.commands;

import controller.ArkhamAsylumGameCommand;
import java.io.IOException;
import model.world.ArkhamAsylumWorld;

/**
 * This command class is used to command the model to display all the information of the world.
 */
public class DisplayWorldInfo implements ArkhamAsylumGameCommand {

  private final Appendable outputAppendable;

  /**
   * Constructor of the DisplayWorldInfo to initialise appendable.
   *
   * @param outputAppendable is used to store and show the message to the users.
   */
  public DisplayWorldInfo(Appendable outputAppendable) {

    if (outputAppendable == null) {
      throw new IllegalArgumentException("Scanner or outputAppendable cannot be null");
    }

    this.outputAppendable = outputAppendable;
  }

  @Override
  public void execute(ArkhamAsylumWorld model) {
    if (model == null) {
      throw new IllegalArgumentException("ArkhamAsylumWorld model cannot be null");
    }

    try {
      outputAppendable.append(model.getWorldInformation());
      outputAppendable.append("\n");
    } catch (IOException io) {
      throw new IllegalArgumentException("Not able to append\n");
    }
  }
}

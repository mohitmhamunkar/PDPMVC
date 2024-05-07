package controller.commands;

import controller.ArkhamAsylumGameCommand;
import java.io.IOException;
import model.world.ArkhamAsylumWorld;

/**
 * This command class is used to command the model to look around the current player.
 */
public class LookAround implements ArkhamAsylumGameCommand {

  private final Appendable outputAppendable;

  /**
   * Constructor of the LookAround to initialise appendable.
   *
   * @param outputAppendable is used to store and show the message to the users.
   */
  public LookAround(Appendable outputAppendable) {

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
      try {
        String neighbouringInformation = model.lookAround();
        outputAppendable.append(neighbouringInformation);
        outputAppendable.append("\n");
      } catch (IllegalArgumentException ia) {
        outputAppendable.append("Something went wrong");
      }
    } catch (IOException io) {
      throw new IllegalArgumentException("Unable to append");
    }
  }
}

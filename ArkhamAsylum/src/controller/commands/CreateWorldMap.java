package controller.commands;

import controller.ArkhamAsylumGameCommand;
import java.io.IOException;
import model.world.ArkhamAsylumWorld;

/**
 * This command is used to tell the model to create the world map.
 */
public class CreateWorldMap implements ArkhamAsylumGameCommand {

  private final Appendable outputAppendable;

  /**
   * Constructor of the createWorldMap to initialise appendable.
   *
   * @param outputAppendable is used to store and show the message to the users.
   */
  public CreateWorldMap(Appendable outputAppendable) {

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
      model.createWorldMap();
      outputAppendable.append("World map created!\n");
    } catch (IOException io) {
      throw new IllegalArgumentException("Not able to append\n");
    }
  }
}

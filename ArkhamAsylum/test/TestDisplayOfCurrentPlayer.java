import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.DisplayInfoOfCurrentPlayer;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;


/**
 * Test Class to test the Display current player command.
 * It tests if the command is being passed correctly or not.
 */
public class TestDisplayOfCurrentPlayer {

  /**
   * Test function to test the Display current player command.
   * It tests if the command is being passed correctly or not.
   */
  @Test
  public void testDisplayOfCurrentPlayer() {
    StringBuffer out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 4);

    ArkhamAsylumGameCommand controller = new DisplayInfoOfCurrentPlayer(out);
    controller.execute(model);

    assertEquals("UniqueCode: 1234321 Display Info Current player invoked!\n", log.toString());
    assertEquals("1234321\n", out.toString());
  }
}


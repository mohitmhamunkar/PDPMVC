import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.LookAround;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * Test class to check Look Around command.
 * If Pick up player command is sending the correct parameters to Model.
 */
public class TestLookAroundCommand {

  /**
   * Test function to check if the command is passed correctly or not.
   */
  @Test
  public void testLookAroundCommand() {
    StringBuffer out = new StringBuffer();

    StringBuilder lookAround = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(lookAround, 11221, 4);

    ArkhamAsylumGameCommand controller = new LookAround(out);
    controller.execute(model);

    assertEquals("UniqueCode: 11221 LookAround method invoked!\n", lookAround.toString());
    assertEquals("LookAround method invoked! 11221\n", out.toString());
  }
}


import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.DisplayWorldInfo;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * Test class to check World Info command.
 * If world info command is sending the correct parameters to Model.
 */
public class TestDisplayWorldInfo {

  /**
   * Test function to check World Info command.
   * If world info command is sending the correct parameters to Model.
   */
  @Test
  public void testDisplayWorldInfo() {
    StringBuffer out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 4);

    ArkhamAsylumGameCommand controller = new DisplayWorldInfo(out);
    controller.execute(model);

    assertEquals("UniqueCode: 1234321 Display Info of world invoked!\n", log.toString());
    assertEquals("1234321\n", out.toString());
  }
}


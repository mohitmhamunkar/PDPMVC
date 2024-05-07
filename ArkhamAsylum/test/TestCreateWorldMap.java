import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.CreateWorldMap;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * Test class to test the create world map command.
 */
public class TestCreateWorldMap {

  /**
   * Test function to test the Create wprld map command.
   * It tests if the command is being passed correctly or not.
   */
  @Test
  public void testCreateWorldMap() {
    StringBuffer out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 5);

    ArkhamAsylumGameCommand controller = new CreateWorldMap(out);
    controller.execute(model);

    StringBuffer expectedStringBuffer = new StringBuffer();

    expectedStringBuffer.append("World map created!\n");

    assertEquals("UniqueCode: 1234321 Create world map invoked!\n", log.toString());
    assertEquals(expectedStringBuffer.toString(), out.toString());
  }
}

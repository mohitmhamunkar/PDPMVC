import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.DisplayWorldInfo;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * Test class of the testDisplayInfoWorld.
 * It checks if the parameters and function is passed and invoked.
 */
public class TestDisplayInfoOfWorld {

  /**
   * Test display of world to check if the function is being invoked properly or not.
   */
  @Test
  public void testDisplayInfoOfWorld() {
    StringBuffer out = new StringBuffer();

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 4);

    ArkhamAsylumGameCommand controller = new DisplayWorldInfo(out);
    controller.execute(model);

    assertEquals("UniqueCode: 1234321 Display Info of world invoked!\n", log.toString());
    assertEquals("1234321\n", out.toString());
  }
}


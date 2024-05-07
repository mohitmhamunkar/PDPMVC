import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.PickUpItemCommand;
import java.util.Scanner;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * Test class to check Pick up player command.
 * If Pick up player command is sending the correct parameters to Model.
 */
public class TestPickUpItemPlayer {

  /**
   * Test function to check if the command is passed correctly or not.
   */
  @Test
  public void testPickUpItemPlayer() {
    StringBuffer out = new StringBuffer();
    Scanner in = new Scanner("Pistol");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 44533, 4);

    ArkhamAsylumGameCommand controller = new PickUpItemCommand(in, out);
    controller.execute(model);

    StringBuffer expectedStringBuffer = new StringBuffer();

    expectedStringBuffer.append("Please enter the item to be picked: \n");
    expectedStringBuffer.append("Item picked by the player successfully.\n");

    assertEquals("Uniquecode: 44533 Input: Pistol\n", log.toString());
    assertEquals(expectedStringBuffer.toString(), out.toString());
  }
}


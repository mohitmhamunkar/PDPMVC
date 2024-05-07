import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.MovePlayer;
import java.util.Scanner;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * Test class to check Move player command.
 * If move player command is sending the correct parameters to Model.
 */
public class TestMovePlayer {

  /**
   * Test if move player command is sending the correct parameters to Model.
   */
  @Test
  public void testMovePlayer() {
    StringBuffer out = new StringBuffer();
    Scanner in = new Scanner("Kitchen\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 4);

    ArkhamAsylumGameCommand controller = new MovePlayer(in, out);
    controller.execute(model);

    StringBuffer expectedStringBuffer = new StringBuffer();

    expectedStringBuffer.append("Please enter the neighbouring space name: \n");
    expectedStringBuffer.append("Player successfully moved\n");

    assertEquals("Uniquecode: 1234321 Input: Kitchen\n", log.toString());
    assertEquals(expectedStringBuffer.toString(), out.toString());
  }
}

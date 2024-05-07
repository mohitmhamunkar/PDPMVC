import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.MovePetCommand;
import java.util.Scanner;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * Test class to check Move pet command.
 * If move pet command is sending the correct parameters to Model.
 */
public class TestMovePet {

  /**
   * Test if move pet command is sending the correct parameters to Model.
   */
  @Test
  public void testMovePet() {
    StringBuffer out = new StringBuffer();
    Scanner in = new Scanner("Kitchen\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 4);

    ArkhamAsylumGameCommand controller = new MovePetCommand(in, out);
    controller.execute(model);

    StringBuffer expectedStringBuffer = new StringBuffer();

    expectedStringBuffer.append("Please enter the space name: \n");
    expectedStringBuffer.append("Pet successfully moved\n");

    assertEquals("Uniquecode: 1234321 Input: Kitchen\n", log.toString());
    assertEquals(expectedStringBuffer.toString(), out.toString());
  }
}

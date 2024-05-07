import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.AttackTargetCharacterCommand;
import java.util.Scanner;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * Test class to check Move pet command.
 * If move pet command is sending the correct parameters to Model.
 */
public class TestAttackTarget {

  /**
   * Test if move pet command is sending the correct parameters to Model.
   */
  @Test
  public void testAttackTarget() {
    StringBuffer out = new StringBuffer();
    Scanner in = new Scanner("Pan\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 4);

    ArkhamAsylumGameCommand controller = new AttackTargetCharacterCommand(in, out);
    controller.execute(model);

    StringBuffer expectedStringBuffer = new StringBuffer();

    expectedStringBuffer.append("Please enter the item to attack: \n");
    expectedStringBuffer.append("Attack was successful.\n");

    assertEquals("UniqueCode: 1234321attack Target function invoked!\n", log.toString());
    assertEquals(expectedStringBuffer.toString(), out.toString());
  }
}

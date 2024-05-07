import static org.junit.Assert.assertEquals;

import controller.ArkhamAsylumGameCommand;
import controller.commands.AddPlayer;
import java.util.Scanner;
import model.world.ArkhamAsylumWorld;
import org.junit.Test;

/**
 * This class is used to test add player functionality.
 * Tests if the given player computer or human is being added successfully or not.
 */
public class TestAddPlayer {

  /**
   * Tests if the given player human is being added successfully or not.
   */
  @Test
  public void testAddHumanPlayer() {
    StringBuffer out = new StringBuffer();
    Scanner in = new Scanner("N\nSUBHANKAR\ngarden\n19\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 3);

    ArkhamAsylumGameCommand controller = new AddPlayer(in, out);
    controller.execute(model);

    StringBuffer expectedStringBuffer = new StringBuffer();

    expectedStringBuffer.append("Is this a computer player (Y/N):\n");
    expectedStringBuffer.append("Please enter the name of the player: \n");
    expectedStringBuffer.append("Please enter the room location: \n");
    expectedStringBuffer.append("Specify the maximum number of the items: \n");
    expectedStringBuffer.append("Player Added!\n");

    assertEquals("UniqueCode: 1234321 Input: SUBHANKAR garden 19 false\n", log.toString());
    assertEquals(expectedStringBuffer.toString(), out.toString());
  }

  /**
   * Tests if the given player human is being added successfully or not.
   */
  @Test
  public void testAddComputerPlayer() {
    StringBuffer out = new StringBuffer();
    Scanner in = new Scanner("Y\nSUBHANKAR\ngarden\n19\n");

    StringBuilder log = new StringBuilder();
    ArkhamAsylumWorld model = new MockModel(log, 1234321, 3);

    ArkhamAsylumGameCommand controller = new AddPlayer(in, out);
    controller.execute(model);

    StringBuffer expectedStringBuffer = new StringBuffer();

    expectedStringBuffer.append("Is this a computer player (Y/N):\n");
    expectedStringBuffer.append("Please enter the name of the player: \n");
    expectedStringBuffer.append("Please enter the room location: \n");
    expectedStringBuffer.append("Specify the maximum number of the items: \n");
    expectedStringBuffer.append("Player Added!\n");

    assertEquals("UniqueCode: 1234321 Input: SUBHANKAR garden 19 true\n", log.toString());
    assertEquals(expectedStringBuffer.toString(), out.toString());
  }
}


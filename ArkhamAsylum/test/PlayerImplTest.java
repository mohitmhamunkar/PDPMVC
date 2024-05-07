import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.item.Weapon;
import model.player.Player;
import model.player.PlayerImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to test the various functionality of the Player implementation.
 */
public class PlayerImplTest {

  private Player player;

  /**
   * Setup code to initialise the player object.
   */
  @Before
  public void setUp() {
    player = new PlayerImpl(
            "Jack",
            "Kitchen",
            22,
            false,
            "player1"
    );
  }

  @Test
  public void testGetRoomId() {
    assertEquals(player.getRoomName(), "Kitchen");
  }

  @Test
  public void testGetPlayerName() {
    String expectedName = "Jack";
    assertEquals(expectedName, player.getPlayerName());
  }

  @Test
  public void testMoveCharacterToSpace() {
    player.moveCharacterToSpace("Kitchen");
    assertEquals("Kitchen", player.getPlayerRoomLocation());
  }

  @Test
  public void testStartingPositionOfPlayer() {
    player = new PlayerImpl(
            "Jack",
            "Kitchen",
            1,
            false,
            "player1"
    );

    assertEquals("Kitchen", player.getPlayerRoomLocation());
  }

  @Test
  public void testPickUpItemMoreThanMaximum() {
    player = new PlayerImpl(
            "Jack",
            "Kitchen",
            1,
            false,
            "player1"
    );

    // Try to pick up the first item.
    boolean isItemPickedUp = player.pickAnItem(new Weapon(1,
            100,
            "Gun"));

    assertTrue(isItemPickedUp);

    // Try to pick up the second item.
    isItemPickedUp = player.pickAnItem(new Weapon(1,
            100,
            "Revolver"));

    assertFalse(isItemPickedUp);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testPickUpItemMoreThanZero() {
    player = new PlayerImpl(
            "Jack",
            "Kitchen",
            0,
            false,
            "player1"
    );

    // Try to pick up the first item.
    boolean isItemPickedUp = player.pickAnItem(new Weapon(1,
            100,
            "Gun"));

    assertFalse(isItemPickedUp);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPickUpItemLessThanZero() {
    player = new PlayerImpl(
            "Jack",
            "Kitchen",
            -1,
            false,
            "player1"
    );

    // Try to pick up the first item.
    boolean isItemPickedUp = player.pickAnItem(new Weapon(1,
            100,
            "Gun"));

    assertFalse(isItemPickedUp);
  }

  @Test
  public void testToString() {
    StringBuilder expectedPlayerString = new StringBuilder();

    expectedPlayerString.append("Name of the player :Jack\n");
    expectedPlayerString.append("Location id of the current player:Kitchen\n");
    expectedPlayerString.append("Items with the player:[]");

    assertEquals(player.toString(), expectedPlayerString.toString());
  }
}
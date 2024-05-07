import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import model.item.Item;
import model.item.Weapon;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to test different functionality of Weapon class.
 */
public class WeaponTest {

  private Item weapon;

  /**
   * Initializes the weapon object class with the default values.
   */
  @Before
  public void setUp() {
    weapon = new Weapon(1, 100, "hammer");
  }

  /**
   * Test for invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    // Invalid roomId:
    Item weapon = new Weapon(-1, 1000, "Hammer");
    weapon.toString();

    // Invalid damageValue
    weapon = new Weapon(1, -1000, "Hammer");
    weapon.toString();

    // Invalid damageValue by keeping it infinite
    weapon = new Weapon(1, Integer.MAX_VALUE, "Hammer");
    weapon.toString();
  }

  /**
   * Test we are getting the same room Id.
   */
  @Test
  public void getRoomId() {
    int expectedRoomValue = 1;
    assertEquals(expectedRoomValue, weapon.getRoomId());

    int notExpectedValue = 2;
    assertNotEquals(notExpectedValue, weapon.getRoomId());
  }

  /**
   * Test for name of the items.
   */
  @Test
  public void getItemName() {
    String expectedName = "hammer";
    assertEquals(expectedName, weapon.getItemName());

    String notExpectedName = "knife";
    assertNotEquals(notExpectedName, weapon.getItemName());
  }

  /**
   * Test for damage value fo the item.
   */
  @Test
  public void getDamageValue() {
    int expectedDamageValue = 100;
    assertEquals(expectedDamageValue, weapon.getDamageValue());

    int notExpectedDamageValue = 101;
    assertNotEquals(notExpectedDamageValue, weapon.getDamageValue());
  }

  /**
   * Test if we are getting required string in formatted.
   */
  @Test
  public void testToString() {
    // 1. test for valid string
    Item weapon1 = new Weapon(1, 100, "hammer");
    String expectedStringValue =
            "Weapon Name: hammer. Damage value: 100. Item can be used in the room id: 1.";
    assertEquals(weapon1.toString(), expectedStringValue);

    // 2. test for valid string
    Item weapon2 = new Weapon(1, 11, "stone");
    String expectedStringValue2 =
            "Weapon Name: stone. Damage value: 11. Item can be used in the room id: 1.";
    assertEquals(weapon2.toString(), expectedStringValue2);

    // 3. test for invalid string
    Item weapon3 = new Weapon(1, 11, "stone");
    String expectedStringValue3 =
            "Weapon Name: water. Damage value: 11. Item can be used in the room id: 1.";
    assertNotEquals(weapon3.toString(), expectedStringValue3);
  }

  /**
   * Test if we equals method is returning true for same values.
   * And returning false if the object values are not same.
   */
  @Test
  public void testEquals() {
    // Test for same object class.
    Item weapon1 = new Weapon(1, 100, "hammer");
    assertTrue(weapon1.equals(weapon1));

    // Test for same values but different objects.
    Item weapon2 = new Weapon(1, 100, "hammer");
    assertTrue(weapon1.equals(weapon2));

    // Test for different vales of two objects.
    Item weapon3 = new Weapon(2, 100, "hammer");
    assertFalse(weapon1.equals(weapon3));
  }

  /**
   * Test if hash code is same for 2 equal objects.
   */
  @Test
  public void testHashCode() {

    // Test for same hash codes.
    int damageValue = 100;
    int roomId = 1;
    String itemName = "hammer";
    assertEquals(weapon.hashCode(),
            Objects.hash(itemName, damageValue, roomId));

    // Test for not same hash codes.
    damageValue = 101;
    assertNotEquals(weapon.hashCode(),
            Objects.hash(itemName, damageValue, roomId));
  }
}
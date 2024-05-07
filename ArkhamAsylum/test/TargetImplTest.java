import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import model.target.CharacterType;
import model.target.Target;
import model.target.TargetImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test target implementation in this class.
 */
public class TargetImplTest {

  private Target target;

  /**
   * Initialises target object class.
   */
  @Before
  public void setUp() {

    target = new TargetImpl.TargetBuilder()
            .targetHealth(50)
            .targetName("Dr Lucky")
            .totalNumberOfSpaces(21)
            .build();
  }

  /**
   * Test for player type which is TARGET.
   */
  @Test
  public void testGetPlayerType() {
    // Check for equal character type
    CharacterType expectedCharacterType = CharacterType.TARGET;
    assertEquals(expectedCharacterType, target.getPlayerType());

    // Check for not equal character type
    expectedCharacterType = CharacterType.PLAYER;
    assertNotEquals(expectedCharacterType, target.getPlayerType());
  }

  /**
   * Move the target character around and test for target location.
   */
  @Test
  public void testGetTargetLocation() {
    // Initially the target character will be at 0 roomId.
    int expectedValue = 0;
    assertEquals(expectedValue, target.getTargetLocation());

    // Move the target character to room 1.
    TargetImpl targetLocalObject = new TargetImpl(50, "Dr lucky", 21);
    targetLocalObject.moveCharacter();

    expectedValue = 1;
    assertEquals(expectedValue, targetLocalObject.getTargetLocation());

    // Move the target character to room 2.
    targetLocalObject.moveCharacter();
    expectedValue = 2;
    assertEquals(expectedValue, targetLocalObject.getTargetLocation());

    // Move the character to the last space and
    // then move it back to the starting point.
    for (int i = 2; i < 22; i++) {
      targetLocalObject.moveCharacter();
    }

    expectedValue = 0;
    assertEquals(expectedValue, targetLocalObject.getTargetLocation());
  }

  /**
   * Test for decreasing target health.
   */
  @Test
  public void testDecreaseTargetHealth() {
    // Decrease target character's health by 10 points.
    target.decreaseTargetHealth(10);

    int expectedValue = 40;
    assertEquals(expectedValue, target.getTargetHealth());

    // Decrease target character's health by 20 points.
    target.decreaseTargetHealth(20);

    expectedValue = 20;
    assertEquals(expectedValue, target.getTargetHealth());
  }


  /**
   * Test for target health after decreasing the value.
   */
  @Test
  public void testGetTargetHealth() {
    Target newTarget = new TargetImpl.TargetBuilder()
            .targetHealth(50)
            .targetName("Dr Lucky")
            .totalNumberOfSpaces(21)
            .build();

    // Decrease target character's health by 10 points.
    newTarget.decreaseTargetHealth(10);

    int expectedValue = 40;
    assertEquals(expectedValue, newTarget.getTargetHealth());

    // Decrease target character's health by 20 points.
    newTarget.decreaseTargetHealth(20);

    expectedValue = 20;
    assertEquals(expectedValue, newTarget.getTargetHealth());
  }

  /**
   * Test for the name of the target.
   */
  @Test
  public void testGetTargetName() {
    String expectedValue = "Dr Lucky";
    assertEquals(expectedValue, target.getTargetName());
  }

  /**
   * Test for the movement of the target character.
   */
  @Test
  public void testMoveCharacter() {
    // Move the target character to room 1.
    TargetImpl targetLocalObject = new TargetImpl(50, "Dr lucky", 21);
    targetLocalObject.moveCharacter();

    int expectedValue = 1;
    assertEquals(expectedValue, targetLocalObject.getTargetLocation());

    // Move the target character to room 2.
    targetLocalObject.moveCharacter();
    expectedValue = 2;
    assertEquals(expectedValue, targetLocalObject.getTargetLocation());

    // Move the character to the last space and
    // then move it back to the starting point.
    for (int i = 2; i < 22; i++) {
      targetLocalObject.moveCharacter();
    }

    expectedValue = 0;
    assertEquals(expectedValue, targetLocalObject.getTargetLocation());
  }

  /**
   * Test of toString with the required class details.
   */
  @Test
  public void testToString() {
    String expectedValue = "Target Name: Dr Lucky. Target health: 50. Target location: 0.";
    assertEquals(expectedValue, target.toString());

    expectedValue = "Target Name: Subhankar. Target health: 50. Target location: 0.";
    assertNotEquals(expectedValue, target.toString());
  }

  /**
   * Test if we equals method is returning true for same values.
   * And returning false if the object values are not same.
   */
  @Test
  public void testEquals() {

    int healthValue = 100;
    String targetName = "Dr Lucky";
    int totalNumberOfSpaces = 21;

    // Test for same object class.
    Target objectTarget = new TargetImpl(healthValue, targetName, totalNumberOfSpaces);
    assertTrue(objectTarget.equals(objectTarget));

    // Test for same values but different objects.
    int healthValue1 = 100;
    String targetName1 = "Dr Lucky";
    int totalNumberOfSpaces1 = 21;
    Target objectTarget1 = new TargetImpl(healthValue1, targetName1, totalNumberOfSpaces1);

    assertTrue(objectTarget.equals(objectTarget1));

    // Test for different vales of two objects.

    // Test for same values but different objects.
    healthValue1 = 101;

    Target objectTarget2 = new TargetImpl(healthValue1, targetName1, totalNumberOfSpaces1);
    assertFalse(objectTarget.equals(objectTarget2));
  }

  /**
   * Test if hash code is same for 2 equal objects.
   */
  @Test
  public void testhashCode() {
    // Test for same hash codes.
    int healthValue = 100;
    String targetName = "Dr Lucky";
    int totalNumberOfSpaces = 21;

    Target objectTarget = new TargetImpl(healthValue, targetName, totalNumberOfSpaces);

    assertEquals(objectTarget.hashCode(),
            Objects.hash(targetName, healthValue, CharacterType.TARGET));

    // Test for not same hash codes.
    healthValue = 101;
    assertNotEquals(objectTarget.hashCode(),
            Objects.hash(targetName, healthValue, CharacterType.PLAYER));
  }


}
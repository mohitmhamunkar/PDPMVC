import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import model.spaces.Space;
import model.spaces.SpaceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of SpaceImpl class with different parameters.
 */
public class SpaceImplTest {

  private Space space;

  /**
   * Initialise the space object class.
   */
  @Before
  public void setUp() {
    int spaceId = 1;
    String spaceName = "Drawing room";
    ArrayList<Integer> spaceLocation = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
    space = new SpaceImpl(spaceId, spaceName, spaceLocation);
  }

  @Test
  public void testGetSpaceId() {
    int expectedValue = 1;
    assertEquals(expectedValue, space.getSpaceId());

    int notExpectedValue = 2;
    assertNotEquals(notExpectedValue, space.getSpaceId());
  }

  @Test
  public void testGetSpaceName() {
    String expectedName = "Drawing room";
    assertEquals(expectedName, space.getSpaceName());

    String notExpectedName = "Living room";
    assertNotEquals(notExpectedName, space.getSpaceName());
  }

  @Test
  public void testGetSpaceLocation() {
    ArrayList<Integer> expectedSpaceLocation = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
    assertEquals(expectedSpaceLocation, space.getSpaceLocation());

    ArrayList<Integer> expectedSpaceLocation1 = new ArrayList<>(Arrays.asList(12, 20, 30, 40));
    assertNotEquals(expectedSpaceLocation1, space.getSpaceLocation());
  }

  @Test
  public void testToString() {
    // Test for equal string
    String expectedValue =
            "Space Name: Drawing room. Space Id: 1. Space Location [10, 20, 30, 40].";
    assertEquals(expectedValue, space.toString());

    // Test for not equal string
    String expectedValue1 =
            "Space Name: Living room. Space Id: 1. Space Location [10, 20, 30, 40].";
    assertNotEquals(expectedValue1, space.toString());
  }


  /**
   * Test if we equals method is returning true for same values.
   * And returning false if the object values are not same.
   */
  @Test
  public void testEquals() {
    int spaceId = 1;
    String spaceName = "Drawing room";
    ArrayList<Integer> spaceLocation = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
    Space spaceObject = new SpaceImpl(spaceId, spaceName, spaceLocation);

    assertTrue(spaceObject.equals(spaceObject));

    Space spaceObject1 = new SpaceImpl(spaceId, spaceName, spaceLocation);

    assertFalse(spaceObject.equals(spaceObject1));

    spaceId = 2;

    Space spaceObject2 = new SpaceImpl(spaceId, spaceName, spaceLocation);

    assertFalse(spaceObject.equals(spaceObject2));
  }

  /**
   * Test if hash code is same for 2 equal objects.
   */
  @Test
  public void testHashCode() {
    int spaceId = 1;
    String spaceName = "Drawing room";
    ArrayList<Integer> spaceLocation = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
    Space spaceObject = new SpaceImpl(spaceId, spaceName, spaceLocation);

    assertEquals(spaceObject.hashCode(), Objects.hash(
            spaceId,
            spaceLocation,
            spaceName));
  }
}
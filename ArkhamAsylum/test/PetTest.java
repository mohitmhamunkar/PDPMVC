import static org.junit.Assert.assertEquals;

import model.pet.Pet;
import model.pet.PetImpl;
import org.junit.Test;

/**
 * Test Class of the PetImpl class.
 * This class contains all the test of all the functionalities
 * of the pet class.
 */
public class PetTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithInvalidName() {
    Pet pet = new PetImpl("", 1, 200);
    pet.toString();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithInvalidSpaceLocation() {
    Pet pet = new PetImpl("BatHound", 10000, 200);
    pet.toString();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithInvalidSpaceLocationNeg() {
    Pet pet = new PetImpl("BatHound", -1, 200);
    pet.toString();
  }

  @Test
  public void testGetPetName() {
    Pet pet = new PetImpl("BatHound", 10, 200);

    assertEquals("BatHound", pet.getPetName());
  }

  @Test
  public void testStartingPetLocation() {
    Pet pet = new PetImpl("BatHound", 0, 200);

    assertEquals(0, pet.getPetLocation());
  }

  @Test
  public void testMovePetLocation() {
    Pet pet = new PetImpl("BatHound", 0, 200);

    pet.movePet(10);
    assertEquals(10, pet.getPetLocation());
  }

  @Test
  public void testToString() {
    Pet pet = new PetImpl("BatHound", 1, 200);

    String expectedString = "Name of the pet: BatHound\n"
            + "Location of the pet: 1\n";

    assertEquals(expectedString,  pet.toString());
  }

}

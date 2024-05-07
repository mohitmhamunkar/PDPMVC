package model.pet;

/**
 * Interface class of the Pet.
 * This class contains function which will be used by the Pet.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface Pet {

  /**
   * This function is used to get the current location of the pet.
   * @return int which denotes the location of the pet
   */
  int getPetLocation();

  /**
   * Move the pet to the specified location.
   * @param roomId Which denotes the room ID to be moved
   */
  void movePet(int roomId);

  /**
   * This function is used to get the name of the pet.
   * @return String value which denotes the name of the pet.
   */
  String getPetName();
}

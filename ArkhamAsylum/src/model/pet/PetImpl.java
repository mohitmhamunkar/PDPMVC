package model.pet;

/**
 * Implementation class of Pet.
 * This contains all the important functionalities of the Pet class.
 */
public class PetImpl implements Pet {

  private final String petName;
  private int petLocation;
  private final int totalNumberOfSpaces;

  /**
   * Constructor of the PetImpl to initialize the fields.
   * @param petName which is unique to the pet
   * @param petStartingLocation denotes the starting location of the pet
   * @param totalNumberOfSpaces denotes the total number of existing spaces
   */
  public PetImpl(String petName, int petStartingLocation,  int totalNumberOfSpaces) {

    // Parameter validation.
    // I am checking if the pet has the same starting location in
    // the class ArkhamAsylumGameWorldImpl. Function: isValidPet

    if (petName == null
            || petName.isEmpty()
            || totalNumberOfSpaces <= 0
            || petStartingLocation < 0
            || petStartingLocation >= totalNumberOfSpaces) {
      throw new IllegalArgumentException("Please validate the parameters of the Pet");
    }

    this.petName = petName;
    this.petLocation = petStartingLocation;
    this.totalNumberOfSpaces = totalNumberOfSpaces;
  }

  @Override
  public int getPetLocation() {
    return this.petLocation;
  }

  @Override
  public void movePet(int roomId) {

    // I have validated if the room location is valid or not.
    // This validation is my ArkhamAsylum WorldImpl class.
    if (roomId > totalNumberOfSpaces || roomId < 0) {
      throw new IllegalArgumentException("Please check your space name provided.");
    }

    this.petLocation = roomId;
  }

  @Override
  public String getPetName() {
    return this.petName;
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();

    string.append(String.format("Name of the pet: %s\n", this.petName));
    string.append(String.format("Location of the pet: %s\n", this.petLocation));

    return string.toString();
  }
}

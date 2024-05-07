package model.target;

import java.util.Objects;
import utils.Constants;

/**
 * Target class which holds the value of the target details.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class TargetImpl implements Target {
  private int totalNumberOfSpaces;
  private Integer targetHealth;
  private final String targetName;
  private int targetLocationRoomId;

  /**
   * Builder class helps in building the Target class.
   */
  public static class TargetBuilder {
    private int targetHealth;
    private String targetName;
    private int totalNumberOfSpaces;

    /**
     * Following builder design pattern to construct the target character class.
     * This constructor initializes the targetHealth.
     * @param targetHealth initial health of the target.
     * @return TargetBuilder class which is used to build the constructor with health.
     */
    public TargetBuilder targetHealth(int targetHealth) {
      if (targetHealth < 0 || targetHealth == Integer.MAX_VALUE) {
        throw new IllegalArgumentException("Target health is not valid");
      }
      // Using a builder pattern over here. Hence, targetHealth needs to be initialized here.
      this.targetHealth = targetHealth;
      return this;
    }

    /**
     * Following builder design pattern to construct the target character class.
     * This constructor initialises the totalNumberOfSpaces.
     * @param totalNumberOfSpaces initial totalNumberOfSpaces of the world.
     * @return TargetBuilder class which is used to build the constructor with totalNumberOfSpaces.
     */
    public TargetBuilder totalNumberOfSpaces(int totalNumberOfSpaces) {
      if (totalNumberOfSpaces < 0 || totalNumberOfSpaces == Integer.MAX_VALUE) {
        throw new IllegalArgumentException("Target health is not valid");
      }
      // Using a builder pattern over here. Hence, totalNumberOfSpaces needs to be initialised here.
      this.totalNumberOfSpaces = totalNumberOfSpaces;
      return this;
    }

    /**
     * Following builder design pattern to construct the target character class.
     * This constructor initialises the name of the target character.
     * @param targetName initial name of the target character.
     * @return TargetBuilder class which is used to build the constructor with totalNumberOfSpaces.
     */
    public TargetBuilder targetName(String targetName) {
      if (targetName == null || targetName.isEmpty()) {
        throw new IllegalArgumentException("Target name should be valid");
      }
      // Using a builder pattern over here. Hence, targetName needs to be initialised here.
      this.targetName = targetName;
      return this;
    }

    /**
     * Finally builds the Target class.
     *
     * @return TargetImpl class with all the parameters.
     */
    public TargetImpl build() {

      if (targetHealth < 0
              || targetHealth == Integer.MAX_VALUE
              || targetName == null
              || targetName.isEmpty()
              || totalNumberOfSpaces <= 0
              || totalNumberOfSpaces == Integer.MAX_VALUE) {
        throw new IllegalArgumentException("Target Parameters not valid");
      }

      return new TargetImpl(
              targetHealth,
              targetName,
              totalNumberOfSpaces
      );
    }
  }

  /**
   * Constructor of TargetImpl to initialise all the values.
   *
   * @param targetHealth        Health of the target
   * @param targetName          name of the target
   * @param totalNumberOfSpaces contains the total number of spaces.
   */
  public TargetImpl(
          int targetHealth,
          String targetName,
          int totalNumberOfSpaces) {

    if (targetHealth < 0
            || targetHealth == Integer.MAX_VALUE
            || targetName == null
            || targetName.isEmpty()
            || totalNumberOfSpaces <= 0
            || totalNumberOfSpaces == Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Target Parameters not valid");
    }

    this.targetHealth = targetHealth;
    this.targetName = targetName;
    this.totalNumberOfSpaces = totalNumberOfSpaces;
  }

  @Override
  public CharacterType getPlayerType() {
    return CharacterType.TARGET;
  }

  @Override
  public int getTargetLocation() {
    return targetLocationRoomId;
  }

  @Override
  public void decreaseTargetHealth(int decreaseHealth) throws IllegalArgumentException {
    targetHealth = targetHealth - decreaseHealth;
  }

  @Override
  public int getTargetHealth() {
    return this.targetHealth;
  }

  @Override
  public String getTargetName() {
    return targetName;
  }

  @Override
  public void moveCharacter() {
    targetLocationRoomId++;
    if (targetLocationRoomId > totalNumberOfSpaces) {
      targetLocationRoomId = 0;
    }
  }

  @Override
  public String toString() {
    StringBuilder targetStringBuilder = new StringBuilder();

    targetStringBuilder.append("Target Name: ");
    targetStringBuilder.append(getTargetName());
    targetStringBuilder.append(Constants.FULL_STOP);
    targetStringBuilder.append(Constants.SPACE);

    targetStringBuilder.append("Target health: ");
    targetStringBuilder.append(getTargetHealth());
    targetStringBuilder.append(Constants.FULL_STOP);
    targetStringBuilder.append(Constants.SPACE);

    targetStringBuilder.append("Target location: ");
    targetStringBuilder.append(getTargetLocation());
    targetStringBuilder.append(Constants.FULL_STOP);

    return targetStringBuilder.toString();
  }

  @Override
  public boolean equals(Object object) {

    if (this == object) {
      return true;
    }

    if (!(object instanceof TargetImpl)) {
      return false;
    }

    // If the above check is not true then we can directly cast object to AutomaticTransmission
    Target target = (Target) object;

    return target.getTargetName() == this.getTargetName()
            && target.getTargetHealth() == this.getTargetHealth()
            && target.getPlayerType() == getPlayerType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            getTargetName(),
            getTargetHealth(),
            getPlayerType());
  }
}

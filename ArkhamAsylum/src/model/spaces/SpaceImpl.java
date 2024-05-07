package model.spaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utils.Constants;

/**
 * This class contains information of Space.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class SpaceImpl implements Space {

  private final String spaceName;
  private final List<Integer> spaceLocation;
  private final int spaceId;

  /**
   * Initialise all the values of the Space class.
   *
   * @param spaceId       unique id of the individual space
   * @param spaceName     given to each space
   * @param spaceLocation 4 digits coordinates for each space
   */
  public SpaceImpl(int spaceId, String spaceName, List<Integer> spaceLocation) {

    // Validate Parameters:
    if (spaceName == null
            || spaceLocation == null
            || spaceId < 0
            || spaceName.isEmpty()
            || spaceLocation.isEmpty()
            || spaceLocation.size() > 4) {
      throw new IllegalArgumentException("Please validate parameters");
    }
    this.spaceId = spaceId;
    this.spaceName = spaceName;
    this.spaceLocation = getSpaceLocationCopy(spaceLocation);
  }

  private List<Integer> getSpaceLocationCopy(List<Integer> spaceLocationArrayList) {
    List<Integer> spaceLocationListNew = new ArrayList<>();

    for (Integer i : spaceLocationArrayList) {
      spaceLocationListNew.add(i);
    }

    return spaceLocationListNew;
  }

  @Override
  public int getSpaceId() {
    return spaceId;
  }

  @Override
  public String getSpaceName() {
    return spaceName;
  }

  @Override
  public List<Integer> getSpaceLocation() {
    return getSpaceLocationCopy(spaceLocation);
  }

  @Override
  public String toString() {
    StringBuilder spaceStringBuilder = new StringBuilder();

    spaceStringBuilder.append("Space Name: ");
    spaceStringBuilder.append(getSpaceName());
    spaceStringBuilder.append(Constants.FULL_STOP);
    spaceStringBuilder.append(Constants.SPACE);

    spaceStringBuilder.append("Space Id: ");
    spaceStringBuilder.append(getSpaceId());
    spaceStringBuilder.append(Constants.FULL_STOP);
    spaceStringBuilder.append(Constants.SPACE);

    spaceStringBuilder.append("Space Location ");
    spaceStringBuilder.append(getSpaceLocation());
    spaceStringBuilder.append(Constants.FULL_STOP);

    return spaceStringBuilder.toString();
  }

  @Override
  public boolean equals(Object object) {

    if (this == object) {
      return true;
    }

    if (!(object instanceof SpaceImpl)) {
      return false;
    }

    // If the above check is not true then we can directly cast object to AutomaticTransmission
    Space spaceObject = (Space) object;

    return spaceObject.getSpaceId() == this.getSpaceId()
            && spaceObject.getSpaceLocation() == this.getSpaceLocation()
            && spaceObject.getSpaceName() == getSpaceName();
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            getSpaceId(),
            getSpaceLocation(),
            getSpaceName()
    );
  }
}
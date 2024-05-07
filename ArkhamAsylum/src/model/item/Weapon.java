package model.item;

import java.util.Objects;
import utils.Constants;

/**
 * Java class to implement weapons type and its power.
 * This is used to create an object of weapon.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class Weapon implements Item {

  private final String weaponName;
  private final Integer damageValue;
  private final Integer roomId;

  /**
   * Constructor to initialize the weapon class.
   *
   * @param weaponName  identifies the name of the weapon
   * @param damageValue sets the power of the weapon
   * @param roomId      identifies in which location the weapon can be used
   */
  public Weapon(Integer roomId,
                Integer damageValue,
                String weaponName) {

    if (weaponName == null
            || damageValue <= 0
            || roomId < 0
            || roomId == Integer.MAX_VALUE
            || damageValue == Integer.MAX_VALUE
            || weaponName.isEmpty()) {
      throw new IllegalArgumentException("Weapon's damage value cannot a negative value or 0");
    }

    this.weaponName = weaponName;
    this.damageValue = damageValue;
    this.roomId = roomId;
  }

  @Override
  public int getRoomId() {
    return roomId;
  }

  @Override
  public String getItemName() {
    return weaponName;
  }

  @Override
  public int getDamageValue() {
    return damageValue;
  }

  @Override
  public String toString() {
    StringBuilder weaponStringBuilder = new StringBuilder();

    weaponStringBuilder.append("Weapon Name: ");
    weaponStringBuilder.append(getItemName());
    weaponStringBuilder.append(Constants.FULL_STOP);
    weaponStringBuilder.append(Constants.SPACE);

    weaponStringBuilder.append("Damage value: ");
    weaponStringBuilder.append(getDamageValue());
    weaponStringBuilder.append(Constants.FULL_STOP);
    weaponStringBuilder.append(Constants.SPACE);

    weaponStringBuilder.append("Item can be used in the room id: ");
    weaponStringBuilder.append(getRoomId());
    weaponStringBuilder.append(Constants.FULL_STOP);

    return weaponStringBuilder.toString();
  }

  @Override
  public boolean equals(Object object) {

    if (this == object) {
      return true;
    }

    if (!(object instanceof Weapon)) {
      return false;
    }

    // If the above check is not true then we can directly cast object to AutomaticTransmission
    Weapon weaponObject = (Weapon) object;

    return weaponObject.getItemName() == this.weaponName
            && weaponObject.getDamageValue() == this.damageValue
            && weaponObject.getRoomId() == getRoomId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            getItemName(),
            getDamageValue(),
            getRoomId()
    );
  }
}

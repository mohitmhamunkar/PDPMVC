package model.item;

/**
 * Interface which contains Item interface.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public interface Item {

  /**
   * For every item there is a room id associated to it.
   * This function is used to return the room id where
   * the item is belonged.
   *
   * @return an integer which denotes the roomId of the item
   */
  int getRoomId();

  /**
   * Function returns the item name.
   *
   * @return the name of the item.
   */
  String getItemName();

  /**
   * For the every item there is a damage value.
   *
   * @return an int that denotes the damage value
   */
  int getDamageValue();
}

package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.item.Item;
import model.item.Weapon;
import model.pet.Pet;
import model.pet.PetImpl;
import model.player.Player;
import model.player.PlayerImpl;
import model.spaces.Space;
import model.spaces.SpaceImpl;
import model.target.TargetImpl;
import model.world.ArkhamAsylumWorld;
import model.world.ArkhamAsylumWorldImpl;
import utils.Constants;
import utils.Pair;
import utils.RandomClassGenerator;

/**
 * This class parses all the data when supplied the argument.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class ArkhamAsylumGameWorldCreator implements ArkhamAsylumGameCreatorInterface {

  private int numberOfSpaces;
  private int numberOfItems;
  private ArkhamAsylumWorld arkhamAsylumWorld;
  private TargetImpl.TargetBuilder targetData;
  private List<Space> spaceList;
  private List<Item> weaponList;
  private List<Player> arrayListPlayer;
  private int rowCount;
  private int colCount;
  private final String fileContent;
  private final int maxTurn;
  private String petName;

  /**
   * Main creator class which is used to create the Arkham asylum world.
   *
   * @param args    contains the command line argument.
   * @param maxTurn contains the maxTurn of the players.
   */
  public ArkhamAsylumGameWorldCreator(String args, int maxTurn) {

    if (args == null || args.isEmpty() || maxTurn < 0) {
      throw new IllegalArgumentException("Please enter the arguments properly");
    }

    this.fileContent = args;
    spaceList = new ArrayList<>();
    weaponList = new ArrayList<>();
    arrayListPlayer = new ArrayList<>();
    this.maxTurn = maxTurn;
    targetData = new TargetImpl.TargetBuilder();
    parseAllData();
  }

  /**
   * This is a single of function which parses all data.
   */
  public void parseAllData() {
    try {
      File mansionFile = new File(fileContent);
      Scanner mansionScanner = new Scanner(mansionFile);

      int count = 0;
      while (mansionScanner.hasNextLine()) {
        count++;
        String data = mansionScanner.nextLine();

        switch (count) {

          case Constants.ONE: {
            Pair<Integer, Integer> pairOfRowCol = parseRowsAndCol(data);
            rowCount = pairOfRowCol.getFirstElement();
            colCount = pairOfRowCol.getSecondElement();

            continue;
          }

          case Constants.TWO: {
            targetData = parseTargetData(data);
            continue;
          }

          case Constants.THREE: {
            petName = data;
            continue;
          }

          case Constants.FOUR: {
            numberOfSpaces = Integer.parseInt(data);
            continue;
          }

          case Constants.FIVE: {
            for (int i = 0; i < numberOfSpaces; i++) {
              spaceList.add(parseSpaceData(data, i));
              if (i != numberOfSpaces - 1) {
                data = mansionScanner.nextLine();
              }
            }
            continue;
          }

          case Constants.SIX: {
            numberOfItems = Integer.parseInt(data);
            continue;
          }

          case Constants.SEVEN: {
            for (int i = 0; i < numberOfItems; i++) {
              weaponList.add(parseWeaponData(data));
              if (i != numberOfItems - 1) {
                data = mansionScanner.nextLine();
              }
            }
            continue;
          }

          default: {
            // Do nothing.
          }
        }
      }
      mansionScanner.close();

      TargetImpl target = targetData.totalNumberOfSpaces(numberOfSpaces)
              .build();
      Pet pet = new PetImpl(petName, target.getTargetLocation(), numberOfSpaces);

      arkhamAsylumWorld = createArkhamasylumWorld(
              rowCount,
              colCount,
              "Arkham Asylum Killing the Joker",
              getArrayListSpaceCopy(spaceList),
              getArrayListItemCopy(weaponList),
              getArraylistPlayerCopy(arrayListPlayer),
              target,
              pet,
              maxTurn
      );

    } catch (IOException e) {
      throw new IllegalArgumentException("The file did not exists");
    }
  }

  private List<Player> getArraylistPlayerCopy(List<Player> arrayListPlayer) {
    List<Player> playerListNew = new ArrayList<>();

    for (Player player : arrayListPlayer) {
      playerListNew.add(new PlayerImpl(
              player.getPlayerName(),
              player.getRoomName(),
              player.getMaximumItemsWithPlayerCount(),
              player.isComputerPlayer(),
              player.getPlayerIconPath()
      ));
    }

    return playerListNew;
  }

  private List<Space> getArrayListSpaceCopy(List<Space> spaceArrayList) {
    List<Space> spaceListNew = new ArrayList<>();

    for (Space space : spaceArrayList) {
      spaceListNew.add(new SpaceImpl(space.getSpaceId(),
              space.getSpaceName(),
              space.getSpaceLocation()));
    }

    return spaceListNew;
  }

  private List<Item> getArrayListItemCopy(List<Item> weaponsArrayList) {
    List<Item> itemListNew = new ArrayList<>();

    for (Item item : weaponsArrayList) {
      itemListNew.add(new Weapon(item.getRoomId(),
              item.getDamageValue(),
              item.getItemName()));
    }

    return itemListNew;
  }

  @Override
  public ArkhamAsylumWorld getArkhamAsylumWorld() {
    return arkhamAsylumWorld;
  }

  /**
   * Parse the data of the Weapon.
   *
   * @param data is a single line string argument.
   * @return Weapon data object which contains the details of weapon.
   */
  private static Weapon parseWeaponData(String data) {
    String[] delimString = data.split(Constants.SPACE);

    Integer roomId = Integer.parseInt(delimString[0]);
    Integer damageValue = Integer.parseInt(delimString[1]);
    StringBuilder weaponName = new StringBuilder();

    for (int i = 2; i < delimString.length; i++) {
      weaponName.append(delimString[i]);
      if (i != delimString.length - 1) {
        weaponName.append(Constants.SPACE);
      }
    }

    return new Weapon(roomId, damageValue, weaponName.toString());
  }

  /**
   * Parse the data of the SpaceImpl.
   *
   * @param data is a single line string argument.
   * @return SpaceImpl data object which contains the details of space.
   */
  private static SpaceImpl parseSpaceData(String data, int spaceId) {
    ArrayList<Integer> locationArray = new ArrayList<>();

    String[] delimString = data.split(Constants.SPACE);
    ArrayList<String> stringList = new ArrayList<>(Arrays.asList(delimString));
    stringList.removeAll(Arrays.asList("", null));

    StringBuilder spaceName = new StringBuilder();

    for (int i = 0; i < 4; i++) {
      locationArray.add(Integer.parseInt(stringList.get(i)));
    }

    for (int j = 4; j < stringList.size(); j++) {
      spaceName.append(stringList.get(j));
      if (j != delimString.length - 1) {
        spaceName.append(Constants.SPACE);
      }
    }

    return new SpaceImpl(spaceId, spaceName.toString(), locationArray);
  }

  /**
   * Gets the total number of rows and cols.
   *
   * @param data is a single line string argument.
   * @return Pair of integer containing the rows and cols.
   */
  private static Pair<Integer, Integer> parseRowsAndCol(String data) {
    String[] delimString = data.split(Constants.SPACE);
    int rowCount = Integer.parseInt(delimString[0]);
    int colCount = Integer.parseInt(delimString[1]);
    StringBuilder worldName = new StringBuilder();

    for (int i = Constants.TWO; i < delimString.length; i++) {
      worldName.append(delimString[i]);
      if (i != delimString.length - 1) {
        worldName.append(Constants.SPACE);
      }
    }

    return new Pair<Integer, Integer>(rowCount, colCount);
  }

  /**
   * Parse the data of the Target using builder pattern.
   *
   * @param data is a single line string argument.
   * @return TargetImpl data object which contains the details of Target.
   */
  private static TargetImpl.TargetBuilder parseTargetData(String data) {
    String[] delimString = data.split(Constants.SPACE);
    int targetValue = Integer.parseInt(delimString[0]);
    StringBuilder targetName = new StringBuilder();

    for (int i = 1; i < delimString.length; i++) {
      targetName.append(delimString[i]);
      if (i != delimString.length - 1) {
        targetName.append(Constants.SPACE);
      }
    }

    return new TargetImpl.TargetBuilder()
            .targetHealth(targetValue)
            .targetName(targetName.toString());
  }

  /**
   * Creates the whole world of Dr Lucky game.
   *
   * @param rowCount         number of rows.
   * @param colCount         number of cols.
   * @param worldName        name of the world.
   * @param spaceArrayList   details of the different spaces in the world.
   * @param weaponsArrayList details of the different weapons in the world.
   * @param targetCharacter  details of the target character.
   * @return DrLuckyWorldImpl the world object
   * @throws IOException if the read or write permission is not given.
   */
  private static ArkhamAsylumWorld createArkhamasylumWorld(
          int rowCount,
          int colCount,
          String worldName,
          List<Space> spaceArrayList,
          List<Item> weaponsArrayList,
          List<Player> arrayListPlayer,
          TargetImpl targetCharacter,
          Pet pet,
          int maxTurn
  ) {
    return new ArkhamAsylumWorldImpl(
            rowCount,
            colCount,
            worldName,
            spaceArrayList,
            weaponsArrayList,
            arrayListPlayer,
            targetCharacter,
            pet,
            maxTurn,
            new RandomClassGenerator());
  }
}

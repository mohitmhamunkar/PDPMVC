package model.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import model.player.Player;
import model.spaces.Space;

/**
 * This class is used to create the world image.
 * @author Subhankar Shah
 * @author Mohit Mhamunkar
 */
public class CreateWorldImage {

  private final List<Space> spaceArrayList;
  private final List<Player> playerArrayList;
  private final int colCount;
  private final int rowCount;
  private final int targetLocation;
  private final int petLocation;
  private final String currentPlayerLocation;

  /**
   * Constructor of the createWorld to initialise all the CreateWorldImage.
   *
   * @param spaceArrayList consists of ArrayList of space
   * @param colCount       consists of column count
   * @param rowCount       consists of row count
   * @param playerArrayList List of players in game
   * @param targetLocation Target Room Number
   * @param petLocation Pet Room Number
   * @param currentPlayerLocation Player's Current location
   */
  public CreateWorldImage(
          List<Space> spaceArrayList,
          List<Player> playerArrayList,
          int targetLocation,
          int colCount,
          int rowCount,
          int petLocation,
          String currentPlayerLocation) {

    if (currentPlayerLocation == null
            || currentPlayerLocation.isEmpty()
            || petLocation < 0
            || !isValidSpace(spaceArrayList)
            || !isValidPlayer(playerArrayList)
            || petLocation > spaceArrayList.size()
            || targetLocation < 0
            || targetLocation >= spaceArrayList.size()
            || rowCount < 0
            || colCount < 0
            || rowCount == Integer.MAX_VALUE
            || colCount == Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Parameters are not valid to create the world");
    }

    this.spaceArrayList = spaceArrayList;
    this.playerArrayList = playerArrayList;
    this.targetLocation = targetLocation;
    this.colCount = colCount;
    this.rowCount = rowCount;
    this.petLocation = petLocation;
    this.currentPlayerLocation = currentPlayerLocation;
  }

  private boolean isValidPlayer(List<Player> playerArrayList) {
    if (playerArrayList == null) {
      return false;
    }

    for (Player player : playerArrayList) {
      if (player.getPlayerName() == null
              || player.getPlayerName().isEmpty()
              || player.getRoomName() == null
              || player.getRoomName().isEmpty()
              || player.getMaximumItemsWithPlayerCount() < 0) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidSpace(List<Space> arrayListSpace) {
    if (arrayListSpace == null) {
      return false;
    }
    for (Space space : arrayListSpace) {
      if (space.getSpaceId() < 0
              || space.getSpaceName().isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * This function is used to create a map of the world.
   */
  public void createWorldMap() {
    BufferedImage bufferedImage = new BufferedImage(
            1000,
            1000,
            BufferedImage.TYPE_INT_ARGB);

    Graphics graph = bufferedImage.getGraphics();
    graph.setColor(Color.WHITE);
    graph.fillRect(0, 0, rowCount * 32 + 200, colCount * 32 + 200);
    graph.setColor(Color.BLACK);

    createRectangles(graph);

    saveFile(bufferedImage);
  }

  /**
   * Save the file inside res folder.
   *
   * @param bufferedImage of which the file is created.
   */
  private void saveFile(BufferedImage bufferedImage) {
    try {
      File file = new File("worldImage.png");
      ImageIO.write(bufferedImage, "png", file);
    } catch (IOException io) {
      throw new IllegalArgumentException("There was a problem in reading the file");
    }
  }

  /**
   * Creates different rectangles according to the x,y coordinate.
   *
   * @param graph to set the rectangle in the graph
   */
  private void createRectangles(Graphics graph) {
    for (Space space : spaceArrayList) {
      int y1 = space.getSpaceLocation().get(0);
      int x1 = space.getSpaceLocation().get(1);
      int y2 = space.getSpaceLocation().get(2);
      int x2 = space.getSpaceLocation().get(3);

      int width = (x2 - x1) + 1;
      int length = (y2 - y1) + 1;
      graph.drawRect(x1 * 32, y1 * 32, width * 32, length * 32);
      graph.drawString(space.getSpaceName() + " " + space.getSpaceId(), x1 * 32 + 4, y1 * 32 + 16);
      try {
        if (!(space.getSpaceId() == petLocation
            && !space.getSpaceName().trim().equalsIgnoreCase(currentPlayerLocation.trim()))) {
          drawPlayers(graph, space, x1, y1, width, length);
        }
        drawTarget(graph, space, x1, y1, x2, y2);
      } catch (IOException io) {
        throw new IllegalArgumentException("Draw error");
      }
    }
  }

  private void drawPlayers(Graphics graphics, Space space, int x1, int y1,
      int width, int length) throws IOException {

    int xfactor = width / 3;
    int yfactor = length / 3;

    int fistXcoordinate = (x1 + xfactor * 32);
    int firstYcoordinate = (y1 + yfactor * 32);
    int xcounter = 0;
    int ycounter = 0;
    for (Player player : playerArrayList) {
      if (player.getRoomName().trim().equalsIgnoreCase(space.getSpaceName().trim())) {
        if (xcounter >= (width * 32)) {
          xcounter = 0;
          ycounter += firstYcoordinate;
        }
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(
            player.getPlayerIconPath());
        BufferedImage image = ImageIO.read(stream);
        graphics.drawImage(image, x1 * 32 + xcounter, (y1 * 32) + ycounter, null);
        xcounter += fistXcoordinate;
      }
    }
  }

  private void drawTarget(Graphics graphics, Space space, int x1, int y1, int x2, int y2) {
    int xmean = Math.round((x1 + x2) / 2);
    int ymean = Math.round((y1 + y2) / 2);
    if (targetLocation == space.getSpaceId()) {
      try {
        InputStream stream = ClassLoader.getSystemClassLoader()
            .getResourceAsStream("icons/target.png");
        BufferedImage image = ImageIO.read(stream);
        graphics.drawImage(image, xmean * 32, ymean * 32, null);
      } catch (IOException e) {
        throw new IllegalArgumentException("Target Draw Error");
      }
    }
  }
}

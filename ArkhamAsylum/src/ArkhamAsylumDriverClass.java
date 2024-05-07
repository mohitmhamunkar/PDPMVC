import controller.ArkhamAsylumConsoleController;
import controller.ArkhamAsylumController;
import controller.ArkhamAsylumFeatures;
import controller.Features;
import java.io.InputStreamReader;
import model.ArkhamAsylumGameCreatorInterface;
import model.ArkhamAsylumGameWorldCreator;
import model.world.ArkhamAsylumWorld;
import model.world.ReadOnlyInterface;
import view.Iview;
import view.View;

/**
 * All class data.
 */
public class ArkhamAsylumDriverClass {

  /**
   * Main driver class which is used to run the program.
   *
   * @param args default argument.
   */
  public static void main(String[] args) {

    int maxTurn = 0;
    try {
      maxTurn = Integer.parseInt(args[1]);
    } catch (IllegalArgumentException io) {
      System.out.println("Max turn should be an integer");
    }

    ArkhamAsylumGameCreatorInterface arkhamAsylumDriver =
            new ArkhamAsylumGameWorldCreator(args[0], maxTurn);
    ArkhamAsylumWorld gameWorld = arkhamAsylumDriver.getArkhamAsylumWorld();

    Iview view = new View((ReadOnlyInterface) gameWorld);

    Features feature = new ArkhamAsylumFeatures(gameWorld, view);

    Readable inputReadable = new InputStreamReader(System.in);
    Appendable outputAppendable = System.out;

    ArkhamAsylumController controller =
            new ArkhamAsylumConsoleController(inputReadable, outputAppendable, maxTurn);
    controller.playArkhamGame(gameWorld);
  }
}

package view;

import controller.Features;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import model.world.ReadOnlyInterface;

/**
 * This class displays the main game screen with all features.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 *
 */
public class GameView implements GameInterface {
  private JFrame frame;
  private JPanel panel4;
  private ReadOnlyInterface model;
  private JLabel jlabelHintText;
  private JDialog dialog;
  private JLabel img;
  private JPanel panel1;
  private JMenuItem item;
  private JMenu item1;


  /**
   * This is the constructor of the class.
   *
   * @param model Read Only Model
   */
  public GameView(ReadOnlyInterface model) {
    frame = new JFrame();
    panel4 = new JPanel();
    this.model = model;
    dialog = new JDialog();
    jlabelHintText = new JLabel();
    img = new JLabel();
    panel1 = new JPanel();
    item = new JMenuItem("Quit");
    item1 = new JMenu();
  }

  /**
   * This method displays the frame and its following features.
   *
   */
  @Override
  public void show() {
    //Creating frame
    frame.setTitle("Game");
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setMinimumSize(new Dimension(500, 500));
    frame.setResizable(true);
    
    //Menu Item
    item1.setText("Menu");
    item1.add(item);
    
    //Adding Menu Bar
    JMenuBar menubar = new JMenuBar();
    menubar.add(item1);
    frame.setJMenuBar(menubar);

    createMap();

    JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    mainPanel.setResizeWeight(0.7);
    mainPanel.setBackground(Color.black);

    panel1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 153, 255)));
    panel1.setBackground(Color.black);
    panel1.add(img);
    mainPanel.add(panel1);
    
    JSplitPane panel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    panel2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 153, 255)));
    panel2.setBackground(Color.black);
    mainPanel.add(panel2);

    jlabelHintText.setFont(new Font("Segoe UI", 0, 16));
    jlabelHintText.setForeground(Color.white);
    String hints = model.getHint();
    jlabelHintText.setText(hints);

    JPanel panel3 = new JPanel();
    panel3.setBackground(new Color(0, 0, 0));
    panel3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 153, 255)));
    panel3.add(jlabelHintText);
    panel2.add(panel3);

    //Creating text in panel 4
    JLabel content = new JLabel();
    content.setFont(new Font("Segoe UI", 0, 20));
    content.setForeground(Color.white);
    String cont = "<html> CHOOSE MOVE <br><br> 1. Move Player - Mouse Click <br><br> 2. "
        + "Pick Item - I<br><br> 3. Look Around - L <br><br> 4. Kill Target - K"
        + "<br><br> 5. Move Pet - P";
    content.setText(cont);
    
    panel4.setBackground(new Color(0, 0, 0));
    panel4.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 153, 255)));
    panel4.add(content);
    panel2.add(panel4);
    
    JScrollPane scroll = new JScrollPane();
    scroll.setViewportView(mainPanel);
    
    frame.getContentPane().add(scroll);
    frame.setVisible(true);
  }

  private void createMap() {
    //Creating background in panel 1
    String path = "worldImage.png";
    try {
      img.setIcon(new ImageIcon(ImageIO.read(new File(path))));
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid File Path.");
    }
  }

  @Override
  public void setFeatures(Features feature) {


    Map<Character, Runnable> map = new HashMap<>();

    map.put('i', () -> feature.pickAnItem());
    map.put('l', () -> feature.lookAround());
    map.put('k', () -> feature.attackTarget());
    map.put('p', () -> feature.movePet());

    frame.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (map.containsKey(c)) {
          map.get(c).run();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });

    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        feature.quitGame();
      }
    });

    img.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int x = e.getX();
        int y = e.getY();
        feature.mouseClicked(x, y);
      }
    });
  }

  /**
   * This method sets Dialog Box for all commands.
   *
   * @param command The command invoked
   * @param list List of items based on commands
   */
  @Override
  public String showCommand(String command, List<String> list) {
    String optionString = (String) JOptionPane.showInputDialog(
            null,
            command,
            command,
            JOptionPane.QUESTION_MESSAGE,
            null,
            list.toArray(),
            null
    );

    return optionString;
  }

  /**
   * This method creates a panel for look around Command.
   *
   * @param info Look around info
   */
  @Override
  public void showLookAround(String info) {
    //Creating Dialog box
    dialog.setSize(700, 450);
    dialog.setTitle("Look Around Info");
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setLocationRelativeTo(null);
    dialog.setResizable(false);

    //Creating text in panel 5
    JTextArea content = new JTextArea();
    content.setFont(new Font("Segoe UI", 0, 20));
    content.setBackground(new Color(0, 0, 0, 0));
    content.setForeground(new Color(51, 153, 255));
    content.setEditable(false);
    content.getCaret().setVisible(false);
    content.setText(info);
    content.setSize(670, 350);
    JScrollPane scroll = new JScrollPane(content);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    dialog.getContentPane().add(scroll);
    dialog.setVisible(true);
  }

  @Override
  public void updatePanels() {
    createMap();
  }
  
  @Override
  public void closeGame() {
    frame.dispose();
    dialog.dispose();
  }

  @Override
  public void showCurrentPlayerTurn(String message) {
    jlabelHintText.setText(message);
  }
}

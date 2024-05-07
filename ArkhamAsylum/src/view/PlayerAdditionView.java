package view;

import controller.Features;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.world.ReadOnlyInterface;

/**
 * This class displays the add player & start screen of the following game.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 *
 */
public class PlayerAdditionView implements PlayerAddition {
  private JDialog dialog1;
  private JTextField textFieldItemLimit;
  private JFrame frame;
  private JButton addPlayerDialogButton;
  private JTextField textfieldPlayerName;
  private JRadioButton humanPlayerRadioButton;
  private final String humanPlayerActionCommand;
  private final String computerPlayerActionCommand;
  private JRadioButton computerPlayerRadioButton;
  private ButtonGroup players;
  private JLabel comboText;
  private JComboBox<String> combobox;
  private JButton start;
  private Dialogs dialogMessage;
  private ReadOnlyInterface model;
  
  /**
   * This is the constructor of the class.
   * @param model Read Only Model
   */
  public PlayerAdditionView(ReadOnlyInterface model) {

    dialog1 = new JDialog();
    textFieldItemLimit = new JTextField();
    frame = new JFrame();
    addPlayerDialogButton = new JButton();
    textfieldPlayerName = new JTextField();
    humanPlayerRadioButton = new JRadioButton();
    computerPlayerRadioButton = new JRadioButton();
    players = new ButtonGroup();
    comboText = new JLabel();
    combobox = new JComboBox<String>();
    start = new JButton();
    dialogMessage = new Dialogs();
    this.model = model;
    humanPlayerActionCommand = "human";
    computerPlayerActionCommand = "computer";
  }
  
  /**
   * This method displays the frame and its following features.
   */
  @Override
  public void show() {
    //Creating frame

    frame.setTitle("Add Player & Start Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(960, 540);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);

    // Adding New Player Button to frame
    JButton newPlayer = new JButton();
    newPlayer.setBackground(new Color(0, 0, 0));
    newPlayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        newPlayerActionPerformed(event);
      }
    });
    newPlayer.setFont(new Font("Segoe UI", 1, 24));
    newPlayer.setForeground(new Color(255, 255, 255));
    newPlayer.setText("Add Player");
    newPlayer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 153, 255)));
    newPlayer.setFocusPainted(false);
    frame.getContentPane().add(newPlayer);
    newPlayer.setBounds(380, 200, 200, 40);

    // Adding Start Game Button to frame
    start.setBackground(new Color(0, 0, 0));
    start.setFont(new Font("Segoe UI", 1, 24));
    start.setForeground(new Color(255, 255, 255));
    start.setText("Start Game");
    start.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 153, 255)));
    start.setFocusPainted(false);
    frame.getContentPane().add(start);
    start.setBounds(380, 320, 200, 40);

    //Adding background to frame
    JLabel bgimg = new JLabel();
    String path = "bg_image.png";
    try {
      InputStream stream = ClassLoader.getSystemClassLoader()
              .getResourceAsStream(path);
      BufferedImage image = ImageIO.read(stream);
      bgimg.setIcon(new ImageIcon(image));
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid File Path.");
    }
    frame.getContentPane().add(bgimg);
    bgimg.setBounds(0, 0, 960, 540);

    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void hide() {
    frame.setVisible(false);
    frame.dispose();
  }

  /**
   * This method creates the dialog of the player addition section.
   */
  public void showPlayerBox() {
    //Creating the Dialog Box
    dialog1.setTitle("Player Details");
    dialog1.setSize(700, 350);
    dialog1.getContentPane().setLayout(null);
    dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog1.setLocationRelativeTo(null);
    dialog1.setResizable(false);
    
    //Adding Header to the Dialog box
    JLabel header = new JLabel();
    header.setFont(new Font("Segoe UI", 1, 18));
    header.setForeground(new Color(51, 153, 255));
    header.setText("Add Player Details");
    dialog1.getContentPane().add(header);
    header.setBounds(270, 10, 200, 25);
    
    //Adding text1 area label to Dialog box
    JLabel text1 = new JLabel();
    text1.setFont(new Font("Segoe UI", 1, 14));
    text1.setForeground(new Color(51, 153, 255));
    text1.setText("Player Name:");
    dialog1.getContentPane().add(text1);
    text1.setBounds(150, 60, 90, 16);
    
    //Adding text2 area label to Dialog box
    JLabel text2 = new JLabel();
    text2.setFont(new Font("Segoe UI", 1, 14));
    text2.setForeground(new Color(51, 153, 255));
    text2.setText("Item Limit:");
    dialog1.getContentPane().add(text2);
    text2.setBounds(165, 100, 90, 16);
    
    //Adding combo box label to Dialog box
    comboText.setFont(new Font("Segoe UI", 1, 14));
    comboText.setForeground(new Color(51, 153, 255));
    comboText.setText("Starting Space:");
    dialog1.getContentPane().add(comboText);
    comboText.setBounds(135, 180, 120, 20);
    
    //Adding text area 1 to Dialog box
    textfieldPlayerName.setFont(new Font("Segoe UI", 0, 14));
    textfieldPlayerName.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
        new Color(51, 153, 255)));
    textfieldPlayerName.setCaretColor(new Color(51, 153, 255));
    textfieldPlayerName.setToolTipText("Player Name");
    dialog1.getContentPane().add(textfieldPlayerName);
    textfieldPlayerName.setBounds(260, 58, 250, 26);
    
    //Adding text area 2 to Dialog box
    textFieldItemLimit.setFont(new Font("Segoe UI", 0, 14));
    textFieldItemLimit.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent event) {
        textfield2KeyTyped(event);
      }
    });
    textFieldItemLimit.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
        new Color(51, 153, 255)));
    textFieldItemLimit.setCaretColor(new Color(51, 153, 255));
    textFieldItemLimit.setToolTipText("Item Limit");
    dialog1.getContentPane().add(textFieldItemLimit);
    textFieldItemLimit.setBounds(260, 98, 250, 26);
    
    //Adding Radio Button1 to the Dialog box
    humanPlayerRadioButton.setFont(new Font("Segoe UI", 1, 14));
    humanPlayerRadioButton.setForeground(new Color(51, 153, 255));
    humanPlayerRadioButton.setText("Human");
    humanPlayerRadioButton.setToolTipText("Human");
    humanPlayerRadioButton.setActionCommand(humanPlayerActionCommand);
    dialog1.getContentPane().add(humanPlayerRadioButton);
    humanPlayerRadioButton.setBounds(260, 138, 99, 26);
    
    //Adding Radio Button1 to the Dialog box
    computerPlayerRadioButton.setFont(new Font("Segoe UI", 1, 14));
    computerPlayerRadioButton.setForeground(new Color(51, 153, 255));
    computerPlayerRadioButton.setText("Computer");
    computerPlayerRadioButton.setToolTipText("Computer");
    computerPlayerRadioButton.setActionCommand(computerPlayerActionCommand);
    dialog1.getContentPane().add(computerPlayerRadioButton);
    computerPlayerRadioButton.setBounds(400, 138, 99, 26);
    
    //Making buttons a group
    players.add(humanPlayerRadioButton);
    players.add(computerPlayerRadioButton);
    humanPlayerRadioButton.setSelected(true);
    
    //Adding Combo Box to Dialog box
    combobox.setFont(new Font("Segoe UI", 0, 14));
    for (String spaceName : model.getListOfSpacesInWorld()) {
      combobox.addItem(spaceName);
    }
    combobox.setToolTipText("Starting Space");
    combobox.setBackground(new Color(255, 255, 255));
    combobox.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 153, 255)));
    dialog1.getContentPane().add(combobox);
    combobox.setBounds(260, 178, 250, 26);
    
    // Adding Add Player Button to Dialog box
    // TODO : Make this button an instance field.
    addPlayerDialogButton.setBackground(new Color(0, 0, 0));
    addPlayerDialogButton.setFont(new Font("Segoe UI", 1, 14));
    addPlayerDialogButton.setForeground(new Color(255, 255, 255));
    addPlayerDialogButton.setText("Add Player");
    addPlayerDialogButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
        new Color(51, 153, 255)));
    addPlayerDialogButton.setFocusPainted(false);
    dialog1.getContentPane().add(addPlayerDialogButton);
    addPlayerDialogButton.setBounds(270, 250, 150, 40);
    dialog1.setVisible(true);
    dialog1.requestFocusInWindow();
  }

  @Override
  public void setFeature(Features feature) {
    addPlayerDialogButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (model.getNumberOfHumanPlayer() <= 10) {
          boolean isComputerPlayer = Objects.equals(
              players.getSelection()
                      .getActionCommand(),
              computerPlayerActionCommand
          );

          int totalNumberOfItem = 0;

          try {
            totalNumberOfItem = Integer.parseInt(textFieldItemLimit.getText());
            } catch (NumberFormatException io) {
              throw new IllegalArgumentException("Item limit should be an integer");
              }
          feature.addPlayer(
              textfieldPlayerName.getText(),
              isComputerPlayer,
              (String) combobox.getSelectedItem(),
              totalNumberOfItem
              );

          humanPlayerRadioButton.setSelected(true);
          textfieldPlayerName.setText("");
          textFieldItemLimit.setText(""); 
        } else {
          humanPlayerRadioButton.setSelected(true);
          textfieldPlayerName.setText("");
          textFieldItemLimit.setText(""); 
          dialog1.dispose();
          dialogMessage.showMessage("Max Players Reached, Start The Game.");
        }
      }
    });
    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (model.getNumberOfHumanPlayer() > 0) {
          feature.startGame(); 
        } else {
          dialogMessage.showError("Add Atleast One Player To Begin!");
        }
      }
    });
  }
  
  /**
   * This checks for the key press character in text field 2.
   * 
   * @param event KeyPress event
   */
  private void textfield2KeyTyped(KeyEvent event) {
    char c = event.getKeyChar();
    if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
      event.consume();
    }
  }

  
  /**
   * This method opens the player details dialog box.
   * 
   * @param event Add Player Button Pressed
   */
  private void newPlayerActionPerformed(ActionEvent event) {
    this.showPlayerBox();
  }
}

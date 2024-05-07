package view;

import controller.Features;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This class displays the introduction of the following game.
 * @author Mohit Mhamunkar
 * @author Subhankar Shah
 *
 */
public class IntroView implements IntroInterface {
  private JFrame frame;
  private JDialog dialog;
  private JMenuItem item;
  private JMenuItem item1;
  private JMenuItem item2;
  
  /**
   * This is the constructor of the class.
   */
  public IntroView() {
    frame = new JFrame();
    dialog = new JDialog();
    item = new JMenuItem("Existing Map");
    item1 = new JMenuItem("New Map");
    item2 = new JMenuItem("Quit");
  }
  
  /**
  * This method displays the frame and its following features.
  * 
  */
  @Override
  public void show() {
    //Creating frame
    frame.setTitle("Intro");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(960, 540);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    
    //Menu Item
    JMenu menu = new JMenu();
    menu.setText("Menu");
    menu.add(item);
    menu.add(item1);
    menu.add(item2);
    
    //Adding Menu Bar
    JMenuBar menubar = new JMenuBar();
    menubar.add(menu);
    frame.setJMenuBar(menubar);
    
    //Adding text to frame
    JLabel text = new JLabel();
    text.setFont(new Font("Segoe UI", 0, 18));
    text.setForeground(Color.white);
    text.setText("Choose The Menu Options");
    frame.getContentPane().add(text);
    text.setBounds(370, 370, 230, 25);
    
    // Adding Button to frame
    JButton aboutUs = new JButton();
    aboutUs.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        aboutUsActionPerformed(event);
      }
    });
    aboutUs.setBackground(new Color(0, 0, 0));
    aboutUs.setFont(new Font("Segoe UI", 0, 18));
    aboutUs.setForeground(new Color(255, 255, 255));
    aboutUs.setText("About Us");
    aboutUs.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 153, 255)));
    aboutUs.setFocusPainted(false);
    frame.getContentPane().add(aboutUs);
    aboutUs.setBounds(420, 410, 120, 40);
    
    //Adding background to frame
    JLabel bgimg = new JLabel();
    String path = "IntroScreen.png";

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
  
  /**
   * This method creates a dialogue of the about me section of the game.
   */
  private void showAboutMe() {
    //Creating Dialog Box
    dialog.setTitle("About Us");
    dialog.setSize(600, 300);
    dialog.getContentPane().setLayout(null);
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setLocationRelativeTo(null);
    dialog.setResizable(false);
    
    //Adding Header to the Dialog box
    JLabel header = new JLabel();
    header.setFont(new Font("Segoe UI", 1, 18));
    header.setForeground(new Color(51, 153, 255));
    header.setText("About Us");
    dialog.getContentPane().add(header);
    header.setBounds(250, 10, 80, 25);
    
    //Adding Content to the Dialog Box
    JLabel content = new JLabel();
    content.setFont(new Font("Segoe UI", 0, 14));
    content.setForeground(new Color(51, 153, 255));
    StringBuilder text = new StringBuilder("<html> Creators: - <br> Mohit Rajendra Mhamunkar <br>");
    text.append("Subhankar Shah <br><br> The game is inspired from Batman villan Joker and the ");
    text.append("task is to <br> kill him with all we got. The orignal game was inspired ");
    text.append("by Kill Dr Lucky. </html>");
    content.setText(text.toString());
    dialog.getContentPane().add(content);
    content.setBounds(50, 30, 500, 150);
    
    //Adding Button to the Dialog Box
    JButton ok = new JButton();
    ok.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
          okActionPerformed(event);
        }
    });
    ok.setBackground(new Color(0, 0, 0));
    ok.setFont(new Font("Segoe UI", 0, 18));
    ok.setForeground(new Color(255, 255, 255));
    ok.setText("OK");
    ok.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 153, 255)));
    ok.setFocusPainted(false);
    dialog.getContentPane().add(ok);
    ok.setBounds(250, 220, 80, 30);
    
    dialog.setVisible(true);
  }
  
  /**
   * This method disposes the Dialog box on click of ok button.
   * 
   * @param event Click of ok button
   */
  private void okActionPerformed(ActionEvent event) {
    dialog.dispose();
  }
  
  /**
   * This method hides the frame.
   */
  @Override
  public void hide() {
    frame.dispose();
  }
  
  /**
   * This method get the Action Listeners for menu clicks.
   * 
   * @param feature Controller
   */
  @Override
  public void setFeatures(Features feature) {
    item.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        feature.openAddPlayers();
      }
    });
    item1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        feature.openFileChooser();
      }
    });
    item2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        feature.quitGame();
      }
    });
  }
  
  /**
   * This method opens Dialog box on click of about Us button.
   * 
   * @param event Click of aboutUs button
   */
  private void aboutUsActionPerformed(ActionEvent event) {
    this.showAboutMe();
  }
}

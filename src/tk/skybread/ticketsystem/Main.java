package tk.skybread.ticketsystem;

import tk.skybread.ticketsystem.tabs.LoggerTab;
import tk.skybread.ticketsystem.tabs.MainTab;
import tk.skybread.ticketsystem.utils.Utils;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

public class Main extends JPanel {
    public static Main instance;

    public static Main getInstance() {
        instance = new Main();
        return instance;
    }

    public Main() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        //panel icons
        ImageIcon icon1 = Utils.createImageIcon("");
        ImageIcon icon2 = Utils.createImageIcon("");
        ImageIcon icon3 = Utils.createImageIcon("");

        //tab item 1
        JComponent panel1 = MainTab.tabPanel();
        tabbedPane.addTab("Main", icon1, panel1, "oof");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        //tab item 2
        JComponent panel2 = Utils.makeTextPanel("Admin Tools");
        tabbedPane.addTab("Admin Tools", icon2, panel2, "Does nothing yet");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        //tab item 4
        JComponent panel3 = LoggerTab.tabPanel();
        tabbedPane.addTab("LoggerTab", icon3, panel3, "working but nothing uses the logger");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);


        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Ticket System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Main(), BorderLayout.CENTER);

        //window size
        frame.setPreferredSize(new Dimension(1200, 800));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
                LoggerTab.Logger(Level.INFO, "Application Started.");
            }
        });
    }
}

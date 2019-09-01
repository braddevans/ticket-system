package tk.skybread.ticketsystem.utils;

import tk.skybread.ticketsystem.Main;

import javax.swing.*;
import java.awt.*;

public class Utils {
    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Main.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    //public void log(String msg) {
     //   appendToLogwindow(msg);
   // }
}

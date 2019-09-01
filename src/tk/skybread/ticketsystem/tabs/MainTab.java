package tk.skybread.ticketsystem.tabs;

import javax.swing.*;
import java.awt.*;

public class MainTab {
    public static JComponent tabPanel() {
        //JPanel instance
        JPanel panel = new JPanel(false);

        //add and edit stuff inside panel like text
        JLabel filler = new JLabel("oof");
        filler.setHorizontalAlignment(JLabel.CENTER);

        //set layout
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}

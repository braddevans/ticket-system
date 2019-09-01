package tk.skybread.ticketsystem.tabs;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class LoggerTab{
    private static JTextArea textArea = new JTextArea(15, 30);

    public static void Logger(Level level, String log) {
        textArea.append( "[" + level + "] " + log );
    }


    public static JComponent tabPanel() {
        //JPanel instance
        JPanel panel = new JPanel(false);

        //int
        JScrollPane textAreaConsole = new JScrollPane(textArea);
        textAreaConsole.setBounds(10,60,780,500);
        textAreaConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //set layout
        panel.setLayout(new GridLayout(1, 1));
        panel.add(textAreaConsole);
        return panel;
    }
}

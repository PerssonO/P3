package View;

import javax.swing.*;

public class AllUsersFrame extends JFrame {

    private JPanel allUsersPanel;

    public AllUsersFrame(){
        setSize(300,450);
        setResizable(false);
        allUsersPanel = new AllUsersPanel();
        add(allUsersPanel);
        setContentPane(allUsersPanel);
        setVisible(true);
    }
}

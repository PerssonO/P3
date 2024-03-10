package Boundary.GUI;

import javax.swing.*;
import java.util.ArrayList;

public class GroupMessageFrame extends JFrame {
    private GroupMessagePanel groupMessagePanel;

    public GroupMessageFrame(ArrayList<String> allUsers, ArrayList<String> contacts, BoundaryHandler boundaryHandler){
        setBounds(300,5,500,455);
        setVisible(true);
        setResizable(false);
        groupMessagePanel = new GroupMessagePanel(allUsers,contacts, boundaryHandler);
        add(groupMessagePanel);
        setContentPane(groupMessagePanel);
    }
}

package Boundary.GUI;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Frame used for containing GroupMessage gui compontents
 */
public class GroupMessageFrame extends JFrame {
        private GroupMessagePanel groupMessagePanel;

    /**
     * Constructor
     * @param allUsers list of every user connected to server
     * @param contacts list of user contacts
     * @param boundaryHandler reference to boundaryHandler
     */
    public GroupMessageFrame(ArrayList<String> allUsers, ArrayList<String> contacts, BoundaryHandler boundaryHandler){
        setBounds(300,5,500,455);
        setVisible(true);
        setResizable(false);
        groupMessagePanel = new GroupMessagePanel(allUsers,contacts, boundaryHandler);
        add(groupMessagePanel);
        setContentPane(groupMessagePanel);
    }
}

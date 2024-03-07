package View;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import Model.User;

public class GroupMessageFrame extends JFrame implements WindowListener {
    private GroupMessagePanel groupMessagePanel;

    //    public GroupMessageFrame(ArrayList<String> allUsers, ArrayList<String> contacts, MainFrame mainFrame){
    public GroupMessageFrame(ArrayList<User> allUsers, ArrayList<User> contacts, MainFrame mainFrame){
        setBounds(300,5,500,455);
        setVisible(true);
        setResizable(false);
        groupMessagePanel = new GroupMessagePanel(allUsers,contacts,mainFrame);
        add(groupMessagePanel);
        setContentPane(groupMessagePanel);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

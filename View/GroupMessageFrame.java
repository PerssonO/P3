package View;

import Client.MessageSender;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GroupMessageFrame extends JFrame implements WindowListener {
    private GroupMessagePanel groupMessagePanel;

    public GroupMessageFrame(MessageSender sender){
        setBounds(300,5,500,455);
        setVisible(true);
        setResizable(false);
        groupMessagePanel = new GroupMessagePanel(sender);
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

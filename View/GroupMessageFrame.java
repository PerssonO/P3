package View;

import Client.MessageSender;

import javax.swing.*;

public class GroupMessageFrame extends JFrame {
    private GroupMessagePanel groupMessagePanel;

    public GroupMessageFrame(MessageSender sender){
        setBounds(300,5,500,255);
        setVisible(true);
        setResizable(false);
        groupMessagePanel = new GroupMessagePanel(sender);
        add(groupMessagePanel);
        setContentPane(groupMessagePanel);


    }

}

package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AllUsersPanel extends JPanel implements ActionListener, MouseListener {

    private JList contactsList;
    private JButton addUser;
    private JButton startChat;
    private ArrayList<String> contacts = new ArrayList();

    public AllUsersPanel() {
        contacts.add("Carlos");
        contacts.add("Steven");
        contacts.add("Esteban");
        setBackground(new Color(207, 219, 239));
        setLayout(null);
        contactsList = new JList();
        contactsList.setBounds(5, 5, 275, 350);
        add(contactsList);
        addUser = new JButton("Add user");
        addUser.setBounds(15, 370, 120, 30);
        add(addUser);
        addUser.addActionListener(this);
        startChat = new JButton("Start Chat");
        startChat.setBounds(145, 370, 120, 30);
        add(startChat);
        startChat.addActionListener(this);
        contactsList.addMouseListener(this);
        contactsList.setListData(contacts.toArray());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addUser) {

        } else if (e.getSource() == startChat) {
            ChatFrame chatFrame = new ChatFrame();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            System.out.println(contacts.get(contactsList.getSelectedIndex()));
            ChatFrame chatFrame = new ChatFrame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

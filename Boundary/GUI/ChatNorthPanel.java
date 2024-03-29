package Boundary.GUI;

import Boundary.GUI.BoundaryHandler;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel holding gui objects related to receiving a message and recipient information
 */
public class ChatNorthPanel extends JPanel {

    private JLabel recipientImage;
    private JLabel recipientName;
    private JList<Object> receivedMessagesJlist;
    private BoundaryHandler boundaryHandler;
    private List<Object> messages = new ArrayList<>();

    /**
     * Constructor for class, creates gui elements
     * @param boundaryHandler reference to boundaryHandler
     * @param recipient name of recipient
     * @param image recipient profile picture
     */
    public ChatNorthPanel(BoundaryHandler boundaryHandler, String recipient, ImageIcon image){
        this.boundaryHandler = boundaryHandler;
        setLayout(null);
        setBounds(0,0,500,300);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        recipientImage = new JLabel();
        recipientImage.setBounds(380,5,100,100);
        add(recipientImage);
        recipientName = new JLabel();
        recipientName.setBounds(380,110,100,20);
        recipientName.setText(recipient);
        add(recipientName);
        recipientImage.setIcon(new ImageIcon(image.getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));
        receivedMessagesJlist = new JList();
        receivedMessagesJlist.setBounds(5,5,360,240);
        add(receivedMessagesJlist);
        receivedMessagesJlist.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(receivedMessagesJlist);
        scrollPane.setBounds(5,5,360,240);
        add(scrollPane);
        receivedMessagesJlist.addSelectionInterval(-1,-1);
        receivedMessagesJlist.setListData(messages.toArray());
        receivedMessagesJlist.repaint();
    }

    /**
     * Method used to add text and images to the message arrayList.
     * invokeLater call used to update the JList with the updated message arrayList
     * @param text text to be added
     * @param image image to be added
     */
    public void populateReceivedMessages(String text, ImageIcon image){
        messages.add(text);
        messages.add(image);
        SwingUtilities.invokeLater(() -> receivedMessagesJlist.setListData(messages.toArray()));
        revalidate();
        repaint();
    }
}

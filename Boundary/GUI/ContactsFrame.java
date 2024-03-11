package Boundary.GUI;



import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * Main frame of the program, holds panels containing gui components used for every other behavior than
 * chatting on the client side
 */
public class ContactsFrame extends JFrame implements WindowListener {

    private ContactsPanel contactsPanel;
    private BoundaryHandler boundaryHandler;

    /**
     * Constructor, creates a window to be filled with frames
     * @param boundaryHandler
     */
    public ContactsFrame(BoundaryHandler boundaryHandler) {
        this.boundaryHandler = boundaryHandler;
        setSize(300,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        contactsPanel = new ContactsPanel(boundaryHandler);
        add(contactsPanel);
        setContentPane(contactsPanel);
        setVisible(true);
        addWindowListener(this);
    }

    /**
     * calls for the contactsPanel to populate the list of contacts
     * @param newContactList arrayList containing the names of the contacts to be added
     */
    public void setContactList(ArrayList<String> newContactList) {
        contactsPanel.populateContactList(newContactList);
    }

    /**
     * calls for the contactsPanel to populate the list of all connected users
     * @param newUserList arrayList containing the names of every contact that is online
     */
    public void setAllUsersList(ArrayList<String> newUserList) {
        contactsPanel.populateAllUserList(newUserList);
    }

    /**
     * calls fot the contactspanel to display the name of the user that last connected
     * @param user name of the last connected user
     */
    public void setLatestLogin(String user) {
        contactsPanel.setLatestLogin(user);
    }

    /**
     * WindowListener method(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Windowlistener method, calls for the boundaryHanler to send the users contacts
     * to server when the window is closed
     * @param e the event to be processed
     */
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println(contactsPanel.getContacts());
        boundaryHandler.sendUpdatedContactList(contactsPanel.getContacts());
        System.out.println("Sending updated contactlist");
    }
    /**
     * Windowlistener method, calls for the boundaryHanler to send the users contacts
     * to server when the window is closed
     * @param e the event to be processed
     */
    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println(contactsPanel.getContacts());
        boundaryHandler.sendUpdatedContactList(contactsPanel.getContacts());
        System.out.println("Sending updated contactlist");
    }

    /**
     * Windowlistener method(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowIconified(WindowEvent e) {

    }

    /**
     * Windowlistener method(not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    /**
     * WindowListener method (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowActivated(WindowEvent e) {

    }

    /**
     * WindowListened method (not implemented)
     * @param e the event to be processed
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}

package Boundary.GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginPanel extends JPanel implements ActionListener, KeyListener {
    private JLabel userImageLabel;
    private JButton changeImageButton;
    private JButton logInButton;
    private JFrame loginFrame;
    private BoundaryHandler boundaryHandler;
    private JTextField userNameTextField;


    public LoginPanel(LoginFrame loginFrame, BoundaryHandler boundaryHandler){
        this.boundaryHandler = boundaryHandler;
        this.loginFrame = loginFrame;
        setLayout(null);
        setBackground(new Color(207, 219, 239));
        ImageIcon defaultImage = new ImageIcon(new ImageIcon("default.jpg").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JLabel loginLabel = new JLabel("Username: ");
        loginLabel.setBounds(15,170,100,30);
        add(loginLabel);
        userNameTextField = new JTextField();
        add(userNameTextField);
        userNameTextField.setBounds(15,200,150,30);
        userImageLabel = new JLabel();
        userImageLabel.setIcon(defaultImage);
        add(userImageLabel);
        userImageLabel.setBounds(15,15,150,150);
        changeImageButton = new JButton("Change image");
        changeImageButton.setBounds(15,235,150,30);
        changeImageButton.addActionListener(this);
        logInButton = new JButton("Log in");
        logInButton.setBounds(15,350,150,30);
        logInButton.addActionListener(this);
        logInButton.setEnabled(false);
        userNameTextField.addKeyListener(this);
        add(changeImageButton);
        add(logInButton);
        userNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!userNameTextField.getText().equals("")){
                    logInButton.setEnabled(true);
                }
                else{
                    logInButton.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!userNameTextField.getText().equals("")){
                    logInButton.setEnabled(true);
                }
                else{
                    logInButton.setEnabled(false);
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!userNameTextField.getText().equals("")){
                    logInButton.setEnabled(true);
                }
                else{
                    logInButton.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeImageButton){
            JFileChooser fc = new JFileChooser();
            int returnValue = fc.showOpenDialog(this);
            if(returnValue == JFileChooser.APPROVE_OPTION){
                setImage(fc.getSelectedFile().getAbsolutePath());
            }
        }
        else if(e.getSource() == logInButton){
            login();
        }
    }
    public void setImage(String url){
        String substring = url.substring(url.length() - 4).toLowerCase();
        if(substring.equals(".jpg") || substring.equals(".png")) {
            userImageLabel.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
        }
    }

    public void login(){
        loginFrame.setVisible(false);
        boundaryHandler.setContactsFrame(new ContactsFrame(boundaryHandler));
        boundaryHandler.newLogIn(userNameTextField.getText(), (ImageIcon) userImageLabel.getIcon());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER && logInButton.isEnabled()){
            login();
            System.out.println("enter");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


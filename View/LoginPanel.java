package View;

import Client.InputHandler;
import Client.OutputHandler;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {
    public JLabel userImage;
    private JButton changeImage;
    private JButton logIn;
    private JFrame loginFrame;
    private MainFrame mainFrame;
    private JTextField userName;


    public LoginPanel(LoginFrame loginFrame, MainFrame mainFrame){
        this.mainFrame = mainFrame;
        this.loginFrame = loginFrame;
        setLayout(null);
        setBackground(new Color(207, 219, 239));
        ImageIcon defaultImage = new ImageIcon(new ImageIcon("default.jpg").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JLabel loginLabel = new JLabel("Username: ");
        loginLabel.setBounds(15,170,100,30);
        add(loginLabel);
        userName = new JTextField();
        add(userName);
        userName.setBounds(15,200,150,30);
        userImage = new JLabel();
        userImage.setIcon(defaultImage);
        add(userImage);
        userImage.setBounds(15,15,150,150);
        changeImage = new JButton("Change image");
        changeImage.setBounds(15,235,150,30);
        changeImage.addActionListener(this);
        logIn = new JButton("Log in");
        logIn.setBounds(15,350,150,30);
        logIn.addActionListener(this);
        logIn.setEnabled(false);
        add(changeImage);
        add(logIn);
        userName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!userName.getText().equals("")){
                    logIn.setEnabled(true);
                }
                else{
                    logIn.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!userName.getText().equals("")){
                    logIn.setEnabled(true);
                }
                else{
                    logIn.setEnabled(false);
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!userName.getText().equals("")){
                    logIn.setEnabled(true);
                }
                else{
                    logIn.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeImage){
            JFileChooser fc = new JFileChooser();
            int returnValue = fc.showOpenDialog(this);
            if(returnValue == JFileChooser.APPROVE_OPTION){
                setImage(fc.getSelectedFile().getAbsolutePath());
            }
        }
        else if(e.getSource() == logIn){
            mainFrame.newLogIn(userName.getText(), (ImageIcon) userImage.getIcon());
            loginFrame.setVisible(false);
            mainFrame.setContactsFrame(new ContactsFrame(mainFrame));
        }
    }
    public void setImage(String url){
        userImage.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING)));
    }
}


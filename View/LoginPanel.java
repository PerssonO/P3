package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {
    public JLabel userImage;
    private JButton changeImage;
    private JButton logIn;
    private JFrame loginFrame;

    public LoginPanel(LoginFrame loginFrame){
        this.loginFrame = loginFrame;
        setLayout(null);
        setBackground(new Color(207, 219, 239));
        ImageIcon defaultImage = new ImageIcon(new ImageIcon("images/default.jpg").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JLabel loginLabel = new JLabel("Username: ");
        loginLabel.setBounds(15,170,100,30);
        add(loginLabel);
        JTextField userName = new JTextField();
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
        add(changeImage);
        add(logIn);
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
            loginFrame.setVisible(false);
            ContactsFrame contactsFrame = new ContactsFrame();
        }
    }
    public void setImage(String url){
        userImage.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150,150,Image.SCALE_AREA_AVERAGING)));
    }
}


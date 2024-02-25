package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatSouthPanel extends JPanel implements ActionListener, KeyListener {

    private JTextArea messageDraft;
    private JLabel userImage;
    private JButton send;
    private JButton attach;
    public ChatSouthPanel(){
        setLayout(null);
        setBounds(0,300,500,250);
        setBackground(new Color(207, 219, 239));
        setVisible(true);
        JLabel userImage = new JLabel();
        userImage.setIcon(new ImageIcon(new ImageIcon("images/375px-Amerikanische_Pekingenten_2013_01b.jpg").getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING)));
        add(userImage);
        userImage.setBounds(380,10,100,100);
        messageDraft = new JTextArea();
        messageDraft.setBackground(Color.WHITE);
        messageDraft.setBounds(5,10,370,140);
        add(messageDraft);
        send = new JButton("Send");
        send.setBounds(380,110,50,40);
        add(send);
        attach = new JButton("Attach");
        attach.setBounds(430,110,50,40);
        add(attach);
        messageDraft.addKeyListener(this);
        send.addActionListener(this);
        messageDraft.setLineWrap(true);
        attach.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == send){
            System.out.println("send");
        }
        else if(e.getSource() == attach){
            JFileChooser fc = new JFileChooser();
            int returnValue = fc.showOpenDialog(this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("ENTER");
            System.out.println(messageDraft.getText());
            messageDraft.setText("");
            e.consume();
            messageDraft.moveCaretPosition(0);


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

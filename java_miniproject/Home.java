package java_miniproject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class Home {
    JFrame frame;
    private String user;
    JPanel listPanel;
    JLabel countLabel;
    Home thisClass;

    Home(String u) {
        user = u;
        thisClass = this;
        frame = new JFrame("User: " + user);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500);
        frame.setName("Home");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        initUI();
        populate();
        frame.setVisible(true);
    }

    private void initUI() {

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel1.setBackground(Styles.primary_white);
        JLabel label1 = new JLabel("User: " + user);
        label1.setFont(Styles.big_label_font);
        label1.setForeground(Styles.primary_violet);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel2.setBackground(Styles.primary_white);
        countLabel = new JLabel("You have 0 passwords saved.");

        JPanel panel5 = new JPanel(new FlowLayout());
        panel5.setBackground(Styles.primary_white);
        JButton addbtn = new JButton("Add new");
        addbtn.setBackground(Styles.primary_button_color);
        addbtn.setBorder(Styles.white_button_border);
        addbtn.setForeground(Color.WHITE);
        addbtn.setMinimumSize(new Dimension(500, 20));

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(Styles.primary_white);
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(listPanel);

        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel4.setBackground(Styles.primary_white);
        JButton changePassword = new JButton("Edit password");
        changePassword.setBackground(Styles.primary_button_color);
        changePassword.setBorder(Styles.white_button_border);
        changePassword.setForeground(Color.WHITE);
        JButton changeprivacykey = new JButton("Edit key");
        changeprivacykey.setBackground(Styles.primary_button_color);
        changeprivacykey.setBorder(Styles.white_button_border);
        changeprivacykey.setForeground(Color.WHITE);

        panel1.add(Box.createHorizontalStrut(30));
        panel1.add(label1);
        panel2.add(Box.createHorizontalStrut(30));
        panel2.add(countLabel);
        panel3.add(listPanel, BorderLayout.CENTER);
        panel3.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        panel3.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
        panel3.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
        panel3.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
        panel4.add(changePassword);
        panel4.add(changeprivacykey);
        panel5.add(addbtn);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel5);
        frame.add(panel4);

        addbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new NewPassword(user, thisClass);
            }
        });
        changePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ChangeProperty(user, "password");
            }
        });
        changeprivacykey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new ChangeProperty(user, "privacy key");
            }
        });
    }

    public void populate() {
        int i = 1;
        listPanel.removeAll();
        try {
            UserDBUtilities db = new UserDBUtilities();
            ArrayList<UserInfo> data = db.getUserInfo(user);
            for(UserInfo user_: data) {
                listPanel.add(new ListItem(this, user, user_.websiteName, user_.username, user_.password, i));
                i++;
            }
            db.endConnection();
        } catch(Exception e) {
            System.out.println(e);
            new ErrorDialog("Error", "Could not load data !!");
        }
        countLabel.setText("You have " + (i-1) + " passwords saved.");
        if(listPanel.getComponentCount() == 0) {
            JLabel emptyLabel = new JLabel("Oops! You have no passwords saved. :)");
            emptyLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            emptyLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
            emptyLabel.setForeground(new Color(166, 166, 166));
            listPanel.add(emptyLabel);
        }
    }
}
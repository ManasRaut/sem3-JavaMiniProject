package java_miniproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import javax.swing.*;

public class ListItem extends JPanel {
    private String acc;
    private JLabel userLabel;
    private JLabel srno;
    private JPanel thisPanel;
    private JLabel webname;
    private String password;
    private String user;
    ListItem thisClass;
    Home home;

    ListItem(Home h, String a, String w, String u, String p, int sr) {
        thisPanel = this;
        thisClass = this;
        home = h;
        user = u;
        acc = a;
        userLabel = new JLabel(u);
        webname = new JLabel(w);
        srno = new JLabel(String.valueOf(sr));
        password = p;
        Dimension size = new Dimension(600, 30);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        this.add(srno);
        this.add(webname);
        this.add(userLabel);

        Border noneBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 148, 214));
        this.setBorder(noneBorder);
        Color hoverColor = new Color(213, 185, 227);
        Color noneColor = new Color(238, 238, 238);
        Color clickColor = new Color(176, 202, 225);

        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    new ActionCnfm(acc, thisClass);
                }
            }
            public void mousePressed(MouseEvent e) {
                thisPanel.setBackground(clickColor);
            }
            public void mouseReleased(MouseEvent e) {
                thisPanel.setBackground(hoverColor);
            }
			public void mouseEntered(MouseEvent e) {
                thisPanel.setBackground(hoverColor);
			}
			public void mouseExited(MouseEvent e) {
                thisPanel.setBackground(noneColor);
			}
        });
    }

    public void action() {
        new CompleteView(home, acc, webname.getText(), user, password);
    }
}
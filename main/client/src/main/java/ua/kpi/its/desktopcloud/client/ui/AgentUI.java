package ua.kpi.its.desktopcloud.client.ui;

import ua.kpi.its.desktopcloud.client.ClientAgent;
import jade.domain.FIPAException;
import org.hibernate.HibernateException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Sergei Tsypanov
 * Date: 04.04.14
 * Time: 15:15
 */
public class AgentUI extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(AgentUI.class.getName());
    private ClientAgent myAgent;

    private JTextField nameField;
    private JTextField passwordField;

    static {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to set Nimbus as LaF", e);
        }
    }

    public AgentUI(ClientAgent a) {
        super(a.getLocalName());

        myAgent = a;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 2));

        p.add(new JLabel("User name:"));
        nameField = new JTextField(16);
        p.add(nameField);

        p.add(new JLabel("Password:"));
        passwordField = new JTextField(16);
        p.add(passwordField);

        getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Enable");

        addButton.addActionListener(new Listener());

        p = new JPanel();
        p.add(addButton);
        getContentPane().add(p, BorderLayout.SOUTH);

        addWindowListener(new WindowListener());
        setResizable(false);
    }

    public void showUI(){
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        setVisible(true);
    }


    private class Listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            final String name = nameField.getText().trim();
            final String password = passwordField.getText().trim();
            try {
                setState(JFrame.ICONIFIED);
                myAgent.register(name, password);
//                nameField.setText("");
//                passwordField.setText("");
            } catch (HibernateException e){
                JOptionPane.showMessageDialog(null,
                        "Failed to save Active User with name = . " +
                                name + " and password " + password + "\n" +
                                e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.log(Level.SEVERE,
                        "Saving active user with " + name + " and password: " + password + " failed",
                        e);
            } catch (FIPAException e) {
                JOptionPane.showMessageDialog(null,
                        "Failed to register agent as service.\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.log(Level.SEVERE,
                        "Failed to register agent as service.",
                        e);
            }
        }
    }

    private class WindowListener extends WindowAdapter{

        @Override
        public void windowClosing(WindowEvent e) {
            dispose();
            myAgent.takeDown();
        }
    }
}

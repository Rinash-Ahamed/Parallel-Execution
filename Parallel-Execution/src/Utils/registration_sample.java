package Utils;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

public class registration_sample {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("JFrame Registration Demo");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel headingPanel = new JPanel();
		JLabel headingLabel = new JLabel("This is the heading panel for our demo course");
		headingPanel.add(headingLabel);

		// Panel to define the layout. We are using GridBagLayout
		JPanel panel = new JPanel(new GridBagLayout());
		// Constraints for the layout
		GridBagConstraints constr = new GridBagConstraints();
		constr.insets = new Insets(5, 5, 5, 5);
		constr.anchor = GridBagConstraints.WEST;

		// Set the initial grid values to 0,0
		constr.gridx = 0;
		constr.gridy = 0;

		// Declare the required Labels
		JLabel userNameLabel = new JLabel("Enter your name :");
		JLabel pwdLabel = new JLabel("Enter your password :");
		JLabel emailLabel = new JLabel("Enter email :");

		// Declare Text fields
		JTextField userNameTxt = new JTextField(20);
		JPasswordField pwdTxt = new JPasswordField(20);
		JTextField emailTxt = new JTextField(20);

		panel.add(userNameLabel, constr);
		constr.gridx = 1;
		panel.add(userNameTxt, constr);
		constr.gridx = 0;
		constr.gridy = 1;

		panel.add(pwdLabel, constr);
		constr.gridx = 1;
		panel.add(pwdTxt, constr);
		constr.gridx = 0;
		constr.gridy = 2;

		panel.add(emailLabel, constr);
		constr.gridx = 1;
		panel.add(emailTxt, constr);
		constr.gridy = 3;

		constr.gridwidth = 2;
		constr.anchor = GridBagConstraints.CENTER;

		// Button with text "Register"
		JButton button = new JButton("Register");
		// add a listener to button
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				headingLabel.setText("Thanks for registering. We'll get back to you shortly.");

			}
		});

		// Add label and button to panel
		panel.add(button, constr);

		mainPanel.add(headingPanel);
		mainPanel.add(panel);

		// Add panel to frame
		frame.add(mainPanel);
		frame.pack();
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

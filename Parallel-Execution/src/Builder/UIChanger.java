package Builder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class UIChanger extends JFrame {

	private String ui[] = { "Metal", "Motif", "Windows" };

	private UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();

	private JRadioButton radio[] = new JRadioButton[ui.length];
	private JButton button = new JButton("JButton");

	private JLabel label = new JLabel("This is a Metal look-and-feel", SwingConstants.CENTER);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox comboBox = new JComboBox(ui);

	public UIChanger() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(3, 1, 0, 5));
		northPanel.add(label);
		northPanel.add(button);
		northPanel.add(comboBox);
		add(northPanel, BorderLayout.NORTH);
		JPanel southPanel = new JPanel();

		ItemHandler handler = new ItemHandler();
		southPanel.setLayout(new GridLayout(1, radio.length));

		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < radio.length; i++) {
			radio[i] = new JRadioButton(ui[i]);
			radio[i].addItemListener(handler);
			group.add(radio[i]);
			southPanel.add(radio[i]);
		}

		add(southPanel, BorderLayout.SOUTH);

		setSize(300, 200);
		setVisible(true);
		radio[0].setSelected(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void changeTheLookAndFeel(int value) {
		try {
			UIManager.setLookAndFeel(looks[value].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		UIChanger dx = new UIChanger();
	}

	private class ItemHandler implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			for (int i = 0; i < radio.length; i++)
				if (radio[i].isSelected()) {
					label.setText("This is a " + ui[i] + " look-and-feel");
					comboBox.setSelectedIndex(i);
					changeTheLookAndFeel(i);
				}
		}
	}
}

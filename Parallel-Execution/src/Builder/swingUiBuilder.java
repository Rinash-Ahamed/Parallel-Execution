package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.testng.annotations.Test;
import test.*;

public class swingUiBuilder extends BrowserFactory {

	static JFrame f, f1;

	JRadioButton r, r1;

	JButton b, b1, b3, listenButton, consoleButton;

	static JTextArea textOut;

	JTextArea listenArea;

	JTextField clazz, method;

	static JTextField packagee;

	JLabel mainLabel, chrome, ff, edge, ie, console, c1Label, c2Label, c3Label, c4Label, c5Label, singleLabel,
			parallelLabel, pLabelText, sLabelText, pack, cls, meth;

	JCheckBox c1, c2, c3, c4, c5;

	Font consoleFont, tiny;

	static Class<?> classObj = null;

	int count = 0;

	public String getHostName() {
		String hostname = "Unknown";
		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		} catch (UnknownHostException ex) {
			System.out.println("Hostname can not be resolved");
		}
		return hostname + "/" + System.getProperty("user.name");
	}

	public swingUiBuilder() throws IOException {

		f = new JFrame(getHostName() + " - TimeSavior");
		// f.setIconImage(ImageIO.read(new File("./files/icon.png")));
		try {
			// f.setIconImage(new
			// ImageIcon(getClass().getResource("icon2.png")).getImage());
			f.setIconImage(ImageIO.read(ClassLoader.getSystemResource("icon2.png")));
		} catch (Exception e) {
		}

		mainLabel = new JLabel("Please select your choice of execution:");
		mainLabel.setBounds(20, 30, 250, 20);
		f.add(mainLabel);

		r = new JRadioButton("Single");
		r.setBounds(250, 30, 60, 20);
		r1 = new JRadioButton("Parallel");
		r1.setBounds(320, 30, 80, 20);
		f.add(r);
		f.add(r1);
		ButtonGroup radio = new ButtonGroup();
		radio.add(r);
		radio.add(r1);

		b = new JButton("Quit");
		b.setBounds(470, 250, 80, 40);
		f.add(b);

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		b3 = new JButton("Stop");
		b3.setBounds(470, 200, 80, 40);
		f.add(b3);
		b3.setVisible(false);

		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				//
				//
			}
		});

		b1 = new JButton("Execute");
		b1.setBounds(470, 300, 80, 40);
		f.add(b1);

		singleLabel = new JLabel("Please select browser for execution");
		singleLabel.setBounds(20, 70, 250, 20);
		f.add(singleLabel);
		singleLabel.setVisible(false);

		String s1[] = { "Select Browser", "Chrome", "Firefox", "IE", "Edge" };
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox browsers = new JComboBox(s1);
		browsers.setBounds(20, 100, 130, 20);
		browsers.setToolTipText("Select any browser");
		f.add(browsers);
		browsers.setVisible(false);

		chrome = new JLabel();
		chrome.setForeground(Color.RED);
		chrome.setBounds(170, 93, 150, 30);
		f.add(chrome);
		chrome.setVisible(false);
		ff = new JLabel();
		ff.setForeground(Color.RED);
		ff.setBounds(170, 93, 150, 30);
		f.add(ff);
		ff.setVisible(false);
		edge = new JLabel();
		edge.setForeground(Color.RED);
		edge.setBounds(170, 93, 150, 30);
		f.add(edge);
		edge.setVisible(false);
		ie = new JLabel();
		ie.setForeground(Color.RED);
		ie.setBounds(170, 93, 150, 30);
		f.add(ie);
		ie.setVisible(false);

		// To get the package, class and method from other classes
		pack = new JLabel("Package");
		pack.setBounds(320, 75, 60, 20);
		packagee = new JTextField();
		packagee.setBounds(380, 75, 200, 20);
		packagee.setVisible(false);
		pack.setLabelFor(packagee);
		pack.setToolTipText("Enter your Package name here");
		pack.setVisible(false);

		packagee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		cls = new JLabel("Class");
		cls.setBounds(320, 100, 40, 20);
		clazz = new JTextField();
		clazz.setBounds(380, 100, 200, 20);
		clazz.setVisible(false);
		cls.setLabelFor(clazz);
		cls.setToolTipText("Enter your Class name here");
		cls.setVisible(false);

		clazz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clazz.setText("");
			}
		});

		meth = new JLabel("Method");
		meth.setBounds(320, 125, 60, 20);
		method = new JTextField();
		method.setBounds(380, 125, 200, 20);
		method.setVisible(false);
		meth.setLabelFor(method);
		meth.setToolTipText("Enter your Method name here");
		meth.setVisible(false);

		method.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				method.setText("");
			}
		});

		tiny = new Font("Verdana", Font.BOLD, 10);
		packagee.setFont(tiny);
		clazz.setFont(tiny);
		method.setFont(tiny);

		f.add(clazz);
		f.add(pack);
		f.add(cls);
		f.add(meth);
		f.add(method);
		f.add(packagee);

		browsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				b1.setEnabled(true);
				b3.setEnabled(true);
				packagee.setVisible(true);
				clazz.setVisible(true);
				method.setVisible(true);
				pack.setVisible(true);
				cls.setVisible(true);
				meth.setVisible(true);

				if (browsers.getSelectedItem().equals("Chrome")) {
					ImageIcon setChrome = getImageFromSystemResource("chrome", ".png");
					chrome.setText(browsers.getSelectedItem() + " Selected");
					chrome.setIcon(setChrome);
					chrome.setVisible(true);
					ff.setVisible(false);
					ie.setVisible(false);
					edge.setVisible(false);
				} else if (browsers.getSelectedItem().equals("Firefox")) {
					ImageIcon setFF = getImageFromSystemResource("firefox", ".png");
					ff.setText(browsers.getSelectedItem() + " Selected");
					ff.setIcon(setFF);
					chrome.setVisible(false);
					ff.setVisible(true);
					ie.setVisible(false);
					edge.setVisible(false);
				} else if (browsers.getSelectedItem().equals("IE")) {
					ImageIcon setIE = getImageFromSystemResource("explorer", ".png");
					ie.setText(browsers.getSelectedItem() + " Selected");
					ie.setIcon(setIE);
					chrome.setVisible(false);
					ff.setVisible(false);
					ie.setVisible(true);
					edge.setVisible(false);
				} else if (browsers.getSelectedItem().equals("Edge")) {
					ImageIcon setEdge = getImageFromSystemResource("edge", ".png");
					edge.setText(browsers.getSelectedItem() + " Selected");
					edge.setIcon(setEdge);
					chrome.setVisible(false);
					ff.setVisible(false);
					ie.setVisible(false);
					edge.setVisible(true);
				} else if (browsers.getSelectedItem().equals("Select Browser")) {
					packagee.setVisible(false);
					clazz.setVisible(false);
					method.setVisible(false);
					pack.setVisible(false);
					cls.setVisible(false);
					meth.setVisible(false);
					b1.setEnabled(false);
					b3.setEnabled(false);
				}
			}
		});

		parallelLabel = new JLabel("Please make your browser selection");
		parallelLabel.setBounds(20, 70, 250, 20);
		f.add(parallelLabel);
		parallelLabel.setVisible(false);

		pLabelText = new JLabel("<html>" + "Parallel MODE selected for execution" + "</html>");
		pLabelText.setForeground(Color.RED);
		pLabelText.setBounds(400, 30, 200, 30);
		pLabelText.setVisible(false);
		f.add(pLabelText);

		sLabelText = new JLabel("<html>" + "Single MODE selected for" + "<br>" + " execution" + "</html>");
		sLabelText.setForeground(Color.RED);
		sLabelText.setBounds(400, 30, 200, 30);
		sLabelText.setVisible(false);
		f.add(sLabelText);

		c1 = new JCheckBox("Chrome");
		c1.setBounds(20, 100, 80, 20);
		f.add(c1);
		c1.setVisible(false);

		c1Label = new JLabel();
		c1Label.setBounds(400, 90, 40, 40);
		c1Label.setVisible(false);
		f.add(c1Label);

		c2 = new JCheckBox("Firefox");
		c2.setBounds(100, 100, 80, 20);
		f.add(c2);
		c2.setVisible(false);

		c2Label = new JLabel();
		c2Label.setBounds(430, 90, 40, 40);
		c2Label.setVisible(false);
		f.add(c2Label);

		c3 = new JCheckBox("IE");
		c3.setBounds(180, 100, 40, 20);
		f.add(c3);

		c3Label = new JLabel();
		c3Label.setBounds(460, 90, 80, 40);
		c3Label.setVisible(false);
		f.add(c3Label);

		c3.setVisible(false);
		c4 = new JCheckBox("Edge");
		c4.setBounds(240, 100, 60, 20);
		f.add(c4);
		c4.setVisible(false);

		c4Label = new JLabel();
		c4Label.setBounds(490, 90, 80, 40);
		c4Label.setVisible(false);
		f.add(c4Label);

		c5 = new JCheckBox("Incognito");
		c5.setBounds(300, 100, 80, 20);
		c5.setVisible(false);
		f.add(c5);

		c5Label = new JLabel();
		c5Label.setBounds(520, 90, 80, 40);
		c5Label.setVisible(false);
		f.add(c5Label);

		List<JCheckBox> checkBoxes = new LinkedList<JCheckBox>();
		// ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
		checkBoxes.add(c1);
		checkBoxes.add(c2);
		checkBoxes.add(c3);
		checkBoxes.add(c4);
		checkBoxes.add(c5);

		// new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent event) {
		//
		// }
		// // JCheckBox checkbox = (JCheckBox) event.getSource();
		// // int index = checkBoxes.indexOf(checkbox) + 1;
		// // System.out.println("Checkbox #" + index + " is clicked");
		// if (c1.isSelected()) {
		// count++;
		// }
		// if (c2.isSelected()) {
		// count++;
		// }
		// if (c3.isSelected()) {
		// count++;
		// }
		// if (c4.isSelected()) {
		// count++;
		// }
		// System.out.println(count);
		// }
		// };
		// c1.addActionListener(actionListener);
		// c2.addActionListener(actionListener);
		// c3.addActionListener(actionListener);
		// c4.addActionListener(actionListener);

		c1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ImageIcon setChrome = getImageFromSystemResource("chrome", ".png");
					c1Label.setIcon(setChrome);
					c1Label.setVisible(true);
					// incognito visibility
					// System.out.println(c1.getText() + " enabled");
					count++;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					c1Label.setVisible(false);
					// System.out.println(c1.getText() + " disabled");
					count--;
				}
			}
		});

		c2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ImageIcon setFF = getImageFromSystemResource("firefox", ".png");
					c2Label.setIcon(setFF);
					c2Label.setVisible(true);
					// System.out.println(c2.getText() + " enabled");
					count++;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					c2Label.setVisible(false);
					// System.out.println(c2.getText() + " disabled");
					count--;
				}
			}
		});

		c3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ImageIcon setIE = getImageFromSystemResource("explorer", ".png");
					c3Label.setIcon(setIE);
					c3Label.setVisible(true);
					// System.out.println(c3.getText() + " enabled");
					count++;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					c3Label.setVisible(false);
					// System.out.println(c3.getText() + " disabled");
					count--;
				}
			}
		});

		c4.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ImageIcon setEdge = getImageFromSystemResource("edge", ".png");
					c4Label.setIcon(setEdge);
					c4Label.setVisible(true);
					// System.out.println(c4.getText() + " enabled");
					count++;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					c4Label.setVisible(false);
					// System.out.println(c4.getText() + " disabled");
					count--;
				}
			}
		});

		c5.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ImageIcon setcIG = getImageFromSystemResource("incognito", ".png");
					c5Label.setIcon(setcIG);
					c5Label.setVisible(true);
					// System.out.println(c5.getText() + " enabled");
					count++;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					c5Label.setVisible(false);
					// System.out.println(c5.getText() + " disabled");
					count--;
				}
			}
		});

		JButton consoleButton, listenerButton;
		consoleButton = new JButton("Console");
		consoleButton.setBackground(Color.BLACK);
		consoleButton.setForeground(Color.WHITE);
		consoleButton.setBounds(120, 132, 80, 15);
		f.add(consoleButton);
		consoleButton.setVisible(false);

		listenerButton = new JButton("Listener");
		listenerButton.setBounds(205, 132, 100, 15);
		listenerButton.setBackground(Color.BLACK);
		listenerButton.setForeground(Color.WHITE);
		f.add(listenerButton);
		listenerButton.setVisible(false);

		r.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					b1.setEnabled(true);
					b3.setEnabled(true);

					parallelLabel.setVisible(false);
					singleLabel.setVisible(true);
					browsers.setVisible(true);
					sLabelText.setVisible(true);
					pLabelText.setVisible(false);

					// checkBox
					c1.setVisible(false);
					c1.setSelected(false);
					c1Label.setVisible(false);
					c2.setVisible(false);
					c2.setSelected(false);
					c2Label.setVisible(false);
					c3.setVisible(false);
					c3.setSelected(false);
					c3Label.setVisible(false);
					c4.setVisible(false);
					c4.setSelected(false);
					c4Label.setVisible(false);
					c5.setVisible(false);
					c5.setSelected(false);
					c5Label.setVisible(false);

					// console
					// consoleButton.setVisible(true);
					// listenerButton.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		r1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					b1.setEnabled(true);
					b3.setEnabled(true);

					singleLabel.setVisible(false);
					parallelLabel.setVisible(true);
					pLabelText.setVisible(true);
					sLabelText.setVisible(false);

					// ComboBox
					browsers.setVisible(false);
					chrome.setVisible(false);
					ff.setVisible(false);
					ie.setVisible(false);
					edge.setVisible(false);

					// checkBox
					c1.setVisible(true);
					c2.setVisible(true);
					c3.setVisible(true);
					c4.setVisible(true);
					c5.setVisible(true);

					// console
					// consoleButton.setVisible(false);
					// listenerButton.setVisible(false);

					packagee.setVisible(false);
					clazz.setVisible(false);
					method.setVisible(false);
					pack.setVisible(false);
					cls.setVisible(false);
					meth.setVisible(false);

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				b3.setVisible(true);

				if (r.isSelected() == false && r1.isSelected() == false) {
					System.out.println("Please select any Mode before proceeding...");
					b1.setEnabled(false);
					b3.setEnabled(false);
				}

				if (r.isSelected()) {
					try {
						if (browsers.getSelectedItem().equals("Select Browser")) {
							System.out.println("Please select any Browser before proceeding...");
							b1.setEnabled(false);
							b3.setEnabled(false);
						} else if (browsers.getSelectedItem().equals("Chrome")) {
							try {
								if (packagee.getText().isEmpty() && clazz.getText().isEmpty()
										&& method.getText().isEmpty()) {
									JOptionPane.showMessageDialog(packagee,
											"Beware: You left the textfields are blank");
								} else if (packagee.getText().isEmpty()) {
									JOptionPane.showMessageDialog(packagee, "You left the Package field is blank");
								} else if (clazz.getText().isEmpty()) {
									JOptionPane.showMessageDialog(clazz, "You left the Class field is blank");
								} else if (method.getText().isEmpty()) {
									JOptionPane.showMessageDialog(method, "You left the Method field is blank");
								} else {
									getClassandMethodsList(clazz.getText());
									getMethod(clazz.getText(), method.getText(), browsers.getSelectedItem().toString());
								}
							} catch (Exception e1) {
								System.out.println("Failure in identifying the root of package, class or method");
								e1.printStackTrace();
							}
						} else if (browsers.getSelectedItem().equals("IE")) {
							try {
								if (packagee.getText().isEmpty() && clazz.getText().isEmpty()
										&& method.getText().isEmpty()) {
									JOptionPane.showMessageDialog(packagee,
											"Beware: You left the textfields are blank");
								} else if (packagee.getText().isEmpty()) {
									JOptionPane.showMessageDialog(packagee, "You left the Package field is blank");
								} else if (clazz.getText().isEmpty()) {
									JOptionPane.showMessageDialog(clazz, "You left the Class field is blank");
								} else if (method.getText().isEmpty()) {
									JOptionPane.showMessageDialog(method, "You left the Method field is blank");
								} else {
									getClassandMethodsList(clazz.getText());
									getMethod(clazz.getText(), method.getText(), browsers.getSelectedItem().toString());
								}
							} catch (Exception e1) {
								System.out.println("Failure in identifying the root of package, class or method");
								e1.printStackTrace();
							}
						} else if (browsers.getSelectedItem().equals("Firefox")) {
							try {
								if (packagee.getText().isEmpty() && clazz.getText().isEmpty()
										&& method.getText().isEmpty()) {
									JOptionPane.showMessageDialog(packagee,
											"Beware: You left the textfields are blank");
								} else if (packagee.getText().isEmpty()) {
									JOptionPane.showMessageDialog(packagee, "You left the Package field is blank");
								} else if (clazz.getText().isEmpty()) {
									JOptionPane.showMessageDialog(clazz, "You left the Class field is blank");
								} else if (method.getText().isEmpty()) {
									JOptionPane.showMessageDialog(method, "You left the Method field is blank");
								} else {
									getClassandMethodsList(clazz.getText());
									getMethod(clazz.getText(), method.getText(), browsers.getSelectedItem().toString());
								}
							} catch (Exception e1) {
								System.out.println("Failure in identifying the root of package, class or method");
								e1.printStackTrace();
							}
						} else if (browsers.getSelectedItem().equals("edge")) {
							try {
								if (packagee.getText().isEmpty() && clazz.getText().isEmpty()
										&& method.getText().isEmpty()) {
									JOptionPane.showMessageDialog(packagee,
											"Beware: You left the textfields are blank");
								} else if (packagee.getText().isEmpty()) {
									JOptionPane.showMessageDialog(packagee, "You left the Package field is blank");
								} else if (clazz.getText().isEmpty()) {
									JOptionPane.showMessageDialog(clazz, "You left the Class field is blank");
								} else if (method.getText().isEmpty()) {
									JOptionPane.showMessageDialog(method, "You left the Method field is blank");
								} else {
									getClassandMethodsList(clazz.getText());
									getMethod(clazz.getText(), method.getText(), browsers.getSelectedItem().toString());
								}
							} catch (Exception e1) {
								System.out.println("Failure in identifying the root of package, class or method");
								e1.printStackTrace();
							}
						} else {
							System.out.println("Problem with dropdown selection");
						}
					} catch (Exception e1) {
						System.out.println("Exception in Single MODE " + e1.toString());
					}
				}

				if (r1.isSelected()) {

					StringBuilder sb = new StringBuilder();
					sb.append("Selected Check Boxes: ");
					for (JCheckBox checkBox : checkBoxes) {
						if (checkBox.isSelected()) {
							sb.append(checkBox.getText()).append(' ');
						}
						System.lineSeparator();
					}

					System.out.println("Total Checkbox selected: " + count);
					System.out.println(sb);
					String selectTwo = "Please select atleast two browsers";
					try {
						if (c1.isSelected() == true && c2.isSelected() == false && c3.isSelected() == false
								&& c4.isSelected() == false && c5.isSelected() == false) {
							System.out.println(selectTwo);
						}
						if (c1.isSelected() == false && c2.isSelected() == true && c3.isSelected() == false
								&& c4.isSelected() == false) {
							System.out.println(selectTwo);
						}
						if (c1.isSelected() == false && c2.isSelected() == false && c3.isSelected() == true
								&& c4.isSelected() == false) {
							System.out.println(selectTwo);
						}
						if (c1.isSelected() == false && c2.isSelected() == false && c3.isSelected() == false
								&& c4.isSelected() == true) {
							System.out.println(selectTwo);
						}
						if (c1.isSelected() == false && c2.isSelected() == false && c3.isSelected() == false
								&& c4.isSelected() == false && c5.isSelected() == true) {
							System.out.println(selectTwo);
						}
						if (c1.isSelected() == false && c2.isSelected() == false && c3.isSelected() == false
								&& c4.isSelected() == false && c5.isSelected() == false) {
							System.out.println("Please select any checkkbox before proceeding...");
						}
						if (c1.isSelected() == true && c2.isSelected() == true && c3.isSelected() == true
								&& c4.isSelected() == true && c5.isSelected() == true) {
							System.out.println("WIP --all");
							// paralleTest("allBrowsers");
						}
//						if (c1.isSelected() == true && c2.isSelected() == true) {
//							System.out.println("WIP");
//						}
//						if (c1.isSelected() == true && c3.isSelected() == true) {
//							System.out.println("WIP --chrome, ie");
//							// paralleTest("ie,chrome");
//						}
//						if (c1.isSelected() == true && c4.isSelected() == true) {
//							System.out.println("WIP");
//						}
//						if (c1.isSelected() == true && c5.isSelected() == true) {
//							System.out.println("WIP --chrome, ig");
//							paralleTest("chrome,ig");
//						}
						if (c1.isSelected() == true && c3.isSelected() == true && c5.isSelected() == true) {
							System.out.println("WIP - chrome, ie,ig");
							 paralleTest("ie,chrome,ig");
						}
//						if (c2.isSelected() == true && c5.isSelected() == true && c3.isSelected() == true) {
//							// paralleTest("ie,ig,firefox");
//							System.out.println("WIP");
//						}
//						if (c3.isSelected() == true && c5.isSelected() == true) {
//							paralleTest("ie,ig");
//						}
//						if (c2.isSelected() == true && c3.isSelected()) {
//							System.out.println("WIP");
//						}
//						if (c3.isSelected() == true && c4.isSelected()) {
//							System.out.println("WIP");
//						}
					} catch (Exception e1) {
						System.out.println("Exception in Parallel MODE");
					}
				}
			}
		});

		console = new JLabel("Console Output:");
		console.setBounds(20, 130, 100, 20);
		f.add(console);

		// ButtonGroup consoleBG = new ButtonGroup();
		// consoleBG.add(consoleButton);
		// consoleBG.add(listenerButton);

		JPanel scrollpanel = new JPanel();
		// scrollpanel.setBorder(new TitledBorder(new EtchedBorder(), "Console
		// Output"));
		scrollpanel.setBounds(19, 149, 435, 205);

		textOut = new JTextArea();
		textOut.setBackground(Color.BLACK);
		textOut.setForeground(Color.WHITE);
		consoleFont = new Font("Verdana", Font.BOLD, 8);
		textOut.setFont(consoleFont);
		textOut.setEditable(true);
		textOut.setLineWrap(false);

		JScrollPane scroll = new JScrollPane(textOut);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(430, 205));
		scrollpanel.add(scroll);
		f.add(scrollpanel);

		// listenArea = new JTextArea();
		// listenArea.setBounds(20, 150, 430, 200);
		// listenArea.setBackground(Color.gray);
		// listenArea.setForeground(Color.BLACK);
		// Font fonts = new Font("Verdana", Font.BOLD, 8);
		// listenArea.setFont(fonts);

		// listenerButton.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// textOut.setVisible(false);
		// listenArea.setVisible(true);
		//
		// PrintStream printStream1 = new PrintStream(new
		// CustomOutputStream(listenArea));
		// System.setOut(printStream1);
		// System.setErr(printStream1);
		// }
		// });

		// consoleButton.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// listenArea.setVisible(false);
		// textOut.setVisible(true);
		// }
		// });
		// f.add(listenArea);

	}

	public static void getClassandMethodsList(String className) {
		try {
			newInstance(className);

			Method[] methods = classObj.getDeclaredMethods();
			System.out.println("Time Savior Runtime logs");
			System.out.print("Available declared methods in class '" + className + "' are: ");
			for (Method method : methods) {
				String MethodName = method.getName();
				System.out.print(MethodName + "(),");
				// method.getParameterTypes();
			}
			System.out.print("." + System.lineSeparator());

			// Method m = classObj.getDeclaredMethod(methodName);
			// for (int i = 0; i < methods.length; i++) {
			// System.out.println(methods[i].toString());
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object newInstance(String className) throws Exception {
		try {
			String packageName = packagee.getText();
			classObj = Class.forName(packageName + "." + className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classObj.newInstance();
	}

	public static void getMethod(String className, String methodName, String param) throws Exception {
		String packageName = packagee.getText();
		classObj = Class.forName(packageName + "." + className);
		Method m = classObj.getDeclaredMethod(methodName, String.class);
		m.invoke(newInstance(className), param);
	}

	@Test
	public void paralleTest(String xmlFileName) {
		Master.runner(xmlFileName);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new swingUiBuilder();
					// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				f.setSize(600, 400);
				f.setLayout(null);
				f.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
				f.setVisible(true);
				f.setResizable(false);

				PrintStream printStream = new PrintStream(new CustomOutputStream(textOut));
				System.setOut(printStream);
				System.setErr(printStream);
			}
		});
	}

	public ImageIcon getImageFromSystemResource(String imageName, String type) {
		ImageIcon sysUrl = null;
		try {
			sysUrl = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(imageName + type)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return sysUrl;

	}

}
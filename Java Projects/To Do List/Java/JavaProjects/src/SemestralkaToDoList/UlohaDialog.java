package SemestralkaToDoList;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Calendar;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;

public class UlohaDialog extends JDialog {

	/**
	 * Tato trieda mi bude vytvarat JDIalog pre ulohu 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblNewLabel;
	private JDateChooser dateChooser;
	private JSpinField spinFieldHodiny;
	private JSpinField spinFieldMinuty;
	private JRadioButton rdbtnVelmiDolezite;
	private JRadioButton rdbtnDolezite;
	private JRadioButton rdbtnMenejDolezite;
	private JLabel lblNewLabel_1;
	private Calendar dnesnyDatum;
	private ButtonGroup btnGroup;
	private JComboBox comboBoxUpozornenie;
	private Uloha novaUloha;
	private JComboBox comboBoxVyberHudby;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private NotifikacieSounds not;
	private JTextField textField_1;

	/**
	 * Tato trieda mi bude vykraslovat dialog pre vytvorenie ulohy
	 * 
	 */
	public static void main(String[] args) {
		

		try {
			UlohaDialog dialog = new UlohaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	
	public UlohaDialog() {
		
		setModal(true); // DOLEZITE!!!! 
		
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setResizable(false);
		setBounds(100, 100, 450, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton buttonPridať = new JButton("Pridať");
				buttonPridať.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pridaj();
						// System.out.println(Integer.parseInt(btnGroup.getSelection().getActionCommand()));
					}
				});
				buttonPridať.setActionCommand("OK");
				buttonPane.add(buttonPridať);
				getRootPane().setDefaultButton(buttonPridať);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textField.setBounds(0, 33, 436, 29);
			panel.add(textField);
			textField.setColumns(10);

			lblNewLabel = new JLabel("Nová úloha");
			lblNewLabel.setForeground(new Color(50, 205, 50));
			lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
			lblNewLabel.setBounds(166, 2, 103, 21);
			panel.add(lblNewLabel);

			dateChooser = new JDateChooser();
			dateChooser.setBounds(10, 72, 133, 29);
			dnesnyDatum = Calendar.getInstance();
			dateChooser.setCalendar(dnesnyDatum);
			panel.add(dateChooser);

			spinFieldHodiny = new JSpinField();
			spinFieldHodiny.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					hodiny();
				}
			});
			spinFieldHodiny.getSpinner().setFont(new Font("Arial", Font.BOLD, 10));
			spinFieldHodiny.setBounds(153, 72, 53, 29);
			spinFieldHodiny.setValue(dnesnyDatum.get(Calendar.HOUR_OF_DAY));
			panel.add(spinFieldHodiny);

			spinFieldMinuty = new JSpinField();
			spinFieldMinuty.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					minuty();
				}
			});
			spinFieldMinuty.getSpinner().setFont(new Font("Arial", Font.BOLD, 10));
			spinFieldMinuty.setBounds(216, 72, 53, 29);
			spinFieldMinuty.setValue(dnesnyDatum.get(Calendar.MINUTE));
			panel.add(spinFieldMinuty);

			rdbtnVelmiDolezite = new JRadioButton("Veľmi dôležité");
			rdbtnVelmiDolezite.setForeground(new Color(255, 69, 0));
			rdbtnVelmiDolezite.setFont(new Font("Arial", Font.BOLD, 10));
			rdbtnVelmiDolezite.setBounds(10, 164, 103, 21);
			panel.add(rdbtnVelmiDolezite);

			rdbtnDolezite = new JRadioButton("Dôležité");
			rdbtnDolezite.setForeground(new Color(65, 105, 225));
			rdbtnDolezite.setFont(new Font("Arial", Font.BOLD, 10));
			rdbtnDolezite.setBounds(166, 164, 103, 21);
			panel.add(rdbtnDolezite);

			rdbtnMenejDolezite = new JRadioButton("Menej dôležité");
			rdbtnMenejDolezite.setForeground(new Color(0, 0, 0));
			rdbtnMenejDolezite.setFont(new Font("Arial", Font.BOLD, 10));
			rdbtnMenejDolezite.setBounds(310, 164, 116, 21);
			panel.add(rdbtnMenejDolezite);

			lblNewLabel_1 = new JLabel("Priorita");
			lblNewLabel_1.setForeground(new Color(240, 128, 128));
			lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 16));
			lblNewLabel_1.setBounds(177, 132, 78, 13);
			panel.add(lblNewLabel_1);

			rdbtnVelmiDolezite.setActionCommand("1");
			rdbtnDolezite.setActionCommand("2");
			rdbtnMenejDolezite.setActionCommand("3");

			btnGroup = new ButtonGroup();
			btnGroup.add(rdbtnDolezite);
			btnGroup.add(rdbtnMenejDolezite);
			btnGroup.add(rdbtnVelmiDolezite);

			String onOff[] = {"On","Off"};
			comboBoxUpozornenie = new JComboBox(onOff);
			comboBoxUpozornenie.setBounds(160, 207, 109, 29);
			panel.add(comboBoxUpozornenie);
			
			String notifikaciaSound[] = {"","Bells","Musical","Reveal"};
			comboBoxVyberHudby = new JComboBox(notifikaciaSound);
			comboBoxVyberHudby.setModel(new DefaultComboBoxModel(new String[] {"None", "Bells", "Musical", "Reveal"}));
			comboBoxVyberHudby.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				String selected = comboBoxVyberHudby.getSelectedItem().toString();
				playSound(selected);
				}
			});
			comboBoxVyberHudby.setBounds(160, 256, 109, 29);
			panel.add(comboBoxVyberHudby);
			
			lblNewLabel_2 = new JLabel("Upozornenie");
			lblNewLabel_2.setForeground(SystemColor.textInactiveText);
			lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(23, 210, 87, 21);
			panel.add(lblNewLabel_2);
			
			lblNewLabel_3 = new JLabel("Zvuk upozornenia");
			lblNewLabel_3.setForeground(SystemColor.textInactiveText);
			lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(23, 259, 127, 21);
			panel.add(lblNewLabel_3);
			
			textField_1 = new JTextField();
			textField_1.setText("55");
			textField_1.setBounds(287, 72, 103, 29);
			panel.add(textField_1);
			textField_1.setColumns(10);
		}
	}
	
	
	
	/**
	 * tato metoda mi spravi to že hodiny nebudu viac ako 23 a ze bude viac tak
	 * dalsi den
	 **/
	private void hodiny() {
		if (spinFieldHodiny.getValue() > 23 && spinFieldHodiny.getValue() < 25) {
			spinFieldHodiny.setValue(0);
			Calendar den = Calendar.getInstance();

			den.set(Calendar.DAY_OF_MONTH, dnesnyDatum.get(Calendar.DAY_OF_MONTH) + 1);
			dnesnyDatum.set(Calendar.DAY_OF_MONTH, dnesnyDatum.get(Calendar.DAY_OF_MONTH) + 1);
			dateChooser.setCalendar(den);
		}
		if (spinFieldHodiny.getValue() < 0) {
			spinFieldHodiny.setValue(0);
		}
		if (spinFieldHodiny.getValue() >= 25) {
			spinFieldHodiny.setValue(dnesnyDatum.get(Calendar.HOUR_OF_DAY));
		}

	}

	/**
	 * tato metoda mi spravi to že minut nebude viac ako 59 a ak ano da dalsiu
	 * hodinu
	 **/
	private void minuty() {
		if (spinFieldMinuty.getValue() > 59 && spinFieldMinuty.getValue() < 61) {
			spinFieldHodiny.setValue(spinFieldHodiny.getValue() + 1);
			spinFieldMinuty.setValue(0);
		}
		if (spinFieldMinuty.getValue() < 0) {
			spinFieldMinuty.setValue(0);
		}
		if (spinFieldMinuty.getValue() > 61) {
			spinFieldMinuty.setValue(dnesnyDatum.get(Calendar.MINUTE));
		}

	}

	/** tato metoda nám vytvorí ulohu a nastavi ju ako atribut aby som nasledne ju mohol ziskat **/
	private void pridaj() {

		String sprava = textField.getText();
		Calendar den = Calendar.getInstance();
		den.setTime(dateChooser.getDate());
		den.set(Calendar.HOUR_OF_DAY, spinFieldHodiny.getValue());
		den.set(Calendar.MINUTE, spinFieldMinuty.getValue());
		den.set(Calendar.SECOND, Integer.parseInt(textField_1.getText()));
		den.set(Calendar.MILLISECOND, 0);
		int priorita = 3;
		
		if (btnGroup.getSelection() != null) {
			priorita = Integer.parseInt(btnGroup.getSelection().getActionCommand());
		} else {
			priorita = 3;
		}
		
		boolean upozornenieOnOff = StringToBooleanPreOnOff(comboBoxUpozornenie.getSelectedItem().toString());
		

		if (dnesnyDatum.compareTo(den) > 0) {
			JOptionPane.showMessageDialog(null, "Datum je uz stary");
		} else {
			NotifikacieSounds vybratySound = notifikaciaSoundStringToEnum(comboBoxVyberHudby.getSelectedItem().toString());
			Uloha newUloha = new Uloha(sprava, den, priorita,upozornenieOnOff,vybratySound);
			this.novaUloha = newUloha;
			dispose();
			
		}
	}
	
	/** tato metoda mi vrati vytovrenu ulohu pre GUI **/
	public Uloha getUloha() {
		return novaUloha;
	}
	/** Tato metoda prehodi string na boolean **/
	private boolean StringToBooleanPreOnOff(String string) {
		if(string.equals("On")) {
			return true;
		}
		else{
			return false;
		}
	}
	/** táto metoda mi da zo Stringu ktorý ziskam z comboxu pi
	 * prída jemu prisluchajuce enum **/
	private NotifikacieSounds notifikaciaSoundStringToEnum(String sound) {
		switch(sound) {
		case "Bells":
			return NotifikacieSounds.BELL;
		case "Musical":
			return NotifikacieSounds.MUSICAL;
		case "Reveal":
			return NotifikacieSounds.REVEAL;
		default:
			return NotifikacieSounds.DEFAULT;
		}
		
	}

	/** tato metoda mi prehra sound ktory bude zvoleny **/
	private void playSound(String vyber) {
		NotifikacieSounds zvolenaNot = notifikaciaSoundStringToEnum(vyber);
		if(zvolenaNot!=null) {
			zvolenaNot.playSound();
		}
	}
}

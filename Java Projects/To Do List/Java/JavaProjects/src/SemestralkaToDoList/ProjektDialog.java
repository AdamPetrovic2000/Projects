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

public class ProjektDialog extends JDialog {

	/**
	 * Tato trieda mi bude vytvarat JDIalog pre projekt ulohu
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblNewLabel;
	private JDateChooser dateChooser;
	private JSpinField spinFieldHodiny;
	private JSpinField spinFieldMinuty;
	private Calendar dnesnyDatum;
	private ButtonGroup btnGroup;
	private ProjektUloha novaUloha;
	private int x;
	private int y;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		

		try {
			ProjektDialog dialog = new ProjektDialog(0,0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	
	public ProjektDialog(int x , int y) {
		this.x = x;
		this.y = y;
		
		setModal(true); // DOLEZITE!!!! 
		
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setResizable(false);
		setBounds(100, 100, 450, 185);
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
		den.set(Calendar.SECOND,0);
		den.set(Calendar.MILLISECOND, 0);

		if (dnesnyDatum.compareTo(den) > 0) {
			JOptionPane.showMessageDialog(null, "Datum je uz stary");
		} else {
			ProjektUloha newUloha = new ProjektUloha(sprava, den,x,y);
			this.novaUloha = newUloha;
			dispose();
			
		}
	}
	
	/** tato metoda mi vrati vytovrenu ulohu pre GUI **/
	public ProjektUloha getUloha() {
		return novaUloha;
	}
	


	
}

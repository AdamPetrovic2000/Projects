package SemestralkaToDoList;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SizeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	int xSize;
	int ySize;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField xTextField;
	private JLabel lblNewLabel_1;
	private JTextField yTextField;

	/**
	 * Tato trieda bude služit na to aby mi vytvorila dialog kde uzivatel zada
	 * velkost obrazku
	 */
	public static void main(String[] args) {
		try {
			SizeDialog dialog = new SizeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SizeDialog() {
		setModal(true);
		setBounds(100, 100, 450, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		contentPanel.add(panel);
		
		lblNewLabel = new JLabel("Zadaj velkosť");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(lblNewLabel);
		
		panel_1 = new JPanel();
		contentPanel.add(panel_1);
		
		xTextField = new JTextField();
		xTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		panel_1.add(xTextField);
		xTextField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("x");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel_1);
		
		yTextField = new JTextField();
		yTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		panel_1.add(yTextField);
		yTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!xTextField.getText().equals("") || !yTextField.getText().equals("")) {
						xSize = Integer.parseInt(xTextField.getText());
						ySize = Integer.parseInt(yTextField.getText());
						dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
	}
}

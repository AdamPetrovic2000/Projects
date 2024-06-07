package SemestralkaToDoList;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class HladajDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldSearchBar;
	private ArrayList<Uloha> poleUloh;
	private JPanel panelHladaneUlohy;
	private UlohaDialog ulohaDialog;
	private JPopupMenu popupMenu;
	private JPopupMenu popupMenuUlohy;
	private JMenuItem newMenuItemEdit;
	private JMenuItem newMenuItemRemove;

	/**
	 * Tato trieda mi spravi JDialog na hladanie uloh a ich upravu
	 */
	public static void main(String[] args) {
		try {
			HladajDialog dialog = new HladajDialog(new ArrayList<>());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HladajDialog(ArrayList<Uloha> poleUloh) {
		setModal(true);

		this.poleUloh = poleUloh;

		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setBounds(100, 100, 585, 560);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				JPanel panelSearch = new JPanel();
				scrollPane.setColumnHeaderView(panelSearch);
				{
					JLabel lblNewLabel = new JLabel("Hladaj : ");
					lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
					panelSearch.add(lblNewLabel);
				}
				{
					textFieldSearchBar = new JTextField();
					textFieldSearchBar.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							hladaj();
						}
					});
					panelSearch.add(textFieldSearchBar);
					textFieldSearchBar.setColumns(20);
				}
			}
			{
				panelHladaneUlohy = new JPanel();
				scrollPane.setViewportView(panelHladaneUlohy);
				{
					popupMenu = new JPopupMenu();
					addPopup(panelHladaneUlohy, popupMenu);
				}
				panelHladaneUlohy.setLayout(new BoxLayout(panelHladaneUlohy, BoxLayout.Y_AXIS));
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
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
	/** tato metoda mi bude hladat ulohy v poli uloh **/
	private void hladaj() {
		String zadanyText = textFieldSearchBar.getText();
		ArrayList<Uloha> poleDoVykresli = new ArrayList<>();
		for (Uloha i : poleUloh) {
			if (i != null) {
				if (i.getDataForExport().toLowerCase().contains(zadanyText.toLowerCase()) || i.getDataForExport().toUpperCase().contains(zadanyText.toUpperCase())){
				poleDoVykresli.add(i);
				//vykresli(poleDoVykresli);
				}
			}
		}
		vykresli(poleDoVykresli);
	}
	/** tato metoda mi vykresli hladane uloh **/
	private void vykresli(ArrayList<Uloha> poleNajdenychUloh) {
		panelHladaneUlohy.removeAll();
		for (int i = 0; i < poleNajdenychUloh.size(); i++) {
			int pozicia = i;
			if (poleNajdenychUloh.get(i) != null) {
				JLabel novyLabel = new JLabel("  " + poleNajdenychUloh.get(i).getDataForExport());
				novyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
				
				popupMenuUlohy = new JPopupMenu();
				addPopup(novyLabel, popupMenuUlohy);

				// vytvram menuItem pre edit uz vytvorenej ulohy
				newMenuItemEdit = new JMenuItem("Edit");
				newMenuItemEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						edit(pozicia);
					}
				});
				popupMenuUlohy.add(newMenuItemEdit);
				
				
				newMenuItemRemove = new JMenuItem("Vymaž");
				newMenuItemRemove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vymaz(pozicia);
					}
				});
				popupMenuUlohy.add(newMenuItemRemove);
				
				
				panelHladaneUlohy.add(novyLabel);
				
			}
		}
		panelHladaneUlohy.repaint();
		panelHladaneUlohy.revalidate();
	}
	
	/** pretaze metoda len pre jednu ulohu **/
	private void vykresli(Uloha uloha) {
		ArrayList<Uloha> pole = new ArrayList<Uloha>();
		pole.add(uloha);
		vykresli(pole);
	}
	
	
	/** tato metoda mi umozny editovat ulohu **/
	private void edit(int index) {
		ulohaDialog = new UlohaDialog();
		ulohaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ulohaDialog.setVisible(true);
		Uloha novaUloha = ulohaDialog.getUloha();
		if(novaUloha!=null) {
			poleUloh.set(index, novaUloha);
			vykresli(novaUloha);
		}
	}
	
	/** tato metoda mi vymaze ulohu z pola **/
	private void vymaz(int pozicia) {
		poleUloh.remove(pozicia);
		vykresli(poleUloh);
	}
	
	public ArrayList<Uloha> getPole() {
		return poleUloh;
	}
	
	/** automaticky vygenerovana metoda pre popup menu **/
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

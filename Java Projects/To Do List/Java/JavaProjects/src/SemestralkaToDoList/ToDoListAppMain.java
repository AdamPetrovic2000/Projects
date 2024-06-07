
package SemestralkaToDoList;

import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.JRadioButtonMenuItem;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;

public class ToDoListAppMain extends JFrame {

	/**
	 * Toto je main trieda ktora bude vykreslovať celu aplikaciu
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panelBocny;
	private JPanel panelHorny;
	private JPanel panelUlohy;
	private ArrayList<Uloha> poleUloh;
	private JPopupMenu popupMenu;
	private JMenuItem menuItemPridatUlohu;
	private UlohaDialog ulohaDialog;
	private JPopupMenu popupMenuUlohy;
	private JMenuItem newMenuItemEdit;
	private JMenuItem newMenuItemRemove;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmNewMenuItem;
	private boolean zobraz = false;
	private JRadioButtonMenuItem rdbtnmntmNewRadioItem;
	private ArrayList<Notifikacia> poleNotifikacii;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmVymazVsetko;
	private JRadioButtonMenuItem rdbtnmntmDatum;
	private JRadioButtonMenuItem rdbtnmntmPriorita;
	private JMenuItem mntmPrint;
	private JMenuItem mntmExport;
	private JLayeredPane layeredPane;
	private JPanel panelU;
	private JMenu mnTodoList;
	private JMenu mnNewMenu_2;
	private ProjektPanel panelProjekt;
	private JMenu mnMenuPreProjekt;
	private JMenuItem mntmProjektTlac;
	private JMenuItem mntmProjektExport;
	private JMenuItem mntmClearProjektPanel;
	private JMenuItem mntmHladaj;

	/*
	 * /** Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoListAppMain frame = new ToDoListAppMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** kostruktor */
	public ToDoListAppMain() {

		poleUloh = new ArrayList<Uloha>(); // tu to budem načitavam tie udaje
		poleNotifikacii = new ArrayList<Notifikacia>(); // teda tu to nemusim
		poleUloh = loadData();
		//panelUlohy = new ProjektPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 591);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		mntmNewMenuItem = new JMenuItem("Pridať ulohu");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pridajUlohu();
			}
		});

		rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Zobraziť datum a caš");
		rdbtnmntmNewRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zobrazDatum();
			}
		});
		mnMenu.add(rdbtnmntmNewRadioItem);
		mnMenu.add(mntmNewMenuItem);

		mnNewMenu_1 = new JMenu("Usporiadaj podľa");
		mnMenu.add(mnNewMenu_1);

		rdbtnmntmPriorita = new JRadioButtonMenuItem("Priority");
		rdbtnmntmPriorita.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				// kontrolaZakliknutnutehoSortuPrePrioritu();
			}
		});
		rdbtnmntmPriorita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kontrolaZakliknutnutehoSortuPrePrioritu();
			}
		});
		mnNewMenu_1.add(rdbtnmntmPriorita);

		rdbtnmntmDatum = new JRadioButtonMenuItem("Datumu a času");
		rdbtnmntmDatum.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				// kontrolaZakliknutnutehoSortuPreDatum();
			}
		});
		rdbtnmntmDatum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kontrolaZakliknutnutehoSortuPreDatum();
			}
		});
		mnNewMenu_1.add(rdbtnmntmDatum);

		mntmVymazVsetko = new JMenuItem("Vymazať všetky ulohy");
		mntmVymazVsetko.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllUlohy();
			}
		});
		mnMenu.add(mntmVymazVsetko);

		mntmPrint = new JMenuItem("Tlač");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();
			}
		});
		mnMenu.add(mntmPrint);

		mntmExport = new JMenuItem("Export");
		mntmExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				export();
			}
		});
		mnMenu.add(mntmExport);
		
		mntmHladaj = new JMenuItem("Hladaj");
		mntmHladaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HladajDialog dialog = new HladajDialog(poleUloh);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				if(dialog.getPole()!=null) {
					poleUloh = dialog.getPole();
					vykresliUlohy();
					nacitajNotifikacie();
					skontrolujNotifikacie();
					saveData();
				}
			}
		});
		mnMenu.add(mntmHladaj);
		
		mnTodoList = new JMenu("Do to list");
		mnTodoList.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				changeToDoList();
				
				mnMenuPreProjekt.setVisible(false);
				mnMenu.setVisible(true);
			}
		});
		
		mnMenuPreProjekt = new JMenu("Menu");
		menuBar.add(mnMenuPreProjekt);
		
		mntmProjektTlac = new JMenuItem("Tlač");
		mntmProjektTlac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProjekt.print();
				
			}
		});
		mnMenuPreProjekt.add(mntmProjektTlac);
		
		mntmProjektExport = new JMenuItem("Export");
		mntmProjektExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProjekt.export();
			}
		});
		mnMenuPreProjekt.add(mntmProjektExport);
		
		mntmClearProjektPanel = new JMenuItem("Vymazať všetko");
		mntmClearProjektPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProjekt.vymazVsetko();
			}
		});
		mnMenuPreProjekt.add(mntmClearProjektPanel);
		menuBar.add(mnTodoList);
		mnMenuPreProjekt.setVisible(false);
		
		
		mnNewMenu_2 = new JMenu("Nástenka (Projekt)");
		mnNewMenu_2.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				changeToPrejekt();
				mnMenu.setVisible(false);
				mnMenuPreProjekt.setVisible(true);
			}
		});
		
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new CardLayout(0, 0));

		scrollPane = new JScrollPane();
		layeredPane.add(scrollPane, "name_883205745581800");

		panelBocny = new JPanel();
		scrollPane.setRowHeaderView(panelBocny);

		panelHorny = new JPanel();
		scrollPane.setColumnHeaderView(panelHorny);
		panelHorny.setLayout(new BoxLayout(panelHorny, BoxLayout.Y_AXIS));

		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(0);
		panelHorny.add(panel_1);

		lblNewLabel = new JLabel("TO DO LIST");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 28));
		panel_1.add(lblNewLabel);

		panelUlohy = new JPanel();
		panelUlohy.setBackground(new Color(245, 245, 245));
		scrollPane.setViewportView(panelUlohy);

		popupMenu = new JPopupMenu();
		addPopup(panelUlohy, popupMenu);

		menuItemPridatUlohu = new JMenuItem("Pridať ulohu");
		menuItemPridatUlohu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pridajUlohu();
			}
		});
		popupMenu.add(menuItemPridatUlohu);
		panelUlohy.setLayout(new BoxLayout(panelUlohy, BoxLayout.Y_AXIS));
		
		panelProjekt = new ProjektPanel();
		layeredPane.add(panelProjekt, "name_884146789949700");
		

		vykresliUlohy();
		skontrolujNotifikacie();
		nacitajNotifikacie();
		kontrolujCiNeexpirovalo();

	}

	/**
	 * táto metoda mi bude vykreslovat resp. aj updatovat ulohy su tam zabudovane aj
	 * menuItemy kvoli tomu že potrebujem priamo ovplyvnovat tie checkBoxy a
	 * potrebujem indexy pola uloh aby som dalej s nimi pracovať tie si posielam do
	 * dalšich pomocnych uloh aby som tu nemal vela kodu a bol viac citaleny
	 **/
	private void vykresliUlohy() {

		panelUlohy.removeAll(); // vymzavam vsetko aby som to mal ciste mohol nacitať znovu
								// vsetky ulohy
		if (poleUloh != null) {
			for (int i = 0; i < poleUloh.size(); i++) {

				int aktualnaPozicia = i;// nechcelo brat i tak som vytvoril dalsiu premenu

				if (poleUloh.get(i) != null) { // preistotu aby nevydzavalo chyby

					// vytvaram CheckBox a davam mu datum ak je vybrate a nazov ulohy

					JCheckBox checkBox = new JCheckBox(datum(poleUloh.get(i)) + " " + poleUloh.get(i).getSprava());

					// Nastavavujem font a velkost a farbu podla toho akú prioritu ma uloha
					checkBox.setFont(new Font("Arial", Font.PLAIN, 24));
					checkBox.setForeground(farba(poleUloh.get(i)));

					// Ked vymažem ulohu aby mi to ostalo označene
					oznaceniaKedVymazesUlohy(poleUloh.get(i), checkBox);

					// vytvaram action listener ked je checkbox kliknty
					checkBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							checkBoxZaskrtnuty(checkBox, poleUloh.get(aktualnaPozicia));
						}
					});

					// vytvaram popup
					popupMenuUlohy = new JPopupMenu();
					addPopup(checkBox, popupMenuUlohy);

					// vytvram menuItem pre edit uz vytvorenej ulohy
					newMenuItemEdit = new JMenuItem("Edit");
					newMenuItemEdit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							editUlohu(aktualnaPozicia);
						}
					});
					popupMenuUlohy.add(newMenuItemEdit);

					// vytvaram menuItem pre vymazanie ulohy zo zoznamu
					newMenuItemRemove = new JMenuItem("Vymaž");
					newMenuItemRemove.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							removeUlohu(aktualnaPozicia);
						}
					});
					popupMenuUlohy.add(newMenuItemRemove);

					// pridavam checkBox na panel a repaintujem a revalidujem
					panelUlohy.add(checkBox);
				}

			}
		}
		panelUlohy.revalidate();
		panelUlohy.repaint();

	}

	/** tato metoda mi odstrani ulohu z pola na zaklade pozicie **/
	private void removeUlohu(int pozicia) {
		removeNotifikaciu(poleUloh.get(pozicia));
		poleUloh.remove(pozicia);
		vykresliUlohy();
		saveData();

	}

	/** toto je metoda ktoru mi automaticky vygeneravalo pre popupmenu **/
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

	/** táto metoda mi vytvori JDialog a vrati mi vytvorenu ulohu z JDialogu **/
	private Uloha vykresliUlohaDialog() {
		ulohaDialog = new UlohaDialog();
		ulohaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ulohaDialog.setVisible(true);
		return ulohaDialog.getUloha();

	}

	/** táto uloha mi podla priority vrati farbu ktorou sa zafarbi uloha **/
	private Color farba(Uloha uloha) {
		int priorita = uloha.getPrioritaUlohy();
		if (!uloha.getisNotExpired()) {
			return new Color(175, 175, 175);
		}

		switch (priorita) {
		case 1:
			return new Color(255, 69, 0);
		case 2:
			return new Color(65, 105, 225);
		default:
			return new Color(0, 0, 0);
		}
	}

	/** táto metoda mi prida ulohu do pola uloh **/
	private void pridajUlohu() {
		Uloha novaUloha = vykresliUlohaDialog();
		if(novaUloha!=null) {
			poleUloh.add(novaUloha);
			vykresliUlohy();
			// rovno zavolame nech nám načita notifikacie
			addNotifikaciu(novaUloha);
			saveData();
			
		}
	}

	/** ked vymažem ulohu aby si stale ostala ta uloha označena **/
	private void oznaceniaKedVymazesUlohy(Uloha uloha, JCheckBox checkBox) {
		if (uloha.getIsChecked()) {
			checkBox.setBackground(new Color(152, 251, 152));
			checkBox.setSelected(true);
			saveData();
		}

	}

	/**
	 * táto metoda mi zistuje ci je začiarknuty aby bol zobrazeny datum ak ano tak
	 * tak nastavi na zobrazenie na true a to vyuziva metoda datum
	 **/
	private void zobrazDatum() {
		if (rdbtnmntmNewRadioItem.isSelected()) {
			zobraz = true;
			vykresliUlohy(); // to tu je na to aby rovno updatetlo

		} else {
			zobraz = false;
			vykresliUlohy();
		}
	}

	/**
	 * tato metoda služo na editaciu uholy tak že vytvori dialog na vytvorenie okna
	 * a vytvori novu ulohu a da ju na poziciu tej starej
	 **/
	private void editUlohu(int pozicia) {
		removeNotifikaciu(poleUloh.get(pozicia));
		Uloha novaUloha = vykresliUlohaDialog();
		if(novaUloha!=null) {
			poleUloh.set(pozicia, novaUloha);
			vykresliUlohy();  //toto som upravil
			addNotifikaciu(novaUloha);
			skontrolujNotifikacie();
			saveData();
			
		}

	}

	/**
	 * táto metoda sluzi na to že ked kliknes je hotova uloha tak ju zafarbi alebo
	 * resp. odfarby a nastavi atribut ulohy na isChecked na true resp. false
	 **/
	private void checkBoxZaskrtnuty(JCheckBox checkBox, Uloha uloha) {
		if (checkBox.isSelected()) {
			checkBox.setBackground(new Color(152, 251, 152));
			uloha.setIsChecked(true);
			removeNotifikaciu(uloha);
			skontrolujNotifikacie();
			saveData();

		} else {
			checkBox.setBackground(new Color(245, 245, 245));
			uloha.setIsChecked(false);
			addNotifikaciu(uloha);
			skontrolujNotifikacie();
			saveData();

		}

	}

	/**
	 * ked je nastaveny zobraz na true vrati mi datum ak je nic tak vrati prazdny
	 * string
	 **/
	private String datum(Uloha uloha) {

		if (zobraz && uloha != null) {
			return uloha.getSimpleDate();
		} else {
			return "";
		}

	}

	/** tato metoda mi prida notifikaciu ku ulohe **/
	private void addNotifikaciu(Uloha uloha) {
		if (uloha != null && uloha.getUpozornenie() && uloha.getIsChecked() == false) {
			poleNotifikacii.add(new Notifikacia(uloha));
		}
		skontrolujNotifikacie();
	}

	/**
	 * tato metoda budem sluzit na to že ked načitam data zo s suboru taj im prida
	 * notifikacie
	 */
	private void nacitajNotifikacie() {
		for (Uloha i : poleUloh) {
			addNotifikaciu(i);
		}
	}

	/** tato metoda odstrani notifikaciu ku ulohe **/
	private void removeNotifikaciu(Uloha uloha) {
		for (int i = 0; i < poleNotifikacii.size(); i++) {
			if (poleNotifikacii.get(i) != null && poleNotifikacii.get(i).getID().equals(uloha.getID())) {
				poleNotifikacii.get(i).cancel();
				poleNotifikacii.remove(i);
			}
		}
	}

	/**
	 * tato metoda mi bude kontrolovať že ked uz stary datum a cas tak mi odstrani
	 * upomienku aby znova nevyskakovala
	 **/
	private void skontrolujNotifikacie() {

		Calendar aktualnyCas = Calendar.getInstance();

		for (int i = 0; i < poleNotifikacii.size(); i++) {
			if (poleNotifikacii.get(i) != null && aktualnyCas.compareTo(poleNotifikacii.get(i).getDatumCas()) > 0) {
				poleNotifikacii.get(i).cancel();
				poleNotifikacii.remove(i);
			}
		}
	}

	/**
	 * táto metoda vytvori swingWorkera(Dalšie vlákno) ktory bude kontotrolovať ci
	 * uloha nemá uz stary datum ak ano tak prida tejto ulohe že uz je expired
	 **/
	private void kontrolujCiNeexpirovalo() {

		SwingWorker<Void, Integer> swingWorker = new SwingWorker<Void, Integer>() {

			@Override
			protected Void doInBackground() throws Exception {
				while (true) {
					// aktualny datum a cas
					Calendar aktualnyCas = Calendar.getInstance();
					// tu hladam ulohy a porovnam pomocou CompareTo
					for (int i = 0; i < poleUloh.size(); i++) {
						if (poleUloh.get(i) != null && aktualnyCas.compareTo(poleUloh.get(i).getDatumCas()) > 0) {
							publish(i);
						}
					}
					// zaspi na 2.5 sekund kvoli vykonu ze kontrolovať bude každych 2.5sekundy
					Thread.sleep(2500);
				}
			}

			@Override
			protected void process(List<Integer> pozicie) {

				for (Integer i : pozicie) {
					if (poleUloh.get(i).getisNotExpired() && !poleUloh.get(i).getIsChecked()) {
						poleUloh.get(i).setSpravaToExpired();
						poleUloh.get(i).setPriorita(4);
						vykresliUlohy();
						saveData();
						// System.out.println("Idem");
					}
				}
			}

		};
		// to iste ako vlakno.start();
		swingWorker.execute();
	}

	/** táto metoda bude služit na ukladanie dat do suboru **/
	private void saveData() {

		File subor = new File("poleUloh.obj");

		if (!subor.exists()) {
			try {
				subor.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(subor));
			oos.writeObject(poleUloh);
			oos.close();
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
			System.err.println("Subor neexistuje");
		}

	}

	/** tato metoda mi bude načitavať data zo suboru **/
	private ArrayList<Uloha> loadData() {
		File subor = new File("poleUloh.obj");

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(subor));

			try {
				ArrayList<Uloha> poleUloh = (ArrayList<Uloha>) ois.readObject();

				if (poleUloh == null) {
					return new ArrayList<Uloha>();

				} else {
					return poleUloh;
				}

			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}
			ois.close();
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
			System.err.println("Subor neexistuje");
		}
		return new ArrayList<Uloha>();

	}

	/** tato metoda vymaže všetky ulohy **/
	private void clearAllUlohy() {
		this.poleUloh = new ArrayList<Uloha>();
		vykresliUlohy();
		saveData();
		poleNotifikacii.forEach(i -> i.cancel()); // lambda vyraz
		this.poleNotifikacii = new ArrayList<Notifikacia>();
	}

	/** tato metoda mi to sortne podla datumu **/
	private void sortByDatum() {
		if (poleUloh != null) {
			Comparator<Uloha> compDatum = (o1, o2) -> o1.getDatumCas().compareTo(o2.getDatumCas());
			Collections.sort(poleUloh, compDatum);
			vykresliUlohy();
		}

	}

	/** tato uloha mi sortne podla priority **/
	private void sortByPriorita() {

		if (poleUloh != null) {
			Comparator<Uloha> compDatum = (o1, o2) -> o1.getPrioritaUlohy() - o2.getPrioritaUlohy();
			Collections.sort(poleUloh, compDatum);
			vykresliUlohy();
		}
	}

	/** tato metoda mi bude kontrolovať aby neboli klikne 2 sorty naraz **/
	private void kontrolaZakliknutnutehoSortuPrePrioritu() {
		if (!rdbtnmntmDatum.isSelected() && rdbtnmntmPriorita.isSelected()) {
			sortByPriorita();
			rdbtnmntmDatum.setEnabled(false);

		} else {
			this.poleUloh = loadData();
			vykresliUlohy();
			rdbtnmntmDatum.setEnabled(true);

		}

	}

	/** tato metoda mi bude kontrolovať aby neboli klikne 2 sorty naraz **/
	private void kontrolaZakliknutnutehoSortuPreDatum() {
		if (!rdbtnmntmPriorita.isSelected() && rdbtnmntmDatum.isSelected()) {
			sortByDatum();
			rdbtnmntmPriorita.setEnabled(false);

		} else {
			this.poleUloh = loadData();
			vykresliUlohy();
			rdbtnmntmPriorita.setEnabled(true);
		}

	}

	/** tato metoda mi pomocou printerjobu a printable umožny vytlačit panel **/
	public void print() {
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		printerJob.setPrintable(new Printable() {

			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				if (pageIndex > 0) {
					return Printable.NO_SUCH_PAGE;
				}

				Graphics2D grafickyObjekt = (Graphics2D) graphics;
				grafickyObjekt.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
				panelUlohy.paint(grafickyObjekt);
				return Printable.PAGE_EXISTS;
			}
		});
		if (printerJob.printDialog() == false)
			return;

		try {
			printerJob.print();
		} catch (PrinterException ex) {
			// handle exception
		}

	}

	/**
	 * tato metoda umožny pomocou fileChosera vybrat miesto kde budu ulohy a
	 * zapisane pomocou fileWritera ako textovy subor
	 ***/
	private void export() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("save as .txt", "txt");
		fileChooser.setFileFilter(filter);

		int userSelection = fileChooser.showSaveDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				FileWriter file;
				try {
					file = new FileWriter(fileChooser.getSelectedFile() + ".txt");
					BufferedWriter writer = new BufferedWriter(file);
					String ulohyForSave = ulohyToString();
					writer.write(ulohyForSave);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

	}

	/** pomocna metoda pre export hodi mi vsetky ulohy do jedneho stringu **/
	private String ulohyToString() {
		StringBuilder strBuilder = new StringBuilder();
		for (Uloha i : poleUloh) {
			if (i != null) {
				strBuilder.append(i.getDataForExport() + "\n");
			}
		}
		return strBuilder.toString();
	}
	
	/** premeni na panel projektu */
	private void changeToPrejekt() {
		layeredPane.removeAll();
		layeredPane.add(panelProjekt);
		mnMenu.setVisible(false);
		mnMenuPreProjekt.setVisible(true);
		layeredPane.repaint();
		layeredPane.revalidate();
	
	}
	/** premeni na panel to do list */
	private void changeToDoList() {	
		layeredPane.removeAll();
		layeredPane.add(scrollPane);
		mnMenu.setVisible(true);
		mnMenuPreProjekt.setVisible(false);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
}

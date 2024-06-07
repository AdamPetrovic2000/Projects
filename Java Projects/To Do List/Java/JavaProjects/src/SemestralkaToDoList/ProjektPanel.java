package SemestralkaToDoList;

import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthStyleFactory;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Point;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JMenu;

public class ProjektPanel extends JPanel {

	private JPanel panel;
	private JPopupMenu popupMenu;
	private JMenuItem mntmNewAdd;
	private JMenuItem mntmNewMenuItem_1;
	private Point point;
	private ArrayList<ProjektUloha> poleUloh;
	private ArrayList<JLabel> poleObrazkov;
	private ProjektDialog ulohaDialog;
	private JPopupMenu popupMenuUlohy;
	private JMenuItem newMenuItemEdit;
	private JMenuItem newMenuItemRemove;
	private JMenuItem mntmTlac;
	private JMenuItem mntmExport;
	private SizeDialog sizeDialog;
	private ArrayList<Point> polePointov;

	/**
	 * Tato trieda bude služi na vytvorenie panelu pre projekt 
	 */
	public ProjektPanel() {
		
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		poleUloh = loadUlohy();// tu budem loadovať
		poleObrazkov = loadObrazky(); // toto budem tiez loadovať

		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		add(panel, BorderLayout.CENTER);

		popupMenu = new JPopupMenu();
		addPopup(panel, popupMenu);

		mntmNewAdd = new JMenuItem("Pridať ulohu");
		mntmNewAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pridajUlohu();
			}
		});
		popupMenu.add(mntmNewAdd);

		mntmNewMenuItem_1 = new JMenuItem("Pridať obrazok");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pridajObrazok();
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		
		mntmTlac = new JMenuItem("Tlač");
		mntmTlac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();
			}
		});
		popupMenu.add(mntmTlac);
		
		mntmExport = new JMenuItem("Export");
		mntmExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				export();
			}
		});
		popupMenu.add(mntmExport);
		panel.setLayout(null);

		vykresli();

	}

	/** tato metoda mi vytvori ulohu pomocou inputu z dialogu a da ju do pola **/
	public void pridajUlohu() {
		point = panel.getMousePosition();
		ulohaDialog = new ProjektDialog(point.x-17, point.y-15);
		ulohaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ulohaDialog.setVisible(true);
		ProjektUloha uloha = ulohaDialog.getUloha();
		poleUloh.add(uloha);
		vykresli();
		saveUlohy();
	}

	/** tato metoda mi nacita obrazok a da ho vo forme icon pre label do pola **/
	private void pridajObrazok() {
		point = panel.getMousePosition();
		File cesta = fileObrazku();
		if (cesta != null) {
			JLabel label = new JLabel("");
			ImageIcon icon = new ImageIcon(cesta.getAbsolutePath());
			sizeDialog = new SizeDialog();
			sizeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			sizeDialog.setVisible(true);
			if (sizeDialog.xSize != 0 || sizeDialog.ySize != 0) {
				Image scaledImage = icon.getImage().getScaledInstance(sizeDialog.xSize, sizeDialog.ySize,
						java.awt.Image.SCALE_SMOOTH);
				label.setIcon(new ImageIcon(scaledImage));
				label.setBounds(point.x-sizeDialog.xSize/2, point.y-sizeDialog.ySize/2, sizeDialog.xSize, sizeDialog.ySize);
				poleObrazkov.add(label);
				saveObrazky();
				vykresli();
			}

		}

	}

	/**
	 * tato metoda sluzi aby som si vybral obrazok z OS a vrati mi k nemu cestu v
	 * objekte File
	 **/
	private File fileObrazku() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("png & jpeg", "png", "jpg");
		fileChooser.setFileFilter(filter);

		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();

		} else {
			return null;
		}
	}

	/** tato metoda mi vykresli ulohy z pola na panel **/
	private void vykresliUlohy() {

		if (poleUloh != null) {
			for (int i = 0; i < poleUloh.size(); i++) {
				int pozicia = i;

				if (poleUloh.get(i) != null) {
					JCheckBox checkBox = new JCheckBox(
							poleUloh.get(i).getSimpleDate() + " " + poleUloh.get(i).getSprava());
					checkBox.setBounds(poleUloh.get(i).getX(), poleUloh.get(i).getY(), 250, 30);

					checkBox.addMouseMotionListener(new MouseMotionAdapter() {

						public void mouseDragged(MouseEvent e) {
							moveUlohu(checkBox, pozicia);
						}

					});
					panel.add(checkBox);
					popupMenuUlohy = new JPopupMenu();
					addPopup(checkBox, popupMenuUlohy);

					newMenuItemEdit = new JMenuItem("Edit");
					newMenuItemEdit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							editUlohu(pozicia);
						}
					});
					popupMenuUlohy.add(newMenuItemEdit);

					newMenuItemRemove = new JMenuItem("Vymaž");
					newMenuItemRemove.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							removeUlohu(pozicia);
						}
					});
					popupMenuUlohy.add(newMenuItemRemove);
				}
			}
		}
	}

	/** tato metoda sluzi na hybanie uloh po panely **/
	private void moveUlohu(JCheckBox checkBox, int pozicia) {
		polePointov = new ArrayList<>();
		Point point = panel.getMousePosition();
		if (point != null) {
			int textSize = checkBox.getText().toCharArray().length;
			point.x = point.x-checkBox.getWidth()/2+(textSize);
			point.y = point.y-checkBox.getHeight()/2;
			checkBox.setLocation(point);
			poleUloh.get(pozicia).setXandY(point.x, point.y);
			checkBox.setEnabled(false);
			isMoving(point, checkBox);
			panel.revalidate();
			panel.repaint();
			saveUlohy();

		}

	}
	/** pomocna metoda pre move lebo mi zaskrtavalo **/
	private void isMoving(Point point,JCheckBox check) {
		polePointov.add(point);
		Iterator<Point> iter = polePointov.iterator();
		if(iter.next().equals(check.getLocation())) {
			check.setEnabled(true);
		}
		
		
	}
	

	/** tato metoda mi odstrani ulohu z pola uloh **/
	private void removeUlohu(int pozicia) {
		poleUloh.remove(pozicia);
		saveUlohy();
		vykresli();
	}

	/** tato metoda mi umožni editovať ulohu **/
	private void editUlohu(int index) {
		ulohaDialog = new ProjektDialog(poleUloh.get(index).getX(), poleUloh.get(index).getY());
		ulohaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ulohaDialog.setVisible(true);
		ProjektUloha uloha = ulohaDialog.getUloha();
		if(uloha!=null) {
			poleUloh.set(index, uloha);
			saveUlohy();
			vykresli();
			
		}
	}

	/**
	 * toto kombinacia vykresli obrazky a vykresli ulohy aby to bolo pekne v jednej
	 * metoda
	 **/
	private void vykresli() {
		panel.removeAll();
		vykresliObrazky();
		vykresliUlohy();
		panel.repaint();
		panel.revalidate();
	}

	/** tato metoda mi vykreslovať obrazky z pola **/
	private void vykresliObrazky() {
		if (poleObrazkov != null) {
			for (int i = 0; i < poleObrazkov.size(); i++) {
				int pozicia = i;

				if (poleObrazkov.get(i) != null) {

					panel.add(poleObrazkov.get(i));

					popupMenuUlohy = new JPopupMenu();
					addPopup(poleObrazkov.get(i), popupMenuUlohy);

					poleObrazkov.get(pozicia).addMouseMotionListener(new MouseMotionListener() {

						@Override
						public void mouseDragged(MouseEvent e) {
							moveObrazok(pozicia);
						}

						@Override
						public void mouseMoved(MouseEvent e) {
							// TODO Auto-generated method stub

						}
						
						
					});

					newMenuItemRemove = new JMenuItem("Vymaž");
					newMenuItemRemove.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							removeObrazok(pozicia);
						}
					});
					popupMenuUlohy.add(newMenuItemRemove);

				}
			}
		}
	}

	/** tato metoda sluzi na hybanie obrazkov po panely **/
	private void moveObrazok(int pozicia) {
		Point point = panel.getMousePosition();
		if (point != null) {
			point.x = point.x - poleObrazkov.get(pozicia).getWidth()/2;
			point.y = point.y -poleObrazkov.get(pozicia).getHeight()/2;
			poleObrazkov.get(pozicia).setLocation(point);
			saveObrazky();
			panel.revalidate();
			panel.repaint();
		}
	}

	/** tato metoda mi odstrani obrazky z pola obrazkov **/
	private void removeObrazok(int pozicia) {
		poleObrazkov.remove(pozicia);
		saveObrazky();
		vykresli();
	}

	/** tato metoda mi bude načitavať ulohy zo suboru **/
	private ArrayList<ProjektUloha> loadUlohy() {
		File subor = new File("poleUlohProjekt.obj");

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(subor));

			try {
				ArrayList<ProjektUloha> poleUloh = (ArrayList<ProjektUloha>) ois.readObject();

				if (poleUloh == null) {
					return new ArrayList<ProjektUloha>();

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
		return new ArrayList<ProjektUloha>();

	}

	/** tato metoda mi ulozi ulohy do suboru **/
	private void saveUlohy() {

		File subor = new File("poleUlohProjekt.obj");

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

	/** tato metoda mi ulozi obrazky do suboru **/
	private void saveObrazky() {

		File subor = new File("poleObrazkov.obj");

		if (!subor.exists()) {
			try {
				subor.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(subor));
			oos.writeObject(poleObrazkov);
			oos.close();
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
			System.err.println("Subor neexistuje");
		}

	}

	/** tato metoda mi nacita obrazky zo suboru **/
	private ArrayList<JLabel> loadObrazky() {
		File subor = new File("poleObrazkov.obj");

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(subor));

			try {
				ArrayList<JLabel> poleObrazkov = (ArrayList<JLabel>) ois.readObject();

				if (poleObrazkov == null) {
					return new ArrayList<JLabel>();

				} else {
					return poleObrazkov;
				}

			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}
			ois.close();
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
			System.err.println("Subor neexistuje");
		}
		return new ArrayList<JLabel>();

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
				panel.paint(grafickyObjekt);
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
	
	
	/** tato metoda mi panel exportne ako png a ulozi na zlovenu cestu cez JFileChooser **/
	public void export() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("save as .png", "png");
		fileChooser.setFileFilter(filter);
		
		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			
			BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			panel.printAll(g);
			g.dispose();
			try { 
			    ImageIO.write(image, "png", new File(fileChooser.getSelectedFile()+""+".png"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
		}
	}
	
	/** tato metoda mi vymaže všetko z panelu aj z poli aj zo suborov **/
	public void vymazVsetko() {
		poleObrazkov = new ArrayList<JLabel>();
		poleUloh = new ArrayList<ProjektUloha>();
		vykresli();
		saveObrazky();
		saveUlohy();
	}
	
	
	/** automaticky vygenerovana metoda pre popupmeu **/
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

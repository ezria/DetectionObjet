package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class FenetrePrincipale{

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	/**
	 * Create the application.
	 */
	public FenetrePrincipale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnDetectionUneForme = new JMenu("Liste Algorithme");
		mnDetectionUneForme.setHorizontalAlignment(SwingConstants.TRAILING);
		menuBar.add(mnDetectionUneForme);
		
		mntmNewMenuItem = new JMenuItem("Detection une forme noir sur fond blanc");
		mntmNewMenuItem.addActionListener(new ActionUneForme());
		mnDetectionUneForme.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		mnDetectionUneForme.add(separator);
		
		mntmNewMenuItem_1 = new JMenuItem("Detection plusieurs formes noirs sur fond blanc");
		mntmNewMenuItem_1.addActionListener(new ActionPlusieurForme());
		mnDetectionUneForme.add(mntmNewMenuItem_1);

		JSeparator separator_1 = new JSeparator();
		mnDetectionUneForme.add(separator_1);
		
		mntmNewMenuItem_2 = new JMenuItem("Detection plusieurs formes multicolores sur fond uniforme");
		mntmNewMenuItem_2.addActionListener(new ActionPlusieurFormeMulticolor());
		mnDetectionUneForme.add(mntmNewMenuItem_2);
		
		JSeparator separator_2 = new JSeparator();
		mnDetectionUneForme.add(separator_1);
		
		mntmNewMenuItem_3 = new JMenuItem("Detection Bit faible");
		mntmNewMenuItem_3.addActionListener(new ActionBitFaible());
		mnDetectionUneForme.add(mntmNewMenuItem_3);
	}
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	class ActionUneForme implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.setContentPane(new FenetreUneForme());
			frame.revalidate();
		}
		
	}
	class ActionPlusieurForme implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			frame.setContentPane(new FenetreMultiForme());
			frame.revalidate();
		}
	}
	class ActionPlusieurFormeMulticolor implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			frame.setContentPane(new FenetreMultiFormeMultiColor());
			frame.revalidate();
		}
	}
	class ActionBitFaible implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			frame.setContentPane(new FenetreBitfailbre());
			frame.revalidate();
		}
	}
}

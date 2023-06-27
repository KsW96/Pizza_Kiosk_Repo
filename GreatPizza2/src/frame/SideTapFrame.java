package frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import img.imageIcon;
import utilty.invisibility;

public class SideTapFrame extends JFrame {
	private invisibility util = new invisibility();
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SideTapFrame frame = new SideTapFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SideTapFrame() {
		FrameStting();
		
		JButton drinkTap = new JButton();
		drinkTap.setBounds(400, 150, 200, 50);
		jlp.add(drinkTap, new Integer(2));
		//util.invisible(drinkTap);
		
		drinkTap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				DrinkTapFrame drinkTap = new DrinkTapFrame();
				
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		setSize(800,900);
		setLocationRelativeTo(null);
	}
	private void FrameStting() {
		JLabel lbl = new JLabel(icon.getMenuTapSide());
		
		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(800, 900));
		
		lbl.setBounds(0, 0, 800, 900);
		
		jlp.add(lbl, new Integer(1));
		
		setContentPane(jlp);
		
	}
	private void drinkTapBtn() {
		
	}

}

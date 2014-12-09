
import java.awt.Container;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class FrameTester implements WindowListener {
	
	private JFrame frame;
	private ImagePanel imagePanelTable;
	
	private static final int width = 900;
	private static final int height = 500;
	
	public FrameTester() {
		
		// Frame
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setTitle("Assault");
		frame.setLocation(250, 55);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// Contenitore del Frame
		Container contentPane = frame.getContentPane();
		
		// Pannello
		//panel.setLayout(null);
		
		// Pannello di immagini
		//ImagePanel imagePanel = new ImagePanel("wait");

		
		
		
		
		
		
		frame.setFocusable(true);
		
		Game game = new Game();
		game.setTableWidth(width);
		game.setTableHeight(height);

		imagePanelTable = new ImagePanel(game,Schema.PATH_SFONDO);
		imagePanelTable.setFocusable(true);
		contentPane.add(imagePanelTable);

		frame.addKeyListener(imagePanelTable);
		frame.setVisible(true);
		frame.addWindowListener(this);
	}
	
	public static void main(String[] args)  {
		
		new FrameTester();
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

	
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements KeyListener, ActionListener {


	Ellipse2D palla;
	private Rectangle2D.Double dashBoard2;
	private Rectangle2D.Double campo;
	private Rectangle2D.Double pallaTest; 
	Timer timer = new Timer(7,this);
	int velX=3, velY=3, shift = 20;
	
	private Image background;
	
	
	
	public ImagePanel(Game game,String image){
		
		
		background = new ImageIcon(image).getImage();
		 
	 
		Dimension size = new Dimension(background.getWidth(null), background.getHeight(null));
		setPreferredSize(size);
		setLayout(null);
		palla = new Ellipse2D.Double(1,10,20,20);
		 		
		pallaTest = new Rectangle2D.Double(palla.getX(),palla.getY(),palla.getWidth(),palla.getHeight());
		
		
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		if (campo ==null){
			campo = new Rectangle2D.Double(0,0,this.getWidth(),this.getHeight());
			dashBoard2 = new Rectangle2D.Double(50,(this.getHeight()-Schema.DashBoard2Height)/2,15,Schema.DashBoard2Height);
			
		}
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g; 
		g2.drawImage(background, 0, 0, null);
		

		g2.setBackground(Color.blue);
		g2.setColor(Color.white);
		g2.fill(palla);
		g2.fill(dashBoard2);
		timer.start();
		
		
	}



	private boolean isCollisioneVerticalePalla(Ellipse2D palla, Rectangle2D rettangolo){
		
		
		Point2D.Double primoVertice = new Point2D.Double(rettangolo.getX(),rettangolo.getY());
		Point2D.Double secondoVertice = new Point2D.Double(rettangolo.getX()+rettangolo.getWidth(),rettangolo.getY());
		Point2D.Double terzoVertice = new Point2D.Double(rettangolo.getX(),rettangolo.getY()+rettangolo.getHeight());
		Point2D.Double quartoVertice = new Point2D.Double(rettangolo.getX()+rettangolo.getWidth(),rettangolo.getY()+rettangolo.getHeight());
		
		Line2D linea_superiore = new Line2D.Double(primoVertice,secondoVertice);
		Line2D linea_inferiore = new Line2D.Double(terzoVertice,quartoVertice);
		
		pallaTest.setRect(palla.getX(),palla.getY(),palla.getWidth(),palla.getHeight());
		
		return linea_superiore.intersects(pallaTest) || linea_inferiore.intersects(pallaTest);
		
	}
	
	private boolean isCollisioneOrizzontalePalla(Ellipse2D palla, Rectangle2D rettangolo){
		
		
		Point2D.Double primoVertice = new Point2D.Double(rettangolo.getX(),rettangolo.getY());
		Point2D.Double secondoVertice = new Point2D.Double(rettangolo.getX()+rettangolo.getWidth(),rettangolo.getY());
		Point2D.Double terzoVertice = new Point2D.Double(rettangolo.getX(),rettangolo.getY()+rettangolo.getHeight());
		Point2D.Double quartoVertice = new Point2D.Double(rettangolo.getX()+rettangolo.getWidth(),rettangolo.getY()+rettangolo.getHeight());
			
		
		Line2D linea_sinistra = new Line2D.Double(primoVertice,terzoVertice);
		Line2D linea_destra = new Line2D.Double(secondoVertice,quartoVertice);
		
		pallaTest.setRect(palla.getX(),palla.getY(),palla.getWidth(),palla.getHeight());
		
		return linea_sinistra.intersects(pallaTest) || linea_destra.intersects(pallaTest);
		
	}


	
	public void actionPerformed(ActionEvent arg0) {
		
		
		
		double x = palla.getX();
		double y = palla.getY();
		
		if(isCollisioneOrizzontalePalla(palla,campo) || isCollisioneOrizzontalePalla(palla,dashBoard2)) {
			
			velX = -velX;
		} 
		
		if((isCollisioneVerticalePalla(palla,campo) || isCollisioneVerticalePalla(palla,dashBoard2))) {
			
			velY = -velY;
		}
		
		
			
			
		x = x + velX;
		y = y + velY;		
		
		palla = new Ellipse2D.Double(x,y,20,20);
		repaint();
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		
		
		if(KeyEvent.VK_DOWN == arg0.getKeyCode() && !isCollisioneDashboardCampoInferiore(dashBoard2,campo)){
			
			dashBoard2.setRect(50,dashBoard2.getY()+shift,15,Schema.DashBoard2Height);
		}
		
		if(KeyEvent.VK_UP == arg0.getKeyCode() && !isCollisioneDashboardCampoSuperiore(dashBoard2,campo)){
			
			dashBoard2.setRect(50,dashBoard2.getY()-shift,15,Schema.DashBoard2Height);
		}
		
	}


	private boolean isCollisioneDashboardCampoSuperiore(Rectangle2D dashBoard , Rectangle2D campo) {
		
		
		Point2D.Double primoVerticeCampo = new Point2D.Double(campo.getX(),campo.getY());
		Point2D.Double secondoVerticeCampo = new Point2D.Double(campo.getX()+campo.getWidth(),campo.getY());
		
		
		Point2D.Double primoVerticeDashboard = new Point2D.Double(dashBoard.getX(),dashBoard.getY());
		Point2D.Double secondoVerticeDashboard = new Point2D.Double(dashBoard.getX()+dashBoard.getWidth(),dashBoard.getY());
		
		Line2D linea_superiore_campo = new Line2D.Double(primoVerticeCampo,secondoVerticeCampo);
		Line2D linea_superiore_dashboard = new Line2D.Double(primoVerticeDashboard,secondoVerticeDashboard);	
		
		
		return linea_superiore_campo.intersects(dashBoard);
		
	}
	
	private boolean isCollisioneDashboardCampoInferiore(Rectangle2D dashBoard , Rectangle2D campo) {
		
		
		
		Point2D.Double terzoVerticeCampo = new Point2D.Double(campo.getX(),campo.getY()+campo.getHeight());
		Point2D.Double quartoVerticeCampo = new Point2D.Double(campo.getX()+campo.getWidth(),campo.getY()+campo.getHeight());
		
		Point2D.Double terzoVerticeDashboard = new Point2D.Double(dashBoard.getX(),dashBoard.getY()+dashBoard.getHeight());
		Point2D.Double quartoVerticeDashboard = new Point2D.Double(dashBoard.getX()+dashBoard.getWidth(),dashBoard.getY()+dashBoard.getHeight());
		
		Line2D linea_inferiore_campo = new Line2D.Double(terzoVerticeCampo,quartoVerticeCampo);
		Line2D linea_inferiore_dashboard = new Line2D.Double(terzoVerticeDashboard,quartoVerticeDashboard);	
		
		
		return linea_inferiore_campo.intersects(dashBoard);
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	



	
}

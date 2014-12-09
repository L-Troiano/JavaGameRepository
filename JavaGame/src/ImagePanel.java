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
	int velX=2, velY=2;
	private Image img;
	
	  public ImagePanel(Game game,String img) {
		    this(game,new ImageIcon(img).getImage());
	  }

	
	public ImagePanel(Game game,Image image){
		
		
	    this.img = image;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
		
		
		palla = new Ellipse2D.Double(1,10,20,20);
		dashBoard2 = new Rectangle2D.Double(50,game.getTableHeight()/2-60,15,60); 
		campo = new Rectangle2D.Double(0,0,game.getTableWidth(),game.getTableHeight());
		pallaTest = new Rectangle2D.Double(palla.getX(),palla.getY(),palla.getWidth(),palla.getHeight());
		
		
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		g.drawImage(img, 0, 0, null);
		
		//super.paintComponent(g);
	/*	 
		Graphics2D g2 = (Graphics2D) g; 
		
		g2.setColor(Color.BLACK);
		g2.fill(palla);
		g2.fill(dashBoard2);
		timer.start();
		*/
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

		double y = dashBoard2.getY();
		
		if('k' == arg0.getKeyChar() && y <= (400 - dashBoard2.getHeight()-40) ){
			
			dashBoard2.setRect(50,dashBoard2.getY()+14,15,60);
		}
		
		if('i' == arg0.getKeyChar() && y >= 0){
			
			dashBoard2.setRect(50,dashBoard2.getY()-14,15,60);
		}
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	



	
}

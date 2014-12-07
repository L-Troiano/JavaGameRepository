import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements KeyListener, ActionListener {


	Ellipse2D palla = new Ellipse2D.Double(0,0,40,40);
	Rectangle dashBoard2 = new Rectangle(300,70,20,80); 
	Timer timer = new Timer(7,this);
	int velX=2, velY=2;
	private Image img;	
	public boolean solved;

	
	public void paintComponent(final Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g; 
		
		g2.setColor(Color.BLACK);
		g2.fill(palla);
		g2.fill(dashBoard2);
		timer.start();
		
		//isCollisione(palla,palla);
	}


	private boolean isCollisione(RectangularShape primo, RectangularShape secondo){
		
		
		Point2D.Double primoVertice = new Point2D.Double((double)secondo.getX(),(double)secondo.getY());
		Point2D.Double secondoVertice = new Point2D.Double((double)secondo.getX()+secondo.getWidth(),(double)secondo.getY());
		Point2D.Double terzoVertice = new Point2D.Double((double)secondo.getX(),(double)secondo.getY()+secondo.getHeight());
		Point2D.Double quartoVertice = new Point2D.Double((double)secondo.getX()+secondo.getWidth(),(double)secondo.getY()+secondo.getHeight());
		
		
		return primo.contains(primoVertice) || primo.contains(secondoVertice) || primo.contains(terzoVertice) || primo.contains(quartoVertice);
		
	}


	
	public void actionPerformed(ActionEvent arg0) {
		if(palla.getX()<0 || palla.getX()>550){
			velX = -velX;
			
		}
		
		if(palla.getY()<0 || palla.getY()>330){
			velY = -velY;
			
		}
		
		double x = palla.getX();
		double y = palla.getY();
		x = x + velX;
		y = y + velY;		
		
		palla = new Ellipse2D.Double(x,y,40,40);
		repaint();
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {

		if('k' == arg0.getKeyChar()){
			dashBoard2.setBounds(300,(int)dashBoard2.getY()+14,20,80);
			
		}
		
		if('i' == arg0.getKeyChar()){
			dashBoard2.setBounds(300,(int)dashBoard2.getY()-14,20,80);
			
		}
		
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	



	
}

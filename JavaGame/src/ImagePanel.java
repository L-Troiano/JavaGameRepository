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
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements KeyListener, ActionListener {


	Ellipse2D palla;
	Rectangle dashBoard2;
	Timer timer = new Timer(7,this);
	int velX=2, velY=2;
	private Image img;	
	public boolean solved;

	
	public ImagePanel(Game game){
		
		palla = new Ellipse2D.Double(0,0,20,20);
		dashBoard2 = new Rectangle(50,game.getTableHeight()/2-60,15,60); 
		
	}
	
	
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
		
		Rectangle2D.Double ball = new Rectangle2D.Double(palla.getX(), palla.getY(), 20, 20);
		
		return primo.intersects(ball);
		//return primo.contains(primoVertice) || primo.contains(secondoVertice) || primo.contains(terzoVertice) || primo.contains(quartoVertice);
		
	}


	
	public void actionPerformed(ActionEvent arg0) {
		
		boolean collisione = isCollisione(dashBoard2, palla);
		
		double x = palla.getX();
		double y = palla.getY();
		
		if(x < 0 || x > 573 || collisione) {
			
			velX = -velX;
		} 
		
		if(y < 0 || y > 350 || collisione){
			
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
		
		if('k' == arg0.getKeyChar() && y <= (400 - dashBoard2.height-40) ){
			
			dashBoard2.setBounds(50,(int)dashBoard2.getY()+14,15,60);
		}
		
		if('i' == arg0.getKeyChar() && y >= 0){
			
			dashBoard2.setBounds(50,(int)dashBoard2.getY()-14,15,60);
		}
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	



	
}

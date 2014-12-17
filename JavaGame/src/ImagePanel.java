import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements KeyListener, ActionListener {


	Ellipse2D palla;
	private Rectangle2D.Double dashBoard1;
	private Rectangle2D.Double dashBoard2;
	private Rectangle2D.Double campo;
	private Rectangle2D.Double pallaTest; 
	Timer timer = new Timer(30,this);
	final java.util.Timer countdownPartita = new java.util.Timer();
	final java.util.Timer countdownInizio = new java.util.Timer();
	int velX=10, velY=10;
	Font fontScritte = new Font("Arial",Font.BOLD,12);
	Font fontPunteggi = new Font("Arial",Font.BOLD,30);
	private Image background;
	private Image sfondoPunteggio;
	private Image dashBoardImage;
	private int player2Score=0;
	private int player1Score=0;
	private int secondi = Schema.COUNTDOWN_PARTITA;;
	private int secondiTrascorsi=0;
	private int secondiTrascorsiTemp=0;
	private int secondiInizio;
	Clip clip;
	private Point2D.Double primoVertice = new Point2D.Double();
	private Point2D.Double secondoVertice = new Point2D.Double();
	private Point2D.Double terzoVertice = new Point2D.Double();
	private Point2D.Double quartoVertice = new Point2D.Double();
	private java.awt.geom.Line2D.Double linea_sinistra = new Line2D.Double();
	private java.awt.geom.Line2D.Double linea_destra = new Line2D.Double();
	private java.awt.geom.Line2D.Double linea_superiore = new Line2D.Double();
	private java.awt.geom.Line2D.Double linea_inferiore = new Line2D.Double();
	private Point2D.Double primoVerticeCampo = new Point2D.Double();
	private Point2D.Double secondoVerticeCampo = new Point2D.Double();
	private Point2D.Double terzoVerticeCampo = new Point2D.Double();
	private Point2D.Double quartoVerticeCampo = new Point2D.Double();
	private java.awt.geom.Line2D.Double linea_superiore_campo = new Line2D.Double();
	private java.awt.geom.Line2D.Double linea_inferiore_campo = new Line2D.Double();
	private Image pallaImage;
	
	
	
	
	public ImagePanel(Game game,String image,String sfondoPunteggio){
		

    
		this.background = new ImageIcon(image).getImage();
		this.sfondoPunteggio = new ImageIcon(sfondoPunteggio).getImage();
		this.dashBoardImage = new ImageIcon(Schema.PATH_DASHBOARD).getImage();
		this.pallaImage = new ImageIcon(Schema.PATH_PALLA).getImage();
		 
	 
		Dimension size = new Dimension(background.getWidth(null), background.getHeight(null));
		setPreferredSize(size);	
		
		countdownInizio.scheduleAtFixedRate(new TimerTask() {
            int j = Schema.COUNTDOWN_INIZIO;
			
			
            public void run() {
            	
            	
               secondiInizio= j--;
               repaint();
                if (j< 0){
                	countdownInizio.cancel(); //annullo il countdown iniziale
                	
	            	countdownPartita.scheduleAtFixedRate(new TimerTask() {
	                    int i = Schema.COUNTDOWN_PARTITA;
	        	   		
	                    public void run() {
	                    	if(i == Schema.COUNTDOWN_PARTITA){	                    			
	                    		 eseguiSuono(new File(Schema.PATH_MUSICA));
	                    		 timer.start();	
	                    	}
	                        secondi = i--;
	                        secondiTrascorsi++;
	                        if (i< 0)
	                        	countdownPartita.cancel();
	                    }
	                }, 0, 1000);
                }
                
            }
        }, 0, 1000);
		

		
	
		


	
		
	}


	
	
	public void paintComponent(Graphics g) {
		
		if (campo ==null){
			
			Schema.DASHBOARD1_HEIGHT= this.getHeight()-Schema.COSTANTE_GRANDEZZA_BOARD*Schema.SHIFT_VERTICALE;
			Schema.DASHBOARD2_HEIGHT= this.getHeight()-Schema.COSTANTE_GRANDEZZA_BOARD*Schema.SHIFT_VERTICALE;
			palla = new Ellipse2D.Double((this.getWidth()-Schema.PALLA_WIDTH)/2,(this.getHeight()-Schema.PALLA_HEIGHT)/2,Schema.PALLA_WIDTH,Schema.PALLA_HEIGHT);
			pallaTest = new Rectangle2D.Double(palla.getX(),palla.getY(),palla.getWidth(),palla.getHeight());
			campo = new Rectangle2D.Double(0,+Schema.DISTANZA_TABELLA_PUNTEGGI,this.getWidth(),this.getHeight()-Schema.DISTANZA_TABELLA_PUNTEGGI);
			dashBoard1 = new Rectangle2D.Double(this.getWidth()-Schema.DISTANZA_LATERALE_BOARD,Schema.DISTANZA_TABELLA_PUNTEGGI+(this.getHeight()-Schema.DISTANZA_TABELLA_PUNTEGGI-Schema.DASHBOARD1_HEIGHT)/2,Schema.DASHBOARD1_WIDTH,Schema.DASHBOARD1_HEIGHT);
			dashBoard2 = new Rectangle2D.Double(Schema.DISTANZA_LATERALE_BOARD,Schema.DISTANZA_TABELLA_PUNTEGGI+(this.getHeight()-Schema.DISTANZA_TABELLA_PUNTEGGI-Schema.DASHBOARD2_HEIGHT)/2,Schema.DASHBOARD2_WIDTH,Schema.DASHBOARD2_HEIGHT);
			
			dashBoardImage = dashBoardImage.getScaledInstance((int)dashBoard1.getWidth(), (int)dashBoard1.getHeight(), Image.SCALE_DEFAULT);
			pallaImage = pallaImage.getScaledInstance((int)palla.getWidth(), (int)palla.getHeight(), Image.SCALE_DEFAULT);
					
		}
		
		super.paintComponent(g);
		
		if(secondiTrascorsi!=0 && secondiTrascorsiTemp!=secondiTrascorsi && secondiTrascorsi%Schema.SECONDI_SHIFT_ORIZZONTALE==0){
			
			secondiTrascorsiTemp = secondiTrascorsi; // è un valore aggiuntivo per controllare che venga spostato una sola volta la barra ogni tot di secondi
			dashBoard1.setRect(dashBoard1.getX()-Schema.SHIFT_ORIZZONTALE, dashBoard1.getY(), Schema.DASHBOARD1_WIDTH,Schema.DASHBOARD1_HEIGHT);
			dashBoard2.setRect(dashBoard2.getX()+Schema.SHIFT_ORIZZONTALE, dashBoard2.getY(), Schema.DASHBOARD2_WIDTH,Schema.DASHBOARD2_HEIGHT);
			


			
		}
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(sfondoPunteggio, 0, 0, null);
		g2.drawImage(background, 0, Schema.DISTANZA_TABELLA_PUNTEGGI, null);
		
		g2.setFont(fontScritte);
		g2.setColor(Color.red);
		g2.drawString("Punteggio giocatore 1:   ", 20, 20);
		g2.drawString("Punteggio giocatore 2:   ", this.getWidth()-230, 20);
		g2.setColor(Color.white);
		g2.drawString(String.valueOf(player1Score), 170, 20);
		g2.drawString(String.valueOf(player2Score), this.getWidth()-50, 20);
		
		g2.setFont(fontPunteggi);
		g2.drawString(String.valueOf(secondi), this.getWidth()/2-10, 30);	
		
		if(secondiInizio>0){
			g2.drawString(Schema.MESSAGGIO_ATTESA, (this.getWidth()/2)-(Schema.MESSAGGIO_ATTESA.length()*7),this.getHeight()/2-100);
			g2.drawString(String.valueOf(secondiInizio), this.getWidth()/2-10, this.getHeight()/2-50);	
		}
		
		
		g2.drawImage(pallaImage, (int)palla.getX(), (int)palla.getY(), this);
		g2.drawImage(dashBoardImage, (int)dashBoard1.getX(), (int)dashBoard1.getY(), this);
		g2.drawImage(dashBoardImage, (int)dashBoard2.getX(), (int)dashBoard2.getY(), this);
		
		
		if(secondi==0){
				
			String scritta = ""; 
			if(player1Score>player2Score){
				scritta="Player 1 vince";				
			}
			else{
				if(player1Score<player2Score){
					scritta="Player 2 vince";	
						
				}
				else{
					scritta="Pareggio";			
				}
			}
			
			
			g2.setColor(Color.red);
			g2.drawString(scritta,(this.getWidth()/2)-(scritta.length()*7),this.getHeight()/2);
			chiudiSuono();
			timer.stop();
		}else{
			
		}
		
		
		
		
	}


	
	private boolean isCollisioneOrizzontalePallaDashBoard(Ellipse2D palla, Rectangle2D rettangolo){

		
		primoVertice.setLocation(rettangolo.getX(),rettangolo.getY());
		secondoVertice.setLocation(rettangolo.getX()+rettangolo.getWidth(),rettangolo.getY());
		terzoVertice.setLocation(rettangolo.getX(),rettangolo.getY()+rettangolo.getHeight());
		quartoVertice.setLocation(rettangolo.getX()+rettangolo.getWidth(),rettangolo.getY()+rettangolo.getHeight());
		linea_sinistra.setLine(primoVertice,terzoVertice);
		linea_destra.setLine(secondoVertice,quartoVertice);
		pallaTest.setRect(palla.getX(),palla.getY(),palla.getWidth(),palla.getHeight());

		return linea_sinistra.intersects(pallaTest) || linea_destra.intersects(pallaTest);
		
//		
//		primoVertice.setLocation(palla.getX()-Math.abs(velX),palla.getY()-Math.abs(velY));
//		secondoVertice.setLocation(palla.getX()+palla.getWidth()+Math.abs(velX),palla.getY()-Math.abs(velY));
//		terzoVertice.setLocation(palla.getX()-Math.abs(velX),palla.getY()+palla.getHeight()+Math.abs(velY));
//		quartoVertice.setLocation(palla.getX()+palla.getWidth()+Math.abs(velX),palla.getY()+palla.getHeight()+Math.abs(velY));
//		
//		linea_sinistra.setLine(primoVertice,terzoVertice);
//		linea_destra.setLine(secondoVertice, quartoVertice);
//		
//		
//		
//		return linea_sinistra.intersects(rettangolo) || linea_destra.intersects(rettangolo);

		
	}
	
	
	private boolean isCollisioneVerticalePallaDashBoard(Ellipse2D palla, Rectangle2D rettangolo){

		
		
		
		primoVertice.setLocation(rettangolo.getX(),rettangolo.getY());
		secondoVertice.setLocation(rettangolo.getX()+rettangolo.getWidth(),rettangolo.getY());
		terzoVertice.setLocation(rettangolo.getX(),rettangolo.getY()+rettangolo.getHeight());
		quartoVertice.setLocation(rettangolo.getX()+rettangolo.getWidth(),rettangolo.getY()+rettangolo.getHeight());
		linea_superiore.setLine(primoVertice,secondoVertice);
		linea_inferiore.setLine(terzoVertice,quartoVertice);
		pallaTest.setRect(palla.getX(),palla.getY(),palla.getWidth(),palla.getHeight());
		return linea_superiore.intersects(pallaTest) || linea_inferiore.intersects(pallaTest);
		
		
//		primoVertice.setLocation(palla.getX()-Math.abs(velX),palla.getY()-Math.abs(velY));
//		secondoVertice.setLocation(palla.getX()+palla.getWidth()+Math.abs(velX),palla.getY()-Math.abs(velY));
//		terzoVertice.setLocation(palla.getX()-Math.abs(velX),palla.getY()+palla.getHeight()+Math.abs(velY));
//		quartoVertice.setLocation(palla.getX()+palla.getWidth()+Math.abs(velX),palla.getY()+palla.getHeight()+Math.abs(velY));
//		
//		linea_superiore.setLine(primoVertice,secondoVertice);
//		linea_inferiore.setLine(terzoVertice, quartoVertice);
//		
//		
//		
//		return linea_superiore.intersects(rettangolo) || linea_inferiore.intersects(rettangolo);

		
	}
	

	private boolean isCollisioneVerticalePallaCampo(Ellipse2D palla, Rectangle2D rettangolo){

		return palla.getY()+velY<rettangolo.getY() || palla.getY()+palla.getHeight()+velY>rettangolo.getY()+rettangolo.getHeight();

		
	}
	
	
	private boolean isCollisioneOrizzontalePallaCampo(Ellipse2D palla, Rectangle2D rettangolo,boolean conta){

		
		boolean collisioneSinistra = palla.getX()+velX<rettangolo.getX(); 
		boolean collisioneDestra = palla.getX()+palla.getWidth()+velX>rettangolo.getX()+rettangolo.getWidth();
		
		if(conta && collisioneSinistra){
			player2Score++;
		}
		if(conta && collisioneDestra){
			player1Score++;
		}
		
		return collisioneSinistra || collisioneDestra;
		
	}


	
	public void actionPerformed(ActionEvent arg0) {
		
		
		
		double x = palla.getX();
		double y = palla.getY();
		
		
		
		if(isCollisioneOrizzontalePallaCampo(palla,campo,true) || isCollisioneOrizzontalePallaDashBoard(palla,dashBoard1) || isCollisioneOrizzontalePallaDashBoard(palla,dashBoard2))  {

			
			velX = -velX;
		} 
		
		if((isCollisioneVerticalePallaCampo(palla,campo)) || isCollisioneVerticalePallaDashBoard(palla,dashBoard1) || isCollisioneVerticalePallaDashBoard(palla,dashBoard2))  {
			
			
			velY = -velY;
		}	
			
		x = x + velX;
		y = y + velY;		
		
		palla.setFrame(x,y,Schema.PALLA_WIDTH,Schema.PALLA_HEIGHT);
		repaint();
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if(KeyEvent.VK_UP == arg0.getKeyCode() && !isCollisioneDashboardCampoSuperiore(dashBoard1,campo)){
			
			dashBoard1.setRect(dashBoard1.getX(),dashBoard1.getY()-Schema.SHIFT_VERTICALE,Schema.DASHBOARD1_WIDTH,Schema.DASHBOARD1_HEIGHT);
		}
		
		if(KeyEvent.VK_DOWN == arg0.getKeyCode() && !isCollisioneDashboardCampoInferiore(dashBoard1,campo)){
			
			dashBoard1.setRect(dashBoard1.getX(),dashBoard1.getY()+Schema.SHIFT_VERTICALE,Schema.DASHBOARD1_WIDTH,Schema.DASHBOARD1_HEIGHT);
		}
		
		if(KeyEvent.VK_W == arg0.getKeyCode() && !isCollisioneDashboardCampoSuperiore(dashBoard2,campo)){
			
			dashBoard2.setRect(dashBoard2.getX(),dashBoard2.getY()-Schema.SHIFT_VERTICALE,Schema.DASHBOARD1_WIDTH,Schema.DASHBOARD2_HEIGHT);
		}
		
		if(KeyEvent.VK_S == arg0.getKeyCode() && !isCollisioneDashboardCampoInferiore(dashBoard2,campo)){
			
			dashBoard2.setRect(dashBoard2.getX(),dashBoard2.getY()+Schema.SHIFT_VERTICALE,Schema.DASHBOARD1_WIDTH,Schema.DASHBOARD2_HEIGHT);
		}
		
	}


	private boolean isCollisioneDashboardCampoSuperiore(Rectangle2D dashBoard , Rectangle2D campo) {
		

		return dashBoard.getY()-Schema.SHIFT_VERTICALE<campo.getY();
		
	}
	
	private boolean isCollisioneDashboardCampoInferiore(Rectangle2D dashBoard , Rectangle2D campo) {

		return dashBoard.getY()+dashBoard.getHeight()+Schema.SHIFT_VERTICALE>campo.getY()+campo.getHeight();
		
	}

	private void eseguiSuono(File soundFile) {
		AudioInputStream sound;
		try {
			sound = AudioSystem.getAudioInputStream(soundFile);
			
		    // load the sound into memory (a Clip)
		    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(sound);
		    clip.start();
		    
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void chiudiSuono(){
		clip.close();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	



	
}

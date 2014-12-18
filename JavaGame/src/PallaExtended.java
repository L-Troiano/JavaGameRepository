import java.awt.geom.Ellipse2D;


public class PallaExtended extends Ellipse2D.Double{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int velX = 0;
	private int velY = 0;
	private String codImpatto;

	
	public PallaExtended(int i, int j, int pallaWidth, int pallaHeight) {
		super.x = i;
		super.y = j;
		super.width = pallaWidth;
		super.height = pallaHeight;
	}


	public int getVelX() {
		return velX;
	}


	public void setVelX(int velX) {
		this.velX = velX;
	}


	public int getVelY() {
		return velY;
	}


	public void setVelY(int velY) {
		this.velY = velY;
	}


	public String getCodImpatto() {
		return codImpatto;
	}


	public void setCodImpatto(String codImpatto) {
		this.codImpatto = codImpatto;
	}


	
	
	
	
}

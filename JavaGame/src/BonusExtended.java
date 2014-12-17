import java.awt.Image;
import java.awt.geom.Rectangle2D;



public class BonusExtended extends Rectangle2D.Double{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private Image image;

	
	public BonusExtended(int i, int j, int bonusWidth, int bonusHeight) {
		super.x = i;
		super.y = j;
		super.width = bonusWidth;
		super.height = bonusHeight;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}



	
	
	
	
}

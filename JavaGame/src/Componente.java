
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.geom.Line2D;


public class Componente extends JComponent {
	public Componente(int n)
	{
		lunghezza=n;
	}


	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2=(Graphics2D)g;
        Rectangle box=new Rectangle(start_position,start_position,lunghezza,lunghezza);
        int z=lunghezza/8;
        int l=0;
        Rectangle box2=new Rectangle(start_position,start_position,z,z);
        g2.draw(box2);
        g2.draw(box);
		
		for(l=start_position;l<start_position+lunghezza;l=l+z)
		{
		Line2D.Double segment=new Line2D.Double(l, start_position, l, start_position+lunghezza);
		g2.draw(segment);
		}
		for(l=start_position;l<start_position+lunghezza;l=l+z)
		{
		Line2D.Double segment=new Line2D.Double(start_position, l, start_position+lunghezza, l);
		g2.draw(segment);
		}
		int x=z;
		int j=z;
		for(int w=0;w<8;w++)
		{
		    if (w%2==0)
		        {
		    	for(l=0;l<lunghezza/2;l=l+z)
		    	{
		    		box2.translate(x, 0);
		    		g2.fill(box2);
		    		x=z*2;
		    		
		    	}
		    	box2.translate(-lunghezza-j,j);
		        }
		    else
		    {
		    	for(l=0;l<lunghezza/2;l=l+z)
		    	{
		    		box2.translate(x, 0);
		    		g2.fill(box2);
		    		x=z*2;
		    		
		    	}
		    	box2.translate(-lunghezza+j,j);
		    }

		}

	}
private int lunghezza;
private int start_position=0;

}


import javax.swing.JOptionPane;
import javax.swing.JFrame;


public class Disegno1 {
	public static void main(String []args)
	{
		
		
		boolean flag=false; 
		while (!flag)
		{
			String in=JOptionPane.showInputDialog("Inserisci la lunghezza del lato della scacchiera (deve essere perfettamente divisibile per 8, senza resto):");
			int n=Integer.parseInt(in);
			
		if (n%8==0)
		{
		JFrame finestra= new JFrame();
		finestra.setSize(900,900);
		finestra.setTitle("Scacchiera");
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestra.setVisible(true);
		Componente figura=new Componente(n);
		finestra.add(figura);
		flag=true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Il numero inserito non è perfettamente divisibile per 8. \nInserisci un'altro numero.");
			flag=false;
		}
		}
	}
	

}

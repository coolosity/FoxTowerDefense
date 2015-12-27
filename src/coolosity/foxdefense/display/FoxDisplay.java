package coolosity.foxdefense.display;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import coolosity.foxdefense.core.FoxGame;

public class FoxDisplay implements Runnable
{

	private JFrame frame;
	private JLabel label;
	private FoxGame game;
	
	public FoxDisplay(String title, int width, int height)
	{
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel();
		frame.add(label);
		frame.setVisible(true);
		(new Thread(this)).start();
	}
	
	public void setGame(FoxGame game)
	{
		this.game = game;
	}

	@Override
	public void run() {
		long lastdraw = System.currentTimeMillis();
		while(true)
		{
			BufferedImage img = new BufferedImage(label.getWidth(),label.getHeight(),BufferedImage.TYPE_INT_ARGB);
			if(game!=null)
			{
				game.draw(img);
			}
			while(System.currentTimeMillis()-lastdraw<1000/200);
			lastdraw = System.currentTimeMillis();
			label.setIcon(new ImageIcon(img));
		}
	}
}

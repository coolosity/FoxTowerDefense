package coolosity.foxdefense.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import coolosity.foxdefense.display.Resources;

public class FoxGame {

	public static boolean grid = true;
	
	private int[][] world;
	
	public FoxGame(String worldToLoad)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File("worlds/"+worldToLoad+".txt")));
			String[] data = reader.readLine().split(" ");
			world = new int[Integer.parseInt(data[0])][Integer.parseInt(data[1])];
			for(int y=0;y<world[0].length;y++)
			{
				String[] line = reader.readLine().split(" ");
				for(int x=0;x<world.length;x++)
				{
					world[x][y] = Integer.parseInt(line[x]);
				}
			}
			reader.close();
		}
		catch(Exception e)
		{
			FoxLogger.err("Could not load world "+worldToLoad);
			e.printStackTrace();
		}
	}
	
	public void tick(long millis)
	{
		
	}
	
	public void draw(BufferedImage img)
	{
		int menuMinWidth = img.getWidth()/5;
		int menuWidth = 0;
		int maxwidth = img.getWidth()-menuMinWidth;
		int maxheight = img.getHeight();
		int width = maxwidth;
		int height = world[0].length*width/world.length;
		if(height>maxheight)
		{
			height = maxheight;
			width = world.length*height/world[0].length;
		}
		drawWorld(img.getSubimage(0, (maxheight-height)/2, width, height));
		menuWidth = img.getWidth()-width;
		drawMenu(img.getSubimage(img.getWidth()-menuWidth, 0, menuWidth, img.getHeight()));
	}
	
	private void drawWorld(BufferedImage img)
	{
		Graphics g = img.getGraphics();
		double ppx = img.getWidth()*1.0/world.length;
		double ppy = img.getHeight()*1.0/world[0].length;
		
		//blocks
		for(int x=0;x<world.length;x++)
		{
			for(int y=0;y<world[0].length;y++)
			{
				g.drawImage(Resources.getImage("block"+world[x][y]), (int)(x*ppx), (int)(y*ppy), (int)(ppx)+1, (int)(ppy)+1, null);
			}
		}
		
		//grid
		if(grid)
		{
			g.setColor(Color.WHITE);
			for(int x=0;x<=world.length;x++)
			{
				g.fillRect((int)(x*ppx)-2, 0, 4, img.getHeight());
			}
			for(int y=0;y<=world[0].length;y++)
			{
				g.fillRect(0, (int)(y*ppy)-2, img.getWidth(), 4);
			}
		}
	}
	
	private void drawMenu(BufferedImage img)
	{
		Graphics g = img.getGraphics();
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
	}
	
	public int getWidth()
	{
		return world.length;
	}
	
	public int getHeight()
	{
		return world[0].length;
	}
}

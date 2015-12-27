package coolosity.foxdefense.display;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import coolosity.foxdefense.core.FoxException;

public class Resources {

	private static final int[] IDS = {
		0, 1
	};
	
	private static HashMap<String,BufferedImage> images;
	
	public static void loadResources()
	{
		images = new HashMap<String,BufferedImage>();
		for(int id : IDS)
		{
			images.put("block"+id, loadImage("res/block"+id+".png"));
		}
	}
	
	public static BufferedImage getImage(String key)
	{
		if(!images.containsKey(key))
			return null;
		return images.get(key);
	}
	
	public static BufferedImage getImage(String key, int rot)
	{
		BufferedImage img = getImage(key);
		if(img==null)return null;
		BufferedImage ret = null;
		switch(rot)
		{
		case 0:
			ret = img;
			break;
		case 1:
			ret = new BufferedImage(img.getHeight(),img.getWidth(),img.getType());
			for(int x=0;x<img.getWidth();x++)
			{
				for(int y=0;y<img.getHeight();y++)
				{
					ret.setRGB(img.getHeight()-1-y, x, img.getRGB(x, y));
				}
			}
			break;
		case 2:
			ret = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
			for(int x=0;x<img.getWidth();x++)
			{
				for(int y=0;y<img.getHeight();y++)
				{
					ret.setRGB(img.getWidth()-1-x, img.getHeight()-1-y, img.getRGB(x, y));
				}
			}
			break;
		case 3:
			ret = new BufferedImage(img.getHeight(),img.getWidth(),img.getType());
			for(int x=0;x<img.getWidth();x++)
			{
				for(int y=0;y<img.getHeight();y++)
				{
					ret.setRGB(y, img.getWidth()-1-x, img.getRGB(x, y));
				}
			}
			break;
		}
		return ret;
	}
	
	private static BufferedImage loadImage(String path)
	{
		File file = new File(path);
		if(!file.exists())
		{
			throw new FoxException("Image does not exist at path "+path);
		}
		try
		{
			BufferedImage img = ImageIO.read(file);
			return img;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new FoxException("Could not load image from path "+path);
		}
	}
}

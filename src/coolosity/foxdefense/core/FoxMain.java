package coolosity.foxdefense.core;

import coolosity.foxdefense.display.FoxDisplay;
import coolosity.foxdefense.display.Resources;

public class FoxMain {

	public static void main(String[] args)
	{
		Resources.loadResources();
		new FoxMain();
	}
	
	private FoxDisplay display;
	private FoxGame game;
	
	public FoxMain()
	{
		game = new FoxGame("sample");
		display = new FoxDisplay("Fox Tower Defense",800,600);
		display.setGame(game);
		go();
	}
	
	private void go()
	{
		long lasttick = System.currentTimeMillis();
		while(true)
		{
			long cur = System.currentTimeMillis();
			game.tick(cur-lasttick);
			lasttick = cur;
		}
	}
}

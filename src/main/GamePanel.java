package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import entity.Player;
import object.AbstractObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable
{
	KeyHandler m_keyHand = new KeyHandler();
	Thread m_gameThread;
	public Player m_player = new Player(this, m_keyHand);
	TileManager m_tileManager = new TileManager(this);
	public CollisionDetector m_collisionDetector = new CollisionDetector(this);
	ArrayList<AbstractObject> m_objects = new ArrayList<AbstractObject>(1000);
	public AssetSetter m_assetSetter = new AssetSetter(this);

	// Screen Settings
	final int originalTileSize = 16; // 16x16 pixel tile size
	final int scale = 4; // scale since screen resolution of modern monitors is much larger

	public final int tileSize = originalTileSize * scale; // 64x64 pixel tile size
	final int maxScreenCol = 20;
	final int maxScreenRow = 15;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	// World settings
	public final int m_maxWorldCol = 50;
	public final int m_maxWorldRow = 50;
	public final int m_worldWidth = tileSize * m_maxWorldCol;
	public final int m_worldHeight = tileSize * m_maxWorldRow;

	public final int FPS = 60;

	public GamePanel()
	{
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		setDoubleBuffered(true); // Should improve performance with JPanel
		addKeyListener(m_keyHand);
		setFocusable(true); // so the game can be focusable to read commands
	}

	public void setupGame()
	{
		m_assetSetter.setAssets();
	}

	public void startGameThread()
	{
		m_gameThread = new Thread(this);
		m_gameThread.start();
	}

	@Override
	public void run()
	{
		double drawInterval = 1000000000/FPS; // How often the screen is updated in nano seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		//long timer = 0;
		//long drawCount = 0;

		while(m_gameThread != null)
		{
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime)/drawInterval;
			//timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1)
			{
				update();
				repaint();
				delta--;
				//drawCount++;
			}
			/*if (timer >= 1000000000)
			{
				System.out.println("FPS: "+drawCount);
				timer = 0;
				drawCount = 0;
			}*/
		}
	}

	public void update ()
	{
		m_player.update();
		for (AbstractObject obj:m_objects)
		{
			if (obj != null)
				obj.update();
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Make it a graphics 2D class to get access to more stuff (why not just start with a 2D graphics?)
		Graphics2D g2D = (Graphics2D)g;

		// Draw tiles before player else tiles will hide player
		m_tileManager.draw(g2D);
		for (AbstractObject obj:m_objects)
		{
			if (obj != null)
				obj.draw(g2D, this);
		}
		m_player.draw(g2D);

		// For freeing up memory
		g2D.dispose();
	}

	public boolean addObjectToMap(AbstractObject object)
	{
		return m_objects.add(object);
	}
}

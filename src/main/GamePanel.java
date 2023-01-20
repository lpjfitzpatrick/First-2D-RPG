package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import entity.Player;
import main.Sound.SoundByte;
import object.AbstractObject;
import tile.TileManager;
import ui.InventoryPanel;
import ui.LogMessage;

public class GamePanel extends JPanel implements Runnable
{
	// System classes
	private KeyHandler m_keyHand = new KeyHandler();
	private Thread m_gameThread;
	private TileManager m_tileManager = new TileManager(this);
	private CollisionDetector m_collisionDetector = new CollisionDetector(this);
	private AssetSetter m_assetSetter = new AssetSetter(this);
	private Sound m_OST = new Sound();
	private Sound m_SFX = new Sound();
	private InventoryPanel m_InventoryPanel = new InventoryPanel(this);
	private LogMessage m_LogMessage = new LogMessage(this);

	// Callbacks
	private LogMessageListener m_LogMessageListener;

	// Entity and objects
	private Player m_player = new Player(this, m_keyHand);
	private ArrayList<AbstractObject> m_objects = new ArrayList<AbstractObject>(1000);

	// Screen Settings
	private final int originalTileSize = 16; // 16x16 pixel tile size
	private final int scale = 4; // scale since screen resolution of modern monitors is much larger

	public final int m_tileSize = originalTileSize * scale; // 64x64 pixel tile size
	final int maxScreenCol = 20;
	final int maxScreenRow = 15;
	public final int screenWidth = m_tileSize * maxScreenCol;
	public final int screenHeight = m_tileSize * maxScreenRow;

	// World settings
	public final int m_maxWorldCol = 50;
	public final int m_maxWorldRow = 50;
	public final int m_worldWidth = m_tileSize * m_maxWorldCol;
	public final int m_worldHeight = m_tileSize * m_maxWorldRow;

	public final int FPS = 60;

	public TileManager tileManager() { return m_tileManager; }
	public Player player() { return m_player; }
	public CollisionDetector collisionDetector() { return m_collisionDetector; }
	public ArrayList<AbstractObject> getObjects() { return m_objects; }
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
		playTheme(SoundByte.eMainTheme);
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
		Iterator<AbstractObject> it = m_objects.iterator();
		while (it.hasNext())
		{
			AbstractObject obj = it.next();
			if (obj != null && obj.isOnScreen())
			{
				obj.update();
				if (obj.removeFromGamePanel()) it.remove();
			}
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
		if (m_keyHand.m_BPressed)
			m_InventoryPanel.draw(g2D);
		m_LogMessage.draw(g2D);

		// For freeing up memory
		g2D.dispose();
	}

	public boolean addObjectToMap(AbstractObject object)
	{
		return m_objects.add(object);
	}

	void playTheme(SoundByte eSoundByte)
	{
		m_OST.setSoundFile(eSoundByte);
		m_OST.play();
		m_OST.loop();
	}

	void stopTheme()
	{
		m_OST.stop();
	}

	public void playSFX(SoundByte eSoundByte)
	{
		m_SFX.setSoundFile(eSoundByte);
		m_SFX.play();
	}

	public void setLogMessageListener(LogMessageListener listener)
	{
		m_LogMessageListener = listener;
	}

	public void notifyLogMessageListener(String message)
	{
		m_LogMessageListener.receiveMessage(message);
	}
}

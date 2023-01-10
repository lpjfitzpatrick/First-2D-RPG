package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable
{
	public GamePanel()
	{
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		setDoubleBuffered(true); // Should improve performance with JPanel
		addKeyListener(m_keyHand);
		setFocusable(true); // so the game can be focusable to read commands
	}

	KeyHandler m_keyHand = new KeyHandler();
	Thread m_gameThread;
	Player m_player = new Player(this, m_keyHand);

	// Screen Settings
	final int originalTileSize = 16; // 16x16 pixel tile size
	final int scale = 3; // scale since screen resolution of modern monitors is much larger

	public final int tileSize = originalTileSize * scale; // 48x48 pixel tile size
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	public final int FPS = 60;

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
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Make it a graphics 2D class to get access to more stuff (why not just start with a 2D graphics?)
		Graphics2D g2D = (Graphics2D)g;

		m_player.draw(g2D);

		// For freeing up memory
		g2D.dispose();
	}
}

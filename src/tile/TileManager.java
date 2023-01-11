package tile;

import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager
{
	GamePanel m_gp;
	Tile[] m_aTiles;

	static final int NUM_TILES = 100;

	public TileManager(GamePanel gp)
	{
		m_gp = gp;

		m_aTiles = new Tile[NUM_TILES];

		getTileImage();
	}

	public void getTileImage()
	{
		try {
			m_aTiles[0] = new Tile();
			m_aTiles[0].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/TestGrass001.png"));

			m_aTiles[1] = new Tile();
			m_aTiles[1].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/TestGrass002.png"));

			m_aTiles[2] = new Tile();
			m_aTiles[2].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/TestWall001.png"));

			m_aTiles[3] = new Tile();
			m_aTiles[3].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/TestWater001.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2D)
	{
		g2D.drawImage(m_aTiles[0].m_image, 0, 0, m_gp.tileSize, m_gp.tileSize, null);
	}
}

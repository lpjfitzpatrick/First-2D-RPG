package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager
{
	GamePanel m_gp;
	Tile[] m_aTiles;
	int m_mapTileNums[][];
	boolean m_mapLoaded;

	static final int NUM_TILES = 100;

	public TileManager(GamePanel gp)
	{
		m_gp = gp;

		m_aTiles = new Tile[NUM_TILES];
		m_mapTileNums = new int[m_gp.m_maxWorldCol][m_gp.m_maxWorldRow];

		getTileImage();
		m_mapLoaded = loadMap("../res/maps/TestWorld001.txt");
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

			m_aTiles[4] = new Tile();
			m_aTiles[4].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/TestFlower001.png"));

			m_aTiles[5] = new Tile();
			m_aTiles[5].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/TestSand001.png"));

			m_aTiles[6] = new Tile();
			m_aTiles[6].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/TestEarth001.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean loadMap(String filePath)
	{
		boolean ret = false;
		try {
			InputStream inStream = getClass().getResourceAsStream(filePath);
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(inStream));

			for (int row = 0; row < m_gp.m_maxWorldRow; row++)
			{
				String line = bufRead.readLine();
				String numbers[] = line.split(" ");
				for (int col = 0; col < m_gp.m_maxWorldCol; col++)
				{
					m_mapTileNums[col][row] = Integer.parseInt(numbers[col]);
				}
			}
			bufRead.close();
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public void draw(Graphics2D g2D)
	{
		if (!m_mapLoaded)
		{
			System.err.println("Error: Map not loaded properly. Not drawing map.");
			return;
		}

		int row = 0, col = 0;
		for (int xPos = 0; xPos < m_gp.screenWidth; xPos += m_gp.tileSize)
		{
			for (int yPos=0; yPos < m_gp.screenHeight; yPos += m_gp.tileSize)
			{
				g2D.drawImage(m_aTiles[m_mapTileNums[col][row]].m_image, xPos, yPos, m_gp.tileSize, m_gp.tileSize, null);
				row++;
			}
			row = 0;
			col++;
		}

	}
}

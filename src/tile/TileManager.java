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
	public Tile[] m_aTiles;
	public int m_mapTileNums[][];
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

			m_aTiles[2] = new Tile(true);
			m_aTiles[2].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/TestWall001.png"));

			m_aTiles[3] = new Tile(true);
			m_aTiles[3].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/TestWater001.png"));

			m_aTiles[4] = new Tile(true);
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

		int worldRow = 0, worldCol = 0;
		for (int worldTilePosX = 0; worldTilePosX < m_gp.m_worldWidth; worldTilePosX += m_gp.tileSize)
		{
			for (int worldTilePosY=0; worldTilePosY < m_gp.m_worldHeight; worldTilePosY += m_gp.tileSize)
			{
				// Only draw tile if it is within our screen box
				if (worldTilePosX + m_gp.tileSize > (m_gp.m_player.m_worldX - m_gp.m_player.m_screenPosX) &&
					worldTilePosX - m_gp.tileSize < (m_gp.m_player.m_worldX + m_gp.m_player.m_screenPosX) &&
					worldTilePosY + m_gp.tileSize > (m_gp.m_player.m_worldY - m_gp.m_player.m_screenPosY) &&
					worldTilePosY - m_gp.tileSize < (m_gp.m_player.m_worldY + m_gp.m_player.m_screenPosX))
				{
					int screenX = worldTilePosX - (int)(m_gp.m_player.m_worldX + 0.5) + (int)(m_gp.m_player.m_screenPosX);
					int screenY = worldTilePosY - (int)(m_gp.m_player.m_worldY + 0.5) + (int)(m_gp.m_player.m_screenPosY);
					g2D.drawImage(m_aTiles[m_mapTileNums[worldCol][worldRow]].m_image, screenX, screenY, m_gp.tileSize, m_gp.tileSize, null);
				}
				worldRow++;
			}
			worldRow = 0;
			worldCol++;
		}

	}
}

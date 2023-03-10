package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

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
		setup(0, "TestGrass001.png", false);
		setup(1, "TestGrass002.png", false);
		setup(2, "TestWall001.png", true);
		setup(3, "TestWater001.png", true);
		setup(4, "TestFlower001.png", true);
		setup(5, "TestSand001.png", false);
		setup(6, "TestEarth001.png", false);
	}

	private void setup(int index, String imageName, boolean collision)
	{
		UtilityTool	uTool = new UtilityTool();

		try {
			m_aTiles[index] = new Tile(collision);
			m_aTiles[index].m_image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/"+imageName));
			m_aTiles[index].m_image = uTool.scaleImage(m_aTiles[index].m_image, m_gp.m_tileSize, m_gp.m_tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean loadMap(String filePath)
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
		for (int worldTilePosX = 0; worldTilePosX < m_gp.m_worldWidth; worldTilePosX += m_gp.m_tileSize)
		{
			for (int worldTilePosY=0; worldTilePosY < m_gp.m_worldHeight; worldTilePosY += m_gp.m_tileSize)
			{
				// Only draw tile if it is within our screen box
				if (worldTilePosX + m_gp.m_tileSize > (m_gp.player().getWorldX() - m_gp.player().m_screenPosX) &&
					worldTilePosX - m_gp.m_tileSize < (m_gp.player().getWorldX() + m_gp.player().m_screenPosX) &&
					worldTilePosY + m_gp.m_tileSize > (m_gp.player().getWorldY() - m_gp.player().m_screenPosY) &&
					worldTilePosY - m_gp.m_tileSize < (m_gp.player().getWorldY() + m_gp.player().m_screenPosX))
				{
					int screenX = worldTilePosX - (int)(m_gp.player().getWorldX() + 0.5) + (int)(m_gp.player().m_screenPosX);
					int screenY = worldTilePosY - (int)(m_gp.player().getWorldY() + 0.5) + (int)(m_gp.player().m_screenPosY);
					g2D.drawImage(m_aTiles[m_mapTileNums[worldCol][worldRow]].m_image, screenX, screenY, null);
				}
				worldRow++;
			}
			worldRow = 0;
			worldCol++;
		}

	}
}

package tile;

import java.awt.image.BufferedImage;

public class Tile
{
	public BufferedImage m_image;
	public boolean m_hasCollision;

	Tile()
	{
		m_hasCollision = false;
	}

	Tile(boolean hasCollision)
	{
		m_hasCollision = hasCollision;
	}
}

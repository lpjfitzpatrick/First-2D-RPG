package tile;

import java.awt.image.BufferedImage;

public class Tile
{
	BufferedImage m_image;
	private boolean m_hasCollision;

	Tile()
	{
		m_hasCollision = false;
	}

	Tile(boolean hasCollision)
	{
		m_hasCollision = hasCollision;
	}

	public boolean hasCollision() { return m_hasCollision; }
}

package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public class ChestObject extends AbstractObject
{
	public ChestObject(int tileSize, int coordX, int coordY)
	{
		super(tileSize, coordX, coordY);
	}

	@Override
	protected void updateParams()
	{
		m_name = "Chest";
		m_hasCollision = true;
		try {
			m_image = ImageIO.read(getClass().getResourceAsStream("../res/objects/Chest.png"));
			m_image = uTool.scaleImage(m_image, m_tileSize, m_tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}

		m_defaultSolidX = 0;
		m_defaultSolidY = m_tileSize/8;
		m_solidArea = new Rectangle(m_defaultSolidX, m_defaultSolidY, m_tileSize, 7*m_tileSize/8);
	}
}

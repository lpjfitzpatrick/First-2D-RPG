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
		} catch (IOException e) {
			e.printStackTrace();
		}

		m_solidArea = new Rectangle(0, m_tileSize/8, m_tileSize, 7*m_tileSize/8);
	}
}

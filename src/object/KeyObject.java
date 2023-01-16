package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class KeyObject extends AbstractObject
{
	public KeyObject(int tileSize, int coordX, int coordY)
	{
		super(tileSize, coordX, coordY);
	}

	@Override
	public void updateParams()
	{
		m_name = "Key";
		m_hasCollision = false;
		try {
			m_image = ImageIO.read(getClass().getResourceAsStream("../res/objects/key.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class KeyObject extends AbstractObject
{
	public KeyObject()
	{
		m_name = "Key";
		try {
			m_image = ImageIO.read(getClass().getResourceAsStream("../res/objects/key.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

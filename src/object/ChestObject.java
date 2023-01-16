package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ChestObject extends AbstractObject
{
	public ChestObject()
	{
		m_name = "Chest";
		try {
			m_image = ImageIO.read(getClass().getResourceAsStream("../res/objects/Chest.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

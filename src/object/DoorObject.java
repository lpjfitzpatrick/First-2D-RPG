package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class DoorObject extends AbstractObject
{
	public DoorObject()
	{
		m_name = "Door";
		try {
			m_image = ImageIO.read(getClass().getResourceAsStream("../res/objects/door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

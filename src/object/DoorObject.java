package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import entity.Entity;
import entity.Player;

public class DoorObject extends AbstractObject
{
	public DoorObject(int tileSize, int coordX, int coordY)
	{
		super(tileSize, coordX, coordY);
	}

	@Override
	public void updateParams()
	{
		m_name = "Door";
		m_hasCollision = true;
		try {
			m_image = ImageIO.read(getClass().getResourceAsStream("../res/objects/door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		m_solidArea = new Rectangle(0, 0, m_tileSize, m_tileSize);
	}

	@Override
	public void collisionAction(Entity entity)
	{
		if (entity instanceof Player)
		{
			Player player = (Player)entity;
			InventoryObject keyItem = new InventoryObject();
			boolean keyFound = false;
			for (InventoryObject item:player.getInventory())
			{
				if (item.m_name == "Key")
				{
					// OpenDoor
					keyItem = item;
					keyFound = true;
					break;
				}
			}
			if (keyFound) player.removeFromInventory(keyItem);
		}
	}
}

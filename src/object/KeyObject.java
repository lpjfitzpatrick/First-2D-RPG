package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

import entity.Entity;
import entity.Player;

public class KeyObject extends InventoryObject
{
	public KeyObject(int tileSize, int coordX, int coordY, ItemState eItemState)
	{
		super(tileSize, coordX, coordY, eItemState);
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

		m_solidArea = new Rectangle(0, 0, m_tileSize, m_tileSize);
	}

	@Override
	public void collisionAction(Entity entity)
	{
		if (entity instanceof Player)
		{
			if (((Player)entity).addToInventory(this))
			{
				m_eItemState = ItemState.eInInventory;
			}
		}
	}
}

package object;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;

import java.io.IOException;
import java.awt.Rectangle;

public class BootsOfSpeedObject extends InventoryObject
{
	private double m_speedBonus = 2;

	public BootsOfSpeedObject(int tileSize, int coordX, int coordY, ItemState eItemState)
	{
		super(tileSize, coordX, coordY, eItemState);
	}

	@Override
	protected void updateParams()
	{
		m_name = "BootsOfSpeed";
		m_hasCollision = false;
		try {
			m_image = ImageIO.read(getClass().getResourceAsStream("../res/objects/boots.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		m_solidArea = new Rectangle(m_tileSize/8, m_tileSize/8, 7*m_tileSize/8, 7*m_tileSize/8);
	}

	@Override
	public void collisionAction(Entity entity)
	{
		if (entity instanceof Player)
		{
			if (((Player)entity).addToInventory(this))
			{
				m_eItemState = ItemState.eInInventory;
				// This should be handled by an equiptment frame onc we have that
				((Player)entity).addToSpeed(m_speedBonus);
			}
		}
	}

	@Override
	public void update()
	{
		if (m_eItemState == ItemState.eInInventory)
		{
			m_removeFromGamePanel = true;
		}
	}
}

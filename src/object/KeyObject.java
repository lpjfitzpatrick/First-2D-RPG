package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

import entity.Entity;
import entity.Player;
import main.Sound.SoundByte;

public class KeyObject extends InventoryObject
{
	public KeyObject(int tileSize, int coordX, int coordY, ItemState eItemState)
	{
		super(tileSize, coordX, coordY, eItemState);
	}

	@Override
	protected void updateParams()
	{
		m_name = "Key";
		m_hasCollision = false;
		try {
			m_image = ImageIO.read(getClass().getResourceAsStream("../res/objects/key.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		m_defaultSolidX = 0;
		m_defaultSolidY = 0;
		m_solidArea = new Rectangle(m_defaultSolidX, m_defaultSolidY, m_tileSize, m_tileSize);
	}

	@Override
	public void collisionAction(Entity entity)
	{
		if (entity instanceof Player)
		{
			Player player = (Player)entity;
			if (player.addToInventory(this))
			{
				m_eItemState = ItemState.eInInventory;
				player.getGamePanel().playSFX(SoundByte.eCoin);
				player.getGamePanel().notifyLogMessageListener("You found a key.aaaa aaaa aaaa aaaa aaabbbbbbbbbb");
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

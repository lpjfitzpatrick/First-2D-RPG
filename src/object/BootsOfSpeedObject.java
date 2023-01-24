package object;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import main.Sound.SoundByte;

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
			m_image = uTool.scaleImage(m_image, m_tileSize, m_tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}

		m_defaultSolidX = m_tileSize/8;
		m_defaultSolidY = m_tileSize/8;
		m_solidArea = new Rectangle(m_defaultSolidX, m_defaultSolidY, 7*m_tileSize/8, 7*m_tileSize/8);
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
				// This should be handled by an equiptment frame onc we have that
				player.addToSpeed(m_speedBonus);
				player.getGamePanel().playSFX(SoundByte.ePowerup);
				player.getGamePanel().notifyLogMessageListener("You found boots of speed.");
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

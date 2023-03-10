package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import entity.Entity;
import entity.Player;
import main.Sound.SoundByte;

public class DoorObject extends OpenableObject
{
	public DoorObject(int tileSize, int coordX, int coordY, OpenableObjectState eObjectState)
	{
		super(tileSize, coordX, coordY, eObjectState);
	}

	@Override
	protected void updateParams()
	{
		m_name = "Door";
		m_hasCollision = true;
		try {
			m_image = ImageIO.read(getClass().getResourceAsStream("../res/objects/door.png"));
			m_image = uTool.scaleImage(m_image, m_tileSize, m_tileSize);
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
			InventoryObject keyItem = new InventoryObject();
			boolean keyFound = false;
			for (InventoryObject item:player.getInventory())
			{
				if (item.m_name == "Key")
				{
					m_eObjectState = OpenableObjectState.eOpen;
					player.getGamePanel().playSFX(SoundByte.eUnlock);
					player.getGamePanel().notifyLogMessageListener("You unlocked the door with your key.");
					keyItem = item;
					keyFound = true;
					break;
				}
			}
			if (keyFound) player.removeFromInventory(keyItem);
			else player.getGamePanel().notifyLogMessageListener("The door appears to be locked.");
		}
	}

	@Override
	public void update()
	{
		if (m_eObjectState == OpenableObjectState.eOpen)
		{
			m_hasCollision = false;
			// If we had an open door image we could update the image here.
			// Then we could e.g. open and close which could be triggered by a keypress
			// We will just remove the door from the screen for now
			m_removeFromGamePanel = true;
		}
	}
}

package main;

import object.KeyObject;
import object.InventoryObject.ItemState;
import object.OpenableObject.OpenableObjectState;
import object.BootsOfSpeedObject;
import object.ChestObject;
import object.DoorObject;

public class AssetSetter
{
	private GamePanel m_gp;

	public AssetSetter(GamePanel gp)
	{
		m_gp = gp;
	}

	public void setAssets()
	{
		KeyObject key0 = new KeyObject(m_gp.m_tileSize, 23, 7, ItemState.eOnGround);
		m_gp.addObjectToMap(key0);

		KeyObject key1 = new KeyObject(m_gp.m_tileSize, 23, 40, ItemState.eOnGround);
		m_gp.addObjectToMap(key1);

		KeyObject key2 = new KeyObject(m_gp.m_tileSize, 38, 8, ItemState.eOnGround);
		m_gp.addObjectToMap(key2);

		DoorObject door1 = new DoorObject(m_gp.m_tileSize, 10, 11, OpenableObjectState.eClosed);
		m_gp.addObjectToMap(door1);

		DoorObject door2 = new DoorObject(m_gp.m_tileSize, 8, 28, OpenableObjectState.eClosed);
		m_gp.addObjectToMap(door2);

		DoorObject door3 = new DoorObject(m_gp.m_tileSize, 12, 22, OpenableObjectState.eClosed);
		m_gp.addObjectToMap(door3);

		ChestObject chest1 = new ChestObject(m_gp.m_tileSize, 10, 7);
		m_gp.addObjectToMap(chest1);

		BootsOfSpeedObject bootsOfSpeed = new BootsOfSpeedObject(m_gp.m_tileSize, 36, 40, ItemState.eOnGround);
		m_gp.addObjectToMap(bootsOfSpeed);
	}
}

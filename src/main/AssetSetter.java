package main;

import object.KeyObject;
import object.InventoryObject.ItemState;
import object.ChestObject;
import object.DoorObject;

public class AssetSetter
{
	GamePanel m_gp;

	public AssetSetter(GamePanel gp)
	{
		m_gp = gp;
	}

	public void setAssets()
	{
		KeyObject key0 = new KeyObject(m_gp.tileSize, 23, 7, ItemState.eOnGround);
		m_gp.addObjectToMap(key0);

		KeyObject key1 = new KeyObject(m_gp.tileSize, 23, 40, ItemState.eOnGround);
		m_gp.addObjectToMap(key1);

		KeyObject key2 = new KeyObject(m_gp.tileSize, 38, 8, ItemState.eOnGround);
		m_gp.addObjectToMap(key2);

		DoorObject door1 = new DoorObject(m_gp.tileSize, 10, 11);
		m_gp.addObjectToMap(door1);

		DoorObject door2 = new DoorObject(m_gp.tileSize, 8, 28);
		m_gp.addObjectToMap(door2);

		DoorObject door3 = new DoorObject(m_gp.tileSize, 12, 22);
		m_gp.addObjectToMap(door3);

		ChestObject chest1 = new ChestObject(m_gp.tileSize, 10, 7);
		m_gp.addObjectToMap(chest1);
	}
}

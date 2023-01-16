package main;

import object.KeyObject;
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
		KeyObject key0 = new KeyObject();
		key0.m_worldX = 23 * m_gp.tileSize;
		key0.m_worldY = 7 * m_gp.tileSize;
		m_gp.m_objects[0] = key0;

		KeyObject key1 = new KeyObject();
		key1.m_worldX = 23 * m_gp.tileSize;
		key1.m_worldY = 40 * m_gp.tileSize;
		m_gp.m_objects[1] = key1;

		KeyObject key2 = new KeyObject();
		key2.m_worldX = 38 * m_gp.tileSize;
		key2.m_worldY = 8 * m_gp.tileSize;
		m_gp.m_objects[2] = key2;

		DoorObject door1 = new DoorObject();
		door1.m_worldX = 10 * m_gp.tileSize;
		door1.m_worldY = 11 * m_gp.tileSize;
		m_gp.m_objects[3] = door1;

		DoorObject door2 = new DoorObject();
		door2.m_worldX = 8 * m_gp.tileSize;
		door2.m_worldY = 28 * m_gp.tileSize;
		m_gp.m_objects[4] = door2;

		DoorObject door3 = new DoorObject();
		door3.m_worldX = 12 * m_gp.tileSize;
		door3.m_worldY = 22 * m_gp.tileSize;
		m_gp.m_objects[5] = door3;

		ChestObject chest1 = new ChestObject();
		chest1.m_worldX = 10 * m_gp.tileSize;
		chest1.m_worldY = 7 * m_gp.tileSize;
		m_gp.m_objects[6] = chest1;
	}
}

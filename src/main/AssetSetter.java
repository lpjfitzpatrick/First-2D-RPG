package main;

import object.KeyObject;

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
	}
}

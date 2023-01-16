package object;

import java.awt.image.BufferedImage;

import main.GamePanel;

import java.awt.Graphics2D;

public class AbstractObject
{
	public BufferedImage m_image;
	public String m_name;
	public boolean m_hasCollision;
	public int m_worldX, m_worldY;

	// This may get some default definition but child class can override anyway
	public void update()
	{}

	public void draw(Graphics2D g2D, GamePanel gp)
	{
		// Only draw object if it's within our screen box
		if (m_worldX + gp.tileSize > (gp.m_player.m_worldX - gp.m_player.m_screenPosX) &&
			m_worldX - gp.tileSize < (gp.m_player.m_worldX + gp.m_player.m_screenPosX) &&
			m_worldY + gp.tileSize > (gp.m_player.m_worldY - gp.m_player.m_screenPosY) &&
			m_worldY - gp.tileSize < (gp.m_player.m_worldY + gp.m_player.m_screenPosX))
		{
			int screenX = m_worldX - (int)(gp.m_player.m_worldX + 0.5) + (int)(gp.m_player.m_screenPosX);
			int screenY = m_worldY - (int)(gp.m_player.m_worldY + 0.5) + (int)(gp.m_player.m_screenPosY);
			g2D.drawImage(m_image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}

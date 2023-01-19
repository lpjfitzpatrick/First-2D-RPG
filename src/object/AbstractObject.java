package object;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class AbstractObject
{
	BufferedImage m_image;
	String m_name;
	boolean m_hasCollision;
	boolean m_isOnScreen;
	private int m_worldX, m_worldY;
	private int m_coordX, m_coordY; // tile cords like 15,25
	int m_tileSize;
	boolean m_removeFromGamePanel = false;

	Rectangle m_solidArea;
	protected int m_defaultSolidX, m_defaultSolidY;

	AbstractObject() {}

	AbstractObject(int tileSize, int coordX, int coordY)
	{
		m_coordX = coordX;
		m_coordY = coordY;
		m_tileSize = tileSize;
		m_worldX = coordX*tileSize;
		m_worldY = coordY*tileSize;
		updateParams();
	}

	public int getWorldX() { return m_worldX; }
	public int getWorldY() { return m_worldY; }
	public int getCoordX() { return m_coordX; }
	public int getCoordY() { return m_coordY; }
	public Rectangle getSolidArea() { return m_solidArea; }
	public int getDefaultSolidX() { return m_defaultSolidX; }
	public int getDefaultSolidY() { return m_defaultSolidY; }
	public boolean hasCollision() { return m_hasCollision; }
	public boolean isOnScreen() { return m_isOnScreen; }
	public boolean removeFromGamePanel() { return m_removeFromGamePanel; }
	public BufferedImage image() { return m_image;}

	public void collisionAction(Entity entity) {}
	public void update() {}
	protected void updateParams() {}

	public void draw(Graphics2D g2D, GamePanel gp)
	{
		// Only draw object if it's within our screen box
		if (m_worldX + gp.m_tileSize > (gp.player().getWorldX() - gp.player().m_screenPosX) &&
			m_worldX - gp.m_tileSize < (gp.player().getWorldX() + gp.player().m_screenPosX) &&
			m_worldY + gp.m_tileSize > (gp.player().getWorldY() - gp.player().m_screenPosY) &&
			m_worldY - gp.m_tileSize < (gp.player().getWorldY() + gp.player().m_screenPosX))
		{
			int screenX = m_worldX - (int)(gp.player().getWorldX() + 0.5) + (int)(gp.player().m_screenPosX);
			int screenY = m_worldY - (int)(gp.player().getWorldY() + 0.5) + (int)(gp.player().m_screenPosY);
			g2D.drawImage(m_image, screenX, screenY, gp.m_tileSize, gp.m_tileSize, null);
			m_isOnScreen = true;
			return;
		}
		m_isOnScreen = false;
	}
}

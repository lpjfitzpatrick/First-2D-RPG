package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public double m_worldX,m_worldY;
	public double m_speed, m_diagonalSpeed;

	public BufferedImage m_upNeutral, m_up1, m_up2, m_downNeutral, m_down1, m_down2;
	public BufferedImage m_leftNeutral, m_left1, m_left2, m_rightNeutral, m_right1, m_right2;
	public Direction m_eDirection;

	public int m_spriteNum = 1;
	public int m_spriteFrameCount;
	public int m_spriteSwapInterval;

	Rectangle m_solidArea;
	public boolean m_isColliding;

	public enum Direction {
		eUp,
		eDown,
		eLeft,
		eRight,
		eUpRight,
		eUpLeft,
		eDownRight,
		eDownLeft
	}

	public Rectangle getSolidArea() { return m_solidArea; }

	public BufferedImage walkingSprite(BufferedImage sprite1, BufferedImage sprite2)
	{
		if (m_spriteNum == 1)
		{
			return sprite1;
		}
		if (m_spriteNum == 2)
		{
			return sprite2;
		}

		// Failcase juse return sprite 1. Prob change to something else later.
		return sprite1;
	}
}

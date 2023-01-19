package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	double m_worldX,m_worldY;
	double m_speed, m_diagonalSpeed;

	BufferedImage m_upNeutral, m_up1, m_up2, m_downNeutral, m_down1, m_down2;
	BufferedImage m_leftNeutral, m_left1, m_left2, m_rightNeutral, m_right1, m_right2;
	Direction m_eDirection;

	int m_spriteNum = 1;
	int m_spriteFrameCount;
	int m_spriteSwapInterval;

	Rectangle m_solidArea;
	int m_defaultSolidX;
	int m_defaultSolidY;
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

	public double getSpeed() { return m_speed; }
	public double getDiagonalSpeed() { return m_diagonalSpeed; }
	public double getWorldX() { return m_worldX; }
	public double getWorldY() { return m_worldY; }
	public Direction getDirection() { return m_eDirection; }
	public Rectangle getSolidArea() { return m_solidArea; }
	public int getDefaultSolidX() { return m_defaultSolidX;	}
	public int getDefaultSolidY() { return m_defaultSolidY; }

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

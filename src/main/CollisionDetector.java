package main;

import entity.Entity;
import object.AbstractObject;

import java.awt.Rectangle;

public class CollisionDetector
{
	private GamePanel m_gp;

	public CollisionDetector(GamePanel gp)
	{
		m_gp = gp;
	}

	private void setEntityCollision(Entity entity, int tileNum1, int tileNum2)
	{
		if (m_gp.tileManager().m_aTiles[tileNum1].hasCollision() || m_gp.tileManager().m_aTiles[tileNum2].hasCollision())
		{
			entity.m_isColliding = true;
		}
	}

	private void setEntityCollision(Entity entity, int tileNum1, int tileNum2, int tileNum3)
	{
		if (
			m_gp.tileManager().m_aTiles[tileNum1].hasCollision() || m_gp.tileManager().m_aTiles[tileNum2].hasCollision() ||
			m_gp.tileManager().m_aTiles[tileNum3].hasCollision())
		{
			entity.m_isColliding = true;
		}
	}

	public void checkTileCollision(Entity entity)
	{
		// Coords of the entities collision box in pixel values
		int entityLeftWorldX = (int)(entity.getWorldX() + 0.5) + entity.getSolidArea().x;
		int entityRightWorldX = (int)(entity.getWorldX() + 0.5) + entity.getSolidArea().x + entity.getSolidArea().width;
		int entityTopWorldY = (int)(entity.getWorldY() + 0.5) + entity.getSolidArea().y;
		int entityBottomWorldY = (int)(entity.getWorldY() + 0.5) + entity.getSolidArea().y + entity.getSolidArea().height;

		// Coords of the entities collision box in row/col
		int entityLeftCol = entityLeftWorldX/m_gp.m_tileSize;
		int entityRightCol = entityRightWorldX/m_gp.m_tileSize;
		int entityTopRow = entityTopWorldY/m_gp.m_tileSize;
		int entityBottomRow = entityBottomWorldY/m_gp.m_tileSize;

		// Tiles the entity may collide with
		int tileNum1, tileNum2, tileNum3;

		switch (entity.getDirection())
		{
		case eUp:
			entityTopRow = (entityTopWorldY - (int)(entity.getSpeed() + 0.5))/m_gp.m_tileSize;
			tileNum1 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityTopRow];
			tileNum2 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityTopRow];
			setEntityCollision(entity, tileNum1, tileNum2);
			break;
		case eUpLeft:
			entityTopRow = (entityTopWorldY - (int)(entity.getDiagonalSpeed() + 0.5))/m_gp.m_tileSize;
			entityLeftCol = (entityLeftWorldX - (int)(entity.getDiagonalSpeed() + 0.5))/m_gp.m_tileSize;
			tileNum1 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityTopRow];
			tileNum2 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityTopRow];
			tileNum3 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2, tileNum3);
			break;
		case eUpRight:
			entityTopRow = (entityTopWorldY - (int)(entity.getDiagonalSpeed() + 0.5))/m_gp.m_tileSize;
			entityRightCol = (entityRightWorldX + (int)(entity.getDiagonalSpeed() + 0.5))/m_gp.m_tileSize;
			tileNum1 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityTopRow];
			tileNum2 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityTopRow];
			tileNum3 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2, tileNum3);
			break;
		case eDown:
			entityBottomRow = (entityBottomWorldY + (int)(entity.getSpeed() + 0.5))/m_gp.m_tileSize;
			tileNum1 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityBottomRow];
			tileNum2 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2);
			break;
		case eDownLeft:
			entityBottomRow = (entityBottomWorldY + (int)(entity.getDiagonalSpeed() + 0.5))/m_gp.m_tileSize;
			entityLeftCol = (entityLeftWorldX - (int)(entity.getDiagonalSpeed() + 0.5))/m_gp.m_tileSize;
			tileNum1 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityBottomRow];
			tileNum2 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityBottomRow];
			tileNum3 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityTopRow];
			setEntityCollision(entity, tileNum1, tileNum2, tileNum3);
			break;
		case eDownRight:
			entityBottomRow = (entityBottomWorldY + (int)(entity.getDiagonalSpeed() + 0.5))/m_gp.m_tileSize;
			entityRightCol = (entityRightWorldX + (int)(entity.getDiagonalSpeed() + 0.5))/m_gp.m_tileSize;
			tileNum1 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityBottomRow];
			tileNum2 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityBottomRow];
			tileNum3 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityTopRow];
			setEntityCollision(entity, tileNum1, tileNum2, tileNum3);
			break;
		case eLeft:
			entityLeftCol = (entityLeftWorldX - (int)(entity.getSpeed() + 0.5))/m_gp.m_tileSize;
			tileNum1 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityTopRow];
			tileNum2 = m_gp.tileManager().m_mapTileNums[entityLeftCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2);
			break;
		case eRight:
			entityRightCol = (entityRightWorldX + (int)(entity.getSpeed() + 0.5))/m_gp.m_tileSize;
			tileNum1 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityTopRow];
			tileNum2 = m_gp.tileManager().m_mapTileNums[entityRightCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2);
			break;
		default:
			break;
		}
	}

	public void checkObjectCollision(Entity entity)
	{
		for(AbstractObject obj:m_gp.getObjects())
		{
			if (obj != null && obj.isOnScreen())
			{
				entity.getSolidArea().x = (int)(entity.getWorldX() + 0.5) + entity.getSolidArea().x;
				entity.getSolidArea().y = (int)(entity.getWorldY() + 0.5) + entity.getSolidArea().y;

				obj.getSolidArea().x = obj.getWorldX() + obj.getDefaultSolidX();
				obj.getSolidArea().y = obj.getWorldY() + obj.getDefaultSolidY();

				switch (entity.getDirection())
				{
				case eUp:
					entity.getSolidArea().y -= entity.getSpeed();
					if (entity.getSolidArea().intersects(obj.getSolidArea()))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eUpLeft:
					entity.getSolidArea().y -= entity.getDiagonalSpeed();
					entity.getSolidArea().x -= entity.getDiagonalSpeed();
					if (entity.getSolidArea().intersects(obj.getSolidArea()))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eUpRight:
					entity.getSolidArea().y -= entity.getDiagonalSpeed();
					entity.getSolidArea().x += entity.getDiagonalSpeed();
					if (entity.getSolidArea().intersects(obj.getSolidArea()))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eDown:
					entity.getSolidArea().y += entity.getSpeed();
					if (entity.getSolidArea().intersects(obj.getSolidArea()))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eDownLeft:
					entity.getSolidArea().y += entity.getDiagonalSpeed();
					entity.getSolidArea().x -= entity.getDiagonalSpeed();
					if (entity.getSolidArea().intersects(obj.getSolidArea()))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eDownRight:
					entity.getSolidArea().y += entity.getDiagonalSpeed();
					entity.getSolidArea().x += entity.getDiagonalSpeed();
					if (entity.getSolidArea().intersects(obj.getSolidArea()))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eLeft:
					entity.getSolidArea().x -= entity.getSpeed();
					if (entity.getSolidArea().intersects(obj.getSolidArea()))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eRight:
					entity.getSolidArea().x += entity.getSpeed();
					if (entity.getSolidArea().intersects(obj.getSolidArea()))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				default:
					break;
				}
				entity.getSolidArea().x = entity.getDefaultSolidX();
				entity.getSolidArea().y = entity.getDefaultSolidY();
				obj.getSolidArea().x = obj.getDefaultSolidX();
				obj.getSolidArea().y = obj.getDefaultSolidY();
			}
		}
	}
}

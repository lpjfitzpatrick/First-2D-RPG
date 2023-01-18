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
				Rectangle entitySolidArea = (Rectangle)entity.getSolidArea().clone();
				entitySolidArea.x = (int)(entity.getWorldX() + 0.5) + entity.getSolidArea().x;
				entitySolidArea.y = (int)(entity.getWorldY() + 0.5) + entity.getSolidArea().y;

				Rectangle objectSolidArea = (Rectangle)obj.getSolidArea().clone();
				objectSolidArea.x = obj.getWorldX() + obj.getSolidArea().x;
				objectSolidArea.y = obj.getWorldY() + obj.getSolidArea().y;

				switch (entity.getDirection())
				{
				case eUp:
					entitySolidArea.y -= entity.getSpeed();
					if (entitySolidArea.intersects(objectSolidArea))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eUpLeft:
					entitySolidArea.y -= entity.getDiagonalSpeed();
					entitySolidArea.x -= entity.getDiagonalSpeed();
					if (entitySolidArea.intersects(objectSolidArea))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eUpRight:
					entitySolidArea.y -= entity.getDiagonalSpeed();
					entitySolidArea.x += entity.getDiagonalSpeed();
					if (entitySolidArea.intersects(objectSolidArea))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eDown:
					entitySolidArea.y += entity.getSpeed();
					if (entitySolidArea.intersects(objectSolidArea))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eDownLeft:
					entitySolidArea.y += entity.getDiagonalSpeed();
					entitySolidArea.x -= entity.getDiagonalSpeed();
					if (entitySolidArea.intersects(objectSolidArea))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eDownRight:
					entitySolidArea.y += entity.getDiagonalSpeed();
					entitySolidArea.x += entity.getDiagonalSpeed();
					if (entitySolidArea.intersects(objectSolidArea))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eLeft:
					entitySolidArea.x -= entity.getSpeed();
					if (entitySolidArea.intersects(objectSolidArea))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				case eRight:
					entitySolidArea.x += entity.getSpeed();
					if (entitySolidArea.intersects(objectSolidArea))
					{
						if (obj.hasCollision()) entity.m_isColliding = true;
						obj.collisionAction(entity);
					}
					break;
				default:
					break;
				}
			}
		}
	}
}

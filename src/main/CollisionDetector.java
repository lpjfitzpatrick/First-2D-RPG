package main;

import entity.Entity;

public class CollisionDetector
{
	GamePanel m_gp;

	public CollisionDetector(GamePanel gp)
	{
		m_gp = gp;
	}

	public void setEntityCollision(Entity entity, int tileNum1, int tileNum2)
	{
		if (m_gp.m_tileManager.m_aTiles[tileNum1].m_hasCollision || m_gp.m_tileManager.m_aTiles[tileNum2].m_hasCollision)
		{
			entity.m_isColliding = true;
		}
	}

	public void setEntityCollision(Entity entity, int tileNum1, int tileNum2, int tileNum3)
	{
		if (
			m_gp.m_tileManager.m_aTiles[tileNum1].m_hasCollision || m_gp.m_tileManager.m_aTiles[tileNum2].m_hasCollision ||
			m_gp.m_tileManager.m_aTiles[tileNum3].m_hasCollision)
		{
			entity.m_isColliding = true;
		}
	}

	public void checkTileCollision(Entity entity)
	{
		// Coords of the entities collision box in pixel values
		int entityLeftWorldX = (int)(entity.m_worldX + 0.5) + entity.m_solidArea.x;
		int entityRightWorldX = (int)(entity.m_worldX + 0.5) + entity.m_solidArea.x + entity.m_solidArea.width;
		int entityTopWorldY = (int)(entity.m_worldY + 0.5) + entity.m_solidArea.y;
		int entityBottomWorldY = (int)(entity.m_worldY + 0.5) + entity.m_solidArea.y + entity.m_solidArea.height;

		// Coords of the entities collision box in row/col
		int entityLeftCol = entityLeftWorldX/m_gp.tileSize;
		int entityRightCol = entityRightWorldX/m_gp.tileSize;
		int entityTopRow = entityTopWorldY/m_gp.tileSize;
		int entityBottomRow = entityBottomWorldY/m_gp.tileSize;

		// Tiles the entity may collide with
		int tileNum1, tileNum2, tileNum3;

		// Needs to be modified to handle diagonals
		switch (entity.m_eDirection)
		{
		case eUp:
			entityTopRow = (entityTopWorldY - (int)(entity.m_speed + 0.5))/m_gp.tileSize;
			tileNum1 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityTopRow];
			tileNum2 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityTopRow];
			setEntityCollision(entity, tileNum1, tileNum2);
			break;
		case eUpLeft:
			entityTopRow = (entityTopWorldY - (int)(entity.m_diagonalSpeed + 0.5))/m_gp.tileSize;
			entityLeftCol = (entityLeftWorldX - (int)(entity.m_diagonalSpeed + 0.5))/m_gp.tileSize;
			tileNum1 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityTopRow];
			tileNum2 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityTopRow];
			tileNum3 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2, tileNum3);
			break;
		case eUpRight:
			entityTopRow = (entityTopWorldY - (int)(entity.m_diagonalSpeed + 0.5))/m_gp.tileSize;
			entityRightCol = (entityRightWorldX + (int)(entity.m_diagonalSpeed + 0.5))/m_gp.tileSize;
			tileNum1 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityTopRow];
			tileNum2 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityTopRow];
			tileNum3 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2, tileNum3);
			break;
		case eDown:
			entityBottomRow = (entityBottomWorldY + (int)(entity.m_speed + 0.5))/m_gp.tileSize;
			tileNum1 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityBottomRow];
			tileNum2 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2);
			break;
		case eDownLeft:
			entityBottomRow = (entityBottomWorldY + (int)(entity.m_diagonalSpeed + 0.5))/m_gp.tileSize;
			entityLeftCol = (entityLeftWorldX - (int)(entity.m_diagonalSpeed + 0.5))/m_gp.tileSize;
			tileNum1 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityBottomRow];
			tileNum2 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityBottomRow];
			tileNum3 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityTopRow];
			setEntityCollision(entity, tileNum1, tileNum2, tileNum3);
			break;
		case eDownRight:
			entityBottomRow = (entityBottomWorldY + (int)(entity.m_diagonalSpeed + 0.5))/m_gp.tileSize;
			entityRightCol = (entityRightWorldX + (int)(entity.m_diagonalSpeed + 0.5))/m_gp.tileSize;
			tileNum1 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityBottomRow];
			tileNum2 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityBottomRow];
			tileNum3 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityTopRow];
			setEntityCollision(entity, tileNum1, tileNum2, tileNum3);
			break;
		case eLeft:
			entityLeftCol = (entityLeftWorldX - (int)(entity.m_speed + 0.5))/m_gp.tileSize;
			tileNum1 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityTopRow];
			tileNum2 = m_gp.m_tileManager.m_mapTileNums[entityLeftCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2);
			break;
		case eRight:
			entityRightCol = (entityRightWorldX + (int)(entity.m_speed + 0.5))/m_gp.tileSize;
			tileNum1 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityTopRow];
			tileNum2 = m_gp.m_tileManager.m_mapTileNums[entityRightCol][entityBottomRow];
			setEntityCollision(entity, tileNum1, tileNum2);
			break;
		default:
			break;
		}
	}
}

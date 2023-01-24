package entity;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.InventoryObject;

public class Player extends Entity
{
	GamePanel m_gp;
	KeyHandler m_keyHandler;
	ArrayList<InventoryObject> m_inventory;
	public final int m_inventoryCapacity = 10;

	public final double m_screenPosX;
	public final double m_screenPosY;

	public GamePanel getGamePanel() { return m_gp; }
	public ArrayList<InventoryObject> getInventory() { return m_inventory; }

	public Player(GamePanel gp, KeyHandler keyHandler)
	{
		m_gp = gp;
		m_keyHandler = keyHandler;
		// Start position of the player relative to he top left corner of the screen
		m_screenPosX = m_gp.screenWidth/2 - (m_gp.m_tileSize/2);
		m_screenPosY = m_gp.screenHeight/2 - (m_gp.m_tileSize/2);

		m_defaultSolidX = 3*m_gp.m_tileSize/16;
		m_defaultSolidY = m_gp.m_tileSize/4;
		m_solidArea = new Rectangle(m_defaultSolidX, m_defaultSolidY, 5*m_gp.m_tileSize/8, 3*m_gp.m_tileSize/4 - m_gp.m_tileSize/32);
		m_inventory = new ArrayList<InventoryObject>(m_inventoryCapacity);

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues()
	{
		// Starting position of player relative to top left corner of world map
		m_worldX = m_gp.m_tileSize * 23;
		m_worldY = m_gp.m_tileSize * 21;
		m_speed = 4;
		computeWalkPace();
		computeDiagonalSpeed();
		m_eDirection = Direction.eDown;
	}

	public void getPlayerImage()
	{
		m_upNeutral = setup("character_up_neutral.png");
		m_up1 = setup("character_up1.png");
		m_up2 = setup("character_up2.png");
		m_downNeutral = setup("character_down_neutral.png");
		m_down1 = setup("character_down1.png");
		m_down2 = setup("character_down2.png");
		m_leftNeutral = setup("character_left_neutral.png");
		m_left1 = setup("character_left1.png");
		m_left2 = setup("character_left2.png");
		m_rightNeutral = setup("character_right_neutral.png");
		m_right1 = setup("character_right1.png");
		m_right2 = setup("character_right2.png");
	}

	private BufferedImage setup(String imageName)
	{
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("../res/player/"+imageName));
			image = uTool.scaleImage(image, m_gp.m_tileSize, m_gp.m_tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void update()
	{
		if (!m_keyHandler.isMoving()) return;

		if (m_keyHandler.upPressed() && !m_keyHandler.downPressed())
		{
			if (m_keyHandler.leftPressed() && !m_keyHandler.rightPressed())
			{
				m_eDirection = Direction.eUpLeft;
			}
			else if (m_keyHandler.rightPressed() && !m_keyHandler.leftPressed())
			{
				m_eDirection = Direction.eUpRight;
			}
			else
			{
				m_eDirection = Direction.eUp;
			}
		}
		else if (m_keyHandler.downPressed() && !m_keyHandler.upPressed())
		{
			if (m_keyHandler.leftPressed() && !m_keyHandler.rightPressed())
			{
				m_eDirection = Direction.eDownLeft;
			}
			else if (m_keyHandler.rightPressed() && !m_keyHandler.leftPressed())
			{
				m_eDirection = Direction.eDownRight;
			}
			else
			{
				m_eDirection = Direction.eDown;
			}
		}
		else if (m_keyHandler.leftPressed() && !m_keyHandler.rightPressed())
		{
			m_eDirection = Direction.eLeft;
		}
		else if (m_keyHandler.rightPressed() && !m_keyHandler.leftPressed())
		{
			m_eDirection = Direction.eRight;
		}

		m_isColliding = false;
		m_gp.collisionDetector().checkTileCollision(this);
		m_gp.collisionDetector().checkObjectCollision(this);

		if (!m_isColliding)
		{
			switch(m_eDirection)
			{
			case eUp:
				m_worldY -= m_speed;
				break;
			case eUpLeft:
				m_worldY -= m_diagonalSpeed;
				m_worldX -= m_diagonalSpeed;
				break;
			case eUpRight:
				m_worldY -= m_diagonalSpeed;
				m_worldX += m_diagonalSpeed;
				break;
			case eDown:
				m_worldY += m_speed;
				break;
			case eDownLeft:
				m_worldY += m_diagonalSpeed;
				m_worldX -= m_diagonalSpeed;
				break;
			case eDownRight:
				m_worldY += m_diagonalSpeed;
				m_worldX += m_diagonalSpeed;
				break;
			case eLeft:
				m_worldX -= m_speed;
				break;
			case eRight:
				m_worldX += m_speed;
				break;
			}
		}

		m_spriteFrameCount++;
		if (m_spriteFrameCount > m_spriteSwapInterval)
		{
			if (m_spriteNum == 1)
			{
				m_spriteNum = 2;
			}
			else if (m_spriteNum == 2)
			{
				m_spriteNum = 1;
			}
			m_spriteFrameCount = 0;
		}
	}

	public void draw(Graphics2D g2D)
	{
		BufferedImage image = null;

		switch (m_eDirection)
		{
		case eUpLeft:
		case eUpRight:
		case eUp:
			if (m_keyHandler.isMoving())
			{
				image = walkingSprite(m_up1, m_up2);
			}
			else
			{
				image = m_upNeutral;
			}
			break;
		case eDownLeft:
		case eDownRight:
		case eDown:
			if (m_keyHandler.isMoving())
			{
				image = walkingSprite(m_down1, m_down2);
			}
			else
			{
				image = m_downNeutral;
			}
			break;
		case eLeft:
			if (m_keyHandler.isMoving())
			{
				image = walkingSprite(m_left1, m_left2);
			}
			else
			{
				image = m_leftNeutral;
			}
			break;
		case eRight:
			if (m_keyHandler.isMoving())
			{
				image = walkingSprite(m_right1, m_right2);
			}
			else
			{
				image = m_rightNeutral;
			}
			break;
		default:
			break;
		}

		g2D.drawImage(image, (int)(m_screenPosX + 0.5), (int)(m_screenPosY + 0.5), null);
	}

	private void computeDiagonalSpeed()
	{
		// Using pythag with opp and adj being the same length hyp^2 = 2*side^2
		// So side = (1/root(2))*hyp and hyp is pixelsPerFrame
		double pixelsPerFrame = m_speed/m_gp.FPS;
		double reducedDistance = pixelsPerFrame/Math.sqrt(2);
		m_diagonalSpeed = reducedDistance*m_gp.FPS;
	}

	private void computeWalkPace()
	{
		// Default pace is 15 with FPS = 60 and speed = 4. Pace increases linearly with speed
		m_spriteSwapInterval = m_gp.FPS - 41 - (int)(m_speed + 0.5);
	}

	public boolean addToInventory(InventoryObject item)
	{
		return m_inventory.add(item);
	}

	public boolean removeFromInventory(InventoryObject item)
	{
		return m_inventory.remove(item);
	}

	public void addToSpeed(double bonusSpeed)
	{
		m_speed += bonusSpeed;
		computeWalkPace();
		computeDiagonalSpeed();
	}
}

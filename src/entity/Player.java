package entity;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.InventoryObject;

public class Player extends Entity
{
	GamePanel m_gp;
	KeyHandler m_keyHandler;
	ArrayList<InventoryObject> m_inventory;

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

		m_solidArea = new Rectangle(3*m_gp.m_tileSize/16, m_gp.m_tileSize/4, 5*m_gp.m_tileSize/8, 3*m_gp.m_tileSize/4 - m_gp.m_tileSize/32);
		m_inventory = new ArrayList<InventoryObject>(10);

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues()
	{
		// Starting position of player relative to top left corner of world map
		m_worldX = m_gp.m_tileSize * 23;
		m_worldY = m_gp.m_tileSize * 21;
		m_speed = 4;
		m_spriteSwapInterval = 15;
		computeDiagonalSpeed();
		m_eDirection = Direction.eDown;
	}

	public void getPlayerImage()
	{
		try {
			m_upNeutral = ImageIO.read(getClass().getResourceAsStream("../res/player/character_up_neutral.png"));
			m_up1 = ImageIO.read(getClass().getResourceAsStream("../res/player/character_up1.png"));
			m_up2 = ImageIO.read(getClass().getResourceAsStream("../res/player/character_up2.png"));
			m_downNeutral = ImageIO.read(getClass().getResourceAsStream("../res/player/character_down_neutral.png"));
			m_down1 = ImageIO.read(getClass().getResourceAsStream("../res/player/character_down1.png"));
			m_down2 = ImageIO.read(getClass().getResourceAsStream("../res/player/character_down2.png"));
			m_leftNeutral = ImageIO.read(getClass().getResourceAsStream("../res/player/character_left_neutral.png"));
			m_left1 = ImageIO.read(getClass().getResourceAsStream("../res/player/character_left1.png"));
			m_left2 = ImageIO.read(getClass().getResourceAsStream("../res/player/character_left2.png"));
			m_rightNeutral = ImageIO.read(getClass().getResourceAsStream("../res/player/character_right_neutral.png"));
			m_right1 = ImageIO.read(getClass().getResourceAsStream("../res/player/character_right1.png"));
			m_right2 = ImageIO.read(getClass().getResourceAsStream("../res/player/character_right2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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

		g2D.drawImage(image, (int)(m_screenPosX + 0.5), (int)(m_screenPosY + 0.5), m_gp.m_tileSize, m_gp.m_tileSize, null);
	}

	private void computeDiagonalSpeed()
	{
		// Using pythag with opp and adj being the same length hyp^2 = 2*side^2
		// So side = (1/root(2))*hyp and hyp is pixelsPerFrame
		double pixelsPerFrame = m_speed/m_gp.FPS;
		double reducedDistance = pixelsPerFrame/Math.sqrt(2);
		m_diagonalSpeed = reducedDistance*m_gp.FPS;
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
		computeDiagonalSpeed();
	}
}

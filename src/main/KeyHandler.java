package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
	private boolean m_upPressed, m_downPressed, m_leftPressed, m_rightPressed;
	public boolean m_BPressed = false;
	// Debug
	public boolean m_debug = false;

	public boolean upPressed() { return m_upPressed; }
	public boolean downPressed() { return m_downPressed; }
	public boolean leftPressed() { return m_leftPressed; }
	public boolean rightPressed() { return m_rightPressed; }

	public boolean leftAndRightPressed()
	{
		return m_leftPressed && m_rightPressed;
	}

	public boolean upAndDownPressed()
	{
		return m_upPressed && m_downPressed;
	}

	public boolean allMovementKeysPressed()
	{
		return m_upPressed && m_downPressed && m_leftPressed && m_rightPressed;
	}

	private boolean noMovementKeysPressed()
	{
		return !m_upPressed && !m_downPressed && !m_leftPressed && !m_rightPressed;
	}

	public boolean isMoving()
	{
		if (allMovementKeysPressed() || noMovementKeysPressed()) return false;

		if (upAndDownPressed() && (!m_leftPressed && !m_rightPressed)) return false;

		if (leftAndRightPressed() && (!m_upPressed && !m_downPressed)) return false;

		return true;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W)
		{
			m_upPressed = true;
		}
		if (code == KeyEvent.VK_S)
		{
			m_downPressed = true;

		}
		if (code == KeyEvent.VK_A)
		{
			m_leftPressed = true;
		}
		if (code == KeyEvent.VK_D)
		{
			m_rightPressed = true;
		}
		if (code == KeyEvent.VK_B)
		{
			if (!m_BPressed)
			{
				m_BPressed = true;
			}
			else
			{
				m_BPressed = false;
			}
		}
		if (code == KeyEvent.VK_NUMPAD0)
		{
			if (!m_debug)
			{
				m_debug = true;
			}
			else
			{
				m_debug = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W)
		{
			m_upPressed = false;
		}
		if (code == KeyEvent.VK_S)
		{
			m_downPressed = false;
		}
		if (code == KeyEvent.VK_A)
		{
			m_leftPressed = false;
		}
		if (code == KeyEvent.VK_D)
		{
			m_rightPressed = false;
		}
	}

}

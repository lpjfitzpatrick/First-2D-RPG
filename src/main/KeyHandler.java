package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
	public boolean upPressed, downPressed, leftPressed, rightPressed;

	public boolean leftAndRightPressed()
	{
		return leftPressed && rightPressed;
	}

	public boolean upAndDownPressed()
	{
		return upPressed && downPressed;
	}

	public boolean allMovementKeysPressed()
	{
		return upPressed && downPressed && leftPressed && rightPressed;
	}

	private boolean noMovementKeysPressed()
	{
		return !upPressed && !downPressed && !leftPressed && !rightPressed;
	}

	public boolean isMoving()
	{
		if (allMovementKeysPressed() || noMovementKeysPressed()) return false;

		if (upAndDownPressed() && (!leftPressed && !rightPressed)) return false;

		if (leftAndRightPressed() && (!upPressed && !downPressed)) return false;

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
			upPressed = true;
		}
		if (code == KeyEvent.VK_S)
		{
			downPressed = true;

		}
		if (code == KeyEvent.VK_A)
		{
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D)
		{
			rightPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W)
		{
			upPressed = false;
		}
		if (code == KeyEvent.VK_S)
		{
			downPressed = false;
		}
		if (code == KeyEvent.VK_A)
		{
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D)
		{
			rightPressed = false;
		}
	}

}

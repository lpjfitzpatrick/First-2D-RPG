package ui;

import java.awt.Graphics2D;

import main.GamePanel;

public class AbstractUI
{
	GamePanel m_gp;

	public AbstractUI(GamePanel gp)
	{
		m_gp = gp;
	}

	public void draw(Graphics2D g2D) {}
}

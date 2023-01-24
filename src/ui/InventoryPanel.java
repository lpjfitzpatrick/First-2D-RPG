package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class InventoryPanel extends AbstractUI
{
	Color m_backgroundColor = new Color(64, 64, 64, 200);

	public InventoryPanel(GamePanel gp)
	{
		super(gp);
	}

	@Override
	public void draw(Graphics2D g2D)
	{
		g2D.setColor(m_backgroundColor);
		int inventorySize = m_gp.player().getInventory().size();
		// Note currently inventory could grow beyond the capacity mean anything beyond would not be
		// displayed properly. Should be imroved later with a better invent system
		for (int i = 1; i < m_gp.player().m_inventoryCapacity; i++)
		{
			g2D.fillRect(
				i*m_gp.screenHeight/25 + (i-1)*m_gp.m_tileSize/2, m_gp.screenWidth/25, m_gp.m_tileSize, m_gp.m_tileSize);
			if (i <= inventorySize)
			{
				g2D.drawImage(
					m_gp.player().getInventory().get(i-1).image(), i*m_gp.screenHeight/25 + (i-1)*m_gp.m_tileSize/2,
					m_gp.screenWidth/25, null);
			}
		}
	}
}

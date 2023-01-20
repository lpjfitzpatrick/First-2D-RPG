package ui;

import main.LogMessageListener;
import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class LogMessage extends AbstractUI implements LogMessageListener
{
	String m_message;
	final int MESSAGE_WINDOW_LENGTH = 7*m_gp.m_tileSize;
	Font m_arial_23i = new Font("Arial", Font.ITALIC, 23);
	Color m_backgroundColor = new Color(192, 192, 192, 200);
	int m_messageCount = 0;

	public LogMessage(GamePanel gp)
	{
		super(gp);

		m_gp.setLogMessageListener(this);
	}

	public void receiveMessage(String message)
	{
		m_message = message;
		m_messageCount = 0;
	}

	public void draw(Graphics2D g2D)
	{
		g2D.setFont(m_arial_23i);
		g2D.setColor(m_backgroundColor);
		int height = 2;

		if (m_message != null)
		{
			FontMetrics fontMetric = g2D.getFontMetrics();
			double messageWidth = fontMetric.getStringBounds(m_message, g2D).getWidth();
			String firstHalf = m_message;
			String secondHalf = null;

			if (messageWidth > MESSAGE_WINDOW_LENGTH)
			{
				height = 3;
				int charWidth = (int)(messageWidth/m_message.length() + 0.5);
				int splitPoint = MESSAGE_WINDOW_LENGTH/charWidth;
				firstHalf = m_message.substring(0, splitPoint);
				secondHalf = m_message.substring(splitPoint);
			}

			g2D.fillRect(m_gp.m_tileSize/2, 10*m_gp.m_tileSize + 3*m_gp.m_tileSize/4, MESSAGE_WINDOW_LENGTH, height*m_arial_23i.getSize());
			g2D.setColor(Color.BLACK);
			g2D.drawString(firstHalf, m_gp.m_tileSize/2, 11*m_gp.m_tileSize + m_gp.m_tileSize/5);
			if (secondHalf != null)
			{
				g2D.drawString(secondHalf, m_gp.m_tileSize/2, 11*m_gp.m_tileSize + m_gp.m_tileSize/5 + m_arial_23i.getSize());
			}

			m_messageCount++;
			if (m_messageCount >= m_gp.FPS*2)
			{
				m_message = null;
				m_messageCount = 0;
			}
		}
	}
}

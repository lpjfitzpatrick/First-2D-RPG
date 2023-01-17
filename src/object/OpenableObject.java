package object;

public class OpenableObject extends AbstractObject
{
	OpenableObjectState m_eObjectState;

	public enum OpenableObjectState
	{
		eOpen,
		eClosed,
		eUnknown
	}

	OpenableObject(int tileSize, int coordX, int coordY, OpenableObjectState eObjectState)
	{
		super(tileSize, coordX, coordY);
		m_eObjectState = eObjectState;
	}
}

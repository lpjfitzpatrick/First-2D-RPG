package object;

public class InventoryObject extends AbstractObject
{
	ItemState m_eItemState;

	public enum ItemState
	{
		eInInventory,
		eEquipped,
		eOnGround,
		eOther
	}

	public InventoryObject() {}

	public InventoryObject(int tileSize, int coordX, int coordY, ItemState eItemState)
	{
		super(tileSize, coordX, coordY);
		m_eItemState = eItemState;
	}
}

package Entity;

import Control.Utils;

public enum PetTypes
{
	POWER,
	SPEED,
	INTELLIGENCE;
	
	@Override
	public String toString()
	{		
		return Utils.convertEnumString(this.name());
	}
}

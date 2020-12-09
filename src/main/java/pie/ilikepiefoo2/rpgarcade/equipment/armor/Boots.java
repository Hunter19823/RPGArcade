package main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor;

import main.java.pie.ilikepiefoo2.rpgarcade.equipment.Slot;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.StatModifier;

/**
 * Boots Class
 * Child to the abstract class StatModifier
 */
public class Boots extends StatModifier {

    /**
     * Manual Constructor.
     * Any setter methods called will save this
     * object.
     */
    public Boots()
    {
        setSlot(Slot.FEET);
    }

    /**
     * Auto-Loaded Constructor.
     * @param name  Will load any boots
     *              with this name, otherwise
     *              it will create one itself
     *              with it's given name.
     */
    public Boots(String name)
    {
        super(name,Slot.FEET);
    }
}

package main.java.pie.ilikepiefoo2.rpgarcade.equipment.weapons;

import main.java.pie.ilikepiefoo2.rpgarcade.equipment.Slot;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.StatModifier;

/**
 * Sword Class
 * Child to the abstract class StatModifier
 */
public class Sword extends StatModifier {

    /**
     * Manual Constructor.
     * Any setter methods called will save this
     * object.
     */
    public Sword()
    {
        setSlot(Slot.HANDS);
    }

    /**
     * Auto-Loaded Constructor.
     * @param name Will load any Swords
     *             with this name, otherwise
     *             it will create one itself
     *             with it's given name.
     */
    public Sword(String name)
    {
        super(name,Slot.HANDS);
    }
}

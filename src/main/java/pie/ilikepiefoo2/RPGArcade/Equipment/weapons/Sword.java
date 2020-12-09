package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;

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
        this.slot = Slot.HANDS;
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
        super(name);
        this.slot = Slot.HANDS;
    }
}

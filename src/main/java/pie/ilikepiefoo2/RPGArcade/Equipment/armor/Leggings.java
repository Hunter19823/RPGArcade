package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;
/**
 * Leggings Class
 * Child to the abstract class StatModifier
 */
public class Leggings extends StatModifier {
    /**
     * Manual Constructor.
     * Any setter methods called will save this
     * object.
     */
    public Leggings()
    {
        this.slot = Slot.LEGS;
    }

    /**
     * Auto-Loaded Constructor.
     * @param name  Will load any Leggings
     *              with this name, otherwise
     *              it will create one itself
     *              with it's given name.
     */
    public Leggings(String name)
    {
        super(name);
        this.slot = Slot.LEGS;
    }
}

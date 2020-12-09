package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;

import static main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot.CHEST;

/**
 * Chestplate Class
 * Child to the abstract class StatModifier
 */
public class Chestplate extends StatModifier {
    /**
     * Manual Constructor.
     * Any setter methods called will save this
     * object.
     */
    public Chestplate()
    {
        this.slot = CHEST;
    }

    /**
     * Auto-Loaded Constructor.
     * @param name  Will load any Chestplate
     *              with this name, otherwise
     *              it will create one itself
     *              with it's given name.
     */
    public Chestplate(String name)
    {
        super(name);
        this.slot = CHEST;
    }
}

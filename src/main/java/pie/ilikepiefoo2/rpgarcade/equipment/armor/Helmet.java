package main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor;

import main.java.pie.ilikepiefoo2.rpgarcade.equipment.Slot;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.StatModifier;
/**
 * Helmet Class
 * Child to the abstract class StatModifier
 */
public class Helmet extends StatModifier {
    /**
     * Manual Constructor.
     * Any setter methods called will save this
     * object.
     */
    public Helmet()
    {
        this.slot = Slot.HEAD;
    }

    /**
     * Auto-Loaded Constructor.
     * @param name  Will load any Helmet
     *              with this name, otherwise
     *              it will create one itself
     *              with it's given name.
     */
    public Helmet(String name)
    {
        super(name);
        this.slot = Slot.HEAD;
    }
}

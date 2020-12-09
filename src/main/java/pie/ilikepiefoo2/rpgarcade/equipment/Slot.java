package main.java.pie.ilikepiefoo2.rpgarcade.equipment;
/**
 *
 * Used to hold all currently
 * implemented Equipment Slots.
 *
 */
public enum Slot {
    HEAD(0, "Helmet"),
    CHEST(1, "Chestplate"),
    LEGS(2, "Leggings"),
    FEET(3, "Boots"),
    HANDS(4, "Weapon"),
    PASSIVE(5, "Passive Effects");

    public final int POSITION;
    public final String NAME;

    /**
     * Slot Enum Constructor
     *
     * @param POSITION  Position in the Equipment Array.
     * @param NAME      Name of the Equipment Slot.
     */
    Slot(int POSITION, String NAME)
    {
        this.POSITION = POSITION;
        this.NAME = NAME;
    }

}

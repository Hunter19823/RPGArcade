package main.java.pie.ilikepiefoo2.RPGArcade.Equipment;

public enum Slot {
    HEAD(0, "Helmet"),
    CHEST(1, "Chestplate"),
    LEGS(2, "Leggings"),
    FEET(3, "Boots"),
    HANDS(4, "Right Hand"),
    PASSIVE(5, "Passive Effects");

    public final int POSITION;
    public final String NAME;

    Slot(int POSITION, String NAME)
    {
        this.POSITION = POSITION;
        this.NAME = NAME;
    }

}

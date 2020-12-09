package main.java.pie.ilikepiefoo2.RPGArcade.Equipment;

public enum Slot {
    HEAD(0, "Helmet"),
    CHEST(1, "Chestplate"),
    LEGS(2, "Leggings"),
    FEET(3, "Boots"),
    LEFT_HAND(4, "Left Hand"),
    RIGHT_HAND(5, "Right Hand"),
    PASSIVE(6, "Passive Effects");

    public final int POSITION;
    public final String NAME;

    Slot(int POSITION, String NAME)
    {
        this.POSITION = POSITION;
        this.NAME = NAME;
    }
}

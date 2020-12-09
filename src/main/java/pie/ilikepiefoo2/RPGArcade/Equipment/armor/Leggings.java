package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;

public class Leggings extends StatModifier {
    public Leggings()
    {
        this.slot = Slot.LEGS;
    }

    public Leggings(String name)
    {
        super(name);
        this.slot = Slot.LEGS;
    }
}

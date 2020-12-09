package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;

import static main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot.CHEST;

public class Chestplate extends StatModifier {
    public Chestplate()
    {
        this.slot = CHEST;
    }

    public Chestplate(String name)
    {
        super(name);
        this.slot = Slot.CHEST;
    }
}

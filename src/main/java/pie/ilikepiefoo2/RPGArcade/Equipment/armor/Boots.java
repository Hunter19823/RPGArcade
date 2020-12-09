package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;

public class Boots extends StatModifier {
    public Boots()
    {
        this.slot = Slot.FEET;
    }

    public Boots(String name)
    {
        super(name);
        this.slot = Slot.FEET;
    }
}

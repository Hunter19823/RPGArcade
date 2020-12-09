package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;

public class Helmet extends StatModifier {
    public Helmet()
    {
        this.slot = Slot.HEAD;
    }

    public Helmet(String name)
    {
        super(name);
        this.slot = Slot.HEAD;
    }
}

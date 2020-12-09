package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;

public class Sword extends StatModifier {

    public Sword()
    {
        this.slot = Slot.HANDS;
    }

    public Sword(String name)
    {
        super(name);
        this.slot = Slot.HANDS;
    }
}

package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Armor;

public enum Entities {
    HUMAN(Human.class);

    public final Class CLASS;

    Entities(Class CLASS)
    {
        this.CLASS = CLASS;
    }

    public static boolean isEntity(String className)
    {
        for(Entities entity : Entities.values())
        {
            if(className.equals(entity.toString())){
                return true;
            }
        }
        return false;
    }
}

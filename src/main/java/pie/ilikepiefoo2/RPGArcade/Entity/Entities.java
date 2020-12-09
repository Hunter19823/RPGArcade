package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Armor;

/**
 *
 * Used to hold all Implemented Entities
 *
 */
public enum Entities {
    HUMAN(Human.class);

    public final Class CLASS;

    /**
     *
     * @param CLASS The class this Enum Represents.
     */
    Entities(Class CLASS)
    {
        this.CLASS = CLASS;
    }

    /**
     * Used to check implemented entities.
     *
     * @param className The name of the class
     * @return boolean  Return true if the className
     *                  is an implemented entity.
     */

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

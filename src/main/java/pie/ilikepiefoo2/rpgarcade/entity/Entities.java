package main.java.pie.ilikepiefoo2.rpgarcade.entity;

import java.util.function.Supplier;

/**
 *
 * Used to hold all Implemented Entities
 *
 */
public enum Entities {
    HUMAN(Human.class,Human::new);

    public final Supplier<Entity> SUPPLIER;
    public final Class CLASS;

    /**
     * Entities Enum Constructor
     * @param CLASS The class this Enum Represents.
     */
    Entities(Class CLASS, Supplier<Entity> SUPPLIER)
    {
        this.CLASS = CLASS;
        this.SUPPLIER = SUPPLIER;
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

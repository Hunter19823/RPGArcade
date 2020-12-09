package main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor;

import main.java.pie.ilikepiefoo2.rpgarcade.entity.Entity;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.StatModifier;

import java.util.function.Supplier;

/**
 *
 * Used to hold all currently
 * implemented Armor types.
 *
 */
public enum Armor {
    HELMET(Helmet.class,Helmet::new),
    CHESTPLATE(Chestplate.class,Chestplate::new),
    LEGGINGS(Leggings.class,Leggings::new),
    BOOTS(Boots.class,Boots::new);

    public final Supplier<StatModifier> SUPPLIER;
    public final Class CLASS;

    /**
     * Armor Enum Constructor
     * @param CLASS The class this Enum Represents.
     */
    Armor(Class CLASS, Supplier<StatModifier> SUPPLIER){
        this.CLASS = CLASS;
        this.SUPPLIER = SUPPLIER;
    }

    /**
     * Used to check implemented Armor.
     *
     * @param className The name of the class
     * @return boolean  Return true if the className
     *                  is an implemented armor piece.
     */
    public static boolean isArmor(String className)
    {
        for(Armor armor : Armor.values())
        {
            if(className.equals(armor.toString())){
                return true;
            }
        }
        return false;
    }
}

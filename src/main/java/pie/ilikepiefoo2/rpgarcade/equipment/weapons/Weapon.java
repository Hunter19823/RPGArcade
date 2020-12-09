package main.java.pie.ilikepiefoo2.rpgarcade.equipment.weapons;

import main.java.pie.ilikepiefoo2.rpgarcade.equipment.StatModifier;

import java.util.function.Supplier;

/**
 *
 * Used to hold all currently
 * implemented Weapon types.
 *
 */
public enum Weapon {
    SWORD(Sword.class, Sword::new);

    public final Supplier<StatModifier> SUPPLIER;
    public final Class CLASS;

    /**
     * Weapon Enum Constructor
     * @param CLASS The class this Enum Represents.
     */
    Weapon(Class CLASS, Supplier<StatModifier> SUPPLIER){
        this.CLASS = CLASS;
        this.SUPPLIER = SUPPLIER;
    }

    /**
     * Used to check implemented Weapon.
     *
     * @param className The name of the class
     * @return boolean  Return true if the className
     *                  is an implemented Weapon.
     */
    public static boolean isWeapon(String className)
    {
        for(Weapon weapons : Weapon.values())
        {
            if(className.equals(weapons.CLASS.toString())){
                return true;
            }
        }
        return false;
    }
}

package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons;

/**
 *
 * Used to hold all currently
 * implemented Weapon types.
 *
 */
public enum Weapon {
    SWORD(Sword.class);

    public final Class CLASS;

    /**
     * Weapon Enum Constructor
     * @param CLASS The class this Enum Represents.
     */
    Weapon(Class CLASS){
        this.CLASS = CLASS;
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

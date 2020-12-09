package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor;

/**
 *
 * Used to hold all currently
 * implemented Armor types.
 *
 */
public enum Armor {
    HELMET(Helmet.class),
    CHESTPLATE(Chestplate.class),
    LEGGINGS(Leggings.class),
    BOOTS(Boots.class);

    public final Class CLASS;

    /**
     * Armor Enum Constructor
     * @param CLASS The class this Enum Represents.
     */
    Armor(Class CLASS){
        this.CLASS = CLASS;
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

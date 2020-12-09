package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor;


public enum Armor {
    HELMET(Helmet.class),
    CHESTPLATE(Chestplate.class),
    LEGGINGS(Leggings.class),
    BOOTS(Boots.class);

    public final Class CLASS;

    Armor(Class clazz){
        this.CLASS = clazz;
    }

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

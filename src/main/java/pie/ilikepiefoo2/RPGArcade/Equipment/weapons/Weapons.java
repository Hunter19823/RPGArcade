package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons;

public enum Weapons {
    SWORD(Sword.class);

    public final String CLASS_NAME;

    Weapons(Class clazz){
        this.CLASS_NAME = clazz.toString();
    }
    Weapons(String CLASS_NAME){
        this.CLASS_NAME = CLASS_NAME;
    }

    public static boolean isWeapon(String className)
    {
        for(Weapons weapons : Weapons.values())
        {
            if(className.equals(weapons.CLASS_NAME)){
                return true;
            }
        }
        return false;
    }
}

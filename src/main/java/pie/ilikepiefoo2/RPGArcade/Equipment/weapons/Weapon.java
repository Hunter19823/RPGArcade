package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons;

public enum Weapon {
    SWORD(Sword.class.toString());

    public final String CLASS_NAME;

    Weapon(Class clazz){
        this.CLASS_NAME = clazz.toString();
    }
    Weapon(String CLASS_NAME){
        this.CLASS_NAME = CLASS_NAME;
    }

    public static boolean isWeapon(String className)
    {
        for(Weapon weapons : Weapon.values())
        {
            if(className.equals(weapons.CLASS_NAME)){
                return true;
            }
        }
        return false;
    }
}

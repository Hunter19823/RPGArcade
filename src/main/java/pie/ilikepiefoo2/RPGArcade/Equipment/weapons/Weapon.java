package main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons;

public enum Weapon {
    SWORD(Sword.class);

    public final Class CLASS;

    Weapon(Class clazz){
        this.CLASS = clazz;
    }

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

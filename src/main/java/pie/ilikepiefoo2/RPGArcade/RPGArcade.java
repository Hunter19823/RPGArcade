package main.java.pie.ilikepiefoo2.RPGArcade;


import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Entity;
import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Human;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Boots;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Chestplate;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Helmet;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Leggings;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons.Sword;

public class RPGArcade {
    public static void main(String[] args)
    {
        //Human player = Human.load("src/main/resources/saved/pie.txt");
        //new Sword("Pie's Epic Sword").setBaseHealthModifier(1);

        Human player = new Human("Pie");
        player.displayStats();

        equipIronArmor(player);

        player.displayStats();



    }

    public static void equipIronArmor(Entity entity)
    {
        Helmet helmet = new Helmet("Iron Helmet");
        Chestplate chestplate = new Chestplate("Iron Chestplate");
        Leggings leggings = new Leggings("Iron Leggings");
        Boots boots = new Boots("Iron Boots");
        helmet.setBaseDamageReduction(1);
        chestplate.setBaseDamageReduction(3);
        leggings.setBaseDamageReduction(2.5);
        boots.setBaseDamageReduction(1);
        entity.equip(helmet);
        entity.equip(chestplate);
        entity.equip(leggings);
        entity.equip(boots);
    }
}

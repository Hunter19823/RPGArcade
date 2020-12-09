package main.java.pie.ilikepiefoo2.rpgarcade;


import main.java.pie.ilikepiefoo2.rpgarcade.entity.Entity;
import main.java.pie.ilikepiefoo2.rpgarcade.entity.Human;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.Slot;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Boots;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Chestplate;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Helmet;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Leggings;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.weapons.Sword;
import main.java.pie.ilikepiefoo2.rpgarcade.util.Chat;
import main.java.pie.ilikepiefoo2.rpgarcade.util.Properties;

import java.io.FileNotFoundException;

/**
 * Driver Class/Testing Class
 */
public class RPGArcade {
    public static void main(String[] args) throws FileNotFoundException
    {
        //Human player = Human.load("src/main/resources/saved/pie.txt");
        //new Sword("Pie's Epic Sword").setBaseHealthModifier(1);
        /*
        Properties properties = new Properties();
        properties.put("Name","Test");
        properties.put("Slot", Slot.HANDS);
        properties.put("baseHealthModifier",2.0);
        properties.put("baseDamageModifier",2.0);
        properties.put("totalDamageModifier",2.0);
        properties.put("totalHealthModifier",2.0);
        properties.put("totalDamageReductionModifier",2.0);
        properties.put("Type", Sword.class.getName());

         */
        Sword sword = (Sword) Sword.load("Testing Sword");
        System.out.println(sword.getProperty("ExampleKey"));
        System.out.println(sword.getSavingFormat());
        sword.setProperty("ExampleKey","This can hold extra info, like descriptions?");
        System.out.println(sword.getProperty("ExampleKey"));
        System.out.println(sword.getSavingFormat());
        System.out.println(sword.removeProperty("ExampleKey"));
        System.out.println(sword.getSavingFormat());
        System.out.println(sword.getProperty("ExampleKey"));

    }

    public static void simulateFight()
    {
        Human player = new Human("Player 1");
        player.strip();
        equipIronArmor(player);
        Human opponent = new Human();
        opponent.setName("Big Beefy Mother Hubber");
        opponent.setBaseHealth(200);
        opponent.setBaseDamage(20);
        Chat.CHAT.println("The winner of this Battle Royal is: "+battleRoyal(opponent,player));
        opponent.heal();
        player.heal();
    }

    /**
     * Equip an entity with Iron Armor.
     *
     * @param entity
     */
    public static Entity equipIronArmor(Entity entity)
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
        return entity;
    }

    /**
     * Host a battleRoyal between two entities until one can no longer fight.
     *
     * @param player1
     * @param player2
     * @return Winner
     */
    public static Entity battleRoyal(Entity player1, Entity player2)
    {
        player1.heal();
        player2.heal();
        Chat.CHAT.printf("%s has challenged %s to a Battle Royal! Challengers go first!%n",player1.toString(), player2.toString());
        while(player1.isAlive() && player2.isAlive()){
            player1.attack(player2);
            if(player2.isAlive()){
                player2.attack(player1);
            }
        }
        return player1.isAlive() ? player1 : player2;
    }
}

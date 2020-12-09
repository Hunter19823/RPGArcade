package main.java.pie.ilikepiefoo2.RPGArcade;


import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Entity;
import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Human;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Boots;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Chestplate;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Helmet;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Leggings;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons.Sword;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.Chat;

public class RPGArcade {
    public static void main(String[] args)
    {
        //Human player = Human.load("src/main/resources/saved/pie.txt");
        //new Sword("Pie's Epic Sword").setBaseHealthModifier(1);
        Human player = new Human("Pie");
        player.displayFullStats();


    }

    public static void simulateFight()
    {
        Human player = new Human("Pie");
        equipIronArmor(player);
        Human opponent = new Human();
        opponent.setName("Big Beefy Mother Fucker");
        opponent.setBaseHealth(200);
        opponent.setBaseDamage(20);
        Chat.CHAT.println("The winner of this Battle Royal is: "+battleRoyal(opponent,player));
        opponent.heal();
        player.heal();
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

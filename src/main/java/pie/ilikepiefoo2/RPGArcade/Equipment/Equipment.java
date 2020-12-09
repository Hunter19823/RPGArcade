package main.java.pie.ilikepiefoo2.RPGArcade.Equipment;


import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Entity;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Boots;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Chestplate;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Helmet;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Leggings;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons.Weapon;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.Chat;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigManager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Equipment{
    private StatModifier[] EQUIPMENT;
    private ArrayList<StatModifier> PASSIVE;

    public Equipment()
    {
        this.PASSIVE = new ArrayList<StatModifier>();
        this.EQUIPMENT = new StatModifier[Slot.values().length-1];
    }

    public StatModifier equip(StatModifier replacement, Slot slot)
    {
        StatModifier currentPiece = null;
        if(slot.POSITION == Slot.values().length-1)
        {
            PASSIVE.add(replacement);
        }else {
            if (this.EQUIPMENT[slot.POSITION] != null) {
                if(this.EQUIPMENT[slot.POSITION].equals(this.EQUIPMENT[slot.POSITION]))
                    return null;
                currentPiece = this.EQUIPMENT[slot.POSITION];
            } else {
                this.EQUIPMENT[slot.POSITION] = replacement;
            }
        }
        return currentPiece;
    }

    public double getBaseDamageModifiers(Entity entity)
    {
        double damage = entity.getBaseDamage();
        for(StatModifier stat : EQUIPMENT)
        {
            if( stat != null) damage += stat.applyBaseDamageModifier(entity);
        }
        for(StatModifier stat : PASSIVE)
        {
            if( stat != null) damage += stat.applyBaseDamageModifier(entity);
        }
        return damage;
    }

    public double getTotalDamageModifiers(Entity entity, double totalDamage)
    {
        for(StatModifier stat : EQUIPMENT)
        {
            if( stat != null) totalDamage += stat.applyTotalDamageModifier(totalDamage);
        }
        for(StatModifier stat : PASSIVE)
        {
            if( stat != null) totalDamage += stat.applyTotalDamageModifier(totalDamage);
        }
        return totalDamage;
    }

    public double getBaseHealthModifiers(Entity entity)
    {
        double health = entity.getBaseHealth();
        for(StatModifier stat : EQUIPMENT)
        {
            if( stat != null) health += stat.applyBaseHealthModifier(entity);
        }
        for(StatModifier stat : PASSIVE)
        {
            if( stat != null) health += stat.applyBaseHealthModifier(entity);
        }
        return health;
    }

    public double getTotalHealthModifiers(Entity entity, double totalHealth)
    {
        for(StatModifier stat : EQUIPMENT)
        {
            if( stat != null) totalHealth += stat.applyTotalHealthModifier(totalHealth);
        }
        for(StatModifier stat : PASSIVE)
        {
            if( stat != null) totalHealth += stat.applyTotalHealthModifier(totalHealth);
        }
        return totalHealth;
    }

    public double getTotalDamageReduction()
    {
        double total = 0;
        for(StatModifier stat : EQUIPMENT)
        {
            if( stat != null) total += stat.getBaseDamageReduction();
        }
        for(StatModifier stat : PASSIVE)
        {
            if( stat != null) total += stat.getBaseDamageReduction();
        }
        return total;
    }

    public StatModifier[] getEquipmentModifiers()
    {
        return EQUIPMENT;
    }

    public ArrayList<StatModifier> getPassiveModifiers()
    {
        return PASSIVE;
    }

    public StatModifier getWeapon()
    {
        return EQUIPMENT[Slot.HANDS.POSITION];
    }

    public Helmet getHelmet()
    {
        return (Helmet) EQUIPMENT[Slot.HEAD.POSITION];
    }

    public Chestplate getChestplate()
    {
        return (Chestplate) EQUIPMENT[Slot.CHEST.POSITION];
    }

    public Leggings getLeggings()
    {
        return (Leggings) EQUIPMENT[Slot.LEGS.POSITION];
    }

    public Boots getBoots()
    {
        return (Boots) EQUIPMENT[Slot.FEET.POSITION];
    }

    public void strip(Entity entity)
    {
        for(int i=0; i<EQUIPMENT.length; i++)
        {
            if(EQUIPMENT[i] != null)
                Chat.CHAT.printf("%s has been stripped of their %s (%s)%n",entity.getName(),EQUIPMENT[i].slot.NAME, EQUIPMENT[i].name);
            EQUIPMENT[i] = null;
        }
    }

    public String getSavingFormat()
    {
        String output = "";
        for(StatModifier stat : EQUIPMENT)
            if(stat != null) output+=String.format("Equipment=%s%n", ConfigManager.getSafeName(stat.name));
        for(StatModifier stat : PASSIVE)
            if(stat != null) output+=String.format("Equipment=%s%n", ConfigManager.getSafeName(stat.name));
        return output;
    }

    public String getEquipmentStats()
    {
        StringBuilder output = new StringBuilder();
        output.append("Equipment: \n");
        for(StatModifier stat : EQUIPMENT)
            if( stat != null) output.append(stat.getStats()+"\n");
        output.append("Passive Buffs: \n");
        for(StatModifier stat : PASSIVE)
            if( stat != null) output.append(stat.getStats()+"\n");
        return output.toString();
    }
}


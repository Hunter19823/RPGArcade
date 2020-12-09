package main.java.pie.ilikepiefoo2.rpgarcade.equipment;


import main.java.pie.ilikepiefoo2.rpgarcade.entity.Entity;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Boots;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Chestplate;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Helmet;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Leggings;
import main.java.pie.ilikepiefoo2.rpgarcade.util.Chat;
import main.java.pie.ilikepiefoo2.rpgarcade.util.ConfigManager;

import java.util.ArrayList;

/**
 * Equipment Class
 * Used to represent an entities's armor,
 * weapons, buffs, and debuffs.
 */
public class Equipment{
    private StatModifier[] EQUIPMENT;
    private ArrayList<StatModifier> PASSIVE;

    /**
     * Constructor
     */
    public Equipment()
    {
        this.PASSIVE = new ArrayList<StatModifier>();
        this.EQUIPMENT = new StatModifier[Slot.values().length-1];
    }

    /**
     * Equips an item to a slot, replacing
     * anything there and returning it.
     * @param replacement       The item being replaced.
     * @param slot              The slot the item belongs in.
     * @return previousPiece    The previous item in that slot.
     */
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

    /**
     * Calculate the damage from any base damage Modifiers.
     * @param entity    The entity attacking.
     * @return damage   The resulting damage after applying
     *                  damage modifiers.
     */
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

    /**
     * Calculate the damage from any total damage Modifiers.
     * @param entity        The entity attacking.
     * @param totalDamage   The total damage calculated so far.
     * @return damage       The resulting damage after applying
     *                      total damage modifiers.
     */
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

    /**
     * Calculate the health from any base health Modifiers.
     * @param entity    The entity attacking.
     * @return damage   The resulting health after applying
     *                  health modifiers.
     */
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

    /**
     * Calculate the health from any total health Modifiers.
     *
     * @param entity        The entity attacking.
     * @param totalHealth   The total health calculated so far.
     * @return damage       The resulting health after applying
     *                      total health modifiers.
     */
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

    /**
     * Calculate the total damage reduction
     * granted by all currently equipped equipment.
     *
     * @return totalDamageReduction
     */
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

    /**
     * Gets all the non-passive equipment.
     * (Armor/Weapons)
     *
     * @return gear
     */
    public StatModifier[] getEquipmentModifiers()
    {
        return EQUIPMENT;
    }

    /**
     * Gets all the passive equipment.
     * (Effects)
     *
     * @return passiveEffects
     */
    public ArrayList<StatModifier> getPassiveModifiers()
    {
        return PASSIVE;
    }

    /**
     * Gets the current weapon being held.
     *
     * @return weapon   Will return null if
     *                  no weapon is equipped.
     */
    public StatModifier getWeapon()
    {
        return EQUIPMENT[Slot.HANDS.POSITION];
    }

    /**
     * Gets the current Helmet.
     *
     * @return Helmet   Will return null if
     *                  Helmet isn't equipped.
     */
    public Helmet getHelmet()
    {
        return (Helmet) EQUIPMENT[Slot.HEAD.POSITION];
    }

    /**
     * Gets the current Chestplate.
     *
     * @return Chestplate   Will return null if
     *                      Chestplate aren't equipped.
     */
    public Chestplate getChestplate()
    {
        return (Chestplate) EQUIPMENT[Slot.CHEST.POSITION];
    }

    /**
     * Gets the current Leggings.
     *
     * @return Leggings   Will return null if
     *                    Leggings aren't equipped.
     */
    public Leggings getLeggings()
    {
        return (Leggings) EQUIPMENT[Slot.LEGS.POSITION];
    }

    /**
     * Gets the current Boots.
     *
     * @return Boots   Will return null if
     *                 Boots aren't equipped.
     */
    public Boots getBoots()
    {
        return (Boots) EQUIPMENT[Slot.FEET.POSITION];
    }

    /**
     * Strips the Equipment from
     * the entity.
     *
     * @param entity Entity being stripped.
     */
    public void strip(Entity entity)
    {
        for(int i=0; i<EQUIPMENT.length; i++)
        {
            if(EQUIPMENT[i] != null)
                Chat.CHAT.printf("%s has been stripped of their %s (%s)%n",entity.getName(),EQUIPMENT[i].getSlot().NAME, EQUIPMENT[i].getName());
            EQUIPMENT[i] = null;
        }
    }

    /**
     * Gets the String used for parsing
     * and saving an Equipments's properties
     *
     * @return SavingFormat
     */
    public String getSavingFormat()
    {
        String output = "";
        for(StatModifier stat : EQUIPMENT)
            if(stat != null) output+=String.format("Equipment=%s%n", ConfigManager.getSafeName(stat.getName()));
        for(StatModifier stat : PASSIVE)
            if(stat != null) output+=String.format("Equipment=%s%n", ConfigManager.getSafeName(stat.getName()));
        return output;
    }

    /**
     * Gets the the stats of each piece of
     * equipment.
     *
     * @return EquipmentStats
     */
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


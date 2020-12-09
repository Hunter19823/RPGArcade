package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Equipment;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Slot;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Armor;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons.Weapon;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.Chat;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigException;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public abstract class Entity {
    public static final String SAVE_LOCATION = "src/main/resources/saved/";
    protected String name = "Default";
    protected double baseHealth = 100;
    protected double currentHealth = baseHealth;
    protected double baseDamage = 10;
    protected int level = 1;
    protected final Equipment equipment = new Equipment();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    private void levelUp()
    {
        this.level++;
        this.baseHealth += level * Math.random() * 10;
        this.baseDamage += level * Math.random() * 7.5;
        save();
    }

    public double getTotalDamageReduction()
    {
        return this.equipment.getTotalDamageReduction();
    }

    public double getBaseHealth()
    {
        return baseHealth;
    }

    public void setBaseHealth(double baseHealth)
    {
        this.baseHealth = baseHealth;
    }

    public double getCurrentHealth()
    {
        return this.currentHealth > getMaxHealth() ? getMaxHealth() : this.currentHealth;
    }

    public void setCurrentHealth(double currentHealth)
    {
        this.currentHealth = currentHealth;
    }

    public void heal()
    {
        setCurrentHealth(getMaxHealth());
        Chat.CHAT.printf("%s's wounds have been completely erased.%n", name);
    }

    public boolean isAlive()
    {
        return getCurrentHealth() > 0;
    }

    public double getMaxHealth()
    {
        return this.equipment.getTotalHealthModifiers(this, this.equipment.getBaseHealthModifiers(this));
    }

    public double getBaseDamage()
    {
        return baseDamage;
    }

    public void setBaseDamage(double baseDamage)
    {
        this.baseDamage = baseDamage;
    }

    public double getMaxDamage()
    {
        return this.equipment.getTotalDamageModifiers(this, this.equipment.getBaseDamageModifiers(this));
    }

    public Equipment getEquipment()
    {
        return this.equipment;
    }

    public StatModifier getWeapon()
    {
        return this.equipment.getWeapon();
    }

    public StatModifier equip(StatModifier equipment)
    {
        StatModifier replacement = this.equipment.equip(equipment, equipment.getSlot());
        if (replacement != null) {
            Chat.CHAT.printf("%s has swapped out their \"%s\" for the \"%s\".%n", this.name, replacement.getName(), equipment.getName());
        } else {
            Chat.CHAT.printf("%s has equipped their \"%s\"%n", this.name, equipment.getName());
        }
        save();
        return replacement;
    }

    public void strip()
    {
        this.equipment.strip(this);
    }

    public void attack(Entity target)
    {
        double damage = Math.random() * (this.getMaxDamage() - this.getBaseDamage() + 1) + this.getBaseDamage();
        target.hurt(damage,this);
    }

    protected double hurt(double damage, Entity attacker)
    {
        double reduction = this.getTotalDamageReduction();
        damage = reduction >= damage ? 0 : damage-reduction;
        currentHealth -= damage;
        StatModifier weapon = attacker.getWeapon();
        if(damage <= 0){
            Chat.CHAT.printf("%s attacked %s with their %s but their attack was not strong enough! Remaining HP: %,.2f%n", attacker.name, this.name, weapon == null ? "fists" : weapon.getName(), getCurrentHealth());
        }else {
            Chat.CHAT.printf("%s attacked %s with their %s for a total of %,.2f damage! Remaining HP: %,.2f%n", attacker.name, this.name, weapon == null ? "fists" : weapon.getName(), damage, getCurrentHealth());
        }
        return currentHealth;
    }

    public static Entity loadEntity(String equipmentName) throws ConfigException, FileNotFoundException
    {
        String toolDetails = ConfigManager.loadFile(getSaveLocation(equipmentName));

        Entity ent = null;

        String[] components = toolDetails.substring(0,toolDetails.indexOf("\n")-1).split("=");

        if(components[0].equals("Type")) {
            for(Entities entity : Entities.values()){
                if(entity.CLASS.toString().equals(components[1]))
                {
                    try {
                        ent = (Entity) entity.CLASS.getConstructor().newInstance();
                        break;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(ent == null)
                throw new ConfigException("Equipment Type not Supported: "+components[1]+"");
        }

        loadEntity(toolDetails,ent);

        return ent;
    }

    private static Entity loadEntity(String blueprint, Entity entity) throws ConfigException
    {
        Scanner scanner = new Scanner(blueprint);
        String[] components;

        while(scanner.hasNextLine())
        {
            components = scanner.nextLine().split("=");
            switch(components[0])
            {
                case "Name":
                    entity.setName(components[1]);
                    break;
                case "Level":
                    entity.setLevel(Integer.parseInt(components[1]));
                    break;
                case "BaseHealth":
                    entity.setBaseHealth(Double.parseDouble(components[1]));
                    break;
                case "BaseDamage":
                    entity.setBaseDamage(Double.parseDouble(components[1]));
                    break;
                case "CurrentHealth":
                    entity.setCurrentHealth(Double.parseDouble(components[1]));
                    break;
                case "Equipment":
                    try {
                        StatModifier statModifier = StatModifier.loadStatModifiers(components[1]);

                        entity.equipment.equip(statModifier, statModifier.getSlot());
                    }catch(FileNotFoundException e)
                    {
                        Chat.CHAT.println("Error trying to load equipment by the name of: \""+components[1]+"\". File does not exist.");
                    }
                    break;
            }
        }
        return entity;
    }

    public void saveToFile(String filePath) throws ConfigException
    {
        ConfigManager.saveFile(filePath,getSavingFormat());
    }


    public String getSavingFormat()
    {
        return String.format(
                "Type=%s%n" +
                        "Name=%s%n" +
                        "Level=%d%n" +
                        "BaseHealth=%f%n" +
                        "BaseDamage=%f%n" +
                        "CurrentHealth=%f%n",
                this.getClass().toString(),
                this.name,
                this.level,
                this.baseHealth,
                this.baseDamage,
                this.currentHealth)+equipment.getSavingFormat();
    }

    public static String getSaveLocation(String name)
    {
        return SAVE_LOCATION+ ConfigManager.getSafeName(name)+".txt";
    }

    public void save()
    {
        saveToFile(getSaveLocation(name));
    }


    public void reload()
    {
        this.quickLoad();
    }

    protected void quickLoad()
    {
        try{
            loadEntity(ConfigManager.loadFile(getSaveLocation(this.name)), this);
        }catch(FileNotFoundException e)
        {
            Chat.CHAT.println("Could not find \""+name+"\". Now saving for future use.");
            save();
        }
    }

    public String toString()
    {
        return String.format("%s (%dL)",name,level);
    }

    public String getStats()
    {
        return String.format(
                "Name: %s%n" +
                "Level: %d%n"+
                "Health: %,.2f / %,.2f%n"+
                "Damage: %,.2f to %,.2f%n"+
                "Armor: %,.2f%n",
                name,
                level,
                getCurrentHealth(),
                getMaxHealth(),
                baseDamage,
                getMaxDamage(),
                getTotalDamageReduction());
    }

    public String getFullStats()
    {
        return String.format(
                "Name: %s%n" +
                        "Level: %d%n"+
                        "Health: %,.2f / %,.2f%n"+
                        "Damage: %,.2f to %,.2f%n"+
                        "Armor: %,.2f%n%s",
                name,
                level,
                getCurrentHealth(),
                getMaxHealth(),
                baseDamage,
                getMaxDamage(),
                getTotalDamageReduction(),
                equipment.getEquipmentStats());
    }

    public void displayStats()
    {
        Chat.CHAT.println(getStats());
    }

    public void displayFullStats()
    {
        Chat.CHAT.println(getFullStats());
    }


}

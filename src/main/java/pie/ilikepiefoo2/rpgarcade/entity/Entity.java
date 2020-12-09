package main.java.pie.ilikepiefoo2.rpgarcade.entity;

import main.java.pie.ilikepiefoo2.rpgarcade.equipment.Equipment;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.StatModifier;
import main.java.pie.ilikepiefoo2.rpgarcade.util.Chat;
import main.java.pie.ilikepiefoo2.rpgarcade.util.ConfigException;
import main.java.pie.ilikepiefoo2.rpgarcade.util.ConfigManager;
import main.java.pie.ilikepiefoo2.rpgarcade.util.Properties;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The mother class of all entities.
 */
public abstract class Entity {
    // The location where all entities are stored.
    public static final String SAVE_LOCATION = "src/main/resources/saved/";

    // Protected Fields used by all entities.
    protected Properties properties = new Properties();
    {
        properties.put("Name","Default");
        properties.put("BaseHealth",100.0);
        properties.put("CurrentHealth",properties.get("BaseHealth"));
        properties.put("BaseDamage",10.0);
        properties.put("Level",1);

    }
    /*
    protected String name = "Default";
    protected double baseHealth = 100;
    protected double currentHealth = baseHealth;
    protected double baseDamage = 10;
    protected int level = 1;

     */

    // All entities have an Equipment.
    protected final Equipment equipment = new Equipment();

    /**
     * Manual Constructor.
     * Will not save until either:
     * 1. Something has been equipped.
     * 2. Entity has leveled up.
     */
    public Entity(){
        properties.put("Type",this.getClass().getName());
    }

    /**
     * Auto-Loaded Constructor.
     *
     * @param name Will load any entities
     *             with this name, otherwise
     *             it will create one itself.
     */
    public Entity(String name)
    {
        properties.put("Type",this.getClass().getName());
        setName(name);
        quickLoad();
    }

    /**
     * Get the name of the Entity
     *
     * @return name
     */
    public String getName()
    {
        return (String) properties.get("Name");
    }

    /**
     * Set the name of the Entity
     *
     * @param name Sets the name
     */
    public void setName(String name)
    {
        properties.put("Name",name);
    }

    /**
     * Get the level of the Entity
     *
     * @return level
     */
    public int getLevel()
    {
        return (int) properties.get("Level");
    }

    /**
     * Set the level of the Entity
     *
     * @param level
     */
    public void setLevel(int level)
    {
        properties.put("Level",level);
    }

    /**
     * Level Up the entity.
     * WIP
     * Saves the entity after use.
     */
    private void levelUp()
    {
        properties.put("Level",(int)properties.get("Level")+1);
        setBaseHealth(getLevel() * Math.random() * 10);
        setBaseDamage(getLevel() * Math.random() * 7.5);
        save();
    }

    /**
     * Calculate the total damage reduction
     * of the entities equipment.
     *
     * @return TotalDamageReduction
     */
    public double getTotalDamageReduction()
    {
        return this.equipment.getTotalDamageReduction();
    }

    /**
     * Gets the base health of the entity.
     *
     * @return BaseHealth
     */
    public double getBaseHealth()
    {
        return (double) properties.get("BaseHealth");
    }

    /**
     * Sets the base health of the entity.
     *
     * @param baseHealth a double above 0.
     */
    public void setBaseHealth(double baseHealth)
    {
        if(baseHealth>0)
            this.properties.put("BaseHealth",baseHealth);
    }


    /**
     * Gets the current health of the entity.
     * If the current Health is larger than the
     * max health, than the current health will be
     * set to max health.
     *
     * @return currentHealth
     */
    public double getCurrentHealth()
    {
        return (double) this.properties.get("CurrentHealth");
    }

    /**
     * Sets the current health of the entity.
     *
     * @param currentHealth
     */
    public void setCurrentHealth(double currentHealth)
    {
        this.properties.put("CurrentHealth",currentHealth);
    }

    /**
     * Fully heals the entity.
     */
    public void heal()
    {
        setCurrentHealth(getMaxHealth());
        Chat.CHAT.printf("%s's wounds have been completely erased.%n", getName());
    }

    /**
     * Checks if the entity is still living.
     *
     * @return isAlive
     */
    public boolean isAlive()
    {
        return getCurrentHealth() > 0;
    }

    /**
     * Gets the maximum health of the
     * entity after equipment bonuses.
     *
     * @return maxHealth
     */
    public double getMaxHealth()
    {
        return this.equipment.getTotalHealthModifiers(this, this.equipment.getBaseHealthModifiers(this));
    }

    /**
     * Gets the base damage of the entity.
     *
     * @return baseDamage
     */
    public double getBaseDamage()
    {
        return (double) properties.get("BaseDamage");
    }

    /**
     * Sets the base damage of the entity.
     *
     * @param baseDamage
     */
    public void setBaseDamage(double baseDamage)
    {
        this.properties.put("BaseDamage",baseDamage);
    }

    /**
     * Gets the maximum damage of the entity
     * with current equipment.
     *
     * @return maxDamage
     */
    public double getMaxDamage()
    {
        return this.equipment.getTotalDamageModifiers(this, this.equipment.getBaseDamageModifiers(this));
    }

    /**
     * Gets the entity's Equipment.
     *
     * @return Equipment
     */
    public Equipment getEquipment()
    {
        return this.equipment;
    }

    /**
     * Gets the Weapon in the entity's hand.
     *
     * @return Weapon (will return null if none present.)
     */
    public StatModifier getWeapon()
    {
        return this.equipment.getWeapon();
    }

    /**
     * Equips a piece of equipment on an enemy.
     *
     * @param equipment     The piece of equipment
     *                      being equipped.
     * @return StatModifier will return null if
     *                       slot is not occupied
     *                       otherwise return the
     *                       previous piece of
     *                       equipment.
     */
    public StatModifier equip(StatModifier equipment)
    {
        StatModifier replacement = this.equipment.equip(equipment, equipment.getSlot());
        if (replacement != null) {
            Chat.CHAT.printf("%s has swapped out their \"%s\" for the \"%s\".%n", getName(), replacement.getName(), equipment.getName());
        } else {
            Chat.CHAT.printf("%s has equipped their \"%s\"%n", getName(), equipment.getName());
        }
        save();
        return replacement;
    }

    /**
     * Removes all equipment from the entity.
     */
    public void strip()
    {
        this.equipment.strip(this);
    }

    /**
     * Attacks another entity.
     *
     * @param target
     */
    public void attack(Entity target)
    {
        double damage = Math.random() * (this.getMaxDamage() - this.getBaseDamage() + 1) + this.getBaseDamage();
        target.hurt(damage,this);
    }

    /**
     * Whenever an entity is being hurt
     * by another entity.
     *
     * @param damage            the damage delt by the attacker.
     * @param attacker          the entity that is attacking.
     * @return remainingHealth  the health remaining after the attack.
     */
    protected double hurt(double damage, Entity attacker)
    {
        double reduction = this.getTotalDamageReduction();
        damage = reduction >= damage ? 0 : damage-reduction;
        setCurrentHealth(getCurrentHealth() - damage);
        StatModifier weapon = attacker.getWeapon();
        if(damage <= 0){
            Chat.CHAT.printf("%s attacked %s with their %s but their attack was not strong enough! Remaining HP: %,.2f%n", attacker.getName(), this.getName(), weapon == null ? "fists" : weapon.getName(), getCurrentHealth());
        }else {
            Chat.CHAT.printf("%s attacked %s with their %s for a total of %,.2f damage! Remaining HP: %,.2f%n", attacker.getName(), this.getName(), weapon == null ? "fists" : weapon.getName(), damage, getCurrentHealth());
        }
        return getCurrentHealth();
    }

    /**
     * Finds, Loads, and Initializes an
     * entity.
     *
     * @param entityName                The name of the entity.
     * @return Entity                   Returns an object of type Entity.
     * @throws ConfigException          If the config manager had any internal issues.
     * @throws FileNotFoundException    If the file does not exist, or could not be found.
     */
    public static Entity loadEntity(String entityName) throws ConfigException, FileNotFoundException
    {
        String toolDetails = ConfigManager.loadFile(getSaveLocation(entityName));

        Entity ent = null;

        String[] components = toolDetails.substring(0,toolDetails.indexOf("\n")-1).split("=");

        if(components[0].equals("Type")) {
            for(Entities entity : Entities.values()){
                if(entity.CLASS.getName().equals(components[1]))
                {
                    ent = entity.SUPPLIER.get();
                    break;
                }
            }
            if(ent == null)
                throw new ConfigException("Equipment Type not Supported: "+components[1]+"");
        }

        loadEntity(toolDetails,ent);

        return ent;
    }

    public Object getProperty(String key)
    {
        return properties.get(key);
    }

    public void setProperty(String key, Object property)
    {
        properties.put(key,property);
        save();
    }

    public Object removeProperty(String key)
    {
        return properties.remove(key);
    }

    public static Entity load(String entityName)
    {
        try {
            return loadEntity(entityName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Initializes an entity with the proper blueprints.
     *
     * @param blueprint         The blueprints needed to create the entity.
     * @param entity            The entity being built.
     * @return Entity           Returns the entity being built.
     * @throws ConfigException  If the config manager had any internal issues.
     */
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
                default:
                    entity.properties.put(components[0],components[1]);
                    break;
            }
        }
        return entity;
    }

    /**
     * Saves the entity to a specific filePath.
     *
     * @param filePath          The location where the file will be saved.
     * @throws ConfigException  If the config manager had any internal issues.
     */
    public void saveToFile(String filePath) throws ConfigException
    {
        ConfigManager.saveFile(filePath,getSavingFormat());
    }

    /**
     * Gets the String used for parsing
     * and saving an Entities's properties
     *
     * @return SavingFormat
     */
    public String getSavingFormat()
    {
        return properties.toSavingFormat()+equipment.getSavingFormat();
    }

    /**
     * Gets the full path of an entity
     * using only it's name.
     *
     * @param name      The name of the entity.
     * @return String   The file path where the
     *                  entity would be stored.
     */
    public static String getSaveLocation(String name)
    {
        return SAVE_LOCATION+ ConfigManager.getSafeName(name)+".txt";
    }

    /**
     * Saves the entity.
     */
    public void save()
    {
        saveToFile(getSaveLocation(getName()));
    }

    /**
     * Overrides the entity with any data
     * stored in it's file.
     */
    public void reload()
    {
        this.quickLoad();
    }

    /**
     * Used to quickly load an existing
     * entity or save a new entity.
     */
    protected void quickLoad()
    {
        try{
            loadEntity(ConfigManager.loadFile(getSaveLocation(this.getName())), this);
        }catch(FileNotFoundException e)
        {
            Chat.CHAT.println("Could not find \""+getName()+"\". Now saving for future use.");
            save();
        }
    }

    /**
     * A simple string representation
     * of the entity.
     *
     * @return String
     */
    public String toString()
    {
        return String.format("%s (%dL)",getName(),getLevel());
    }

    /**
     * Gets the stats of the entity
     * without it's equipment.
     *
     * @return Stats
     */
    public String getStats()
    {
        return String.format(
                "Name: %s%n" +
                "Level: %d%n"+
                "Health: %,.2f / %,.2f%n"+
                "Damage: %,.2f to %,.2f%n"+
                "Armor: %,.2f%n",
                getName(),
                getLevel(),
                getCurrentHealth(),
                getMaxHealth(),
                getBaseDamage(),
                getMaxDamage(),
                getTotalDamageReduction());
    }

    /**
     * Gets the stats of the entity
     * with it's equipment.
     *
     * @return Stats
     */
    public String getFullStats()
    {
        return String.format(
                "Name: %s%n" +
                        "Level: %d%n"+
                        "Health: %,.2f / %,.2f%n"+
                        "Damage: %,.2f to %,.2f%n"+
                        "Armor: %,.2f%n%s",
                getName(),
                getLevel(),
                getCurrentHealth(),
                getMaxHealth(),
                getBaseDamage(),
                getMaxDamage(),
                getTotalDamageReduction(),
                equipment.getEquipmentStats());
    }

    /**
     * Display's an entities's stats.
     */
    public void displayStats()
    {
        Chat.CHAT.println(getStats());
    }

    /**
     * Display's an entities's full stats.
     */
    public void displayFullStats()
    {
        Chat.CHAT.println(getFullStats());
    }


}

package main.java.pie.ilikepiefoo2.rpgarcade.equipment;

import main.java.pie.ilikepiefoo2.rpgarcade.entity.Entity;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Armor;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.weapons.Weapon;
import main.java.pie.ilikepiefoo2.rpgarcade.util.Chat;
import main.java.pie.ilikepiefoo2.rpgarcade.util.ConfigException;
import main.java.pie.ilikepiefoo2.rpgarcade.util.ConfigManager;
import main.java.pie.ilikepiefoo2.rpgarcade.util.Properties;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * StatModifier Class
 *
 * The mother class of all Equipment.
 */
public abstract class StatModifier {
    // The location where all entities are stored.
    public static final String SAVE_LOCATION="src/main/resources/saved/";

    // Protected fields used by all Equipment.
    protected Properties properties = new Properties();
    {
        properties.put("Name","Default");
        properties.put("Slot",null);
        properties.put("baseHealthModifier",1.0);
        properties.put("baseDamageModifier",1.0);
        properties.put("baseDamageReduction",0.0);
        properties.put("totalDamageModifier",0.0);
        properties.put("totalHealthModifier",0.0);
        properties.put("totalDamageReductionModifier",0.0);

    }
    /*
    protected String name = "Default";
    protected Slot slot = null;
    protected double baseHealthModifier = 1;
    protected double baseDamageModifier = 1;
    protected double baseDamageReduction = 0;
    protected double totalDamageModifier = 0;
    protected double totalHealthModifier = 0;
    protected double totalDamageReductionModifier = 0;
     */

    /**
     * Manual Constructor.
     * Any setter methods called will save this
     * object.
     */
    public StatModifier() {
        properties.put("Type",this.getClass().getName());
    }

    /**
     * Auto-Loaded Constructor.
     * @param name Will load any Equipment
     *             with this name, otherwise
     *             it will create one itself
     *             with it's given name.
     */
    public StatModifier(String name, Slot slot)
    {
        setName(name);
        setSlot(slot);
        properties.put("Type",this.getClass().getName());
        quickLoad();
    }

    /**
     * Calculates bonus base damage.
     *
     * @param attacker
     * @return bonusDamage
     */
    public double applyBaseDamageModifier(Entity attacker)
    {
        return attacker.getBaseDamage()*getBaseDamageModifier() - attacker.getBaseDamage();
    }

    /**
     * Calculates bonus base health.
     *
     * @param target
     * @return bonusHealth
     */
    public double applyBaseHealthModifier(Entity target)
    {
        return target.getBaseHealth()*getBaseHealthModifier() - target.getBaseHealth();
    }

    /**
     * Calculates total damage multipliers.
     *
     * @param totalDamage
     * @return resultingDamage
     */
    public double applyTotalDamageModifier(double totalDamage)
    {
        return totalDamage * getTotalDamageModifier();
    }
    /**
     * Calculates total health multipliers.
     *
     * @param totalHealth
     * @return resultingHealth
     */
    public double applyTotalHealthModifier(double totalHealth)
    {
        return totalHealth * getTotalHealthModifier();
    }

    /**
     * Calculates total damage reduction
     *
     * @param totalDamage
     * @return reducedTotal
     */
    public double applyTotalDamageReductionModifier(double totalDamage)
    {
        return totalDamage * getTotalDamageReductionModifier();
    }

    /**
     * Gets the name of the Equipment.
     *
     * @return name
     */
    public String getName()
    {
        return (String) properties.get("Name");
    }

    /**
     * Sets the name of the Equipment.
     *
     * @param name
     */
    public void setName(String name)
    {
        properties.put("Name",name);
    }

    /**
     * Gets the slot of the Equipment.
     *
     * @return Slot
     */
    public Slot getSlot()
    {
        return (Slot) properties.get("Slot");
    }

    /**
     * Sets the slot.
     *
     * @param slot
     */
    public void setSlot(Slot slot)
    {
        properties.put("Slot",slot);
    }

    /**
     * Gets the base health modifier.
     *
     * @return baseHealthModifier
     */
    public double getBaseHealthModifier()
    {
        return (double) properties.get("baseHealthModifier");
    }

    /**
     * Sets the base health modifier.
     * Saves the Equipment.
     *
     * @param baseHealthModifier
     */
    public void setBaseHealthModifier(double baseHealthModifier)
    {
        properties.put("baseHealthModifier",baseHealthModifier);
        save();
    }

    /**
     * Gets the base damage modifier
     *
     * @return baseDamageModifier
     */
    public double getBaseDamageModifier()
    {
        return (double) properties.get("baseDamageModifier");
    }

    /**
     * Sets the base damage modifier
     * Saves the Equipment.
     *
     * @param baseDamageModifier
     */
    public void setBaseDamageModifier(double baseDamageModifier)
    {
        properties.put("baseDamageModifier",baseDamageModifier);
        save();
    }

    /**
     * Gets the total damage modifer.
     *
     * @return totalDamageModifier.
     */
    public double getTotalDamageModifier()
    {
        return (double) properties.get("totalDamageModifier");
    }

    /**
     * Sets the total damage modifier
     * Saves the Equipment.
     *
     * @param totalDamageModifier
     */
    public void setTotalDamageModifier(double totalDamageModifier)
    {
        properties.put("totalDamageModifier",totalDamageModifier);
        save();
    }

    /**
     * Gets the base damage reduction.
     *
     * @return baseDamageReduction
     */
    public double getBaseDamageReduction()
    {
        return (double) properties.get("baseDamageReduction");
    }

    /**
     * Sets the base damage reduction.
     * Saves the Equipment.
     *
     * @param baseDamageReduction
     */
    public void setBaseDamageReduction(double baseDamageReduction)
    {
        properties.put("baseDamageReduction",baseDamageReduction);
        save();
    }

    /**
     * Get the total damage reduction modifier.
     *
     * @return totalDamageReductionModifier
     */
    public double getTotalDamageReductionModifier()
    {
        return (double) properties.get("totalDamageReductionModifier");
    }

    /**
     * Sets the total damage reduction modifier
     * Saves the Equipment.
     *
     * @param totalDamageReductionModifier
     */
    public void setTotalDamageReductionModifier(double totalDamageReductionModifier)
    {
        properties.put("totalDamageReductionModifier",totalDamageReductionModifier);
        save();
    }

    /**
     * Gets the total health modifier
     *
     * @return totalHealthModifier
     */
    public double getTotalHealthModifier()
    {
        return (double) properties.get("totalHealthModifier");
    }

    /**
     * Sets the total health modifier
     * Saves the Equipment.
     *
     * @param totalHealthModifier
     */
    public void setTotalHealthModifier(double totalHealthModifier)
    {
        properties.put("totalHealthModifier",totalHealthModifier);
        save();
    }

    /**
     * Gets the location where the
     * Equipment will-be/is stored.
     *
     * @param stat
     * @return FilePath
     */
    public static String getSaveLocation(StatModifier stat)
    {
        return SAVE_LOCATION+ConfigManager.getSafeName(stat.getName())+".txt";
    }

    /**
     * Gets the location where the
     * Equipment with that name
     * will-be/is stored.
     *
     * @param name
     * @return FilePath
     */
    public static String getSaveLocation(String name)
    {
        return SAVE_LOCATION+ConfigManager.getSafeName(name)+".txt";
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

    public static StatModifier load(String equipmentName)
    {
        try {
            return loadStatModifiers(equipmentName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Load an equipment from the name of
     * the equipment.
     *
     * @param equipmentName             The of the Equipment
     * @return Equipment                Returns an object that implements StatModifier
     * @throws ConfigException          If the config manager had any internal issues.
     * @throws FileNotFoundException    If the file does not exist, or could not be found.
     */
    public static StatModifier loadStatModifiers(String equipmentName) throws ConfigException, FileNotFoundException
    {
        String toolDetails = ConfigManager.loadFile(getSaveLocation(equipmentName));

        StatModifier stat = null;

        String[] components = toolDetails.substring(0,toolDetails.indexOf("\n")).split("=");

        if(components[0].equals("Type")) {
            for(Weapon weapon : Weapon.values()){
                if(weapon.CLASS.getName().equals(components[1]))
                {
                    stat = weapon.SUPPLIER.get();
                    break;
                }
            }
            for(Armor armor : Armor.values()){
                if(armor.CLASS.getName().equals(components[1]))
                {
                    stat = armor.SUPPLIER.get();
                    break;
                }
            }
            if(stat == null)
                throw new ConfigException("Equipment Type not Supported: "+components[1]+"");
        }

        loadStatModifiers(toolDetails,stat);

        return stat;
    }

    /**
     * Loads an equipment from a blueprint onto
     * an equipment object.
     *
     * @param blueprint                 The blueprint for the Equipment.
     * @param mod                       The Equipment being modified.
     * @return mod                      The Equipment being modified.
     * @throws ConfigException          If the config manager had any internal issues.
     */
    private static StatModifier loadStatModifiers(String blueprint, StatModifier mod) throws ConfigException
    {
        Scanner scanner = new Scanner(blueprint);
        String[] components;
        StatModifier stat = mod;
        while(scanner.hasNextLine())
        {
            components = scanner.nextLine().split("=");
            switch(components[0])
            {
                case "Name":
                    stat.setName(components[1]);
                    break;
                case "Slot":
                    System.out.println(components[1]);
                    stat.setSlot(Slot.valueOf(components[1]));
                    break;
                case "baseHealthModifier":
                    stat.setBaseHealthModifier(Double.parseDouble(components[1]));
                    break;
                case "baseDamageModifier":
                    stat.setBaseDamageModifier(Double.parseDouble(components[1]));
                    break;
                case "baseDamageReduction":
                    stat.setBaseDamageReduction(Double.parseDouble(components[1]));
                    break;
                case "totalDamageModifier":
                    stat.setTotalDamageModifier(Double.parseDouble(components[1]));
                    break;
                case "totalHealthModifier":
                    stat.setTotalHealthModifier(Double.parseDouble(components[1]));
                    break;
                case "totalDamageReductionModifier":
                    stat.setTotalDamageReductionModifier(Double.parseDouble(components[1]));
                    break;
                default:
                    stat.properties.put(components[0],components[1]);
                    break;
            }
        }
        return stat;
    }

    /**
     * Shortcut to load an equipment if it exists,
     * or saves one if it does not.
     */
    protected void quickLoad()
    {
        try{
            loadStatModifiers(ConfigManager.loadFile(getSaveLocation(getName())), this);
        }catch(FileNotFoundException e)
        {
            Chat.CHAT.println("Could not find \""+getName()+"\". Now saving for future use...");
            save();
        }
    }

    /**
     * Saves equipment.
     */
    public void save()
    {
        ConfigManager.saveFile(getSaveLocation(this),getSavingFormat());
    }

    /**
     * Gets the String used for parsing
     * and saving an Equipment's properties
     *
     * @return SavingFormat
     */
    public String getSavingFormat()
    {
        return properties.toSavingFormat();
    }


    /**
     * Gets the stats of the Equipment Piece.
     *
     * @return Stats
     */
    public String getStats()
    {
        String output = String.format(
                "\tName: %s%n" +
                "\tClass: %s%n" +
                "\tSlot: %s%n" ,
                getName(),
                this.getClass().getSimpleName(),
                getSlot().NAME
        );
        if(getBaseHealthModifier() != 1)
            output += String.format("\tBase Health Bonus: %,.2f%%%n",(getBaseHealthModifier()-1)*100);
        if(getBaseDamageModifier() != 1)
            output += String.format("\tBase Damage Bonus: %,.2f%%%n",(getBaseDamageModifier()-1)*100);
        if(getBaseDamageReduction() != 0)
            output += String.format("\tDamage Reduction Bonus: %,.2f Damage%n",getBaseDamageReduction());
        if(getTotalDamageModifier() != 0)
            output += String.format("\tStackable Damage Bonus: %,.2f%%%n",(getTotalDamageModifier())*100);
        if(getTotalHealthModifier() != 0)
            output += String.format("\tStackable Health Bonus: %,.2f%%%n",(getTotalHealthModifier())*100);
        if(getTotalDamageReductionModifier() != 0)
            output += String.format("\tStackable Damage Reduction Bonus: %,.2f%%%n",(getTotalDamageReductionModifier())*100);

        return output;
    }

    /**
     * Displays the stats of this equipment piece.
     */
    public void displayStats()
    {
        Chat.CHAT.print(getStats());
    }
}

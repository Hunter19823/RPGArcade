package main.java.pie.ilikepiefoo2.rpgarcade.equipment;

import main.java.pie.ilikepiefoo2.rpgarcade.entity.Entity;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.armor.Armor;
import main.java.pie.ilikepiefoo2.rpgarcade.equipment.weapons.Weapon;
import main.java.pie.ilikepiefoo2.rpgarcade.util.Chat;
import main.java.pie.ilikepiefoo2.rpgarcade.util.ConfigException;
import main.java.pie.ilikepiefoo2.rpgarcade.util.ConfigManager;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
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
    protected String name = "Default";
    protected Slot slot = null;
    protected double baseHealthModifier = 1;
    protected double baseDamageModifier = 1;
    protected double baseDamageReduction = 0;
    protected double totalDamageModifier = 0;
    protected double totalHealthModifier = 0;
    protected double totalDamageReductionModifier = 0;

    /**
     * Manual Constructor.
     * Any setter methods called will save this
     * object.
     */
    public StatModifier() { }

    /**
     * Auto-Loaded Constructor.
     * @param name Will load any Equipment
     *             with this name, otherwise
     *             it will create one itself
     *             with it's given name.
     */
    public StatModifier(String name)
    {
        this.name = name;
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
        return attacker.getBaseDamage()*baseDamageModifier - attacker.getBaseDamage();
    }

    /**
     * Calculates bonus base health.
     *
     * @param target
     * @return bonusHealth
     */
    public double applyBaseHealthModifier(Entity target)
    {
        return target.getBaseHealth()*baseHealthModifier - target.getBaseHealth();
    }

    /**
     * Calculates total damage multipliers.
     *
     * @param totalDamage
     * @return resultingDamage
     */
    public double applyTotalDamageModifier(double totalDamage)
    {
        return totalDamage * totalDamageModifier;
    }
    /**
     * Calculates total health multipliers.
     *
     * @param totalHealth
     * @return resultingHealth
     */
    public double applyTotalHealthModifier(double totalHealth)
    {
        return totalHealth * totalHealthModifier;
    }

    /**
     * Calculates total damage reduction
     *
     * @param totalDamage
     * @return reducedTotal
     */
    public double applyTotalDamageReductionModifier(double totalDamage)
    {
        return totalDamage * totalDamageReductionModifier;
    }

    /**
     * Gets the name of the Equipment.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the Equipment.
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the slot of the Equipment.
     *
     * @return Slot
     */
    public Slot getSlot()
    {
        return slot;
    }

    /**
     * Gets the base health modifier.
     *
     * @return baseHealthModifier
     */
    public double getBaseHealthModifier()
    {
        return baseHealthModifier;
    }

    /**
     * Sets the base health modifier.
     * Saves the Equipment.
     *
     * @param baseHealthModifier
     */
    public void setBaseHealthModifier(double baseHealthModifier)
    {
        this.baseHealthModifier = baseHealthModifier;
        save();
    }

    /**
     * Gets the base damage modifier
     *
     * @return baseDamageModifier
     */
    public double getBaseDamageModifier()
    {
        return baseDamageModifier;
    }

    /**
     * Sets the base damage modifier
     * Saves the Equipment.
     *
     * @param baseDamageModifier
     */
    public void setBaseDamageModifier(double baseDamageModifier)
    {
        this.baseDamageModifier = baseDamageModifier;
        save();
    }

    /**
     * Gets the total damage modifer.
     *
     * @return totalDamageModifier.
     */
    public double getTotalDamageModifier()
    {
        return totalDamageModifier;
    }

    /**
     * Sets the total damage modifier
     * Saves the Equipment.
     *
     * @param totalDamageModifier
     */
    public void setTotalDamageModifier(double totalDamageModifier)
    {
        this.totalDamageModifier = totalDamageModifier;
        save();
    }

    /**
     * Gets the base damage reduction.
     *
     * @return baseDamageReduction
     */
    public double getBaseDamageReduction()
    {
        return baseDamageReduction;
    }

    /**
     * Sets the base damage reduction.
     * Saves the Equipment.
     *
     * @param baseDamageReduction
     */
    public void setBaseDamageReduction(double baseDamageReduction)
    {
        this.baseDamageReduction = baseDamageReduction;
        save();
    }

    /**
     * Get the total damage reduction modifier.
     *
     * @return totalDamageReductionModifier
     */
    public double getTotalDamageReductionModifier()
    {
        return totalDamageReductionModifier;
    }

    /**
     * Sets the total damage reduction modifier
     * Saves the Equipment.
     *
     * @param totalDamageReductionModifier
     */
    public void setTotalDamageReductionModifier(double totalDamageReductionModifier)
    {
        this.totalDamageReductionModifier = totalDamageReductionModifier;
        save();
    }

    /**
     * Gets the total health modifier
     *
     * @return totalHealthModifier
     */
    public double getTotalHealthModifier()
    {
        return totalHealthModifier;
    }

    /**
     * Sets the total health modifier
     * Saves the Equipment.
     *
     * @param totalHealthModifier
     */
    public void setTotalHealthModifier(double totalHealthModifier)
    {
        this.totalHealthModifier = totalHealthModifier;
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

        String[] components = toolDetails.substring(0,toolDetails.indexOf("\n")-1).split("=");

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
                    stat.slot = Slot.valueOf(components[1]);
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
            loadStatModifiers(ConfigManager.loadFile(getSaveLocation(this.name)), this);
        }catch(FileNotFoundException e)
        {
            Chat.CHAT.println("Could not find \""+name+"\". Now saving for future use...");
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
        return String.format(
                "Type=%s%n" +
                "Name=%s%n" +
                "Slot=%s%n" +
                "baseHealthModifier=%f%n" +
                "baseDamageModifier=%f%n" +
                "baseDamageReduction=%f%n" +
                "totalDamageModifier=%f%n" +
                "totalHealthModifier=%f%n" +
                "totalDamageReductionModifier=%f%n",
                this.getClass().getName(),
                this.name,
                this.slot,
                this.baseHealthModifier,
                this.baseDamageModifier,
                this.baseDamageReduction,
                this.totalDamageModifier,
                this.totalHealthModifier,
                this.totalDamageReductionModifier
        );
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
                this.name,
                this.getClass().getSimpleName(),
                this.slot.NAME
        );
        if(baseHealthModifier != 1)
            output += String.format("\tBase Health Bonus: %,.2f%%%n",(baseHealthModifier-1)*100);
        if(baseDamageModifier != 1)
            output += String.format("\tBase Damage Bonus: %,.2f%%%n",(baseDamageModifier-1)*100);
        if(baseDamageReduction != 0)
            output += String.format("\tDamage Reduction Bonus: %,.2f Damage%n",baseDamageReduction);
        if(totalDamageModifier != 0)
            output += String.format("\tStackable Damage Bonus: %,.2f%%%n",(totalDamageModifier)*100);
        if(totalHealthModifier != 0)
            output += String.format("\tStackable Health Bonus: %,.2f%%%n",(totalHealthModifier)*100);
        if(totalDamageReductionModifier != 0)
            output += String.format("\tStackable Damage Reduction Bonus: %,.2f%%%n",(totalDamageReductionModifier)*100);

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

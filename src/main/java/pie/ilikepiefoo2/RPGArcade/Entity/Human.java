package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigException;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigManager;

import java.util.Scanner;

public class Human extends Entity {

    public Human() { }

    public Human(String name)
    {
        this.name = name;
        this.quickLoad();
    }


    public static Human load(String filePath) throws ConfigException
    {
        Human out = new Human();
        out.loadFromFile(filePath);
        return out;
    }

    @Override
    public void loadFromFile(String filePath) throws ConfigException
    {
        String playerDetails = ConfigManager.loadFile(filePath);
        Scanner scanner = new Scanner(playerDetails);
        String[] components;
        while(scanner.hasNextLine())
        {
            components = scanner.nextLine().split("=");
            if(components[0].equals("Type")) {
                if(!components[1].equals(this.getClass().toString()))
                    throw new ConfigException("This File is the Wrong Type.");
                continue;
            }
            switch(components[0])
            {
                case "Name":
                    setName(components[1]);
                    break;
                case "Level":
                    setLevel(Integer.parseInt(components[1]));
                    break;
                case "BaseHealth":
                    setBaseHealth(Double.parseDouble(components[1]));
                    break;
                case "BaseDamage":
                    setBaseDamage(Double.parseDouble(components[1]));
                    break;
                case "CurrentHealth":
                    setCurrentHealth(Double.parseDouble(components[1]));
                    break;
            }
        }
    }

    @Override
    public void saveToFile(String filePath) throws ConfigException
    {
        ConfigManager.saveFile(filePath,getSavingFormat());
    }

    @Override
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
                this.currentHealth);
    }
}

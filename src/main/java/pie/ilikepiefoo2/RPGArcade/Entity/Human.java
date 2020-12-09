package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigException;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigManager;

import java.util.Scanner;

public class Human extends Entity {

    public Human() { }

    public Human(String name)
    {
        this.name = name;
        quickLoad();
    }


    public static Human load(String filePath) throws ConfigException
    {
        return new Human().loadFromFile(filePath);
    }

    @Override
    public Human loadFromFile(String filePath) throws ConfigException
    {
        String playerDetails = ConfigManager.loadFile(filePath);
        Scanner scanner = new Scanner(playerDetails);
        String[] components;
        Human temp = null;
        while(scanner.hasNextLine())
        {
            components = scanner.nextLine().split("=");
            if(components[0].equals("Type")) {
                temp = new Human();
                continue;
            }else{
                if(temp == null)
                    throw new ConfigException("File not saved correctly.");
            }
            switch(components[0])
            {
                case "Name":
                    temp.setName(components[1]);
                    break;
                case "Level":
                    temp.setLevel(Integer.parseInt(components[1]));
                    break;
                case "BaseHealth":
                    temp.setBaseHealth(Double.parseDouble(components[1]));
                    break;
                case "BaseDamage":
                    temp.setBaseDamage(Double.parseDouble(components[1]));
                    break;
                case "CurrentHealth":
                    temp.setCurrentHealth(Double.parseDouble(components[1]));
                    break;
            }
        }
        return temp;
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

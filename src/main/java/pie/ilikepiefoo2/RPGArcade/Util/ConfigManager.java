package main.java.pie.ilikepiefoo2.RPGArcade.Util;

import java.io.*;
import java.util.Scanner;

public class ConfigManager {
    public static String loadFile(String path) throws ConfigException
    {
        /*
            Try to open the file at filepath
            otherwise throw a
            generic config error.
         */
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(path);
        }catch(FileNotFoundException e)
        {
            throw new ConfigException(e);
        }
        /*
            Attempt to read the file
            otherwise throw a
            generic config error
         */
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e)
        {
            throw new ConfigException(e);
        }

        return sb.toString();
    }

    public static void saveFile(String path, String data) throws ConfigException
    {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(data);
        } catch (IOException e) {
            throw new ConfigException(e);
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {}
            }
        }
    }

    public static String getSafeName(String name)
    {
        StringBuilder builder = new StringBuilder();
        for(char c : name.toCharArray())
        {
            if((c >= 'a' && c <='z') || (c >= 'A' && c <= 'Z'))
            {
                builder.append(c);
            }else if(c == ' '){
                builder.append('_');
            }
        }
        return builder.toString();
    }




}

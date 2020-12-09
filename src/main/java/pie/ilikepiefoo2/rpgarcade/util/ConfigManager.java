package main.java.pie.ilikepiefoo2.rpgarcade.util;

import java.io.*;

/**
 * Config Manager
 */
public class ConfigManager {
    /**
     * Loads File and returns it as a string.
     *
     * @param path                      Location of the File.
     * @return FileContent              The contents of the File.
     * @throws ConfigException          If there is an internal config error.
     * @throws FileNotFoundException    If the file/folder containing the file
     *                                  doesn't exist.
     */
    public static String loadFile(String path) throws ConfigException, FileNotFoundException
    {
        FileReader fileReader = null;
        fileReader = new FileReader(path);
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

    /**
     * Save a file to a location
     *
     * @param path              File location
     * @param data              File data.
     * @throws ConfigException  If there is an internal config error.
     */
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

    /**
     * Converts a name to a filename that is safe.
     *
     * @param name
     * @return fileName
     */
    public static String getSafeName(String name)
    {
        StringBuilder builder = new StringBuilder();
        for(char c : name.toCharArray())
        {
            if((c >= 'a' && c <='z') || (c >= 'A' && c <= 'Z') || c == '_')
            {
                builder.append(c);
            }else if(c == ' '){
                builder.append('_');
            }
        }
        return builder.toString();
    }




}

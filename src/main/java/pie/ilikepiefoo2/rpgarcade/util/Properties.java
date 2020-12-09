package main.java.pie.ilikepiefoo2.rpgarcade.util;

import java.util.HashMap;

public class Properties extends HashMap<String,Object> {
    public Properties()
    {
        super();
    }
    public String toSavingFormat()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Type="+this.get("Type")+"\n");
        for (Entry<String, Object> stringObjectEntry : this.entrySet()) {
            if(stringObjectEntry.getKey() != "Type")
                builder.append(stringObjectEntry.getKey()+"="+stringObjectEntry.getValue()+"\n");
        }
        return builder.toString();
    }
}

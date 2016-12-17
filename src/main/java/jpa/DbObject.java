package jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbObject {

    static List<String> stubTextParams = new ArrayList<String>();
    static List<String> stubDescriptions = new ArrayList<String>();
    static {
        stubTextParams.add("first");
        stubTextParams.add("second");
        stubDescriptions.add("desc one");
        stubDescriptions.add("desc two");
    }

    Map<Long, String> textParams = new HashMap<Long, String>();
    Map<Long, Long> numParams = new HashMap<Long, Long>();
    String description = "";

    //TODO: Implement DbObject properly
    private long objectId;
    private static Long counter = Long.valueOf(1);

    public DbObject(){
        objectId = counter++;
    }

    public long getObjectId() {
        return objectId;
    }
    public String getDescription() {
        return description;
    }
    public String getTextParam(long attributeId) {
        if (!textParams.containsKey(attributeId))
            textParams.put(attributeId, stubTextParams.get(Double.valueOf(Math.random()*stubTextParams.size()).intValue()));
        return textParams.get(attributeId);
    }
    public Long getNumParam(long attributeId) {
        if (!numParams.containsKey(attributeId))
            numParams.put(attributeId, counter);
        return numParams.get(attributeId);
    }


    public void setDescription(String description) {
        this.description = description;
    }
    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }
    public void setTextParam(long attributeId, String value) {
        textParams.put(attributeId, value);
    }
    public void setNumParam(long attributeId, Long value) {
        numParams.put(attributeId, value);
    }
}

package jpa;


import org.mindrot.jbcrypt.BCrypt;

public class DbObject {
    //TODO: Implement DbObject properly
    private int objectId;

    public DbObject(){}

    public String getDescription() {
        return "stub description";
    }
    public void setDescription(String description) {
    }

    public String getTextParamValue(long attributeId){
        return "stubTPV";
    }
    public void setTextParamValue(long attributeId, String value) {
    }

    public long getNumericParamValue(long attributeId) {
        return 0xF00;
    }
    public void setNumericParamValue(long attributeId, long value) {
    }

    //stub method, remove completely when implementing this class
    public String getPassword() {
        return BCrypt.hashpw("lol", BCrypt.gensalt());
    }
}

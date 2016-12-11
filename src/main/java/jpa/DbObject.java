package jpa;

public class DbObject {
    //TODO: Implement DbObject properly
    private long objectId;
    private static int counter;

    public DbObject(){
        counter++;
    }

    public long getObjectId() {
        //return objectId;
        return counter;
    }

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
        return attributeId;
    }
    public void setNumericParamValue(long attributeId, long value) {
    }
}

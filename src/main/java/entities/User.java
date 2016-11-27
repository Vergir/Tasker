package entities;

import jpa.Dao;
import jpa.DbObject;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    private static final long USERNAME_ID = 1;
    private static final long PASSWORD_ID = 2;
    private static final long ROLE_ID = 3;

    private DbObject dbObject;

    public User() {
        dbObject = new DbObject();
    }
    public User(String username, String password, Role role) {
        this();
        dbObject.setTextParamValue(USERNAME_ID, username);
        dbObject.setTextParamValue(PASSWORD_ID, password);
        dbObject.setNumericParamValue(ROLE_ID, role.toLong());
    }

    public long getId() {
        return dbObject.getObjectId();
    }

    public String getUsername() {
        return dbObject.getTextParamValue(USERNAME_ID);
    }
    public void setUsername(String username) {
        dbObject.setTextParamValue(USERNAME_ID, username);
    }

    public String getPassword() {
        //TODO: replace stub to return dbObject.getTextParamValue(PASSWORD_ID);
        return BCrypt.hashpw("lol", BCrypt.gensalt());
    }
    public void setPassword(String password) {
        dbObject.setTextParamValue(PASSWORD_ID, password);
    }

    public Role getRole() {
        long roleId = dbObject.getNumericParamValue(ROLE_ID);
        return (Role)Dao.getObjectById(roleId);
    }
    public void setRole(Role role) {
        dbObject.setNumericParamValue(ROLE_ID, role.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!getUsername().equals(user.getUsername())) return false;
        return getPassword().equals(user.getPassword());

    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }
}

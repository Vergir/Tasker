package entities;

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
    public User(Long id) {
        dbObject = DbObject.find(id);
    }
    public User(String username, String password, Role role) {
        this();
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public Long getId() {
        return dbObject.getObjectId();
    }

    public String getUsername() {
        return dbObject.getTextParam(USERNAME_ID);
    }
    public void setUsername(String username) {
        dbObject.setTextParam(USERNAME_ID, username);
    }

    public String getPassword() {
        //TODO: replace stub to return dbObject.getTextParamValue(PASSWORD_ID);
        return dbObject.getTextParam(PASSWORD_ID);
    }
    public void setPassword(String password) {
        dbObject.setTextParam(PASSWORD_ID, BCrypt.hashpw(password, BCrypt.gensalt()));
    }

    public Role getRole() {
        return new Role(dbObject.getNumParam(ROLE_ID));
    }
    public void setRole(Role role) {
        dbObject.setNumParam(ROLE_ID, role.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }
}

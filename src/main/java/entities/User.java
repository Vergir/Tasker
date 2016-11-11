package entities;

public class User {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

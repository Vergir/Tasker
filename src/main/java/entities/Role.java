package entities;

/**
 * Created by Vergir on 11/11/2016.
 */
public class Role {
    private String description;
    private boolean canCreateUsers;
    private boolean canCreateProjects;
    private boolean canAssignDevelopers;

    public final static Role ADMIN = new Role("Administrator", true, true, true);

    private Role(){}

    private Role(String description, boolean canCreateUsers, boolean canCreateProjects, boolean canAssignDevelopers) {
        this.description = description;
        this.canCreateUsers = canCreateUsers;
        this.canCreateProjects = canCreateProjects;
        this.canAssignDevelopers = canAssignDevelopers;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCanCreateUsers() {
        return canCreateUsers;
    }

    public boolean isCanCreateProjects() {
        return canCreateProjects;
    }

    public boolean isCanAssignDevelopers() {
        return canAssignDevelopers;
    }

    public static Role getADMIN() {
        return ADMIN;
    }

    @Override
    public String toString() {
        return description;
    }
}

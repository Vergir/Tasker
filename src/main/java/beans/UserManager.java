package beans;

import entities.Role;
import entities.Task;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

public class UserManager {
    private static final List<String> names = Arrays.asList(("Mary\n" +
            "Patricia\n" +
            "Linda\n" +
            "Barbara\n" +
            "Elizabeth\n" +
            "Jennifer\n" +
            "Maria\n" +
            "Susan\n" +
            "Margaret\n" +
            "Dorothy\n" +
            "Lisa\n" +
            "Nancy\n" +
            "Karen\n" +
            "Betty\n" +
            "Helen\n" +
            "Sandra\n" +
            "Donna\n" +
            "Carol\n" +
            "Ruth\n" +
            "Sharon\n" +
            "Michelle\n" +
            "Laura\n" +
            "Sarah\n" +
            "Kimberly\n" +
            "Deborah\n" +
            "Jessica\n" +
            "Shirley\n" +
            "Cynthia\n" +
            "Angela\n" +
            "Melissa\n" +
            "Brenda\n" +
            "Amy\n" +
            "Anna\n" +
            "Rebecca\n" +
            "Virginia\n" +
            "Kathleen\n" +
            "Pamela\n" +
            "Martha\n" +
            "Debra\n" +
            "Amanda\n" +
            "Stephanie\n" +
            "Carolyn\n" +
            "Christine\n" +
            "Marie\n" +
            "Janet\n" +
            "Catherine\n" +
            "Frances\n" +
            "Ann\n" +
            "Joyce\n" +
            "Diane\n" +
            "Alice\n" +
            "Julie\n" +
            "Heather\n" +
            "Teresa\n" +
            "Doris\n" +
            "Gloria\n" +
            "Evelyn\n" +
            "Jean\n" +
            "Cheryl\n" +
            "Mildred\n" +
            "Katherine\n" +
            "Joan\n" +
            "Ashley\n" +
            "Judith\n" +
            "Rose\n" +
            "Janice\n" +
            "Kelly\n" +
            "Nicole\n" +
            "Judy\n" +
            "Christina\n" +
            "Kathy\n" +
            "Theresa\n" +
            "Beverly\n" +
            "Denise\n" +
            "Tammy\n" +
            "Irene\n" +
            "Jane\n" +
            "Lori\n" +
            "Rachel\n" +
            "Marilyn\n" +
            "Andrea\n" +
            "Kathryn\n" +
            "Louise\n" +
            "Sara\n" +
            "Anne\n" +
            "Jacqueline\n" +
            "Wanda\n" +
            "Bonnie\n" +
            "Julia\n" +
            "Ruby\n" +
            "Lois\n" +
            "Tina\n" +
            "Phyllis\n" +
            "Norma\n" +
            "Paula\n" +
            "Diana\n" +
            "Annie\n" +
            "Lillian\n" +
            "Emily\n" +
            "Robin\n" +
            "Peggy\n" +
            "Crystal\n" +
            "Gladys\n" +
            "Rita\n" +
            "Dawn\n" +
            "Connie\n" +
            "Florence\n" +
            "Tracy\n" +
            "Edna\n" +
            "Tiffany\n" +
            "Carmen\n" +
            "Rosa\n" +
            "Cindy\n" +
            "Grace\n" +
            "Wendy\n" +
            "Victoria\n" +
            "Edith").split("\n"));

    private List<User> users;
    @Autowired
    TaskManager taskManager;
    @Autowired
    RoleManager roleManager;

	public UserManager() {
        users = new ArrayList<User>();
        for (String name : names)
            users.add(new User(name, name+name, new Role("none", false, false, false)));
        users.add(new User("[admin]", "adminadmin", new Role("Chief Admin", true, true, true)));
	}

	@PostConstruct
    private void init() {
        int i = 0;
        Role[] roles = roleManager.getRoles().toArray(new Role[]{});

        for (User u : users)
            if (u.getRole().getDescription().equals("none"))
                u.setRole(roles[i++ % 3]);
    }


    public List<User> getUsers() {
	    List<User> result = new ArrayList<User>(users);
	    User admin = null;
	    for (User u : result)
	        if (u.getUsername().equals("[admin]"))
	            admin = u;
	    result.remove(admin);
        return result;
    }

    public User getUser(String username, String password) {
        for (User u : users)
            if (u.getUsername().toLowerCase().equals(username.toLowerCase()) && BCrypt.checkpw(password, u.getPassword()))
                return u;
        return null;
    }
    public User getUser(Long id) {
	    for (User u : users)
	        if (u.getId().equals(id))
	            return u;
	    return null;
    }

    public void addUser(String username, String password, Role role) {
        users.add(new User(username, password, role));
    }


    public Set<Task> getUserTasks(User u){
	    Set<Task> result = new HashSet<Task>();

	    for (Task t : taskManager.getTasks())
            if (t.getAssignee().equals(u.getId()))
                result.add(t);

	    return result;
    }
}

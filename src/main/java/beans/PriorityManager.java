package beans;

import entities.Priority;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vergir on 21/12/2016.
 */
public class PriorityManager {
    private Set<Priority> priorities;

    PriorityManager() {
        priorities = new HashSet<Priority>();
        priorities.add(new Priority("Critical", (long)99));
        priorities.add(new Priority("High", (long)75));
        priorities.add(new Priority("Moderate", (long)50));
        priorities.add(new Priority("Low", (long)25));
        priorities.add(new Priority("Minimal", (long)1));
    }

    public Set<Priority> getPriorities() {
        return priorities;
    }

    public Priority getPriority(String desc) {
        for (Priority p : priorities)
            if (p.getDescription().toLowerCase().equals(desc.toLowerCase()))
                return p;
        return null;
    }
}

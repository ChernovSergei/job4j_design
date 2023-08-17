package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info result = new Info(0, 0, 0);
        Iterator<User> currentIterator = current.iterator();

        HashMap<Integer, String> previousMap = new HashMap<>();
        previous.forEach(T -> previousMap.put(T.getId(), T.getName()));

        while (currentIterator.hasNext()) {
            User currentUser = currentIterator.next();
            String previousName = previousMap.get(currentUser.getId());

            if (previousName == null) {
                result.incrementAdded();
                continue;
            }
            if (!previousName.equals(currentUser.getName())) {
                result.incrementChanged();
            }
            previousMap.remove(currentUser.getId());
        }

        for (int i = 0; i < previousMap.size(); i++) {
            result.incrementDeleted();
        }

        return result;
    }
}

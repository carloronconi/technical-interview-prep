package examples.binarytree.treemapdictionary;

import java.time.LocalDate;

public record User(String name, String surname, LocalDate birthday) implements Comparable<User> {
    @Override
    public int compareTo(User o) {
        int surnameCompare = surname.compareTo(o.surname);
        if (surnameCompare != 0) return surnameCompare;

        int nameCompare = name.compareTo(o.name);
        if (nameCompare != 0) return nameCompare;

        return birthday.compareTo(o.birthday);
    }
}

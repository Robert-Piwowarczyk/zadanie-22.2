import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class Store {

    private List<User> users;

    public Store() {
        users = new ArrayList<>();
        users.add(new User("Daniel", "Aback", 34));
        users.add(new User("Adam", "Nowak", 42));
        users.add(new User("Beata", "Zagórska", 23));
    }

    public List<User> getAll() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }

    public List<User> search(String imie, String nazwisko, Integer wiek) {
        List<User> resultList = new LinkedList<>();
        for (User user : users) {
            boolean isTrue = userSearch(imie, nazwisko, wiek, user);
            if(isTrue) {
                resultList.add(user);
            }
        }

        return resultList;
    }

    private boolean userSearch(String imie, String nazwisko, Integer wiek, User user) {
        boolean nameTrue = true;
        boolean surnameTrue = true;
        boolean ageTrue = true;

        if(!StringUtils.isEmpty(imie) && !imie.equals(user.getName())) {
            nameTrue = false;
        }

        if(!StringUtils.isEmpty(nazwisko) && !nazwisko.equals(user.getLastName())) {
            surnameTrue = false;
        }

        if(wiek != null && wiek != user.getAge()) {
            ageTrue = false;
        }

        return nameTrue && surnameTrue && ageTrue;
    }

    public void delete(String imie, String nazwisko, Integer wiek) {
        List<User> usersToDelete = new LinkedList<>();
        for (User user : users) {
            boolean isOk = userSearch(imie, nazwisko, wiek, user);
            if(isOk) {
                usersToDelete.add(user);
            }
        }
        users.removeAll(usersToDelete);
    }
}
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ConstruktorController {
    private Store userStore;

    public ConstruktorController(Store userStore) {
        this.userStore = userStore;
    }

    @ResponseBody
    @GetMapping("/users")
    public String users() {
        List<User> users = userStore.getAll();
        return convertToString(users);
    }

    private String convertToString(List<User> users) {
        String result = "";

        for (User user : users) {
            result += user.toString() + "<br/>";
        }
        return result;
    }

    @RequestMapping("/add")
    public String add(@RequestParam(defaultValue = "Nieznany", required = false) String imie,
                      @RequestParam String nazwisko,
                      @RequestParam Integer wiek) {
        if(StringUtils.isEmpty(imie)) {
            return "redirect:/err.html";
        } else  {
            User user = new User(imie, nazwisko, wiek);
            userStore.add(user);
            return "redirect:/success.html";
        }
    }

    @PostMapping("/search")
    @ResponseBody
    public String search(@RequestParam String imie,
                         @RequestParam String nazwisko,
                         @RequestParam Integer wiek) {

        List<User> users = userStore.search(imie, nazwisko, wiek);
        return convertToString(users);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String imie,
                         @RequestParam String nazwisko,
                         @RequestParam Integer wiek) {

        userStore.delete(imie, nazwisko, wiek);
        return "redirect:/users";
    }
}

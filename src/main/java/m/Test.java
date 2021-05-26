package m;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by qincasin on 2021/5/23.
 */
public class Test {
    public static void main(String[] args) {
        List<User> list =new ArrayList<>();
        User user = new User();
        user.setUser("zhangsan");
        user.setId(1);
        User2 u = new User2();
        Optional.ofNullable(user.getUser())
                .ifPresent(u::setUser);
        Optional.ofNullable(user.getId())
                .ifPresent(user::setId);
        System.out.println(u);

        User uu = new User();
        uu.setId(2);
        uu.setUser("lisi");
        list.add(user);
        list.add(uu);
        System.out.println(list);
        List<User> list2 = list.stream()
                .peek(a -> a.setId(null))
                .collect(Collectors.toList());
        System.out.println(list2);

    }
    static class User{
        private Integer id;
        private String user;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", user='" + user + '\'' +
                    '}';
        }
    }

    static class User2{
        private Integer id;
        private String user;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "User2{" +
                    "id=" + id +
                    ", user='" + user + '\'' +
                    '}';
        }
    }
}

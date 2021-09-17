package stream;

import java.util.Optional;

/**
 * Created by qincasin on 2021/9/1.
 */
public class OptionalTest {
    public static void main(String[] args) {
        User u = new User();
        Optional.ofNullable(getUser()).orElse(null);
        System.out.println(u);
        String s = "aabb%sid=%s";
        String format = String.format(s, "1", "");
        System.out.println(format);

    }
    public static User getUser(){
        return null;
//        return new User("zhangsan",1,null);
    }
}

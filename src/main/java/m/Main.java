package m;

/**
 * Created by qincasin on 2021/8/30.
 */
public class Main {
    public static void main(String[] args) {
        User u = new User("zhangsan","nan");
        Base<User> base = new Base<>();
        base.setExtra(u);
        base.setId(1111);
        System.out.println(base);
    }
}

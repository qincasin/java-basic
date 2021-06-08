package stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by qincasin on 2021/5/28.
 */
public class Test {
    public static void main(String[] args) {
        List<User> list =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User u = new User();
            u.setName("张三"+i);
            u.setId(i);
            u.setAge(i);
            list.add(u);
        }
        for (int i = 0; i < 1; i++) {
            User u = new User();
            u.setName("张三"+i);
            u.setId(i);
            u.setAge(i);
            list.add(u);
        }
        System.out.println(list);
        List<Map<String,String>> l = new ArrayList<>();
        Map<String,String> m = new TreeMap<>();
        for (User user : list) {
            if (m.size()>0 && m.size()%4==0){
                l.add(m);
                m = new TreeMap<>();
            }
            m.put(user.getName(),"1");
        }
        if (m.size()>0){
            l.add(m);
        }
        System.out.println("-----------");
        System.out.println(l);

        Long a = 10L;
        System.out.println(a<9);

    }

}

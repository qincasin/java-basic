package date;

import java.util.Date;

/**
 * Created by qincasin on 2021/6/16.
 */
public class TestDate {
    public static void main(String[] args) {
        Date d = new Date(1415521345000L);
        Date d2 = new Date(1415521345000L);
        System.out.println(d.getTime() == d2.getTime());
        System.out.println(d.equals(d2));
    }
}

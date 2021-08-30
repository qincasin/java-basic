package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by qincasin on 2021/8/9.
 */
public class LimitTest {
    public static void main(String[] args) {
        List<A> list = new ArrayList<>();
        list.add(new A(1,"1"));
        list.add(new A(2,"2"));
        list.add(new A(3,"3"));
        list.add(new A(4,"4"));
        list.add(new A(5,"5"));
        long count = list.stream().map(A::getI).filter(a -> a.compareTo(4) < 0).count();
        System.out.println(count);
        List<A> as = list.stream().limit(count).collect(Collectors.toList());
        System.out.println(as);

        Integer a = 110;
        String format = String.format("%02d", a);
        System.out.println(format.length());
        System.out.println(format);
    }

 static class A{
     public A(Integer i, String name) {
         this.i = i;
         this.name = name;
     }

     @Override
     public String toString() {
         return "A{" +
                 "i=" + i +
                 ", name='" + name + '\'' +
                 '}';
     }

     private Integer i;
        private String name;

     public Integer getI() {
         return i;
     }

     public void setI(Integer i) {
         this.i = i;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }
 }
}


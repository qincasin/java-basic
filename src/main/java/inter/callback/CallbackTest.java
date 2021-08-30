package inter.callback;

import java.util.Optional;
import java.util.concurrent.Callable;


/**
 * Created by qincasin on 2021/7/9.
 */
public class CallbackTest {
    public static void main(String[] args) {

        Task task = new SimpleTask();
//        CallBack c = new CallBack() {
//            @Override
//            public String call() {
//                return a;
//            }
//        };
        task.executeWith();
        Task task2 = new SimpleTask2();
        task2.executeWith();

    }
}
class SimpleTask extends Task<TaskA> {

    @Override
    protected TaskA build() {
        TaskA bean = new TaskA("1", "id1");
        return bean;
    }
}


class SimpleTask2 extends Task<TaskB> {
    @Override
    protected TaskB build() {
        TaskB bean = new TaskB("1", "id1");
        return bean;
    }
}

interface CallBack<R> {
    R call();
}

abstract class Task<R> {
    final void executeWith() {
        R c = build();
        execute(c);
    }


    protected  void execute(R call1){
        if (call1 instanceof TaskA){
            System.out.println("taskA:"+call1);
        }else if (call1 instanceof TaskB){
            System.out.println("taskB:"+call1);
        }else {
            System.out.println("error");
        }

    }

    protected abstract R build();


}
class TaskA{
    public TaskA(String name, String id) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TaskA{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
class TaskB{
    public TaskB(String name, String id) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TaskB{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}






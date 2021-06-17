package lock;

/**
 * Created by qincasin on 2021/6/14.
 */
public class MiniReenTrantLock implements Lock{
    /**
     * 锁的是什么？
     * 资源 -> state
     * 0 表示未加锁状态
     * >0 表示当前lock是加锁状态..
     */
    private volatile int state;





    @Override
    public void lock() {

    }

    @Override
    public void unlock() {

    }

}

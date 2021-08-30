ThreadLocal源码
# 类简单介绍
```java
/*
 * 该类提供了线程局部 (thread-local) 变量。 这些变量不同于它们的普通对应物，
 * 因为访问某个变量（通过其 get 或 set 方法）的每个线程都有自己的局部变量
 * 它独立于变量的初始化副本。ThreadLocal 实例通常是类中的 private static 字段
 * 它们希望将状态与某一个线程（例如，用户 ID 或事务 ID）相关联。
 *
 * 例如，以下类生成对每个线程唯一的局部标识符。
 *
 * 线程 ID 是在第一次调用 UniqueThreadIdGenerator.getCurrentThreadId() 时分配的，
 * 在后续调用中不会更改。
 * <pre>
 * import java.util.concurrent.atomic.AtomicInteger;
 *
 * public class ThreadId {
 *     // 原子性整数，包含下一个分配的线程Thread ID
 *     private static final AtomicInteger nextId = new AtomicInteger(0);
 *
 *     // 每一个线程对应的Thread ID
 *     private static final ThreadLocal<Integer> threadId =
 *         new ThreadLocal<Integer>() {
 *             @Override protected Integer initialValue() {
 *                 return nextId.getAndIncrement();
 *         }
 *     };
 *
 *     // 返回当前线程对应的唯一Thread ID, 必要时会进行分配
 *     public static int get() {
 *         return threadId.get();
 *     }
 * }
 * </pre>
 * 每个线程都保持对其线程局部变量副本的隐式引用，只要线程是活动的并且 ThreadLocal 实例是可访问的
 * 在线程消失之后，其线程局部实例的所有副本都会被垃圾回收，（除非存在对这些副本的其他引用）。
 */

```
# 类中的几个小方法以及部分局部变量介绍
## threadLocalHashCode
>     //线程获取threadLocal.get()时 如果是第一次在某个 threadLocal对象上get时，会给当前线程分配一个value
    //这个value 和 当前的threadLocal对象 被包装成为一个 entry 其中 key是 threadLocal对象，value是threadLocal对象给当前线程生成的value
    //这个entry存放到 当前线程 threadLocals 这个map的哪个桶位？ 与当前 threadLocal对象的threadLocalHashCode 有关系。
    // 使用 threadLocalHashCode & (table.length - 1) 的到的位置 就是当前 entry需要存放的位置。
    private final int threadLocalHashCode = nextHashCode();


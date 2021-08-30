package m;

/**
 * Created by qincasin on 2021/8/30.
 */
public class Base<T> {
    private Integer id;
    private T extra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public T getExtra() {
        return extra;
    }

    public void setExtra(T extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", extra=" + extra +
                '}';
    }
}

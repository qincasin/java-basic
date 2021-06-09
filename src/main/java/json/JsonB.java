package json;

/**
 * Created by qincasin on 2021/6/8.
 */
public class JsonB {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JsonB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
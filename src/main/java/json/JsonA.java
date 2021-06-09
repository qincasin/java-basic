package json;

/**
 * Created by qincasin on 2021/6/8.
 */
public class JsonA {
    private Integer idA;
    private String nameA;
    private JsonB before;

    public Integer getIdA() {
        return idA;
    }

    public void setIdA(Integer idA) {
        this.idA = idA;
    }

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public JsonB getBefore() {
        return before;
    }

    @Override
    public String toString() {
        return "JsonA{" +
                "idA=" + idA +
                ", nameA='" + nameA + '\'' +
                ", before=" + before +
                '}';
    }

    public void setBefore(JsonB before) {
        this.before = before;
    }
}

package inter.def;

/**
 * Created by qincasin on 2021/7/22.
 */
public abstract class AbstractMethod implements InterfaceA {
    protected GoodsResp resp = null;

    @Override
    public GoodsResp defaultM(GoodsReq type) {
        return resp;
    }
}

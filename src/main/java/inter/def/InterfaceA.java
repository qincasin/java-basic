package inter.def;

/**
 * Created by qincasin on 2021/7/22.
 */
public interface InterfaceA {
    GoodsResp rpc(GoodsReq req);

    GoodsResp defaultM(GoodsReq type);
}

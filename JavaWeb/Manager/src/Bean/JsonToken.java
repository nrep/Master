package Bean;

/**
 * Created by YocyTang on 2017/2/18.
 * 用于构建传送给前端json格式的token
 */
public class JsonToken {
    private String token;
    public JsonToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
}

package Bean;

/**
 * Created by YocyTang on 2017/1/24.
 * 用于构建错误的json信息
 */
public class ErrorInfo {
    private String information;
    public ErrorInfo(String information){
        this.information = information;
    }
    public String getInformation(){
        return information;
    }
}

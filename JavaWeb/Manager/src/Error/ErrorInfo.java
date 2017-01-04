package Error;

/**
 * Created by YocyTang on 2017/1/4.
 */
public class ErrorInfo {
    private String info;
    private boolean failed= true;
    public ErrorInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return info;
    }
    public boolean isFailed(){
        return failed;
    }
    public void setInfo(String info){
        this.info = info;
    }
}

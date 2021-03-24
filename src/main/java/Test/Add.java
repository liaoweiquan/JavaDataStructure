package Test;

public class Add {
    private volatile int cnt;
    public Add(){
        cnt = 0;
    }
    public void addCnt(){
        ++ cnt;
    }
    public int getCnt() {
        return cnt;
    }
}

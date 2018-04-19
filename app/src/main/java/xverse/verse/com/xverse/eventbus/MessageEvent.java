package xverse.verse.com.xverse.eventbus;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class MessageEvent {
    private String message;

    public MessageEvent(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}

package models;
import play.mvc.*;
import play.libs.*;
import play.libs.F.*;
import java.util.*;
/**
 * Created by HoangYell on 14/06/2016.
 */
public class SimpleChat {
    private static List<WebSocket.Out<String>> connections = new ArrayList<WebSocket.Out<String>>();
    public static void start(WebSocket.In<String> in, WebSocket.Out<String> out)
    {
        connections.add(out);

        in.onMessage(new Callback<String>(){
            public  void invoke(String event){
                SimpleChat.notifyAll(event);
            }
        });

        in.onClose(new Callback0(){
            public void invoke(){
                SimpleChat.notifyAll("-----------------------------onClose");
            }
        });
    }

    public  static  void notifyAll(String message){
        for (WebSocket.Out<String> out : connections)
        {
            System.out.println("___NotifyAll:"+message);
            out.write("___NotifyAll:"+message);
        }
    }
}




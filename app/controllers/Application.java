package controllers;
import models.SimpleChat;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

import views.html.index;


public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result wsJs() {
        return ok(views.js.ws.render());
    }

    public static WebSocket<String> wsInterface() {
        return new WebSocket<String>() {
            @Override
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
                SimpleChat.start(in,out);
            }
        };
    }
}





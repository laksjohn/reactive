package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class SenderVerticle extends AbstractVerticle {

    @Override
    public void start(){
        final EventBus eventBus = this.vertx.eventBus();
        this.vertx.setPeriodic(1000,h->{
            eventBus.send("test","");
        });


    }


    private void sendMessage(RoutingContext routingContext){
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam("PATH_PARAM");
            eventBus.request("ADDRESS", message,
            reply -> {
            if (reply.succeeded()) {
                System.out.println("Received reply: " + reply.result().body());
            } else {
                System.out.println("No reply");
            }
        });
        routingContext.response().end(message);
    }




}

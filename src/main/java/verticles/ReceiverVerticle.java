package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.RoutingContext;

public class ReceiverVerticle extends AbstractVerticle {

    private void sendMessage(RoutingContext routingContext){
        final EventBus eventBus = vertx.eventBus();
        final String message = routingContext.request().getParam("PATH_PARAM");
        eventBus.consumer("ADDRESS", message1 -> {

                System.out.println("Received reply: " + message1.body());
                message1.reply("some response");

        });
        routingContext.response().end(message);
    }
}

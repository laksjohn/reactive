package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle {

    public void start(){

        deployApiVerticle(vertx);
    }

    private Future<String> deployApiVerticle(Vertx vertx){
        return vertx.deployVerticle(ApiVerticle.class.getName())
                .onFailure(throwable -> System.out.println("ApiVerticle Not Deployed"+ throwable))
                .onSuccess(success->System.out.println("ApiVerticle deployed"));
    }
}

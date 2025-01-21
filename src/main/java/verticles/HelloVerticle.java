package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class HelloVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> promise){
        System.out.println("Hello Vert.x");
    }

}

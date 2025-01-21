import io.vertx.core.Future;
import io.vertx.core.Vertx;
import verticles.ApiVerticle;
import verticles.MainVerticle;


public class Application {

    public static void main(String args[]){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MainVerticle.class.getName());
    }


}

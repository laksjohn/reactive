package router;

import handler.BookHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class BookRouter {

    private final Vertx vertx;
    private final BookHandler bookHandler;

    public BookRouter(Vertx vertx,BookHandler bookHandler){
        this.vertx=vertx;
        this.bookHandler=bookHandler;
    }

    public void setRouter(Router router){
        router.mountSubRouter("/api/v1",buildBookRouter());
    }

    private Router buildBookRouter() {

        final Router router=Router.router(vertx);

        router.route("/books*").handler(BodyHandler.create());
        router.get("/books/:id").handler(bookHandler::getBook);
        router.post("/books").handler(bookHandler::postBook);
        router.post("/books/100").blockingHandler(bookHandler::getBook);

        return router;

    }
}

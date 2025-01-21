package verticles;

import handler.BookHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import repository.BookRepository;
import router.BookRouter;
import service.BookService;

public class ApiVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> promise){

        final BookRepository bookRepository = new BookRepository();
        final BookService bookService = new BookService(bookRepository);
        final BookHandler bookHandler = new BookHandler(bookService);
        final BookRouter bookRouter = new BookRouter(vertx,bookHandler);
        final Router router = Router.router(vertx);
        bookRouter.setRouter(router);

        buildHttpServer(vertx,promise,router);
    }

    private void buildHttpServer(Vertx vertx, Promise<Void> promise, Router router) {

        final int port =8888;

        vertx.createHttpServer().requestHandler(router).listen(port,http->{
            int i=(int)(Math.random()*100);
            System.out.println("Req id= "+i + Thread.currentThread().getName());
//            vertx.executeBlocking(r-> {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
            if(http.succeeded()){
                promise.complete();
                System.out.println("Success Run HTTP Server");
            }else{
                promise.fail(http.cause());
                System.out.println("Http Server failed");
            }
        });


        vertx.setPeriodic(1000, h -> {
            System.out.println("Periodic on " + Thread.currentThread().getName());
            vertx.executeBlocking(f -> {
                System.out.println("Future on " + Thread.currentThread().getName());
                f.complete();
            }, r -> {
                System.out.println("Result on " + Thread.currentThread().getName());
            });
        });

        vertx.setPeriodic(1000,h->{
            System.out.println("Starting "+Thread.currentThread().getName());
            vertx.executeBlocking(f->{
                System.out.println("Blocking on "+ Thread.currentThread().getName());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                f.complete();
            },r-> {
                System.out.println("Ending on " + Thread.currentThread().getName());
            });
        });


        vertx.setPeriodic(1000,h->{
            System.out.println("first");
            vertx.executeBlocking(f->{
                System.out.println("second");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                f.complete();
            });
            System.out.println("third");
        });
    }
}

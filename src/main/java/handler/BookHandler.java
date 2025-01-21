package handler;

import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;
import model.Book;
import service.BookService;

public class BookHandler {

    private final BookService bookService;



    public BookHandler(BookService bookService){
        this.bookService=bookService;
    }

    public Future<Book> getBook(RoutingContext rc){

        final String id = rc.queryParams().get("id");
        return bookService.getBook(Integer.parseInt(id))
                .onSuccess(success->ResponseUtils.buildOkResponse(rc,success))
                .onFailure(throwable -> ResponseUtils.buildErrorResponse(rc,throwable));
    }

    public Future<Book> postBook(RoutingContext rc){

        return bookService.createBook("name","author")
                .onSuccess(success->ResponseUtils.buildOkResponse(rc,success))
                .onFailure(throwable -> ResponseUtils.buildErrorResponse(rc,throwable));
    }
}

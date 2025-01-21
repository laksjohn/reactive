package repository;

import io.vertx.core.Future;
import model.Book;

import java.util.HashMap;
import java.util.Map;

public class BookRepository {

    Map<Integer, Book>  bookMap= new HashMap<>();


    public Future<Book> insert (Book book){
        bookMap.put(book.getId(),book);
        return Future.succeededFuture(book);

    }

    public Future<Integer> count(){
        return Future.succeededFuture(bookMap.size());
    }

    public Future<Book> getBook(int bookId){
        if (!bookMap.containsKey(bookId)) {
            return Future.failedFuture("Book Not Found");
        } else {
            return Future.succeededFuture(bookMap.get(bookId));
        }

    }
}

package service;

import io.vertx.core.Future;
import model.Book;
import repository.BookRepository;

public class BookService {

    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }


    public Future<Book> getBook(int id){

        return bookRepository.getBook(id)
                .onSuccess(success -> System.out.println(success))
                .onFailure(throwable -> System.out.println(throwable));

    }

    public Future<Book> createBook(String name,String author){
        Book book = new Book();
        book.setId((int) (Math.random()*100));
                book.setAuthor(author);
        book.setTitle(name);

        return bookRepository.insert(book)
                .onFailure(throwable -> System.out.println(throwable))
                .onSuccess(success->System.out.println(success))
                .onComplete(ar->System.out.println("Request completed"));
    }
}

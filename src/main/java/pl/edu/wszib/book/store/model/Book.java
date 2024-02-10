package pl.edu.wszib.book.store.model;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "tbook")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    @Getter
    private String isbn;
    private double price;
    private int quantity;

    public Book(int id) {
        this.id = id;
    }

}

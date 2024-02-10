package pl.edu.wszib.book.store.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "trent")
public class Rental {
    @Id
    private int rentalId;
    private int bookId;
    private String name;
    private String surname;
    private String phone;
    private LocalDate startDate;
    private LocalDate endDate;

}



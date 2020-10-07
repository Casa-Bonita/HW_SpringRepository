package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Passport {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private int id;

    @NonNull
    private String series;

    @NonNull
    private int number;

    @NonNull
    private String holderName;

    @NonNull
    private int year;

    @NonNull
    private String country;
}

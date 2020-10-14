package repository;

import entity.Passport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Integer> {

    List<Passport> getPassportBySeries (String series);

    Passport getPassportBySeriesAndNumber (String series, int number);

    Passport getPassportByNumberAndCountry (int number, String series);

    List<Passport> getPassportByNumberOrNumber (int numberOne, int numberTwo);
}

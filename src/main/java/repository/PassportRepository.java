package repository;

import entity.Passport;
import org.springframework.data.repository.CrudRepository;

public interface PassportRepository extends CrudRepository<Passport, Integer> {
}

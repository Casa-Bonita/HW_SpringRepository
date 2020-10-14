package service;

import entity.Passport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PassportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PassportService {

    @Autowired
    PassportRepository passportRepository;

    public void save (Passport passport){
        passportRepository.save(passport);
    }

    public List<Passport> getAll(){
        return (List<Passport>) passportRepository.findAll();
    }

    public Optional<Passport> getById(int id){
        return passportRepository.findById(id);
    }

    public void remove (Passport passport){
        passportRepository.delete(passport);
    }

    public List<Passport> getPassportBySeries (String series){
        return passportRepository.getPassportBySeries (series);
    }

    public Passport getPassportBySeriesAndNumber (String series, int number){
        return passportRepository.getPassportBySeriesAndNumber(series, number);
    }

    public Passport getPassportByNumberAndCountry (int number, String series){
        return passportRepository.getPassportByNumberAndCountry(number, series);
    }

    public List<Passport> getPassportByNumberOrNumber(int numberOne, int numberTwo){
        return passportRepository.getPassportByNumberOrNumber(numberOne, numberTwo);
    }

}

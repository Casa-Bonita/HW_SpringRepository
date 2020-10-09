import entity.Passport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ClientService;
import service.PassportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//    2. Создать Spring (не MVC) приложение. Взять класс Passport из предыдущей задачи и заменить DAO-паттерн на spring data repository.
//    В Main проверить работу базовых методов: получение всех паспортов, получение по id, сохранение, удаление (через репозиторий).
//    Добавить свои методы в репозиторий, используя query methods:
//    - получение объекта паспорта по его серии (согласно статье https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
//    название метода можно делать не getPassportBy..., а findBy....);
//    - получение объекта паспорта по его серии и номеру;
//    - написать какой-нибудь свой метод.
//    Проверить работу созданных методов.

        Scanner scn = new Scanner(System.in);

        List<Passport> listPassport = new ArrayList<>();

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        PassportService service = context.getBean(PassportService.class);
        //ClientService service = context.getBean(ClientService.class);

        String input = "";
        while(!input.equals("7")){
            System.out.println("1. Create.");
            System.out.println("2. Read.");
            System.out.println("3. Read By Id.");
            System.out.println("4. Update.");
            System.out.println("5. Delete.");
            System.out.println("6. Get parcel by name and receip.");
            System.out.println("7. Exit.");

            input = scn.nextLine();
            if(input.equals("1")){
                System.out.println("1. Create.");

                service.save(new Passport("AA", 123, "Ivan", 1980, "Russia"));
                service.save(new Passport("BB", 456, "Olga", 1981, "Italy"));
                service.save(new Passport("CC", 789, "Petr", 1982, "USA"));
                service.save(new Passport("DD", 147, "Anna", 1983, "France"));
                service.save(new Passport("EE", 258, "Oleg", 1984, "China"));
                service.save(new Passport("FF", 369, "Irina", 1985, "Japan"));
                listPassport = service.getAll();
            }

            else if(input.equals("2")){
                System.out.println("2. Read.");

                listPassport = service.getAll();
                listPassport.stream()
                        .forEach(x -> System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", x.getId(), x.getSeries(), x.getNumber(), x.getHolderName(), x.getYear(), x.getCountry()));

            }

            else if(input.equals("3")){
                System.out.println("3. Read By Id.");

                System.out.println("Паспорта, имещиеся в таблице:");
                listPassport = service.getAll();
                listPassport.stream()
                        .forEach(x -> System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", x.getId(), x.getSeries(), x.getNumber(), x.getHolderName(), x.getYear(), x.getCountry()));

                System.out.println("Введите id посылки, которую нужно найти:");
                String inputId = scn.nextLine();
                int id = Integer.parseInt(inputId);

                Passport passport = service.getById(id).get();

                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", passport.getId(), passport.getSeries(), passport.getNumber(), passport.getHolderName(), passport.getYear(), passport.getCountry());

            }

            else if(input.equals("4")){
                System.out.println("4. Update.");

                System.out.println("Паспорта, имещиеся в таблице:");
                listPassport = service.getAll();
                listPassport.stream()
                        .forEach(x -> System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", x.getId(), x.getSeries(), x.getNumber(), x.getHolderName(), x.getYear(), x.getCountry()));

                System.out.println("Введите номер паспорта, который будет изменён:");
                String temp = scn.nextLine();
                int passportNumber = Integer.parseInt(temp);

                System.out.println("Введите новое имя владельца паспорта:");
                String newHolderName = scn.nextLine();

                System.out.println("Введите год рождения нового владельца:");
                temp = scn.nextLine();
                int newYear = Integer.parseInt(temp);

                System.out.println("Введите страну нового владельца:");
                String newCountry = scn.nextLine();

                int id = 0;

                for (int i = 0; i < listPassport.size(); i++) {
                    int tempPassportNumber = listPassport.get(i).getNumber();
                    if(passportNumber == tempPassportNumber){
                        id = listPassport.get(i).getId();
                    }
                }

                Passport changedPassport = service.getById(id).get();
                changedPassport.setHolderName(newHolderName);
                changedPassport.setYear(newYear);
                changedPassport.setCountry(newCountry);

                service.save(changedPassport);
                System.out.println("Паспорта после изменения:");
                listPassport = service.getAll();
                listPassport.stream()
                        .forEach(x -> System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", x.getId(), x.getSeries(), x.getNumber(), x.getHolderName(), x.getYear(), x.getCountry()));

            }

            else if(input.equals("5")){
                System.out.println("5. Delete.");

                System.out.println("Паспорта, имещиеся в таблице:");
                listPassport = service.getAll();
                listPassport.stream()
                        .forEach(x -> System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", x.getId(), x.getSeries(), x.getNumber(), x.getHolderName(), x.getYear(), x.getCountry()));

                System.out.println("Введите номер паспорта, который будет удалён:");
                String temp = scn.nextLine();
                int passportNumber = Integer.parseInt(temp);

                Passport deletedPassport = null;

                for (int i = 0; i < listPassport.size(); i++) {
                    int tempPassportNumber = listPassport.get(i).getNumber();
                    if(passportNumber == tempPassportNumber){
                        deletedPassport = listPassport.get(i);
                    }
                }

                try{
                    service.remove(deletedPassport);
                }catch (Exception ex){
                    ex.printStackTrace();
                    System.out.println("Паспорт не удалился:");
                    listPassport.stream()
                            .forEach(x -> System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", x.getId(), x.getSeries(), x.getNumber(), x.getHolderName(), x.getYear(), x.getCountry()));
                }

                System.out.println("Паспорта после удаления:");
                listPassport.stream()
                        .forEach(x -> System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", x.getId(), x.getSeries(), x.getNumber(), x.getHolderName(), x.getYear(), x.getCountry()));

            }

            else if(input.equals("6")){
//                System.out.println("Enter name");
//                String name = scn.nextLine();
//                System.out.println("Enter receip");
//                String receip = scn.nextLine();
//                final Parcel parcel = service.getParcelByNameAndReceip(name, receip);
//                System.out.println(parcel.getParcelName()+" "+parcel.getRecipientName());

            }else if(input.equals("7")){
                System.out.println("7. Exit.");

            }

            else{
                System.out.println("Invalid input.");

            }
        }

    }
}

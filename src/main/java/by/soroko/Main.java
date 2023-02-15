package by.soroko;

import by.soroko.model.Animal;
import by.soroko.model.Car;
import by.soroko.model.Flower;
import by.soroko.model.House;
import by.soroko.model.Person;
import by.soroko.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) throws IOException {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
        // task8();
//        task9();
//        task10();
//        task11();
//        task12();
//        task13();
        //    task14();
        task15();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(s -> s.getAge() >= 10 && s.getAge() <= 20).
                sorted(Comparator.comparingInt(Animal::getAge)).
                skip(14).limit(7).
                forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(animal -> "Japanese".equals(animal.getOrigin())).
                map(animal -> {
                    animal.setBread(animal.getBread().toUpperCase());
                    return animal;
                }).
                filter(animal -> "Female".equals(animal.getGender())).
                map(animal -> animal.getBread()).
                forEach(System.out::println);

    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(animal -> animal.getAge() > 30
                        && animal.getOrigin().charAt(0) == 'A').
                map(animal -> animal.getOrigin()).
                distinct().forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().
                filter(animal -> "Female".equals(animal.getGender())).
                count());

    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().
                filter(animal -> animal.getAge() >= 20
                        && animal.getAge() <= 30).
                anyMatch(animal -> "Hungarian".equals(animal.getOrigin())));
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().
                allMatch(animal -> "Male".equals(animal.getGender())
                        || "Female".equals(animal.getGender())));
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().
                noneMatch(animal -> "Oceania".equals(animal.getOrigin())));
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream().
                sorted(Comparator.comparing(Animal::getBread)).
                limit(100).
                max(Comparator.comparing(Animal::getAge)).
                ifPresent(animal -> System.out.println(animal.getAge()));
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream().map(Animal::getBread).
                map(String::toCharArray).
                min(Comparator.comparing(chars -> chars.length)).
                ifPresent(chars -> System.out.println(chars.length));
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().
                mapToInt(animal -> animal.getAge()).
                reduce((accumulator, element) -> accumulator + element).getAsInt());
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().
                filter(animal -> "Indonesian".equals(animal.getOrigin())).
                mapToInt(animal -> animal.getAge()).
                average().getAsDouble());
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream().filter(person -> "Male".equals(person.getGender())).
                filter(person -> person.getDateOfBirth().isBefore(LocalDate.now().minusYears(18))
                        && person.getDateOfBirth().isAfter(LocalDate.now().minusYears(27))).
                sorted(Comparator.comparing(Person::getRecruitmentGroup)).
                limit(200).
                forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        houses.stream().flatMap(house -> house.getPersonList().stream().
                        map(person -> {
                            int key = 0;
                            if ("Hospital".equals(house.getBuildingType())) {
                                key = 1;
                            } else if (person.getDateOfBirth().isAfter(LocalDate.now().minusYears(18))
                                    || (person.getDateOfBirth().isBefore(LocalDate.now().minusYears(63))
                                    && "Male".equals(person.getGender()))
                                    || (person.getDateOfBirth().isBefore(LocalDate.now().minusYears(58))
                                    && "Female".equals(person.getGender()))) {
                                key = 2;
                            } else {
                                key = 3;
                            }
                            return Map.entry(key, person);
                        })).
                sorted(Map.Entry.comparingByKey()).
                limit(500).
                map(Map.Entry::getValue).
                forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        System.out.println(cars.stream().map(car -> {
                    int key = 0;
                    if ("Jaguar".equals(car.getCarMake()) || "White".equals(car.getColor())) {
                        key = 1;
                    } else if (car.getMass() < 1500
                            && ("BMW".equals(car.getCarMake())
                            || "Lexus".equals(car.getCarMake())
                            || "Chrysler".equals(car.getCarMake())
                            || "Toyota".equals(car.getCarMake()))) {
                        key = 2;
                    } else if (("Black".equals(car.getColor())
                            && car.getMass() > 4000)
                            || "GMC".equals(car.getCarMake())
                            || "Dodge".equals(car.getCarMake())) {
                        key = 3;
                    } else if (car.getReleaseYear() < 1982
                            || "Civic".equals(car.getCarMake())
                            || "Cherokee".equals(car.getCarMake())) {
                        key = 4;
                    } else if (!"Yellow".equals(car.getColor())
                            && !"Red".equals(car.getColor())
                            && !"Green".equals(car.getColor())
                            && !"Blue".equals(car.getColor())
                            || car.getPrice() > 40000) {
                        key = 5;
                    } else if (car.getVin().contains("59")) {
                        key = 6;
                    } else {
                        key = 7;
                    }
                    return Map.entry(key, car);
                }).
                filter(entry -> entry.getKey().compareTo(7) != 0).
                collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, toList()))).
                values().stream()
                .map(carsList -> carsList.stream()
                        .mapToDouble(car -> car.getMass())
                        .sum() * 0.00714)
                .peek(System.out::println)
                .mapToDouble(money -> money)
                .sum());
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        System.out.println(flowers.stream().
                sorted(Comparator.comparing(Flower::getOrigin).reversed().
                        thenComparing(Flower::getPrice).
                        thenComparing(Flower::getWaterConsumptionPerDay).reversed()).
                filter(fl -> fl.getCommonName().charAt(0) >= 'C'
                        && fl.getCommonName().charAt(0) <= 'S').
                filter(Flower::isShadePreferred)
                .filter(f -> f.getFlowerVaseMaterial().contains("Glass")
                        || f.getFlowerVaseMaterial().contains("Aluminum")
                        || f.getFlowerVaseMaterial().contains("Steel")).
                mapToDouble(f -> f.getPrice() +
                        f.getWaterConsumptionPerDay() * 365 * 5 * 1.39 / 1000).
                sum());
    }
}
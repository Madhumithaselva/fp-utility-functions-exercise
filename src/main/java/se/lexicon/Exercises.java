package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;
import java.lang.String;
import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.Formatter.*;
import java.time.Period;
import java.util.function.*;
import java.util.Comparator;


public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here

        List<Person> result = new ArrayList<>();
        Predicate<Person> findErik= (person)->person.getFirstName().equals("Erik");
        result=storage.findMany(findErik);
        result.forEach(p->System.out.println(p));

        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here

        List<Person> result = new ArrayList<>();
        Predicate<Person> findFemale = (person)->person.getGender().equals(Gender.FEMALE);
        result=storage.findMany(findFemale);
        result.forEach(p->System.out.println(p));

        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here

        List<Person> result = new ArrayList<>();
        LocalDate birthDate = LocalDate.of(20003, 1, 1);
        Predicate<Person> bornAfter2000 = (person)->person.getBirthDate().isAfter(birthDate) || person.getBirthDate().equals(birthDate);
        result=storage.findMany(bornAfter2000);
        result.forEach(p->System.out.println(p));

        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here

        Person result;
        Predicate<Person> filterById123 = (person)->person.getId()==123;
        result = storage.findOne(filterById123);
        if (result!=null){
            System.out.println("Person with id 123 found \n"+ result);
        }
        else {
            System.out.println("Person with id 123 not found");
        }

        System.out.println("----------------------");
    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> filterById456 = (person)->person.getId()==456;
        Function<Person,String> personToString = person->"Name: "+ person.getFirstName()+
                                                            " "+ person.getLastName()+
                                                            " born "+person.getBirthDate();
        String result = storage.findOneAndMapToString(filterById456,personToString);

        if(result!=null){
            System.out.println(result);
        }
        else {
            System.out.println("No matching person found");
        }

        System.out.println("----------------------");
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here

        List<Person> result = new ArrayList<>();
        Predicate<Person> malePersonList = (person)->person.getGender().equals(Gender.MALE) && person.getFirstName().startsWith("E");
        Function<Person,String> personToString = person->"Name: "+ person.getFirstName()+
                                                          " "+ person.getLastName()+
                                                          " Gender "+ person.getGender()+
                                                          " Birthdate: "+person.getBirthDate();
        List<String> resultList = storage.findManyAndMapEachToString(malePersonList,personToString);

        if(resultList!=null){
            for (String str : resultList)
             System.out.println(str);
        }
        else {
            System.out.println("No matching person found");
        }
        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        List<Person> result = new ArrayList<>();
        Predicate<Person> filterBelowAge10 = (person)->Period.between(person.getBirthDate(),LocalDate.now()).getYears()<10;
        Function<Person,String> personToString = person-> person.getFirstName()+
                " "+ person.getLastName() + " "+
                (LocalDate.now().getYear() - person.getBirthDate().getYear())+
                " years";
        List<String> resultList = storage.findManyAndMapEachToString(filterBelowAge10,personToString);

        if(resultList!=null){
            for (String str : resultList)
                System.out.println(str);
        }
        else {
            System.out.println("No matching person found");
        }

        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);

        //Write your code here
        List<Person> result = new ArrayList<>();
        Predicate<Person> findUlf= (person)->person.getFirstName().equals("Ulf");
        Consumer<Person> printPersonInfo = person ->
                System.out.println("Person: " + person.getFirstName() + ", BirthDate" + person.getBirthDate());
        storage.findAndDo(findUlf,printPersonInfo);
        //result.forEach(p->System.out.println(p));

        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here

        List<Person> result = new ArrayList<>();
        Predicate<Person> findLastNameContainsFirstName= (person)->person.getLastName().contains(person.getFirstName());
        Consumer<Person> printPersonInfo = person ->
                System.out.println("Person: " + person.getFirstName() + " " + person.getLastName());
        storage.findAndDo(findLastNameContainsFirstName,printPersonInfo);
        //result.forEach(p->System.out.println(p));

        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here

        List<Person> result = new ArrayList<>();
        Predicate<Person> findPalindromeFirstName= person->person.getFirstName().equalsIgnoreCase(
                                                   new StringBuilder(person.getFirstName()).reverse().toString());
        Consumer<Person> printPersonInfo = person ->
                System.out.println("Persons have Palindrome First name are:  " + person.getFirstName() + " " + person.getLastName());
        storage.findAndDo(findPalindromeFirstName,printPersonInfo);

        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here

        List<Person> nameStartWithA = new ArrayList<>();
        nameStartWithA= storage.findAndSort(Comparator.comparing(person->person.getBirthDate()));
        System.out.println("Sorted by birthdate for persons with firstName starting with 'A'");
        nameStartWithA.forEach(p->System.out.println(p));

        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> bornBefore1950Predicate = person -> person.getBirthDate().getYear() < 1950;
        Comparator<Person> birthDateComparator = Comparator.comparing(person -> person.getBirthDate());
        List<Person> findBornBefore1950 = storage.findAndSort(bornBefore1950Predicate,birthDateComparator);
        System.out.println("Person born before 1950 in reverse order");
        findBornBefore1950.forEach(p->System.out.println(p));

        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here

        List<Person> sortWithDate = new ArrayList<>();

        Comparator<Person> lastNameFirstNameComparator = Comparator
                .comparing((Person person) -> person.getLastName())
                .thenComparing((Person person) -> person.getFirstName())
                .thenComparing((Person person) -> person.getBirthDate());

        sortWithDate = storage.findAndSort(lastNameFirstNameComparator);
        sortWithDate.forEach(p->System.out.println(p));

        System.out.println("----------------------");
    }
}

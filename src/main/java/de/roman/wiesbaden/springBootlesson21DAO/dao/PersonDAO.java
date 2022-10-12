package de.roman.wiesbaden.springBootlesson21DAO.dao;

import de.roman.wiesbaden.springBootlesson21DAO.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

//Он будет общаться со списком и будет извлекать людей из списка, находить какого-то
//конкретного человека по id из списка
@RequestMapping("dao/people")
@Component
public class PersonDAO {
    // список параметризован классом Person
     private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Bob"));
        people.add(new Person(++PEOPLE_COUNT, "Mike"));
        people.add(new Person(++PEOPLE_COUNT,"Katy"));

    }

    // Список из объектов класса Person
    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        // Мы находим человека с этим id и возвращаем его, если этого человека нет, то возвращаем null
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    // все остальные методы по созданию и удалению будут лежать здесь
    // тут мы реализуем только чтение
}

package de.roman.wiesbaden.springBootlesson21DAO.controllers;

import de.roman.wiesbaden.springBootlesson21DAO.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("controllers/people")
@Component
// Person не компонент, на не нужно будет создавать Beanы
public class PeopleController {
// Мы должны внедрить Bean в наш контролллер. Для этого мы заводим поле PersonDAO
// Spring внедрит объект класса PersonDAO в наш контроллер
    // Лучше использовать внедрение через конструктор
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //Нам придется в моделе передавать объект, содержащий в себе людей
    // на представление, чтобы таймлиф его, список из людей отобразил

    // Теперь будем использовать наше DAO, чтобы получать людей
    // Для этого мы должны внедрить объект DAO в этот контроллер
    // Спринг автоматически внедрит автоматически PersonDAO в наш контроллер
//--------------------
    //Получим всех людей из DAO и положим их в модель и передадим на отображение этих людей в представление.
    // и с помощью таймлиф мы отобразим этих людей в представлении
    // тут под ключем people будет лежать список из людей
    // динамический массив ArrayList из объектов класса Person
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
// метод будет принимать id в самом запросе

//    Во время, когда мы запустим приложение, то можно сюда поместить любое число
//            и это число поместится в аргументы этого метода с помощью антотации
    // @PathVariable мы извлечем этот id из url из адреса и получим к нему доступ
    // внутри этого метода, используя анатацию @PathVariable и используем целое число
    // id
@GetMapping("/{id}")
public String show(@PathVariable("id") int id, Model model) {
    model.addAttribute("person", personDAO.show(id));
    return "people/show";
}
}

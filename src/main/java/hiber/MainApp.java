package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      //создаем несколько юзеров
      userService.add(new User("Lesly", "Lastname1", "user1@mail.ru", new Car("bmw" ,525)));
      userService.add(new User("Yan", "Lastname2", "user2@mail.ru", new Car("saab" ,2)));
      userService.add(new User("Petr", "Lastname3", "user3@mail.ru", new Car("lada" ,2107)));
      userService.add(new User("Ivan", "Lastname4", "user4@mail.ru", new Car("mercedez" ,600)));

      //пушим юзеров в лист
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      //берем юзера с указанным авто
      User user = userService.getUserByCar("mercedez", 600);
      System.out.println("User with a chosen car is " + user.getId());
      context.close();
   }
}

package com.elieldev.cursojava.config;

import com.elieldev.cursojava.entities.Category;
import com.elieldev.cursojava.entities.Order;
import com.elieldev.cursojava.entities.User;
import com.elieldev.cursojava.entities.enums.OrderStatus;
import com.elieldev.cursojava.repositories.CategoryRepositorys;
import com.elieldev.cursojava.repositories.OrderRepositorys;
import com.elieldev.cursojava.repositories.UserRepositorys;
import java.time.Instant;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration //informa para o spring que essa classe é de configuração
@Profile("test") //informa que essas configurações so serão utilizadas no perfil de teste
public class TestConfig implements CommandLineRunner {

  @Autowired //resolve o sistema de dependencia e associar a instancia a dependencia userRepositorys
  private UserRepositorys userRepositorys;

  @Autowired
  private OrderRepositorys orderRepositorys;

  @Autowired
  private CategoryRepositorys categoryRepositorys;

  @Override
  public void run(String... args) throws Exception { //metodo que instancia os dados no banco assim que inicia a aplicação
    //categoria

    Category cat1 = new Category(null, "Electronics");
    Category cat2 = new Category(null, "Books");
    Category cat3 = new Category(null, "Computers");

    categoryRepositorys.saveAll(Arrays.asList(cat1, cat2, cat3));

    // usuario

    User u1 = new User(
      null,
      "Maria Brown",
      "maria@gmail.com",
      "988888888",
      "123456"
    );

    User u2 = new User(
      null,
      "Alex Green",
      "alex@gmail.com",
      "977777777",
      "123456"
    );

    //pedido

    Order o1 = new Order(
      null,
      Instant.parse("2019-06-20T19:53:07Z"),
      OrderStatus.PAID,
      u1
    ); //formato de data ISO 8601
    Order o2 = new Order(
      null,
      Instant.parse("2019-07-21T03:42:10Z"),
      OrderStatus.DELIVERED,
      u2
    ); //passa o u1 e u2 em cada um para acossiar o pedido ao usuario em questao
    Order o3 = new Order(
      null,
      Instant.parse("2019-07-22T15:21:22Z"),
      OrderStatus.WAITING_PAYMENT,
      u1
    ); //OrderStatus é o enum do estatus do pedido de cada usuario

    userRepositorys.saveAll(Arrays.asList(u1, u2)); //metodo que salta uma lista no banco chamando o userRepositorys que é o objeto que acessa os dados
    orderRepositorys.saveAll(Arrays.asList(o1, o2, o3)); //salva no banco a lista de pedidos
  }
}

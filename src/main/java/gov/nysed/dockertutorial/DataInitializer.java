package gov.nysed.dockertutorial;

import gov.nysed.dockertutorial.model.Item;
import gov.nysed.dockertutorial.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  private final ItemRepository itemRepository;

  public DataInitializer(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if (itemRepository.count() == 0) {
      itemRepository.save(new Item("Alice Avery"));
      itemRepository.save(new Item("Bill Billingsworth"));
      itemRepository.save(new Item("Charlie Chaplin"));
      itemRepository.save(new Item("David Davidson"));
      itemRepository.save(new Item("Emma Emerson"));
      itemRepository.save(new Item("Frank Franklin"));
      itemRepository.save(new Item("Grace Garrison"));
      itemRepository.save(new Item("Henry Henderson"));
      itemRepository.save(new Item("Ian Iverson"));
      itemRepository.save(new Item("Jack Jackson"));
      itemRepository.save(new Item("Kevin Kevinson"));
      itemRepository.save(new Item("Liam Liamson"));
      itemRepository.save(new Item("Michael Michaelson"));
      itemRepository.save(new Item("Nathan Nathanson"));
      itemRepository.save(new Item("Oliver Oliverson"));
      itemRepository.save(new Item("Peter Peterson"));
      itemRepository.save(new Item("Quincy Quincyson"));
      itemRepository.save(new Item("Robert Robertson"));
      itemRepository.save(new Item("Samuel Samuelson"));
      itemRepository.save(new Item("Thomas Thomason"));
      itemRepository.save(new Item("Upton Uptonon"));
      itemRepository.save(new Item("Victor Victorson"));
      itemRepository.save(new Item("William Williamson"));
      itemRepository.save(new Item("Xavier Xavierson"));
      itemRepository.save(new Item("Yusuf Yusufson"));
      itemRepository.save(new Item("Zachary Zacharison"));
      System.out.println("H2 Database seeded successfully!");
    }
  }
}
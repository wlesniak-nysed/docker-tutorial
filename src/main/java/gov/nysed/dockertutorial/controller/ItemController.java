package gov.nysed.dockertutorial.controller;

import gov.nysed.dockertutorial.model.Item;
import gov.nysed.dockertutorial.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/items")
public class ItemController {

  private final ItemRepository itemRepository;

  @Autowired
  public ItemController(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @GetMapping("/hello")
  String sayHello() {
    return "Hello World";
  }

  // POST endpoint: Saves data to H2 database
  @PostMapping("/create")
  Item createItem(@RequestBody Item item) {
    return itemRepository.save(item);
  }

  // GET endpoint: Retrieves all saved items
  @GetMapping("/all")
  List<Item> getAllItems() {
    return itemRepository.findAll();
  }

}

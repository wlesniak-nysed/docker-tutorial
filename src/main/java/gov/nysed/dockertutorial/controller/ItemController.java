package gov.nysed.dockertutorial.controller;

import gov.nysed.dockertutorial.dto.ItemDto;
import gov.nysed.dockertutorial.model.Item;
import gov.nysed.dockertutorial.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/items")
public class ItemController {

  private final ItemService itemService;

  @Autowired
  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/hello")
  String sayHello() {
    return "Hello World";
  }

  // GET endpoint: Retrieves all saved items
  @GetMapping("/all")
  public List<ItemDto> getAllItems() {
    return itemService.getAllItems();
  }

  // POST endpoint: Saves data to H2 database
  @PostMapping("/create")
  public ItemDto createItem(@RequestBody ItemDto itemDto) {
    Item newItem = itemService.createItem(itemDto);
    return new ItemDto(newItem.getId(), newItem.getName());
  }

  // DELETE endpoint: deletes data from H2 database
  @DeleteMapping("/delete")
  public ResponseEntity<Object> deleteItem(@RequestBody ItemDto itemDto) {
    return itemService.deleteItem(itemDto);
  }

}

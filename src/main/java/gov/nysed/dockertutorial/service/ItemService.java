package gov.nysed.dockertutorial.service;

import gov.nysed.dockertutorial.dto.ItemDto;
import gov.nysed.dockertutorial.model.Item;
import gov.nysed.dockertutorial.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

  ItemRepository itemRepository;

  @Autowired
  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<ItemDto> getAllItems() {
    return itemRepository.findAll().stream().map(item -> new ItemDto(item.getId(), item.getName()))
                         .collect(Collectors.toList());
  }

  @Transactional
  public Item createItem(ItemDto itemDto) {
    Item item = new Item(itemDto.getName());
    return itemRepository.save(item);
  }

  @Transactional
  public ResponseEntity<Object> deleteItem(ItemDto itemDto) {
    // deleteById doesn't return a count when the entity is not found, but it will throw an
    // exception; so catch it and return an appropriate response
    try {
      itemRepository.deleteById(itemDto.getId());
      return ResponseEntity.ok().build();
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.notFound().build();
    }
  }
}

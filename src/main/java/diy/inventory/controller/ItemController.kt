package diy.inventory.controller

import diy.inventory.data.dto.Item
import diy.inventory.service.ItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/items")
class ItemController(private val itemService: ItemService) {
    @GetMapping
    fun getAllItems(): List<Item> = itemService.getAllItems()

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: Long): ResponseEntity<Item> {
        val item = itemService.getItemById(id)
        return if (item != null) {
            ResponseEntity.ok(item)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createItem(@RequestBody item: Item): Item = itemService.createItem(item)

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Long): ResponseEntity<Void> {
        itemService.deleteItem(id)
        return ResponseEntity.ok().build()
    }
}

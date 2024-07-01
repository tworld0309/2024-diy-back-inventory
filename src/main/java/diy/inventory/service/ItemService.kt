package diy.inventory.service

import diy.inventory.data.dto.Item
import org.springframework.stereotype.Service


@Service
class ItemService {
    private val items = mutableListOf<Item>()

    fun getAllItems(): List<Item> = items

    fun getItemById(id: Long): Item? =
        items.find { it.id == id }

    fun createItem(item: Item): Item {
        items.add(item)
        return item
    }

    fun deleteItem(id: Long) {
        items.removeIf { it.id == id }
    }
}

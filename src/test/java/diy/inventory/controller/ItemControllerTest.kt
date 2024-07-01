package diy.inventory.controller

import diy.inventory.data.dto.Item
import diy.inventory.service.ItemService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ItemController::class)
class ItemControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var itemService: ItemService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getAllItems() {
        val item1 = Item(1L, "Item1")
        val item2 = Item(2L, "Item2")
        given(itemService.getAllItems()).willReturn(listOf(item1, item2))

        mockMvc.perform(get("/api/items"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$[0].name").value("Item1"))
            .andExpect(jsonPath("\$[1].name").value("Item2"))
    }

    @Test
    fun getItemById() {
        val item = Item(1L, "Item1")
        given(itemService.getItemById(1L)).willReturn(item)

        mockMvc.perform(get("/api/items/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$.name").value("Item1"))
    }

    @Test
    fun createItem() {
        val item = Item(1L, "NewItem")
        given(itemService.createItem(item)).willReturn(item)

        mockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":1,\"name\":\"NewItem\"}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$.name").value("NewItem"))
    }

    @Test
    fun deleteItem() {
        mockMvc.perform(delete("/api/items/1"))
            .andExpect(status().isOk)
    }
}

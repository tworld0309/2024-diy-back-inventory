package diy.user.controller

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@OpenAPIDefinition(info = Info(title = "물품 처리요청 API", description = "물품 처리요청 API", version = "v1"))
@RestController
@RequestMapping(value = ["health"])
@Slf4j
@RequiredArgsConstructor
@Validated
class HealthController() {
    @Operation(summary = "물품등록 요청", description = "물품 등록을 진행할 수 있다.", tags = ["addItem"])
    @RequestMapping(value = ["/status"], method = [RequestMethod.GET])
    fun info(@Value("\${server.port}") port: String): String? {
        return "Inventory 서비스의 기본 동작 Port: {$port}"
    }
}
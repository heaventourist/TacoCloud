package com.lucifov.taco_cloud.controller

import com.lucifov.taco_cloud.data.OrderRepository
import com.lucifov.taco_cloud.models.Order
import com.lucifov.taco_cloud.utils.LoggerDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
class OrderController(@Autowired val orderRepo: OrderRepository) {
    companion object{
        private val log by LoggerDelegate<Companion>()
    }
    @GetMapping("/current")
    fun orderFrom(model: Model): String{
        log.debug(model.asMap().keys.toString())
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid @ModelAttribute("order") order: Order, bindingResult: BindingResult, sessionStatus: SessionStatus): String{
        if(bindingResult.hasErrors()){
            log.debug("ProcessOrder has error")
            return "orderForm"
        }
        val saved = orderRepo.save(order)
        log.debug("Order submitted: $saved")
        sessionStatus.setComplete()
        return "redirect:/"
    }
}
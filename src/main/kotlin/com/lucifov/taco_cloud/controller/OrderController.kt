package com.lucifov.taco_cloud.controller

import com.lucifov.taco_cloud.models.Order
import com.lucifov.taco_cloud.utils.LoggerDelegate
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/orders")
class OrderController {
    companion object{
        private val logger by LoggerDelegate<Companion>()
    }
    @GetMapping("/current")
    fun orderFrom(model: Model): String{
        model.addAttribute("order", Order())
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Validated order: Order, bindingResult: BindingResult): String{
        if(bindingResult.hasErrors()){
            logger.debug("ProcessOrder has error")
            return "orderForm"
        }
        logger.info("Order submitted: $order")
        return "redirect:/"
    }
}
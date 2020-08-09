package com.lucifov.taco_cloud.controller

import com.lucifov.taco_cloud.data.IngredientRepository
import com.lucifov.taco_cloud.data.TacoRepository
import com.lucifov.taco_cloud.models.Design
import com.lucifov.taco_cloud.models.Ingredient
import com.lucifov.taco_cloud.models.Order
import com.lucifov.taco_cloud.models.Taco
import com.lucifov.taco_cloud.utils.LoggerDelegate
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
class DesignTacoController(@Autowired val ingredientRepository: IngredientRepository,
                           @Autowired val tacoRepository: TacoRepository) {

    companion object {
        private val log: Logger by LoggerDelegate<Companion>()
    }

    @ModelAttribute
    fun addAttributes(model: Model){
        model.addAttribute("order", Order())
        model.addAttribute("design", Design())
    }

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients = ingredientRepository.findAll().toList()
        Ingredient.Type.values().forEach { type ->
            model.addAttribute(type.toString().toLowerCase(), ingredients.filter { ingredient ->
                ingredient.type == type
            })
        }
        log.debug("Ingredients added to models")
    }

    @GetMapping
    fun showDesignForm(model: Model): String {
        log.debug("show design form")
        log.debug(model.asMap().toString())
        return "design"
    }

    @PostMapping
    fun processDesign(@Valid @ModelAttribute("design") design: Design, bindingResult: BindingResult, @ModelAttribute("order") order: Order): String {
        if (bindingResult.hasErrors()) {
            log.debug("ProcessDesign has errors")
            return "design"
        }
        log.debug("Processing design: $design")
        val saved: Taco = tacoRepository.save(design.toTaco())

        order.designs.add(saved)

        return "redirect:/orders/current"
    }

    private fun Design.toTaco(): Taco = Taco(name = name, ingredients = ingredients.map { ingredientRepository.findOne(it) })
}
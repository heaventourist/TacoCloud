package com.lucifov.taco_cloud.controller

import com.lucifov.taco_cloud.data.IngredientRepository
import com.lucifov.taco_cloud.models.Ingredient
import com.lucifov.taco_cloud.models.Taco
import com.lucifov.taco_cloud.utils.LoggerDelegate
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/design")
class DesignTacoController(@Autowired val ingredientRepository: IngredientRepository) {

    companion object {
        private val logger: Logger by LoggerDelegate<Companion>()
    }

    @ModelAttribute
    fun addIngredientsToModel(model: Model){
        val ingredients = ingredientRepository.findAll().toList()
        Ingredient.Type.values().forEach { type ->
            model.addAttribute(type.toString().toLowerCase(), ingredients.filter { ingredient ->
                ingredient.type == type
            })
        }
    }

    @GetMapping
    fun showDesignForm(model: Model): String {
        logger.debug("show design form")
        model.addAttribute("design", Taco())
        return "design"
    }

    @PostMapping
    fun processDesign(@Valid @ModelAttribute("design") design: Taco, bindingResult: BindingResult): String{
        if(bindingResult.hasErrors()){
            logger.debug("ProcessDesign has errors")
            return "design"
        }
        logger.debug("Processing design: $design")
        return "redirect:/orders/current"
    }
}
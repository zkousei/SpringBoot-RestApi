package com.example.demo.application.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.recource.DeleteResponse;
import com.example.demo.application.recource.GetRecipeResponse;
import com.example.demo.application.recource.PatchRecipeBody;
import com.example.demo.application.recource.PatchRecipeModel;
import com.example.demo.application.recource.PatchRecipeResponce;
import com.example.demo.application.recource.PostRecipeBody;
import com.example.demo.application.recource.PostRecipeErrorResponse;
import com.example.demo.application.recource.PostRecipeResponce;
import com.example.demo.application.recource.RecipeModel;
import com.example.demo.domain.object.Recipe;
import com.example.demo.domain.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@PostMapping("/recipes")
	@ResponseBody
	public ResponseEntity<Object> postRecipe(@RequestBody @Validated PostRecipeBody request, BindingResult result) {

		// バリデーションエラーの場合エラーレスポンス
		if (result.hasErrors()) {
			log.error(result.toString());
			return new ResponseEntity<>(new PostRecipeErrorResponse("Recipe creation failed!", result.toString()),new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());

		Integer id = recipeService.createRecipe(request.getTitle(), request.getMaking_time(), request.getServes(), request.getIngredients(), request.getCost(), now);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		RecipeModel recipe = new RecipeModel(String.valueOf(id), request.getTitle(), request.getMaking_time(), request.getServes(), request.getIngredients(), String.valueOf(request.getCost()), sdf.format(now), sdf.format(now));
		List<RecipeModel> recipeList = new ArrayList<RecipeModel>();
		recipeList.add(recipe);

		return new ResponseEntity<>(new PostRecipeResponce("Recipe Successfully Created!",recipeList), new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/recipes")
	@ResponseBody
	public ResponseEntity<Object> getRecipe(){

		List<Recipe> allRecipe = recipeService.getAllRecipes();

		return new ResponseEntity<>(new GetRecipeResponse(allRecipe), new HttpHeaders(), HttpStatus.OK);


	}

	@GetMapping("/recipes/{id}")
	@ResponseBody
	public ResponseEntity<Object> getRecipeById(@PathVariable("id") Integer id){

		List<Recipe> recipe = recipeService.getRecipeById(id);

		return new ResponseEntity<>(new GetRecipeResponse(recipe), new HttpHeaders(), HttpStatus.OK);

	}

	@PatchMapping("/recipes/{id}")
	@ResponseBody
	public ResponseEntity<Object> patchRecipeById(@PathVariable("id") Integer id, @RequestBody @Validated PatchRecipeBody request, BindingResult result) {

		Timestamp now = new Timestamp(System.currentTimeMillis());
		recipeService.updateRecipeById(id, request.getTitle(), request.getMaking_time(), request.getServes(), request.getIngredients(), request.getCost(), now);

		PatchRecipeModel recipe = new PatchRecipeModel(request.getTitle(), request.getMaking_time(), request.getServes(), request.getIngredients(), request.getCost());


		List<PatchRecipeModel> listRecipe = new ArrayList<PatchRecipeModel>();
		listRecipe.add(recipe);

		return new ResponseEntity<>(new PatchRecipeResponce("Recipe Successfully Updated!",listRecipe), new HttpHeaders(), HttpStatus.OK);

	}

	@DeleteMapping("/recipes/{id}")
	@ResponseBody
	public DeleteResponse deleteRecipeById(@PathVariable("id") Integer id) {

		int deleteCount = recipeService.deleteRecipeById(id);

		if (deleteCount == 0) {
			return new DeleteResponse("No Recipe Found");
		}
		else {
			return new DeleteResponse("Recipe Successfully Removed!");
		}

	}


}

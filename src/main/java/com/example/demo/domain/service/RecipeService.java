package com.example.demo.domain.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.object.Recipe;
import com.example.demo.domain.repository.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	public Integer createRecipe(String title, String makingTime, String serves, String ingredients, int cost, Timestamp now) {



		Integer id = recipeRepository.insertRecipe(title, makingTime, serves, ingredients, cost, now);

		return id;

	}


	public List<Recipe> getAllRecipes() {

		List<Map<String,Object>> allRecipesList = recipeRepository.selectAllRecipes();

		List<Recipe> allRecipes = new ArrayList<Recipe>();

		for (Map<String, Object> mapRecipe: allRecipesList) {
			Recipe recipe = new Recipe();
			recipe.setId((Integer)mapRecipe.get("id"));
			recipe.setTitle((String)mapRecipe.get("title"));
			recipe.setMaking_time((String)mapRecipe.get("making_time"));
			recipe.setServes((String)mapRecipe.get("serves"));
			recipe.setIngredients((String)mapRecipe.get("ingredients"));
			recipe.setCost((Integer)mapRecipe.get("cost"));

			allRecipes.add(recipe);
		}

		return allRecipes;

	}

	public List<Recipe> getRecipeById(Integer id) {
		// TODO: 0件の場合のエラーハンドリング
		Map<String,Object> mapRecipe = recipeRepository.selectRecipeById(id);
		Recipe recipe = new Recipe();
		recipe.setId((Integer)mapRecipe.get("id"));
		recipe.setTitle((String)mapRecipe.get("title"));
		recipe.setMaking_time((String)mapRecipe.get("making_time"));
		recipe.setServes((String)mapRecipe.get("serves"));
		recipe.setIngredients((String)mapRecipe.get("ingredients"));
		recipe.setCost((Integer)mapRecipe.get("cost"));

		List<Recipe> listRecipe = new ArrayList<Recipe>();
		listRecipe.add(recipe);

		return listRecipe;


	}

	public void updateRecipeById(Integer id, String title, String makingTime, String serves, String ingredients, Integer cost, Timestamp now)  {
		recipeRepository.updateRecipeById(id, title, makingTime, serves, ingredients, cost, now);

	}

	public int deleteRecipeById(Integer id) {
		int count = recipeRepository.deleteRecipeById(id);

		return count;
	}


}



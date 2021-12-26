package com.example.demo.domain.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer insertRecipe(String title, String makingTime, String serves, String ingredients, Integer cost, Timestamp now) {

		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ")
			.append("recipes (title, making_time, serves, ingredients, cost, created_at, updated_at) ")
			.append("VALUES (?, ?, ?, ?, ?, ?, ?) ")
			.append("RETURNING id");


		Integer id = jdbcTemplate.queryForObject(query.toString(), Integer.class ,title, makingTime, serves, ingredients, cost, now, now);

		return id;
	}


	public List<Map<String,Object>> selectAllRecipes() {

		StringBuilder query = new StringBuilder();
		query.append("SELECT ")
			.append("id, title, making_time, serves, ingredients, cost ")
			.append("FROM recipes");

		List<Map<String,Object>> allRecipes = jdbcTemplate.queryForList(query.toString());

		return allRecipes;


	}

	public Map<String,Object> selectRecipeById(Integer id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ")
		.append("id, title, making_time, serves, ingredients, cost ")
		.append("FROM recipes ")
		.append("WHERE id = ?");

		Map<String,Object> recipe = jdbcTemplate.queryForMap(query.toString(), id);

		return recipe;

	}

	public void updateRecipeById(Integer id, String title, String makingTime, String serves, String ingredients, Integer cost, Timestamp now) {

		StringBuilder query = new StringBuilder();
		query.append("UPDATE recipes ")
		    .append("SET title = ?, making_time = ?, serves = ?, ingredients = ?, cost = ?, updated_at = ? ")
		    .append("WHERE id = ?");

		jdbcTemplate.update(query.toString(), title, makingTime, serves, ingredients, cost, now, id);
	}

	public int deleteRecipeById(Integer id) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM recipes ")
			.append("WHERE id = ?");

		int count = jdbcTemplate.update(query.toString(), id);

		return count;


	}

}

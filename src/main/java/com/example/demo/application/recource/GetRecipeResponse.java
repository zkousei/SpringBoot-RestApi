package com.example.demo.application.recource;

import java.util.List;

import com.example.demo.domain.object.Recipe;

import lombok.Value;

@Value
public class GetRecipeResponse {

	private List<Recipe> recipes;

}

package com.example.demo.application.recource;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class PostRecipeResponce {
	@JsonProperty("message")
	private String message;
	@JsonProperty("recipe")
	private List<RecipeModel> recipe;

}

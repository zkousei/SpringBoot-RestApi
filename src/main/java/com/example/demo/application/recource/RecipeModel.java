package com.example.demo.application.recource;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class RecipeModel {
	@JsonProperty("id")
	private String id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("making_time")
	private String makingTime;
	@JsonProperty("serves")
	private String serves;
	@JsonProperty("ingredients")
	private String ingredients;
	@JsonProperty("cost")
	private String cost;
	@JsonProperty("created_at")
	private String createdAt;
	@JsonProperty("updated_at")
	private String updatedAt;
}

package com.example.demo.application.recource;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class PatchRecipeModel {
	@JsonProperty("title")
	private String title;
	@JsonProperty("making_time")
	private String makingTime;
	@JsonProperty("serves")
	private String serves;
	@JsonProperty("ingredients")
	private String ingredients;
	@JsonProperty("cost")
	private Integer cost;
}

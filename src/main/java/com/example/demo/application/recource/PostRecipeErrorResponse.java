package com.example.demo.application.recource;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class PostRecipeErrorResponse {
	@JsonProperty("message")
	private String message;

	@JsonProperty("required")
	private String required;

}

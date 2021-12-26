package com.example.demo.application.recource;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class DeleteResponse {
	@JsonProperty("message")
	private String message;
}

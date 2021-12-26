package com.example.demo.application.recource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PatchRecipeBody {
	@NotBlank
	@Size(max = 100)
	private String title;

	@NotBlank
	@Size(max = 100)
	private String making_time;

	@NotBlank
	@Size(max = 100)
	private String serves;

	@NotBlank
	@Size(max = 300)
	private String ingredients;

	@NotNull
	private Integer cost;


}

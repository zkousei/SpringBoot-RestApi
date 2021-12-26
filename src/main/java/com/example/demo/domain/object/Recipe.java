package com.example.demo.domain.object;

import lombok.Data;

@Data
public class Recipe {
	private Integer id;
	private String title;
	private String making_time;
	private String serves;
	private String ingredients;
	private Integer cost;
}

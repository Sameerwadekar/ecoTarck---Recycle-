package com.learn.ecostrack.dto;

import java.time.LocalDateTime;


import com.learn.ecostrack.entities.User;
import com.learn.ecostrack.entities.WorkShop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollementsDto {
	private User user;
	private WorkShop workShop;
	private LocalDateTime enrollAt;
}

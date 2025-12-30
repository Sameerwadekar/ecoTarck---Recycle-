package com.learn.ecostrack.dto;


import com.learn.ecostrack.entities.User;
import com.learn.ecostrack.enums.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecycleRequestDto {
	private Integer id;
	private String itemName;
	private String itemType;
	private String itemImage;
	private String reason;
	private int quantity;
	private User user;
	private RequestStatus requestStatus;
}

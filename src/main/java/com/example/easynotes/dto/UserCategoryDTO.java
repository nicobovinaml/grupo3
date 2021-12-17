package com.example.easynotes.dto;

import com.example.easynotes.enums.UserCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCategoryDTO {

    @NotNull
    private Long id;

    @NotNull
    private UserCategoryEnum category;
}

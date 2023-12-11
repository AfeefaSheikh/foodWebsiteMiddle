package com.example.foodWebSiteDubai.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressListDto {
    List<AddressDto> addressDtoList;
    Long userId;
}

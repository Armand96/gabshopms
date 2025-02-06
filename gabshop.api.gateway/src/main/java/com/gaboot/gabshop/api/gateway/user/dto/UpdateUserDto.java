package com.gaboot.gabshop.api.gateway.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class UpdateUserDto extends CreateUserDto{
    private long id;
}

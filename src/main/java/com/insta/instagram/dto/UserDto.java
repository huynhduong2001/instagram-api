package com.insta.instagram.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String name;
    private String userImage;

}

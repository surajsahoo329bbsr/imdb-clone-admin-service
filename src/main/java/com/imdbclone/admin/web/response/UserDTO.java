package com.imdbclone.admin.web.response;

import com.imdbclone.admin.entity.Milestone;
import lombok.*;

@Setter
@Getter
public class UserDTO extends Milestone {

    private Long id;

    private String username;

    private String email;

}

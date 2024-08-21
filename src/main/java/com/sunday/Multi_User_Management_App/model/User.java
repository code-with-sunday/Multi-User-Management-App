package com.sunday.Multi_User_Management_App.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sunday.Multi_User_Management_App.enums.ROLE;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Table(name = "users")
@JsonPropertyOrder({"id", "username", "firstName", "middleName", "lastName", "email",
        "role", "createDate", "updateDate"})
public class User extends AuditBaseEntity{
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ROLE role;

}

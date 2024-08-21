package com.sunday.Multi_User_Management_App.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sunday.Multi_User_Management_App.enums.TagMark;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Table(name = "Task")
@JsonPropertyOrder({"id", "tagMark", "tasks"})
public class Tag extends AuditBaseEntity{

    private TagMark tagMark;

    @ManyToOne()
    private Task tasks ;
}

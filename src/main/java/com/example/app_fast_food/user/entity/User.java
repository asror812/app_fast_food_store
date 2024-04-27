package com.example.app_fast_food.user.entity;


import com.example.app_fast_food.check.entity.Check;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    @NonNull @NotBlank
    private String phoneNumber;

    @NonNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String password;

/*    @Enumerated(EnumType.STRING)
    private StatusEnum status;*/

    @OneToMany(mappedBy = "user")
    private List<Check> check;
}

package com.example.foodWebSiteDubai.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserRoleMapping")
public class UserRoleMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MapId")
    private Long mapId;
    @Column(name = "RoleId")
    private Long roleId;
}

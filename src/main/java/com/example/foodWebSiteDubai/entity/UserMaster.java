package com.example.foodWebSiteDubai.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
//import org.hibernate.annotations.GeneratedColumn;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserMaster")
public class UserMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long userId;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "EmailId")
    private String emailId;
    @Column(name = "Password")
    private String password;
    @Column(name = "Status")
    private String  status;
    @Column(name = "UserName")
    private String userName;

    @OneToMany
    @JoinColumn(name = "UserId" , referencedColumnName = "UserId")
    private List<AddressMaster> addressMasters;

    @OneToOne
    @JoinColumn(name = "UserId")
    private UserRole userRoleEntity;
}

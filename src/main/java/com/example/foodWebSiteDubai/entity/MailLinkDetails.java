package com.example.foodWebSiteDubai.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MailLinkDetails")
public class MailLinkDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailLinkId")
    private Long mailLinkId;
    @Column(name = "Uuid")
    private String uuid;
    @Column(name = "UserId")
    private Long userId;
    @Column(name = "LinkType")
    private String linkType;
}

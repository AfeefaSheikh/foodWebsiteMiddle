package com.example.foodWebSiteDubai.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "MailLinkType")
public class MailLinkType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailId;

    @Column(name = "linkUrl")
    private String linkUrl;

    @Column(name = "LinkType")
    private String linkType;


}

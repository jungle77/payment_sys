package com.jungle77.paymentsys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchand")
public class Merchand {

    @Id
    @NotNull
    @Column(name = "id")
    private String id;

    @NotNull
//    @ManyToOne(targetEntity = User.class)
//    @JoinColumn(name = "user_id")
    private String user_id;
    
    @NotNull
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private Boolean isActive;

}
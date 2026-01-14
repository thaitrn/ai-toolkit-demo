package com.vtrip.{servicename}.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity @Table(name="{table_name}")@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder public class{Entity}{

@Id @GeneratedValue(strategy=GenerationType.IDENTITY)private Long id;

// Add your fields here
// Example:
// @Column(nullable = false)
// private String name;

// @Column(unique = true, nullable = false)
// private String email;

// @Enumerated(EnumType.STRING)
// @Column(nullable = false)
// private Status status;

@CreationTimestamp @Column(name="created_at",nullable=false,updatable=false)private Instant createdAt;

@UpdateTimestamp @Column(name="updated_at")private Instant updatedAt;

@Column(name="created_by")private String createdBy;

@Column(name="updated_by")private String updatedBy;

@Version private Long version;}

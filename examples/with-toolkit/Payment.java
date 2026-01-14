package com.vtrip.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * ✅ WITH AI TOOLKIT - All 5 developers write EXACTLY this code
 * 
 * Enforced by:
 * - 01-core-architecture.mdc: Entity patterns
 * - templates/entity-template.java: Standard template
 * 
 * Features:
 * - ✅ Consistent audit fields (createdAt, updatedAt, createdBy, updatedBy)
 * - ✅ Optimistic locking with @Version
 * - ✅ Lombok annotations for boilerplate reduction
 * - ✅ Consistent column naming (snake_case)
 */
@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "transaction_id", unique = true, length = 100)
    private String transactionId;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    // ===== Audit Fields =====

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Version
    private Long version;
}

package com.asac6c.reddit.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false)
    LocalDateTime createdAt;
    //    Long createdBy;
    @Setter
    LocalDateTime updatedAt;
//    Long updatedBy;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
package com.peanutbutter.peanutbutter.base;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @CreatedDate
    @Column(nullable =  false)
    private LocalDate updatedAt;

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt(){
        return updatedAt;
    }

   

}

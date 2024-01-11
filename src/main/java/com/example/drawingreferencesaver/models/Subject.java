package com.example.drawingreferencesaver.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.example.drawingreferencesaver.models.Reference;

import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends AbstractPersistable<Long> {

    private String name;

    @ManyToMany
    private List<Reference> references;
}

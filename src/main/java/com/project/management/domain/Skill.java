package com.project.management.domain;

import javax.persistence.*;

@Table(name = "skills")
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skills_id")
    private int id;
    @Column(name = "skill_name")
    private String name;
    @Column(name = "skill_level")
    private String level;

    public Skill() {

    }
}

package com.project.management.domain;

import javax.persistence.*;
import java.util.List;

@Table(name = "developers")
@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private Integer id;

    @Column()
    private String name;
    @Column()
    private int salary;
    @Column()
    private String sex;
    @Column()
    private int age;


    @ManyToMany
    @JoinTable(
            name = "developers_projects",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public Developer(String name, int salary, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.salary = salary;
    }
    public Developer(){}



    @Override
    public String toString() {
        return "Customer name --" + name +
                "  salary-- " + salary +
                "  age -- " + age +
                "  sex --" + sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


}

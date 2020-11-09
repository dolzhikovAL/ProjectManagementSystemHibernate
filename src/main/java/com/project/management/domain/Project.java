package com.project.management.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Table(name = "projects")
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer id;

    @Column()
    private String name;

    @Column(name = "dead_line")
    private LocalDate deadLine;

    @ManyToMany
    @JoinTable(
            name = "developers_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Developer> developers;


    @ManyToMany
    @JoinTable(
            name = "companies_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<Company> companies;

    @ManyToMany
    @JoinTable(
            name = "customer_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    public Project() {
    }


/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return name.equals(project.getName());
    }

    @Override
    public int hashCode(){
        return Objects.hash(name);
    }*/

    public Project(String name, LocalDate deadLine) {
        this.name = name;
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "Customer name --" + name +
                " deadLine -- " + deadLine;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }
}

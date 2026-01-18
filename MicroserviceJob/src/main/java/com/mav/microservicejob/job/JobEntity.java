package com.mav.microservicejob.job;

import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "jobs")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "minSalary", nullable = false)
    @NumberFormat(pattern = "$###,###,###.00", style = NumberFormat.Style.CURRENCY)
    private Double minSalary;

    @Column(name = "maxSalary", nullable = false)
    @NumberFormat(pattern = "$###,###,###.00", style = NumberFormat.Style.CURRENCY)
    private Double maxSalary;

    @Column(name = "location", nullable = false)
    private String location;
    private Long companyId;

    public JobEntity(Long id, String title, String description, Double minSalary, Double maxSalary, String location, Long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.companyId = companyId;
    }

    public JobEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
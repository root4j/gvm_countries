package com.oracle.graal.demo.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author rjay
 */
@Entity
@Table(name = "COUNTRY")
public class Country  implements Serializable {
        private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODE", length = 3, nullable = false)
    private String code;
    
    @Column(name = "NAME", length = 200, nullable = false)
    private String name;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

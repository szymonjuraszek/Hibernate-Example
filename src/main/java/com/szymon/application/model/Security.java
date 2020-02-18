package com.szymon.application.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Security implements Serializable, Cloneable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "security_id")
    private List<SixMarketMapping> sixMarketMappings;

    public Security clone() throws CloneNotSupportedException
    {
        Security clonedObj = (Security)super.clone();
        return clonedObj;
    }
}

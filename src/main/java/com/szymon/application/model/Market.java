package com.szymon.application.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Market implements Serializable, Cloneable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String externalId;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "market_id")
    private List<SixMarketMapping> sixMarketMappings;

    public Market clone() throws CloneNotSupportedException
    {
        Market clonedObj = (Market)super.clone();
        return clonedObj;
    }
}

package com.szymon.application.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class SixMarketMapping implements Serializable, Cloneable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Market market;

    @ManyToOne(fetch = FetchType.LAZY)
    private Security security;

    public SixMarketMapping clone() throws CloneNotSupportedException
    {
        SixMarketMapping clonedObj = (SixMarketMapping)super.clone();

        clonedObj.market = this.market.clone();
        clonedObj.security = this.security.clone();

        return clonedObj;
    }
}

package com.szymon.application.model;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Market implements Serializable, Cloneable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId(mutable=true)
    @Column(unique=true)
    private String externalId;

    private String name;

    //    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "market_id")
//    private List<SixMarketMapping> sixMarketMappings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Market market = (Market) o;
        return Objects.equals(externalId, market.getExternalId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(externalId);
    }

    public Market clone() throws CloneNotSupportedException
    {
        Market clonedObj = (Market)super.clone();
        return clonedObj;
    }
}

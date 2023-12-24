package org.sakaevrs.les.les4;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name = "test.magic")
public class Magic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmagic;
    @Column(name = "название")
    private String name;
    @Column(name = "повреждение")
    private int damage;
    @Column(name = "атака")
    private int attBonus;

    public Magic(String name, int damage, int attBonus) {
        this.name = name;
        this.damage = damage;
        this.attBonus = attBonus;
    }
}

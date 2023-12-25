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
    @Column(name = "title")
    private String name;
    @Column(name = "defect")
    private int damage;
    @Column(name = "push")
    private int attBonus;
    @Column(name = "armour")
    private int def;

    public Magic(String name, int damage, int attBonus, int def) {
        this.name = name;
        this.damage = damage;
        this.attBonus = attBonus;
        this.def = def;
    }
    public Magic() {

    }
}

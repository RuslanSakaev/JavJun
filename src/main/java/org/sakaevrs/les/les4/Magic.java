package org.sakaevrs.les.les4;

import org.sakaevrs.sem.sem2.task3.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table (name = "test.magic")
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

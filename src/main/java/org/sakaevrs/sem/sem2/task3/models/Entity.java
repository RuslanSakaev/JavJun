package org.sakaevrs.sem.sem2.task3.models;


import org.sakaevrs.sem.sem2.task3.Column;

import java.util.UUID;

@org.sakaevrs.sem.sem2.task3.Entity
public class Entity {

    @Column(name = "id", primaryKey = true)
    private UUID id;

}

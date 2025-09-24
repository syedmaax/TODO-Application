package com.app.todo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "task_seq_gen")
    @TableGenerator(
        name = "task_seq_gen",
        table = "task_seq",
        pkColumnName = "seq_name",      // primary key column
        valueColumnName = "next_val",   // value column
        pkColumnValue = "task",         // identifier for this sequence
        initialValue = 1,
        allocationSize = 1
    )

    private Long id;
    private String title;
    private boolean completed;
}

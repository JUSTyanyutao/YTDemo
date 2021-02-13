package com.just.ytdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "yt_student")
@Data
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
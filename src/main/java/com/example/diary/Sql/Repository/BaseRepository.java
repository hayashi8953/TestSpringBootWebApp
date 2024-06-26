package com.example.diary.Sql.Repository;

import org.springframework.jdbc.core.JdbcTemplate;


public abstract class BaseRepository {

    protected final JdbcTemplate jdbcTemplate;
    public static String databaseAccess;

    // jdbcの情報を受け取るコンストラクタ
    protected BaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}

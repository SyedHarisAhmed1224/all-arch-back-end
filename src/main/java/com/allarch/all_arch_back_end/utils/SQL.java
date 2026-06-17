package com.allarch.all_arch_back_end.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SQL {

    private final JdbcTemplate jdbcTemplate;

    public SQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> execute(String sql, Object... params) {
        try {
            return jdbcTemplate.queryForList(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer executeQueryOnce(String sql, Object... params) {
        try {
            return jdbcTemplate.update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
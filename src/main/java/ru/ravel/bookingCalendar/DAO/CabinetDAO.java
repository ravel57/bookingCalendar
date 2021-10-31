package ru.ravel.bookingCalendar.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CabinetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long getCabinetIdByNumber(long CabinetNumber){
        return jdbcTemplate.queryForObject(
                "select id from cabinet\n" +
                        "where number = ?;",
                new Object[]{CabinetNumber},
                Long.class
        );
    }
}

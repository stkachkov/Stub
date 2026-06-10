package org.example.stub.service;

import org.example.stub.dto.PostRequestDto;
import org.example.stub.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DataBaseWorker {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataBaseWorker(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDto getUserByLogin(String login) {
        String sql = "SELECT u.login, u.password, ue.email, u.registration_date FROM users u JOIN user_emails ue ON u.login = ue.login WHERE u.login = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Timestamp timestamp = rs.getTimestamp("registration_date");
            String formattedDate = timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new UserDto(rs.getString("login"), rs.getString("password"), rs.getString("email"), formattedDate);
        }, login);
    }

    @Transactional
    public UserDto createUser(PostRequestDto requestDto) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        jdbcTemplate.update("INSERT INTO users (login, password, registration_date) VALUES (?, ?, ?)", requestDto.getLogin(), requestDto.getPassword(), timestamp);
        jdbcTemplate.update("INSERT INTO user_emails (login, email) VALUES (?, ?)", requestDto.getLogin(),requestDto.getEmail());

        return new UserDto(requestDto.getLogin(), requestDto.getPassword(), requestDto.getEmail(), formattedDate);
    }
}

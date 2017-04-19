package com.account.dao;

import com.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountDaoImpl implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("SELECT * FROM accounts", ROW_MAPPER);
    }

    @Override
    public Optional<Account> getAccountByName(String name) {
        Optional<Account> account = Optional.empty();
        try {
            account = Optional.of(jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE first_name = ?",
                    new Object[]{name}, ROW_MAPPER));
        } catch (DataAccessException e) {

        }
        return account;
    }

    @Override
    public Account insert(Account account) {
        assert jdbcTemplate.update("INSERT INTO accounts (first_name, middle_name, last_name) VALUES (?, ?, ?);",
                account.getFirstName(), account.getMiddleName(), account.getLastName()) > 0;
        return getAccountByName(account.getFirstName()).get();
    }

    @Override
    public Account update(Account account) {
        assert jdbcTemplate.update("UPDATE accounts SET first_name = ? WHERE last_name = ?",
                account.getFirstName(), account.getLastName()) > 0;
        return getAccountByName(account.getFirstName()).get();
    }

    @Override
    public int deleteByName(String name) {
        return jdbcTemplate.update("DELETE FROM accounts WHERE first_name = ?", name);
    }
}

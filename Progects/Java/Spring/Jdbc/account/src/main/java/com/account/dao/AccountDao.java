package com.account.dao;

import com.account.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface AccountDao {
    RowMapper<Account> ROW_MAPPER = (ResultSet resultSet, int rowNumb) -> {
        return Account.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .middleName(resultSet.getString("middle_name"))
                .lastName(resultSet.getString("last_name"))
                .build();
    };

    List<Account> findAll();

    Optional<Account> getAccountByName(String name);

    Account insert(Account account);

    Account update(Account account);

    int deleteByName(String name);
}

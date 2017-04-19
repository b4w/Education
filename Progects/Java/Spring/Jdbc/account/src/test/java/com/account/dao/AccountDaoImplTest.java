package com.account.dao;

import com.account.model.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.Assert.*;

public class AccountDaoImplTest {

    private EmbeddedDatabase embeddedDatabase;

    private JdbcTemplate jdbcTemplate;

    private AccountDao accountDao;

    @Before
    public void setUp() throws Exception {
        // create db for test
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts() // added scripts schema.sql and data.sql
                .setType(EmbeddedDatabaseType.H2)
                .build();

        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        accountDao = new AccountDaoImpl(jdbcTemplate);
    }

    @After
    public void tearDown() throws Exception {
        embeddedDatabase.shutdown();
    }

    @Test
    public void findAll() throws Exception {
        assertNotNull(accountDao.findAll());
        assertEquals(3, accountDao.findAll().size());
    }

    @Test
    public void getAccountByName() throws Exception {
        assertTrue(accountDao.getAccountByName("Ivan").isPresent());
        assertFalse(accountDao.getAccountByName("Servey").isPresent());
    }

    @Test
    public void insert() throws Exception {
        Account account = accountDao.insert(Account.builder()
                .firstName("Kolya")
                .middleName("Kolya")
                .lastName("Kolya")
                .build());

        assertNotNull(account);
        assertNotNull(account.getId());
        assertEquals("Kolya", account.getFirstName());
    }

    @Test
    public void update() throws Exception {
        Account account = accountDao.insert(Account.builder()
                .firstName("Vasya")
                .middleName("Vasya")
                .lastName("Vasya")
                .build());

        account.setFirstName("Kolya");
        account = accountDao.update(account);

        assertNotNull(account.getId());
        assertEquals("Kolya", account.getFirstName());
    }

    @Test
    public void deleteByName() throws Exception {
        assertEquals(1, accountDao.deleteByName("Ivan"));
        assertEquals(0, accountDao.deleteByName("NoName"));
    }
}
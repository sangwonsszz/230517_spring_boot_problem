package io.playdata.spring_boot_test.service;

import io.playdata.spring_boot_test.model.Account;
import io.playdata.spring_boot_test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveAccount(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();

        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw new IllegalArgumentException("빈 유저 이름 또는 빈 패스워드");
        }
        if (accountRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("중복된 유저 이름");
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(password);
        account.setPassword(encryptedPassword);

        accountRepository.save(account);
    }
}
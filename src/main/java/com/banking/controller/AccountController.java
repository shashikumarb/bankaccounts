package com.banking.controller;

import com.banking.dao.AccountDAO;
import com.banking.model.Account;
import com.banking.service.MessageProducer;
import com.banking.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class AccountController {
    @Autowired
    AccountDAO accountDAO;

    @Autowired
    MessageProducer messageProducer;

    @Autowired
    SequenceGeneratorService seqGeneratorService;

    @PostMapping("/create")
    public void create(@RequestBody Account account) {
        account.setId(seqGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        messageProducer.sendMessage(account);
    }

    @GetMapping("/read")
    public List<Account> read(){
        return accountDAO.findAll();
    }

    @PutMapping("/update")
    public Account update(@RequestBody Account account) {
        return accountDAO.save(account);
    }
}


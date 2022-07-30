package com.banking.controller;

import com.banking.dao.AccountDAO;
import com.banking.model.Account;
import com.banking.service.MessageProducer;
import com.banking.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class AccountController {
    @Autowired
    AccountDAO accountDAO;

    @Autowired
    MessageProducer messageProducer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    SequenceGeneratorService seqGeneratorService;

    private static HttpHeaders headers;

    @PostMapping("/create")
    public String create(@RequestBody Account account) {
        account.setId(seqGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        messageProducer.sendMessage(account);
        return "Success";
    }

    @PostMapping("/balanceupdate/{accountnumber}/{amount}")
    public Account update(@PathVariable Long accountnumber, @PathVariable Long amount) {

        Account account = restTemplate.getForObject("http://accounttranscations/bank/read/" + accountnumber, Account.class);
        account.setBalance(account.getBalance() + amount);
        ResponseEntity<Account> updatedaccount = restTemplate.postForEntity("http://accounttranscations/bank/updatetransaction", account, Account.class);

        return updatedaccount.getBody();
    }

    @GetMapping("/read")
    public List<Account> read(){
        return accountDAO.findAll();
    }

}


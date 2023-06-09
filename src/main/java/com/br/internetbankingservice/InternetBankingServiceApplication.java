package com.br.internetbankingservice;

import com.br.internetbankingservice.domain.Client;
import com.br.internetbankingservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class InternetBankingServiceApplication implements ApplicationRunner {

	@Autowired
	private ClientRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(InternetBankingServiceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		createClients();
	}

	private void createClients() {
		Client client1 = new Client();
		client1.setName("João Domingos");
		client1.setExclusive(false);
		client1.setBalance(new BigDecimal("5000.00"));
		client1.setAccountNumber("21232");
		client1.setBirthDate(new Date());

		Client client2 = new Client();
		client2.setName("Janaina Silva");
		client2.setExclusive(true);
		client2.setBalance(new BigDecimal("10000.00"));
		client2.setAccountNumber("24424");
		client2.setBirthDate(new Date());

		Client client3 = new Client();
		client3.setName("Michael Jonas");
		client3.setExclusive(false);
		client3.setBalance(new BigDecimal("2500.00"));
		client3.setAccountNumber("42352");
		client3.setBirthDate(new Date());

		repository.save(client1);
		repository.save(client2);
		repository.save(client3);
	}
}

package in.natalia.main.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.natalia.main.dto.AccountDto;
import in.natalia.main.entities.Account;
import in.natalia.main.mapper.AccountMapper;
import in.natalia.main.repositories.AccountRepository;
import in.natalia.main.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);

		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) { 
		Account account = accountRepository
				                          .findById(id)
				                          .orElseThrow(() -> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		//first check account with provided exits or not
		Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		
		//add the provided amount onto the existing amount
		account.setBalance(account.getBalance() + amount);
		
		
		//save the updated account to the db
		Account savedAccount = accountRepository.save(account);
		
		//map it to accountDto
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accountList = accountRepository.findAll();
		
		return accountList
					.stream()
					.map((account) -> AccountMapper.mapToAccountDto(account))
					.collect(Collectors.toList());
		                           
		
	}

	@Override
	public void deleteAccount(Long id) {
		//check if accounts exist or not
		Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		//delete account from db
		
		accountRepository.deleteById(id);
		
	}

	
}

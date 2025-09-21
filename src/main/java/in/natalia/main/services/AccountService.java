package in.natalia.main.services;


import java.util.List;

import in.natalia.main.dto.AccountDto;


public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
    List<AccountDto> getAllAccounts();
    
    void deleteAccount(Long id);
}

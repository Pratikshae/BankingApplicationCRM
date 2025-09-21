package in.natalia.main.mapper;

import in.natalia.main.dto.AccountDto;
import in.natalia.main.entities.Account;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDto accountDto) {
		Account account = new Account();
		account.setId(accountDto.getId());
		account.setAccountHolderName(accountDto.getAccountHolderName());
		account.setBalance(accountDto.getBalance());
		return account;
	}

	
	public static AccountDto mapToAccountDto(Account account) {
		
		
		AccountDto accountDto =  new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setAccountHolderName(account.getAccountHolderName());
		accountDto.setBalance(account.getBalance());
		
		return accountDto;
	}
}

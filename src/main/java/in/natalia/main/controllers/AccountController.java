package in.natalia.main.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.natalia.main.dto.AccountDto;
import in.natalia.main.services.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


	
	@Autowired
	private AccountService accountService;

   
	
	//Add Account REST API	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	//Get Account REST API
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) {
		AccountDto accountDto = accountService.getAccountById(id);
		
		return ResponseEntity.ok(accountDto);
		
	}
    
	
    //Deposit REST API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id, 
			                                 @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		
		AccountDto accountDto = accountService.deposit(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}
	
	//Withdraw REST API
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable("id")  Long id,
			                                   @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		
		AccountDto accountDto = accountService.withdraw(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}
	
	//Get All Accounts REST API
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> accounts = accountService.getAllAccounts();
		
		return ResponseEntity.ok(accounts);
	}
	
	//Delete Account REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted successfully");
	}
}

package com.example.txbank.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.txbank.dto.BalanceResponse;
import com.example.txbank.dto.SupportMessageResponse;
import com.example.txbank.dto.UserBankInfoResponse;
import com.example.txbank.dto.UserBankTransactionResponse;
import com.example.txbank.service.TxBankService;
import com.example.txbank.service.TxBankSupportService;

@Controller
//@RequestMapping("/txbank")
public class MyControllerBank {

	@Autowired
	private TxBankService txBankService;
	@Autowired
	private TxBankSupportService txBankSupportService;
	
	
	@ModelAttribute
    public void addAttributes(Model model, CsrfToken token) {
        model.addAttribute("csrfToken", token);
    }

	@GetMapping("/welcome")
	public String welcome(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		if(userDetails != null) {
			model.addAttribute("isLoggedIn", true);
		} else {
			model.addAttribute("isLoggedIn", false);
		}
		
		return "welcome"; 
	}

	@GetMapping("/login")
	public String login() {
		return "login"; 
	}

	@GetMapping("/register")
	public String registerUser() {
		return "register"; 
	}

	@PostMapping("/register")
	public String saveUserBank(@RequestParam String login, @RequestParam String password, @RequestParam String name) {
		txBankService.saveUser(login, password, name);
		return "redirect:/welcome";
	}
	
	@GetMapping("/myProfile")
	public String myProfile(Model model) {	
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		UserBankInfoResponse userBankInfoResponse = txBankService.getUserByLogin(currentPrincipalName);
		int userBankId = userBankInfoResponse.getId();
		
	    BalanceResponse balanceResponse = txBankService.getMyBalanceCard(userBankId);
	    UserBankTransactionResponse userBankTransactionResponse = txBankService.getTransactionHistory(userBankId);
	    
	    model.addAttribute("balanceResponse", balanceResponse);
	    model.addAttribute("userBankTransactionResponse", userBankTransactionResponse);
	    
		return "myProfile";
	}
	
	@PostMapping("/myProfile")
	public String transferInfo(Model model, @RequestParam("typePayment") String typePayment, @RequestParam("amount") BigDecimal amount, @RequestParam("recipient") Long receiverBankCard, 
			@RequestParam("commentTransaction") String commentTransaction) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		UserBankInfoResponse userBankInfoResponse = txBankService.getUserByLogin(currentPrincipalName);
		int userBankId = userBankInfoResponse.getId();		
		UserBankTransactionResponse userBankTransactionResponse = txBankService.transferMoney(typePayment, userBankId, receiverBankCard, amount, commentTransaction);
			
		return "redirect:/myProfile";
	}
	
	@GetMapping("/calculate")
	@ResponseBody
	public Map<String, Object> calculateCredit(@RequestParam("amountCredit") BigDecimal amountCredit, 
			@RequestParam("clientMonth") BigDecimal clientMonth) {
		
	    if (amountCredit == null || clientMonth == null) {
	        throw new IllegalArgumentException("Parameters cannot be null");
	    }

	    List<BigDecimal> mathematicalCredit = txBankService.getMathematicalCredit(amountCredit, clientMonth);
	    BigDecimal clientMonthResult = mathematicalCredit.get(0).setScale(2, RoundingMode.HALF_UP);
	    BigDecimal yearResult = mathematicalCredit.get(1).setScale(2, RoundingMode.HALF_UP);
	    BigDecimal monthResult = mathematicalCredit.get(2).setScale(2, RoundingMode.HALF_UP);
	    BigDecimal dayResult = mathematicalCredit.get(3).setScale(2, RoundingMode.HALF_UP);
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("clientMonthResult", clientMonthResult);
	    response.put("yearResult", yearResult);
	    response.put("monthResult", monthResult);
	    response.put("dayResult", dayResult);

	    return response;
	}
	
	@PostMapping("/myProfile/support")
	public String supportMessage(@RequestParam("userEmail") String userEmail, @RequestParam("userMessage") String userMessage) {
		
		SupportMessageResponse supportMessageResponse = txBankSupportService.saveSupportMessage(userEmail, userMessage);	
		
		return "redirect:/myProfile";
	}
	
	@PostMapping("/logout")
	public String logout() {	
		return "logout";
	}
	
	@GetMapping("/infoWindow")
	public String mistakeInfo() {
		return "infoWindow";
	}
	
}

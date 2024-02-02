package az.evilcastle.turnbased.controllers;

import az.evilcastle.turnbased.models.UserAccount;
import az.evilcastle.turnbased.services.interfaces.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/userAccounts")
    public List<UserAccount> getAllUserAccounts() {
        return userAccountService.getAllUserAccounts();
    }
}

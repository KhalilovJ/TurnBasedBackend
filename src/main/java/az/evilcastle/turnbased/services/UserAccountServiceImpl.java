package az.evilcastle.turnbased.services;

import az.evilcastle.turnbased.Repo.UserAccountRepo;
import az.evilcastle.turnbased.models.UserAccount;
import az.evilcastle.turnbased.services.interfaces.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepo userAccountRepo;

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepo.getAllUserAccounts();
    }
}

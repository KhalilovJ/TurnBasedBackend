package az.evilcastle.turnbased.services;

import az.evilcastle.turnbased.Repo.UserAccountRepo;
import az.evilcastle.turnbased.entities.UserAccount;
import az.evilcastle.turnbased.services.interfaces.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

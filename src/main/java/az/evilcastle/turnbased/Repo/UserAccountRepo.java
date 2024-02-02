package az.evilcastle.turnbased.Repo;

import az.evilcastle.turnbased.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {

    @Query("SELECT u FROM UserAccount u")
    List<UserAccount> getAllUserAccounts();
}

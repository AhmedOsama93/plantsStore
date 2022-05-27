package onlineStore.plantsStore.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usersRepository extends JpaRepository<users,Long> {


    @Query("select u from users u where u.username=?1")

   users findusersByEmail(String email);

    @Query("select u from users u where u.verifyCode=?1")
    users findusersByVC(String VC);

    @Query("select u from users u where u.verifyCodePassword=?1")
    users findusersByVCP(String VC);
}

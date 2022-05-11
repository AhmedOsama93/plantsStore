package onlineStore.plantsStore.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usersRepository extends JpaRepository<users,Long> {

//    @Query("select u from users u where u.email=?1")
//
//   Optional<users>findusersByEmail(String email);

    @Query("select u from users u where u.email=?1")

   users findusersByEmail(String email);


}

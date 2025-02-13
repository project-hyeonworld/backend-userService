package io.userservice.api.user.infrastructure.jpa;

import io.userservice.api.user.infrastructure.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {

    //@Query("DELETE FROM MyTable WHERE study_id = :studyId")
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :user_id")
    int deleteById(@Param("user_id") long userId);

    List<User> findByLogin(boolean login);

    Optional<User> findByName(String userName);

    @Modifying
    @Query("SELECT u.id as id, u.name as name FROM User u WHERE u.id IN :userIds")
    List<UserNameProjection> findNamesByIdsIn(List<Long> userIds);

    @Modifying
    @Query("Update User u Set u.login = false")
    void logOutAll();

    interface UserNameProjection {

        Long getId();

        String getName();
    }
}

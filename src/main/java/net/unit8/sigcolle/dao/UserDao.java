package net.unit8.sigcolle.dao;

import net.unit8.sigcolle.DomaConfig;
import net.unit8.sigcolle.model.User;
import org.seasar.doma.*;

import javax.ejb.Remove;

/**
 * @author takahashi
 */
@Dao(config = DomaConfig.class)
public interface UserDao {
    @Insert
    int insert(User user);

    @Select
    User selectByUserId(Long userId);

    @Select
    int countByEmail(String email);

    @Select(ensureResult = true)
    User selectByEmail(String email);

    @Update
    int update(User user);

    @Delete
    int delete(User user);

}

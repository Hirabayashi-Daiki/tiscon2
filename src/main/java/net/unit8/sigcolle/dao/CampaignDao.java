package net.unit8.sigcolle.dao;

import java.util.List;

import net.unit8.sigcolle.DomaConfig;
import net.unit8.sigcolle.model.Campaign;
import org.h2.command.ddl.CreateUser;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

/**
 * @author kawasima
 */
@Dao(config = DomaConfig.class)
public interface CampaignDao {
    @Select(ensureResult = true)
    Campaign selectById(Long campaignId);

    @Select
    List<Campaign> selectAll();

    @Insert
    int insert(Campaign campaign);

//    @Select(ensureResult = true )
//    List<Campaign> selectedCreateUser(Long createUserId);
}

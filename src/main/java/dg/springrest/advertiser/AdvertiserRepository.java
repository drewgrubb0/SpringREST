package dg.springrest.advertiser;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is the repository interface that will connect the AdvertiserController to the H2 Database.
 * Things to note:
 * Select Corresponds to GET
 * Insert Corresponds to POST
 * Update Corresponds to PUT
 * Delete Corresponds to DELETE
 */
@Mapper
@Repository
public interface AdvertiserRepository
{
    @Select("SELECT * FROM advertiser")
    public List<Advertiser> getAll();

    @Select("SELECT * FROM advertiser WHERE id = #{id}")
    public Advertiser findById(long id);

    @Update("UPDATE advertiser set name = #{name}, contactName = #{contactName}, creditLimit = #{creditLimit} WHERE id = #{id}")
    public int update(Advertiser newAdvertiser);

    @Insert("INSERT INTO advertiser (id, name, contactName, creditLimit) VALUES (#{id}, #{name}, #{contactName}, #{creditLimit})")
    public int add(Advertiser newAdvertiser);

    @Delete("DELETE FROM advertiser WHERE id = #{id}")
    public int deleteById(long id);
}

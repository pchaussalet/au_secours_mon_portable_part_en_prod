package net.chaussalet.bbl.asmppp.mygreatapp.repository;

import net.chaussalet.bbl.asmppp.mygreatapp.model.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.SortedSet;

public interface AddressRepository {
    @Select("SELECT * FROM address")
    SortedSet<Address> list();

    @Insert("INSERT INTO address (firstname, lastname, email) VALUES (#{firstname}, #{lastname}, #{email})")
    void save(Address address);

    @Delete("DELETE FROM address WHERE id=#{id}")
    void remove(int id);
}

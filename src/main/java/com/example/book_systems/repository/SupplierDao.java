package com.example.book_systems.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.book_systems.entity.Supplier;

@Repository
public interface SupplierDao extends JpaRepository<Supplier, String> {
	
	// 查找全部
	public List<Supplier> findAll();
	
	// 透過 統編 查找
	@Query(value = "select serial_num, `name`, compiled, email, phone, location_id, location_name"
			+ " from supplier where compiled = :input_su_compiled ", nativeQuery = true)
	public List<Supplier> findByCompileds(@Param("input_su_compiled") int su_compiled);
	
	// 透過 id 查找
	@Query(value = "select serial_num, `name`, compiled, email, phone, location_id, location_name"
			+ " from supplier where serial_num = :input_serial_num ", nativeQuery = true)
	public List<Supplier> findBySerialNum(@Param("input_serial_num") String serial_num);
	
	@Query(value = "select su.* from supplier su where"
			+ " su.name like (case when :input_su_name is null then '%%' else concat('%',:input_su_name,'%') end) and"
			+ " su.compiled like (case when :input_su_compiled is null then '%%' else concat('%',:input_su_compiled,'%') end) and"
			+ " su.location_id = (case when :input_su_lo_id is null then su.location_id else :input_su_lo_id end)",nativeQuery = true)
	public List<Supplier> searchSuppliers(
			@Param("input_su_name") String su_name,
			@Param("input_su_compiled") Integer su_compiled,
			@Param("input_su_lo_id") String su_lo_id
		);
	
//	@Modifying
//	@Transactional
//	@Query(value = "insert into supplier "
//			+ " select concat(:input_su_lo_id,(lpad((select count(su.location_id)+1 from location lo"
//			+ " left join supplier su on lo.location_id = su.location_id "
//			+ " group by lo.location_id having lo.location_id = :input_su_lo_id),5,0))),"
//			+ " :input_su_name, :input_su_compiled, :input_su_email, "
//			+ " :input_su_phone, :input_su_lo_id, :input_su_lo_name ", nativeQuery = true)
//	public int inserSupplier(
//			@Param("input_su_name") String su_name,
//			@Param("input_su_compiled") int su_compiled,
//			@Param("input_su_email") String su_email,
//			@Param("input_su_phone") String su_phone,
//			@Param("input_su_lo_id") String su_lo_id,
//			@Param("input_su_lo_name") String su_lo_name
//		);
	
	
	
//	@Modifying
//	@Transactional
//	@Query(value = "update supplier"
//			+ " set serial_num = :input_NewSe_num,"
//			+ " `name` =  :input_name ,"
//			+ " compiled = :input_compiled ,"
//			+ " email = :input_email ,"
//			+ " phone = :input_phone ,"
//			+ " location_id = :input_location_id,"
//			+ " location_name = :input_location_name "
//			+ " where serial_num = :input_serial_num ",nativeQuery = true)
//	public int updateThisSupplier(
//				@Param("input_NewSe_num") String NewSe_num,
//				@Param("input_name") String name,
//				@Param("input_compiled") int compiled,
//				@Param("input_email") String email,
//				@Param("input_phone") String phone,
//				@Param("input_location_id") String location_id,
//				@Param("input_location_name") String location_name,
//				@Param("input_serial_num") String serial_num
//			);
	
	@Modifying
	@Transactional
	@Query(value = "insert into supplier "
			+ " select concat('S',(lpad((select count(*)+1 from supplier),6,0))),"
			+ " :input_su_name, :input_su_compiled, :input_su_email, "
			+ " :input_su_phone, :input_su_lo_id, :input_su_lo_name ", nativeQuery = true)
	public int inserSupplier(
			@Param("input_su_name") String su_name,
			@Param("input_su_compiled") int su_compiled,
			@Param("input_su_email") String su_email,
			@Param("input_su_phone") String su_phone,
			@Param("input_su_lo_id") String su_lo_id,
			@Param("input_su_lo_name") String su_lo_name
		);
	
	@Modifying
	@Transactional
	@Query(value = "update supplier set "
			+ " `name` =  :input_name ,"
			+ " compiled = :input_compiled ,"
			+ " email = :input_email ,"
			+ " phone = :input_phone ,"
			+ " location_id = :input_location_id,"
			+ " location_name = :input_location_name "
			+ " where serial_num = :input_serial_num ",nativeQuery = true)
	public int updateThisSupplier(
				@Param("input_name") String name,
				@Param("input_compiled") int compiled,
				@Param("input_email") String email,
				@Param("input_phone") String phone,
				@Param("input_location_id") String location_id,
				@Param("input_location_name") String location_name,
				@Param("input_serial_num") String serial_num
			);
}

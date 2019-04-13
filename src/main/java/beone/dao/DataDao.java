package beone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import beone.entity.SensorData;

@Repository
public interface DataDao extends JpaRepository<SensorData, Long>{

	@Query(value = "select * from sensor_data d where d.time>?1 and d.time<?2", nativeQuery = true)
	List<SensorData> getHistory(long from  , long to);

	@Query(value = "select * from sensor_data d order by d.time  desc LIMIT 10", nativeQuery = true)
	List<SensorData> get10line();

	
}

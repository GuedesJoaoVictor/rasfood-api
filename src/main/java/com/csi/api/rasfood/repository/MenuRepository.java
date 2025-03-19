package com.csi.api.rasfood.repository;

import com.csi.api.rasfood.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query(value = "select * from menu where menu_category_id = ?1 and status = true", nativeQuery = true)
    List<Menu> findAllByCategory(Long category);
}

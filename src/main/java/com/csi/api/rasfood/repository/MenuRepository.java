package com.csi.api.rasfood.repository;

import com.csi.api.rasfood.dto.MenuDto;
import com.csi.api.rasfood.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("select new com.csi.api.rasfood.dto.MenuDto(m.name, m.description, m.price, m.menuCategory.name) from Menu m where m.name like %:name% and m.status = true")
    List<MenuDto> findAllByName(String name);

    @Query(value = "select * from menu where menu_category_id = ?1 and status = true", nativeQuery = true)
    List<Menu> findAllByCategory(Long category);
}

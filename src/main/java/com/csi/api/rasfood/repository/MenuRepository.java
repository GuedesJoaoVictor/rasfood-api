package com.csi.api.rasfood.repository;

import com.csi.api.rasfood.dto.MenuDto;
import com.csi.api.rasfood.entity.Menu;
import com.csi.api.rasfood.repository.projection.MenuProjection;
import jakarta.transaction.Transactional;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends PagingAndSortingRepository<Menu, Long> {
    @Query("select new com.csi.api.rasfood.dto.MenuDto(m.name, m.description, m.price, m.menuCategory.name) from Menu m where m.name like %:name% and m.status = true")
    Page<MenuDto> findAllByName(String name, Pageable pageable);

    @Query(value = "select m.name, m.description, m.price, mc.name as category from menu m, menu_category mc where mc.id = m.menu_category_id and m.menu_category_id = ?1 and m.status = true", nativeQuery = true)
    Page<MenuProjection> findAllByCategory(Long category, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Menu m set m.status = case m.status when true then false else true end where m.id = :id")
    void updateStatus(Long id);

    Menu save(Menu existingMenu);

    Optional<Menu> findById(Long id);

    void deleteById(Long id);
}

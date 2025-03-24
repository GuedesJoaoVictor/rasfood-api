package com.csi.api.rasfood.specification;

import com.csi.api.rasfood.entity.Menu;
import org.springframework.data.jpa.domain.Specification;

public class MenuSpecification {
    public static Specification<Menu> name(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%"+name+"%");
    }

    public static Specification<Menu> category(Long category) {
        return (root, query, cb) -> cb.equal(root.get("menuCategory").get("id"), category);
    }

    public static Specification<Menu> available(boolean available) {
        return (root, query, cb) -> cb.equal(root.get("status"), available);
    }
}

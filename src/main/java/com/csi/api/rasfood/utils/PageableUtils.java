package com.csi.api.rasfood.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PageableUtils {
    public static Pageable createPageable(int page, int size, Sort.Direction sort, String property) {
        if(Objects.nonNull(sort) && Objects.nonNull(property)) {
            return PageRequest.of(page, size, Sort.by(sort, property));
        }
        return PageRequest.of(page, size);
    }
}

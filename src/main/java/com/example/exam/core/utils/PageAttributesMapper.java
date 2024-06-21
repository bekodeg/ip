package com.example.exam.core.utils;

import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.function.Function;

public class PageAttributesMapper {
    private PageAttributesMapper() {
    }

    public static <E, D> Map<String, Object> toAttributes(Page<E> page, Function<E, D> mapper) {
        return Map.of(
                "items", page.getContent().stream().map(mapper::apply).toList(),
                "currentPage", page.getNumber(),
                "totalPages", page.getTotalPages());
    }
}

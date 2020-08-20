package ru.zimin.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TypeFileEnum {

    XLS(1L, "xls/xlsx"),
    JSON(2L, "json")
    ;

    private final Long id;
    private final String description;

    static private Map<Long, TypeFileEnum> typeFiles;

    static {
        typeFiles = new HashMap<>();
        Arrays.stream(TypeFileEnum.values()).forEach(event -> typeFiles.put(event.id, event));
    }

    public static TypeFileEnum getById(Long id) {
        return typeFiles.get(id);
    }
}

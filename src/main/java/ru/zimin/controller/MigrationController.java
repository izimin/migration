package ru.zimin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zimin.model.enums.TypeFileEnum;
import ru.zimin.service.MigrationService;

import java.io.IOException;

@RestController
@RequestMapping("/migrate")
public class MigrationController {

    private final MigrationService migrationService;

    @Autowired
    public MigrationController(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    /**
     * Перенос данных из конкретного источника в таблицу developer
     * @param file - файл-источник из которого берутся данные
     * @param typeFile - формат файла-источника
     **/
    @GetMapping("developer")
    public void migrateDeveloper(@RequestParam TypeFileEnum typeFile, @RequestBody byte[] file) throws IOException {
        migrationService.migrateDeveloper(typeFile, file);
    }
}

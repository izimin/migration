package ru.zimin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zimin.exception.ValidationException;
import ru.zimin.model.DeveloperDto;
import ru.zimin.model.enums.TypeFileEnum;
import ru.zimin.repository.MigrationRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MigrationService {

    private final MigrationRepository migrationRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public MigrationService(MigrationRepository migrationRepository, ObjectMapper objectMapper) {
        this.migrationRepository = migrationRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Перенос данных из конкретного источника в таблицу developer
     * @param file - файл-источник из которого берутся данные
     * @param typeFile - формат файла-источника
     **/
    public void migrateDeveloper(TypeFileEnum typeFile, byte[] file) throws IOException {
        switch (typeFile) {
            case XLS: {
                migrateFromXls(file);
                break;
            }
            case JSON: {
                // TODO
                break;
            }
            default: {
                migrateFromXls(file);
                break;
            }
        }
    }

    /**
     * Перенос данных из xml
     * @param file - файл-источник из которого берутся данные
     **/
    private void migrateFromXls(byte[] file) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new ByteArrayInputStream(file));
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow headerRow = sheet.getRow(0);

        int clmnDataIdx = -1;
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            if ("data".equalsIgnoreCase(headerRow.getCell(i).getStringCellValue())) {
                clmnDataIdx = i;
            }
        }

        if (clmnDataIdx < 0) {
            throw new ValidationException("Файл имеет некорректную структуру");
        }

        List<DeveloperDto> inserts = new ArrayList<>();

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            HSSFCell cell = sheet.getRow(i).getCell(clmnDataIdx);
            inserts.add(objectMapper.readValue(cell.getStringCellValue(), DeveloperDto.class));
        }

        migrationRepository.saveAll(inserts);

        workbook.close();
    }
}
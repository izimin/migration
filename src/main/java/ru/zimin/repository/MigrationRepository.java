package ru.zimin.repository;

import org.springframework.data.repository.CrudRepository;
import ru.zimin.model.DeveloperDto;

import java.util.UUID;

public interface MigrationRepository extends CrudRepository<DeveloperDto, UUID> {

}

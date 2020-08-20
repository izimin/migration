package ru.zimin.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "developer")
public class DeveloperDto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @JsonIgnore
    private UUID id;

    @JsonAlias("external_id")
    private Long externalId;

    @JsonAlias("inn")
    private String inn;

    @JsonAlias("kpp")
    private String kpp;

    @JsonAlias("name")
    private String name;

    @JsonAlias("ogrn")
    private String ogrn;

    @JsonAlias("opf_full")
    private String opfFull;

    @JsonAlias("reg_date")
    private Timestamp regDate;

    @JsonAlias("short_name")
    private String shortName;

    @JsonAlias("region_id")
    private UUID regionId;
}

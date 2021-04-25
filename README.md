# JSON DB Converter for Spring Data JDBC

## tl;dr

*JSON DB Converter* is a demo project that show the usage of JSON data with the PostreSQL/H2 datatype *json* and *jsonb*
with the use of Jackson JSON Library (H2 is not directly supporting *jsonb*). In the entity the column is defined as
unusual, the type is a Jackson serializable class ... That's all.

To use the project just copy the dedicated code snippets to your project.
This is not a library for integration into your project.

## Getting started

### Dependencies

Lib *spring-boot-starter-json* is needed, if your project is a web project this is most likely
installed via dependencies.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-json</artifactId>
</dependency>
```

Lib *jackson-module-kotlin* if you are using kotlin.

```xml
<dependency>
    <groupId>com.fasterxml.jackson.module</groupId>
    <artifactId>jackson-module-kotlin</artifactId>
</dependency>
```

**Remove the Runtime Scope** from `com.h2database:h2`/`org.postgresql:postgresql` (You don't have to use both)

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
```

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

### Copy Classes

Copy all files from [src/main/kotlin/com/codingjj/jsondbconverter](src/main/kotlin/com/codingjj/jsondbconverter)
to you project. Can be moved to any Package. Just make sure the package info oth teh Kotlin classes is refactored
correctly.

### Create Configuration Class for Class List of DTOs

The Converter has to know which classes you are using as DTO to write to a database. Unfortunately right now,
this can not be done via annotation.
[Configuration class from Unit Test](src/test/kotlin/com/codingjj/jsondbconverter/JsonJdbcClassListConfiguration.kt)

```kotlin
@Configuration
class JsonJdbcClassListConfiguration : AbstractJsonJdbcClassListConfiguration {

    override val jsonClassList: List<Class<*>> = listOf(
        YourFirstDto::class.java,
        YourFirstSecond::class.java,
    )
}
```

### Create Database Field for JSON

Create database table with a *json/jsonb* field.
[Table Creation - Flyway Definition](src/test/resources/db/migration/common/V1.002__json.sql)

```sql
CREATE TABLE json_tbl
(
    # ...
    json1   json,
    json2   jsonb,
    json3   json  NOT NULL,
    json4   jsonb NOT NULL,
    # ...
);
```

#### H2 support for jsonb type

*H2* does not support *jsonb*, but you can create an alias type.
[Alias Definition - Flyway Definition](src/test/resources/db/migration/h2/V1.001__jsonb_type.sql)

```sql
CREATE TYPE "JSONB" AS json;
```

### Create Entity

Create an entity like normal, just add the DTO class as type
[Entity Definition Kotlin](src/test/kotlin/com/codingjj/jsondbconverter/database/JsonbTblEntity.kt)

```kotlin
@Table("jsonb_tbl")
data class JsonbTblEntity(

    // ...

    @Column("json1")
    var some1Dto: Some1Dto?,

    // ...
)
```

### DONE

You are done and can test your project

## Addendum

### Unit Tests

There is one unit test
[JsonJdbcConfigurationTest](src/test/kotlin/com/codingjj/jsondbconverter/JsonJdbcConfigurationTest.kt)
to demonstrate the code. This test can be run via your IDE with H2 easily. To run this test on PostgreSQL I added a
batch script to execute it [run-test-postgres-local.bat](run-test-postgres-local.bat)

There is also a batch script for running the test on H2 [run-test-h2-local.bat](run-test-h2-local.bat)

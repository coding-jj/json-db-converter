package com.codingjj.jsondbconverter

import com.codingjj.jsondbconverter.database.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest(classes = [SpringEnableAutoConfiguration::class])
internal class JsonJdbcConfigurationTest {

    @Autowired
    private lateinit var jsonTblRepository: JsonTblRepository

    @Autowired
    private lateinit var jsonbTblRepository: JsonbTblRepository

    @Nested
    inner class TypeJson {

        @Test
        fun insert_data_with_null_dtos() {

            // Arrange
            val id = UUID.randomUUID()
            val jsonTblEntity = JsonTblEntity(
                id,
                "insert_data_with_null_dtos",
                null,
                null
            )

            // Act
            jsonTblRepository.save(jsonTblEntity)

            // Assume
            val actual = jsonTblRepository.findById(id).orElse(null)

            assertThat(actual.id).isEqualTo(id)
            assertThat(actual.version).isEqualTo(1)
            assertThat(actual.name).isEqualTo("insert_data_with_null_dtos")

            assertThat(actual.some1Dto).isNull()
            assertThat(actual.some2Dto).isNull()
        }

        @Test
        fun insert_data_with_some1Dto_set() {

            // Arrange
            val some1Dto = Some1Dto("hello", null)
            val id = UUID.randomUUID()
            val jsonTblEntity = JsonTblEntity(
                id,
                "insert_data_with_some1Dto_set",
                some1Dto,
                null
            )

            // Act
            jsonTblRepository.save(jsonTblEntity)

            // Assume
            val actual = jsonTblRepository.findById(id).orElse(null)

            assertThat(actual.id).isEqualTo(id)
            assertThat(actual.version).isEqualTo(1)
            assertThat(actual.name).isEqualTo("insert_data_with_some1Dto_set")

            assertThat(actual.some1Dto).isEqualTo(some1Dto)
            assertThat(actual.some2Dto).isNull()
        }

        @Test
        fun insert_data_with_some2Dto_set() {

            // Arrange
            val some2Dto = Some2Dto("f1", "f2", "f3", null)
            val id = UUID.randomUUID()
            val jsonTblEntity = JsonTblEntity(
                id,
                "insert_data_with_some2Dto_set",
                null,
                some2Dto
            )

            // Act
            jsonTblRepository.save(jsonTblEntity)

            // Assume
            val actual = jsonTblRepository.findById(id).orElse(null)

            assertThat(actual.id).isEqualTo(id)
            assertThat(actual.version).isEqualTo(1)
            assertThat(actual.name).isEqualTo("insert_data_with_some2Dto_set")

            assertThat(actual.some1Dto).isNull()
            assertThat(actual.some2Dto).isEqualTo(some2Dto)
        }

        @Test
        fun insert_data_with_both_dtos_set() {

            // Arrange
            val some1Dto = Some1Dto("hello", null)
            val some2Dto = Some2Dto("f1", "f2", "f3", null)
            val id = UUID.randomUUID()
            val jsonTblEntity = JsonTblEntity(
                id,
                "insert_data_with_both_dtos_set",
                some1Dto,
                some2Dto
            )

            // Act
            jsonTblRepository.save(jsonTblEntity)

            // Assume
            val actual = jsonTblRepository.findById(id).orElse(null)

            assertThat(actual.id).isEqualTo(id)
            assertThat(actual.version).isEqualTo(1)
            assertThat(actual.name).isEqualTo("insert_data_with_both_dtos_set")

            assertThat(actual.some1Dto).isEqualTo(some1Dto)
            assertThat(actual.some2Dto).isEqualTo(some2Dto)
        }
    }

    @Nested
    inner class TypeJsonb {

        @Test
        fun insert_data_with_null_dtos() {

            // Arrange
            val id = UUID.randomUUID()
            val jsonbTblEntity = JsonbTblEntity(
                id,
                "insert_data_with_null_dtos",
                null,
                null
            )

            // Act
            jsonbTblRepository.save(jsonbTblEntity)

            // Assume
            val actual = jsonbTblRepository.findById(id).orElse(null)

            assertThat(actual.id).isEqualTo(id)
            assertThat(actual.version).isEqualTo(1)
            assertThat(actual.name).isEqualTo("insert_data_with_null_dtos")

            assertThat(actual.some1Dto).isNull()
            assertThat(actual.some2Dto).isNull()
        }

        @Test
        fun insert_data_with_some1Dto_set() {

            // Arrange
            val some1Dto = Some1Dto("hello", null)
            val id = UUID.randomUUID()
            val jsonbTblEntity = JsonbTblEntity(
                id,
                "insert_data_with_some1Dto_set",
                some1Dto,
                null
            )

            // Act
            jsonbTblRepository.save(jsonbTblEntity)

            // Assume
            val actual = jsonbTblRepository.findById(id).orElse(null)

            assertThat(actual.id).isEqualTo(id)
            assertThat(actual.version).isEqualTo(1)
            assertThat(actual.name).isEqualTo("insert_data_with_some1Dto_set")

            assertThat(actual.some1Dto).isEqualTo(some1Dto)
            assertThat(actual.some2Dto).isNull()
        }

        @Test
        fun insert_data_with_some2Dto_set() {

            // Arrange
            val some2Dto = Some2Dto("f1", "f2", "f3", null)
            val id = UUID.randomUUID()
            val jsonbTblEntity = JsonbTblEntity(
                id,
                "insert_data_with_some2Dto_set",
                null,
                some2Dto
            )

            // Act
            jsonbTblRepository.save(jsonbTblEntity)

            // Assume
            val actual = jsonbTblRepository.findById(id).orElse(null)

            assertThat(actual.id).isEqualTo(id)
            assertThat(actual.version).isEqualTo(1)
            assertThat(actual.name).isEqualTo("insert_data_with_some2Dto_set")

            assertThat(actual.some1Dto).isNull()
            assertThat(actual.some2Dto).isEqualTo(some2Dto)
        }

        @Test
        fun insert_data_with_both_dtos_set() {

            // Arrange
            val some1Dto = Some1Dto("hello", null)
            val some2Dto = Some2Dto("f1", "f2", "f3", null)
            val id = UUID.randomUUID()
            val jsonbTblEntity = JsonbTblEntity(
                id,
                "insert_data_with_both_dtos_set",
                some1Dto,
                some2Dto
            )

            // Act
            jsonbTblRepository.save(jsonbTblEntity)

            // Assume
            val actual = jsonbTblRepository.findById(id).orElse(null)

            assertThat(actual.id).isEqualTo(id)
            assertThat(actual.version).isEqualTo(1)
            assertThat(actual.name).isEqualTo("insert_data_with_both_dtos_set")

            assertThat(actual.some1Dto).isEqualTo(some1Dto)
            assertThat(actual.some2Dto).isEqualTo(some2Dto)
        }
    }
}
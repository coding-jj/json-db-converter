package com.codingjj.jsondbconverter.database

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("jsonb_tbl")
data class JsonbTblEntity(

    @Id
    @Column("id")
    var id: UUID,

    @Column("name")
    var name: String,

    @Column("json1")
    var some1Dto: Some1Dto?,

    @Column("json2")
    var some2Dto: Some2Dto?
) {

    @Version
    @Column("version")
    var version: Long = 0
}

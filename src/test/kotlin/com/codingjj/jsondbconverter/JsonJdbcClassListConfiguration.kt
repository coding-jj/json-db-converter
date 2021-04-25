package com.codingjj.jsondbconverter

import com.codingjj.jsondbconverter.database.Some1Dto
import com.codingjj.jsondbconverter.database.Some2Dto
import org.springframework.context.annotation.Configuration

@Configuration
class JsonJdbcClassListConfiguration : AbstractJsonJdbcClassListConfiguration {

    override val jsonClassList: List<Class<*>> = listOf(
        Some1Dto::class.java,
        Some2Dto::class.java,
    )
}
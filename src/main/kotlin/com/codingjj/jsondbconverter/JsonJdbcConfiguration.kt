package com.codingjj.jsondbconverter

import com.codingjj.jsondbconverter.converters.H2JsonReadingConverter
import com.codingjj.jsondbconverter.converters.H2JsonWritingConverter
import com.codingjj.jsondbconverter.converters.PgJsonReadingConverter
import com.codingjj.jsondbconverter.converters.PgJsonWritingConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import java.util.*

@Configuration
class JsonJdbcConfiguration(
    private val applicationContext: ApplicationContext,

    @Autowired(required = false)
    private val jsonClassList: AbstractJsonJdbcClassListConfiguration?,

    @Value("\${spring.datasource.url:null}")
    private val datasourceUrl: String?

) : AbstractJdbcConfiguration() {

    override fun jdbcCustomConversions(): JdbcCustomConversions {

        val converters: ArrayList<GenericConverter> = ArrayList()

        jsonClassList?.let {
            it.jsonClassList.forEach { clazz ->
                if (isPostgres()) {
                    converters.add(PgJsonReadingConverter(clazz, applicationContext))
                    converters.add(PgJsonWritingConverter(clazz, applicationContext))
                }
                if (isH2()) {
                    converters.add(H2JsonReadingConverter(clazz, applicationContext))
                    converters.add(H2JsonWritingConverter(clazz, applicationContext))
                }
            }
        }

        return JdbcCustomConversions(converters)
    }

    private fun isPostgres(): Boolean = isDatabaseType("jdbc:postgresql:")

    private fun isH2(): Boolean = isDatabaseType("jdbc:h2:")

    private fun isDatabaseType(jdbcStartString: String): Boolean {
        if (datasourceUrl.isNullOrBlank()) return false

        val datasourceUrlLower = datasourceUrl.toLowerCase(Locale.ROOT)
        return datasourceUrlLower.startsWith(jdbcStartString)
    }
}

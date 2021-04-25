package com.codingjj.jsondbconverter.converters

import com.fasterxml.jackson.databind.ObjectMapper
import org.postgresql.util.PGobject
import org.springframework.context.ApplicationContext
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.data.convert.WritingConverter
import java.util.*

@WritingConverter
class PgJsonWritingConverter<S>(
    private val sourceClazz: Class<S>,
    private val applicationContext: ApplicationContext
) : GenericConverter {

    // Don't initialize ObjectMapper on @Configuration Class, so use ApplicationContext to retrieve ObjectMapper
    private val objectMapper: ObjectMapper by lazy { applicationContext.getBean(ObjectMapper::class.java) }

    override fun getConvertibleTypes(): MutableSet<GenericConverter.ConvertiblePair>? =
        Collections.singleton(GenericConverter.ConvertiblePair(sourceClazz, PGobject::class.java))

    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor): Any {
        val sourceObject = sourceClazz.cast(source)
        val pgJsonObject = PGobject()
        pgJsonObject.type = "json"
        pgJsonObject.value = objectMapper.writeValueAsString(sourceObject)
        return pgJsonObject
    }
}

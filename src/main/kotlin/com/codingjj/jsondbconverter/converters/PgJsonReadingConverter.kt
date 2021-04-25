package com.codingjj.jsondbconverter.converters

import com.fasterxml.jackson.databind.ObjectMapper
import org.postgresql.util.PGobject
import org.springframework.context.ApplicationContext
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.data.convert.ReadingConverter
import java.util.*

@ReadingConverter
class PgJsonReadingConverter<T>(
    private val targetClazz: Class<T>,
    private val applicationContext: ApplicationContext
) : GenericConverter {

    // Don't initialize ObjectMapper on @Configuration Class, so use ApplicationContext to retrieve ObjectMapper
    private val objectMapper: ObjectMapper by lazy { applicationContext.getBean(ObjectMapper::class.java) }

    override fun getConvertibleTypes(): MutableSet<GenericConverter.ConvertiblePair>? =
        Collections.singleton(GenericConverter.ConvertiblePair(PGobject::class.java, targetClazz))

    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor): Any? {
        val pgObject = PGobject::class.java.cast(source)
        val json = pgObject.value
        return objectMapper.readValue(json, targetClazz)
    }
}

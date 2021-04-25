package com.codingjj.jsondbconverter.database

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Some2Dto(
    @JsonProperty("field1") val field1: String?,
    @JsonProperty("field2") val field2: String?,
    @JsonProperty("field3") val field3: String?,
    @JsonProperty("field4") val field4: String?,
)

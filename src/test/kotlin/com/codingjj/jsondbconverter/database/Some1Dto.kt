package com.codingjj.jsondbconverter.database

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Some1Dto(
    @JsonProperty("someField") val someField: String,
    @JsonProperty("nullField") val nullField: String?,
)

package com.codingjj.jsondbconverter.database

import org.springframework.data.repository.CrudRepository
import java.util.*

interface JsonbTblRepository : CrudRepository<JsonbTblEntity, UUID>
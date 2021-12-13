package com.zalesskyi.domain.models

import org.joda.time.DateTime

data class HeaderModel(override val id: Long,
                       override val creationTime: DateTime): Displayable
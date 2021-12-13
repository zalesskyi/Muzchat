package com.zalesskyi.domain.models

import org.joda.time.DateTime

interface Displayable {

    val id: Long
    val creationTime: DateTime
}
package com.zalesskyi.domain.extensions

import org.joda.time.DateTime
import org.joda.time.Minutes
import org.joda.time.Seconds

fun DateTime.diffInMinutes(another: DateTime) =
        Minutes.minutesBetween(this, another).minutes

fun DateTime.diffInSeconds(another: DateTime) =
        Seconds.secondsBetween(this, another).seconds
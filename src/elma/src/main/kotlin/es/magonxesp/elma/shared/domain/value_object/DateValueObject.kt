package es.magonxesp.elma.shared.domain.value_object

import es.magonxesp.elma.shared.domain.DateUtils

open class DateValueObject(
    open val value: String?
) {
    fun toLocalDateTime() = DateUtils.parse(value!!)
}

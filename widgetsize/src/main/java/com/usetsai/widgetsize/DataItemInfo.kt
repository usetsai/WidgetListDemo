package com.usetsai.widgetsize

data class DataItemInfo(val id: Long,
                        val text: String,
                        var check: Boolean,
                        val clazz: Class<*>? = null)
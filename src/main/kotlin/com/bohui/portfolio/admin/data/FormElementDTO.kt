package com.bohui.portfolio.admin.data

abstract class FormElementDTO(
    val name: String,
    val size: Int,
    val type: String
)

class TextFormElementDTO(
    name: String,
    size: Int
) : FormElementDTO(name = name, size = size, type = "text")

class DateFormElementDTO(
    name: String,
    size: Int
) : FormElementDTO(name = name, size = size, type = "date")

class SelectFormElementDTO(
    name: String,
    size: Int,
    options: List<Any> // 선택에 의한 true, false를 담는다.
) : FormElementDTO(name = name, size = size, type = "select"){
    val options: List<Any> = options
}

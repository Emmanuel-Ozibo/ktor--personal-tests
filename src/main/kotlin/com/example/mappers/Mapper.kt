package com.example.mappers

interface Mapper<INPUT, OUTPUT> {
    fun mapFromInput(input: INPUT): OUTPUT
    fun mapToInput(output: OUTPUT): INPUT
}

package com.giwankim.refactoring.ch08.move.statements.into.function

import java.io.OutputStream
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun renderPerson(
    out: OutputStream,
    person: Person,
): String {
    val result = StringBuilder()
    result.appendLine("<p>${person.name}</p>")
    result.appendLine(renderPhoto(person.photo))
    result.appendLine("<p>title: ${person.photo.title}</p>")
    result.appendLine(emitPhotoData(person.photo))
    return result.toString()
}

fun renderPhoto(photo: Photo): String = "🐶"

fun photoDiv(photo: Photo): String =
    listOf(
        "<div>",
        zznew(photo),
        "</div>",
    ).joinToString("\n")

fun zznew(photo: Photo) =
    listOf(
        "<p>title: ${photo.title}</p>",
        emitPhotoData(photo),
    ).joinToString("\n")

fun emitPhotoData(photo: Photo): String {
    val result = StringBuilder()
    result.appendLine("<p>location: ${photo.location}</p>")
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.KOREA)
    result.append("<p>date: ${photo.date.format(formatter)}</p>")
    return result.toString()
}

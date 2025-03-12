package com.giwankim.refactoring.ch08.move.statements.to.callers

import java.io.PrintStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun renderPerson(
    outStream: PrintStream,
    person: Person,
) {
    outStream.println("<p>${person.name}</p>")
    renderPhoto(outStream, person.photo)
    zztmp(outStream, person.photo)
    outStream.println("<p>location: ${person.photo.location}</p>")
}

fun listRecentPhotos(
    outStream: PrintStream,
    photos: List<Photo>,
) {
    photos
        .filter { it.date.isAfter(recentDateCutoff()) }
        .forEach {
            outStream.println("<div>")
            zztmp(outStream, it)
            outStream.println("<p>location: ${it.location}</p>")
            outStream.println("</div>")
        }
}

private fun zztmp(
    outStream: PrintStream,
    photo: Photo,
) {
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.KOREA)
    outStream.println("<p>title: ${photo.title}</p>")
    outStream.println("<p>date: ${photo.date.format(formatter)}</p>")
}

fun renderPhoto(
    outStream: PrintStream,
    photo: Photo,
) {
    outStream.println(photo.data)
}

fun recentDateCutoff(): LocalDateTime = LocalDateTime.now().minusDays(3)

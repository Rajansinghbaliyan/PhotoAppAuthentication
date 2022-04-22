package io.cherrytechnologies.photoappauthentication.web.controllers

import io.cherrytechnologies.photoappauthentication.customexceptions.BadRequestException
import io.cherrytechnologies.photoappauthentication.customexceptions.InternalErrorException
import io.cherrytechnologies.photoappauthentication.customexceptions.NotFoundException
import io.cherrytechnologies.photoappauthentication.types.Message
import io.cherrytechnologies.photoappauthentication.utils.responseBadRequest
import io.cherrytechnologies.photoappauthentication.utils.responseInternalError
import io.cherrytechnologies.photoappauthentication.utils.responseNotFound
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler

@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundExceptionHandler(e: NotFoundException) = Message(e.toString()).responseNotFound()

    @ExceptionHandler(value = [BadRequestException::class, MethodArgumentNotValidException::class])
    fun badRequestExceptionHandler(e: Exception) = Message(e.message.toString()).responseBadRequest()

    @ExceptionHandler(value = [InternalErrorException::class])
    fun internalErrorExceptionHandler(e: InternalErrorException) = Message(e.toString()).responseInternalError()

//    @ExceptionHandler(value = [Exception::class])
//    fun globalExceptionHandler(e: Exception) = Message(e.message.toString()).responseInternalError(e.stackTraceToString())
}



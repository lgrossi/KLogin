package com.opentibiabr.klogin.tools

import java.io.InputStream
import java.io.Reader
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class KtsLoader() {
    companion object {
        fun engine(): ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")

        inline fun <reified T> Any?.castOrError(): T = takeIf { it is T }?.let { it as T }
            ?: throw IllegalArgumentException("Cannot cast $this to expected type ${T::class}")

        inline fun <reified T> load(script: String, engine: ScriptEngine = engine()): T =
            kotlin.runCatching { engine.eval(script) }
                .getOrElse { throw Exception("Cannot load script", it) }
                .castOrError()

        inline fun <reified T> load(reader: Reader, engine: ScriptEngine = engine()): T =
            kotlin.runCatching { engine.eval(reader) }
                .getOrElse { throw Exception("Cannot load script", it) }
                .castOrError()

        inline fun <reified T> load(inputStream: InputStream, engine: ScriptEngine = engine()): T = load(inputStream.reader(), engine)
        inline fun <reified T> loadAll(vararg inputStream: InputStream, engine: ScriptEngine = engine()): List<T> = inputStream.map { load(it, engine) }
    }
}

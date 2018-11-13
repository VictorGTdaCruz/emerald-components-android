package br.com.stone.emeraldcomponentsandroid

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class Task1 : DefaultTask() {

    init {
        group = "com.kotlinexpertise"
        description = "task1"
    }


    @TaskAction
    fun run() {
        println("Creating component")
        val greeterClass = ClassName("", "Greeter")
        val file = FileSpec.builder("", "HelloWorld")
                .addType(TypeSpec.classBuilder("Greeter")
                        .primaryConstructor(FunSpec.constructorBuilder()
                                .addParameter("name", String::class)
                                .build())
                        .addProperty(PropertySpec.builder("name", String::class)
                                .initializer("name")
                                .build())
                        .addFunction(FunSpec.builder("greet")
                                .addStatement("println(%S)", "Hello, \$name")
                                .build())
                        .build())
                .addFunction(FunSpec.builder("main")
                        .addParameter("args", String::class, VARARG)
                        .addStatement("%T(args[0]).greet()", greeterClass)
                        .build())
                .build()

        file.writeTo(System.out)
    }
}

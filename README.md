<h1 align="center">kotlin-unwrap</h1>

[![Download][bintray-badge]][bintray-version]
[![Build Status][travis-badge]][travis-url]

Simpler version of https://github.com/importre/kotlin-unwrap


## build.gradle

```gradle
repositories {
    jcenter()
}

dependencies {
    compile "com.myhau:kotlin-unwrap:<VERSION>"
}
```


## Example

```kotlin
// returns nullable
fun foo(name: String?): String? = name

val _a = foo("Hello")
val _b = foo("World")
val _c = foo("!")

// example: unwrap all variables
val res = unwrap(_a, _b, _c) { a, b, c ->
    println("$a, $b$c") // prints: "Hello, World!"
}
```

### Error handling using `?:`

```kotlin
val _a = foo("Hello")
val _b = foo("World")
val _c = foo(null)

// example: error handing
val res = unwrap(_a, _b, _c) { a, b, c ->
    _a * _b - _c
} ?: -12

println("$res") // prints: -12 
```


### Simpler version with `notnull`

```kotlin
val _a = foo("Hello")
val _b = foo("World")
val _c = foo(null)

// example: error handing
val res = notnull(_a, _b, _c) {
    "value"
} ?: "altvalue"

println("$res") // prints: "altvalue"
```

## Test

```sh
$ ./gradlew check
```


## License

Apache 2.0 © Jaewe Heo
Apache 2.0 © Michał Fudała



[bintray-badge]: https://api.bintray.com/packages/myhau/kotlin-unwrap/kotlin-unwrap/images/download.svg
[bintray-version]: https://bintray.com/myhau/kotlin-unwrap/kotlin-unwrap/_latestVersion
[travis-badge]: https://travis-ci.org/myhau/kotlin-unwrap.svg?branch=master
[travis-url]: https://travis-ci.org/myhau/kotlin-unwrap

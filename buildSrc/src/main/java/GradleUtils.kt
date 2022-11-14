import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

fun Project.gradleFileProperties(rootDir: File, propertiesFileName: String): Properties {
    val properties = Properties()
    val localProperties = File(rootDir, propertiesFileName)

    if (localProperties.isFile) {
        InputStreamReader(
            FileInputStream(localProperties), Charsets.UTF_8
        ).use { reader ->
            properties.load(reader)
        }
    }

    return properties
}
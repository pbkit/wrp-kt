import org.gradle.api.InvalidUserDataException
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

fun RepositoryHandler.pbkit(project: Project, repository: String) =
    maven("https://maven.pkg.github.com/pbkit/$repository") {
        fun get(key: String): String? = project.findProperty(key) as? String
        val username = get("pbkit.publisher.username")
            ?: System.getenv("PBKIT_PUBLISHER_USERNAME")
            ?: throw InvalidUserDataException("Could not find pbkit.publisher.username or PBKIT_PUBLISHER_USERNAME")
        val token = get("pbkit.publisher.token")
            ?: System.getenv("PBKIT_PUBLISHER_TOKEN")
            ?: throw InvalidUserDataException("Could not find pbkit.publisher.token or PBKIT_PUBLISHER_TOKEN")

        credentials {
            this.username = username
            this.password = token
        }
    }

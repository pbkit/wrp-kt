import org.gradle.api.Project

inline val Project.releaseAAR: Any get() = "$buildDir/outputs/aar/$name-release.aar"
inline val Project.releaseJAR: Any get() = "$buildDir/libs/$name-$version.jar"
